package org.mangorage.tiabcurio.neoforge;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.mangorage.tiabcurio.common.CommonClass;
import org.mangorage.tiabcurio.common.Constants;

@Mod(Constants.MOD_ID)
public class TiabCurioNeoForge {

    public TiabCurioNeoForge(IEventBus eventBus) {
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
    }
}
