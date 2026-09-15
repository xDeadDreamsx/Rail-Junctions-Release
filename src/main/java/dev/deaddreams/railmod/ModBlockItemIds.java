package dev.deaddreams.railmod;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public final class ModBlockItemIds {
    public static final BlockItemId RAIL_CROSS = create("rail_cross");
    public static final BlockItemId DEAD_END = create("dead_end");
    public static final BlockItemId RAIL_SWITCH = create("rail_switch");
    public static final BlockItemId TEE_JUNCTION_RAIL = create("tee_junction_rail");

    private ModBlockItemIds() {
    }

    private static BlockItemId create(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(RailMod.MOD_ID, name);
        return BlockItemId.create(id, id);
    }
}
