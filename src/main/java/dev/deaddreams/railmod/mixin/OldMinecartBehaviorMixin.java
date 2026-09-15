package dev.deaddreams.railmod.mixin;

import dev.deaddreams.railmod.util.MinecartRailHooks;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.MinecartBehavior;
import net.minecraft.world.entity.vehicle.minecart.OldMinecartBehavior;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OldMinecartBehavior.class)
public abstract class OldMinecartBehaviorMixin extends MinecartBehavior {
    protected OldMinecartBehaviorMixin(AbstractMinecart minecart) {
        super(minecart);
    }

    @Inject(method = "moveAlongTrack", at = @At("HEAD"))
    private void railmod$prepareSpecialRails(ServerLevel level, CallbackInfo ci) {
        MinecartRailHooks.prepareRail(level, this.minecart);
    }

    @Inject(method = "moveAlongTrack", at = @At("TAIL"))
    private void railmod$stopAtDeadEnd(ServerLevel level, CallbackInfo ci) {
        MinecartRailHooks.stopAtDeadEnd(level, this.minecart);
    }
}
