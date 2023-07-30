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
        Configs.register();
        NetworkHandler.register();
    }

}
