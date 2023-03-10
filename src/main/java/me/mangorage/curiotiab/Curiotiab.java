package me.mangorage.curiotiab;

import me.mangorage.curiotiab.client.ClientRegistry;
import me.mangorage.curiotiab.common.network.NetworkHandler;
import me.mangorage.curiotiab.common.network.client.CUseTiab;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

import static me.mangorage.curiotiab.common.Constants.MODID;
@Mod(MODID)
public class Curiotiab {
    public Curiotiab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueCompatMessages);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        NetworkHandler.register();
    }

    public void enqueueCompatMessages(final InterModEnqueueEvent evt) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("tiab")
                        .size(1)
                        .icon(new ResourceLocation("curios:slot/empty_tiab"))
                        .build()
        );
    }

    public void onKeyInput(TickEvent.ClientTickEvent evt) {
        if (evt.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (ClientRegistry.useTiab.consumeClick() && mc.level != null) {
            HitResult result = mc.player.pick(mc.player.getReachDistance(), mc.getPartialTick(), true);

            if (result instanceof BlockHitResult hit)
                NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CUseTiab(hit));
        }
    }
}
