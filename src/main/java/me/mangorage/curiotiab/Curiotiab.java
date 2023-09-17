package me.mangorage.curiotiab;


import com.magorage.tiab.api.ITimeInABottleAPI;
import com.magorage.tiab.api.TiabProvider;

import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.commands.CurioTiabCommand;
import me.mangorage.curiotiab.common.config.Configs;
import me.mangorage.curiotiab.common.core.Util;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod(MODID)
public class Curiotiab {
    public static final TiabProvider API_PROVIDER = new TiabProvider((api) -> {
        CurioTiabCommand.setAPI(api);
        Util.setAPI(api);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CurioTiabHudOverlay.getInstance().setAPI(api));
    });

    public Curiotiab() {
        Configs.register();
        NetworkHandler.register();
        InterModComms.sendTo(ITimeInABottleAPI.IMC.MOD_ID, ITimeInABottleAPI.IMC.GET_API, () -> API_PROVIDER);
    }
}
