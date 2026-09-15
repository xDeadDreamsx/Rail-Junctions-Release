/*
 * SPDX-License-Identifier: LGPL-3.0-only
 *
 * Adapted/reimplemented for Rail Junctions (formerly Rail Mod) from the tee-junction rail behavior in Little Logistics.
 * Modified for Fabric / Minecraft 26.2 on 2026-09-07.
 * See THIRD_PARTY.md for attribution and upstream licensing details.
 */
package dev.deaddreams.railmod.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;

public final class TeeJunctionRailBlock extends BaseRailBlock {
    public static final MapCodec<TeeJunctionRailBlock> CODEC = simpleCodec(TeeJunctionRailBlock::new);

    public static final EnumProperty<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public TeeJunctionRailBlock(BlockBehaviour.Properties properties) {
        super(false, properties);
        registerDefaultState(defaultBlockState()
                .setValue(SHAPE, RailShape.SOUTH_EAST)
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends BaseRailBlock> codec() {
        return CODEC;
    }

    @Deprecated
    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        boolean powered = context.getLevel().hasNeighborSignal(context.getClickedPos());
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

        BlockState state = defaultBlockState()
                .setValue(FACING, facing)
                .setValue(POWERED, powered)
                .setValue(WATERLOGGED, waterlogged);

        return withResolvedShape(state);
    }

    @Override
    protected BlockState updateState(BlockState state, Level level, BlockPos pos, boolean movedByPiston) {
        return state;
    }

    @Override
    protected BlockState updateDir(Level level, BlockPos pos, BlockState state, boolean first) {
        return state;
    }

    @Override
    protected void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block block,
            Orientation orientation,
            boolean movedByPiston
    ) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        if (level.isClientSide()) {
            return;
        }

        boolean powered = level.hasNeighborSignal(pos);
        if (powered != state.getValue(POWERED)) {
            level.setBlock(pos, withResolvedShape(state.setValue(POWERED, powered)), Block.UPDATE_CLIENTS);
        }
    }

    private static BlockState withResolvedShape(BlockState state) {
        Direction facing = state.getValue(FACING);
        Direction root = facing.getOpposite();
        Direction exit = state.getValue(POWERED) ? facing.getCounterClockWise() : facing.getClockWise();
        return state.setValue(SHAPE, RailShapeHelper.between(root, exit));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE, FACING, POWERED, WATERLOGGED);
    }
}
