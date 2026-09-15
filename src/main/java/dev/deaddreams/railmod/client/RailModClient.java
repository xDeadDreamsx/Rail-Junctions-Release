package dev.deaddreams.railmod.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import dev.deaddreams.railmod.RailMod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBakedItemModel;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperUnbakedItemModel;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import org.joml.Matrix4fc;

public final class RailModClient implements ClientModInitializer {
    private static final Identifier DEAD_END_ITEM = Identifier.fromNamespaceAndPath(RailMod.MOD_ID, "dead_end");
    private static final Identifier RAYS_BUFFER_STOP_TEXTURE = Identifier.fromNamespaceAndPath(
            "usefulrailroads",
            "textures/block/rays_buffer_stop.png"
    );
    private static final Identifier BUFFER_STOP_MODEL = Identifier.fromNamespaceAndPath(
            "usefulrailroads",
            "block/buffer_stop"
    );

    @Override
    public void onInitializeClient() {
        PreparableModelLoadingPlugin.register(
                (sharedState, executor) -> CompletableFuture.supplyAsync(
                        () -> sharedState.resourceManager().getResource(RAYS_BUFFER_STOP_TEXTURE).isPresent(),
                        executor
                ),
                (raysPackActive, context) -> {
                    if (!raysPackActive) {
                        return;
                    }

                    context.modifyItemModelBeforeBake().register((model, modifierContext) -> {
                        if (!modifierContext.itemId().equals(DEAD_END_ITEM)) {
                            return model;
                        }

                        return new DeadEndUnbakedItemModel(model);
                    });
                }
        );
    }

    private static final class DeadEndUnbakedItemModel extends WrapperUnbakedItemModel {
        private final ItemModel.Unbaked raysGuiModel = new CuboidItemModelWrapper.Unbaked(
                BUFFER_STOP_MODEL,
                Optional.empty(),
                List.of()
        );

        private DeadEndUnbakedItemModel(ItemModel.Unbaked wrapped) {
            super(wrapped);
        }

        @Override
        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            super.resolveDependencies(resolver);
            raysGuiModel.resolveDependencies(resolver);
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation) {
            ItemModel defaultModel = super.bake(context, transformation);
            ItemModel raysGui = raysGuiModel.bake(context, transformation);
            return new DeadEndBakedItemModel(defaultModel, raysGui);
        }
    }

    private static final class DeadEndBakedItemModel extends WrapperBakedItemModel {
        private final ItemModel raysGuiModel;

        private DeadEndBakedItemModel(ItemModel wrapped, ItemModel raysGuiModel) {
            super(wrapped);
            this.raysGuiModel = raysGuiModel;
        }

        @Override
        public void update(
                ItemStackRenderState state,
                ItemStack stack,
                ItemModelResolver resolver,
                ItemDisplayContext displayContext,
                ClientLevel level,
                ItemOwner itemOwner,
                int seed
        ) {
            ItemModel model = displayContext == ItemDisplayContext.GUI ? raysGuiModel : wrapped;
            model.update(state, stack, resolver, displayContext, level, itemOwner, seed);
        }
    }
}
