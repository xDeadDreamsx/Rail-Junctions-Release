package dev.deaddreams.railmod.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.RailShape;

final class RailShapeHelper {
    private RailShapeHelper() {
    }

    static RailShape between(Direction first, Direction second) {
        if (!first.getAxis().isHorizontal() || !second.getAxis().isHorizontal() || first == second) {
            throw new IllegalArgumentException("Rail endpoints must be two different horizontal directions");
        }

        if (first == second.getOpposite()) {
            return first.getAxis() == Direction.Axis.X ? RailShape.EAST_WEST : RailShape.NORTH_SOUTH;
        }

        boolean north = first == Direction.NORTH || second == Direction.NORTH;
        boolean south = first == Direction.SOUTH || second == Direction.SOUTH;
        boolean east = first == Direction.EAST || second == Direction.EAST;
        boolean west = first == Direction.WEST || second == Direction.WEST;

        if (north && east) {
            return RailShape.NORTH_EAST;
        }
        if (north && west) {
            return RailShape.NORTH_WEST;
        }
        if (south && east) {
            return RailShape.SOUTH_EAST;
        }
        if (south && west) {
            return RailShape.SOUTH_WEST;
        }

        throw new IllegalArgumentException("Unsupported rail endpoints: " + first + " and " + second);
    }
}
