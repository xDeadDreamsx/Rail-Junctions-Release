package dev.deaddreams.railmod;

import java.util.function.Function;

import dev.deaddreams.railmod.block.DeadEndBlock;
import dev.deaddreams.railmod.block.RailCrossBlock;
import dev.deaddreams.railmod.block.RailSwitchBlock;
import dev.deaddreams.railmod.block.TeeJunctionRailBlock;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    public static final Block RAIL_CROSS = register(
            ModBlockItemIds.RAIL_CROSS,
            RailCrossBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).sound(SoundType.METAL)
    );

    public static final Block DEAD_END = register(
            ModBlockItemIds.DEAD_END,
            DeadEndBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).strength(1.5F).sound(SoundType.METAL)
    );

    public static final Block RAIL_SWITCH = register(
            ModBlockItemIds.RAIL_SWITCH,
            RailSwitchBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).sound(SoundType.METAL)
    );

    public static final Block TEE_JUNCTION_RAIL = register(
            ModBlockItemIds.TEE_JUNCTION_RAIL,
            TeeJunctionRailBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).sound(SoundType.METAL)
    );

    private ModBlocks() {
    }

    private static Block register(
            BlockItemId id,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties
    ) {
        Block block = register(id.block(), blockFactory, properties);
        BlockItem blockItem = new BlockItem(
                block,
                new Item.Properties().useBlockDescriptionPrefix().setId(id.item())
        );
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);
        return block;
    }

    private static Block register(
            ResourceKey<Block> id,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties
    ) {
        Block block = blockFactory.apply(properties.setId(id));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(output -> {
            output.accept(RAIL_CROSS.asItem());
            output.accept(DEAD_END.asItem());
            output.accept(RAIL_SWITCH.asItem());
            output.accept(TEE_JUNCTION_RAIL.asItem());
        });
    }
}
