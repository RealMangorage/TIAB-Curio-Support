package me.mangorage.curiotiab;

import me.mangorage.curiotiab.common.config.Configs;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

import static me.mangorage.curiotiab.common.core.Constants.MODID;

@Mod(MODID)
public class Curiotiab {
    public Curiotiab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueCompatMessages);

        Configs.register();
        NetworkHandler.register();
    }

    /**
     *  TODO: Figure out how to use Datapack driven registration
     *  TODO: system from CuriosAPI, tried to do it but no luck!
     *
     *  This works so keep it.
      */
    @Deprecated
    public void enqueueCompatMessages(final InterModEnqueueEvent evt) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("tiab")
                        .size(1)
                        .icon(new ResourceLocation("curiotiab:slot/empty_tiab"))
                        .build()
        );
    }

}
