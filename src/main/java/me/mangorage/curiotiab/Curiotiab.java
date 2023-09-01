package me.mangorage.curiotiab;

import com.haoict.tiab.common.core.api.ApiRegistry;
import com.magorage.tiab.api.ITimeInABottleAPI;
import com.magorage.tiab.api.TiabProvider;
import me.mangorage.curiotiab.client.screens.overlays.CurioTiabHudOverlay;
import me.mangorage.curiotiab.common.commands.CurioTiabCommand;
import me.mangorage.curiotiab.common.config.Configs;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod(MODID)
public class Curiotiab {
    public static final TiabProvider API_PROVIDER = new TiabProvider((api) -> {
        CurioTiabCommand.setAPI(api);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CurioTiabHudOverlay.getInstance().setAPI(api));
    });

    public Curiotiab() {
        Configs.register();
        NetworkHandler.register();

        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("tiab")
                        .size(1)
                        .icon(new ResourceLocation("curiotiab:slot/empty_tiab"))
                        .build()
        );
        InterModComms.sendTo(ITimeInABottleAPI.IMC.MOD_ID, ITimeInABottleAPI.IMC.GET_API, () -> API_PROVIDER);
    }
}
