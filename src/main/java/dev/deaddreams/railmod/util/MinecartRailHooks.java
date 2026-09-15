package dev.deaddreams.railmod.util;

import dev.deaddreams.railmod.ModBlocks;
import dev.deaddreams.railmod.block.DeadEndBlock;
import dev.deaddreams.railmod.block.RailCrossBlock;
import dev.deaddreams.railmod.block.RailSwitchBlock;
import dev.deaddreams.railmod.block.TeeJunctionRailBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.Vec3;

public final class MinecartRailHooks {
    private static final double MIN_MOTION = 1.0E-4;
    // The bumper sits on the FACING edge of the dead-end block. A minecart is
    // roughly one block long, so centering it one block out from the dead-end
    // center leaves its nose right at the bumper instead of inside it.
    private static final double DEAD_END_STOP_OFFSET = 1.0;

    private MinecartRailHooks() {
    }

    public static void prepareRail(ServerLevel level, AbstractMinecart cart) {
        Vec3 motion = cart.getDeltaMovement();
        if (Math.abs(motion.x()) < MIN_MOTION && Math.abs(motion.z()) < MIN_MOTION) {
            return;
        }

        RailShape desired = Math.abs(motion.x()) > Math.abs(motion.z())
                ? RailShape.EAST_WEST
                : RailShape.NORTH_SOUTH;

        BlockPos current = cart.blockPosition();
        Direction travel = dominantDirection(motion);

        if (travel != null && isMisalignedJunctionAhead(level, current, travel)) {
            cart.setDeltaMovement(Vec3.ZERO);
            return;
        }

        prepareCross(level, current, desired);
        prepareCross(level, current.below(), desired);

        if (travel != null) {
            prepareCross(level, current.relative(travel), desired);
            prepareCross(level, current.relative(travel).below(), desired);
        }
    }

    public static void stopAtDeadEnd(ServerLevel level, AbstractMinecart cart) {
        DeadEndHit hit = findDeadEnd(level, cart.blockPosition());
        if (hit == null) {
            return;
        }

        Direction bumperSide = hit.state().getValue(DeadEndBlock.FACING);
        double x = hit.pos().getX() + 0.5 + bumperSide.getStepX() * DEAD_END_STOP_OFFSET;
        double z = hit.pos().getZ() + 0.5 + bumperSide.getStepZ() * DEAD_END_STOP_OFFSET;

        cart.setDeltaMovement(Vec3.ZERO);
        cart.setPos(x, cart.getY(), z);
    }

    private static boolean isMisalignedJunctionAhead(ServerLevel level, BlockPos current, Direction travel) {
        BlockPos next = current.relative(travel);
        BlockState state = level.getBlockState(next);
        if (!isSwitchOrTee(state)) {
            state = level.getBlockState(next.below());
        }

        RailShape shape;
        if (state.is(ModBlocks.RAIL_SWITCH)) {
            shape = state.getValue(RailSwitchBlock.SHAPE);
        } else if (state.is(ModBlocks.TEE_JUNCTION_RAIL)) {
            shape = state.getValue(TeeJunctionRailBlock.SHAPE);
        } else {
            return false;
        }

        Direction entrySide = travel.getOpposite();
        return !shapeConnectsTo(shape, entrySide);
    }

    private static boolean shapeConnectsTo(RailShape shape, Direction side) {
        return switch (shape) {
            case NORTH_SOUTH, ASCENDING_NORTH, ASCENDING_SOUTH ->
                    side == Direction.NORTH || side == Direction.SOUTH;
            case EAST_WEST, ASCENDING_EAST, ASCENDING_WEST ->
                    side == Direction.EAST || side == Direction.WEST;
            case SOUTH_EAST -> side == Direction.SOUTH || side == Direction.EAST;
            case SOUTH_WEST -> side == Direction.SOUTH || side == Direction.WEST;
            case NORTH_WEST -> side == Direction.NORTH || side == Direction.WEST;
            case NORTH_EAST -> side == Direction.NORTH || side == Direction.EAST;
        };
    }

    private static boolean isSwitchOrTee(BlockState state) {
        return state.is(ModBlocks.RAIL_SWITCH) || state.is(ModBlocks.TEE_JUNCTION_RAIL);
    }

    private static DeadEndHit findDeadEnd(ServerLevel level, BlockPos current) {
        BlockState state = level.getBlockState(current);
        if (state.is(ModBlocks.DEAD_END)) {
            return new DeadEndHit(current, state);
        }

        BlockPos below = current.below();
        state = level.getBlockState(below);
        if (state.is(ModBlocks.DEAD_END)) {
            return new DeadEndHit(below, state);
        }

        return null;
    }

    private static void prepareCross(ServerLevel level, BlockPos pos, RailShape desired) {
        BlockState state = level.getBlockState(pos);
        if (state.is(ModBlocks.RAIL_CROSS) && state.getValue(RailCrossBlock.SHAPE) != desired) {
            level.setBlock(pos, state.setValue(RailCrossBlock.SHAPE, desired), Block.UPDATE_CLIENTS);
        }
    }

    private static Direction dominantDirection(Vec3 motion) {
        if (Math.abs(motion.x()) > Math.abs(motion.z())) {
            if (Math.abs(motion.x()) < MIN_MOTION) {
                return null;
            }
            return motion.x() > 0 ? Direction.EAST : Direction.WEST;
        }

        if (Math.abs(motion.z()) < MIN_MOTION) {
            return null;
        }
        return motion.z() > 0 ? Direction.SOUTH : Direction.NORTH;
    }

    private record DeadEndHit(BlockPos pos, BlockState state) {
    }
}
