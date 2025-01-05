package org.mangorage.tiabcurio.fabric;

import net.fabricmc.api.ModInitializer;
import org.mangorage.tiabcurio.fabric.trinket.BottleTrinket;

public class FabricTiabCurio implements ModInitializer {

    @Override
    public void onInitialize() {
        new BottleTrinket();
    }
}
