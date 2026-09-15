package dev.deaddreams.railmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RailMod implements ModInitializer {
    public static final String MOD_ID = "railmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        LOGGER.info("Rail Junctions initialized.");
    }
}
