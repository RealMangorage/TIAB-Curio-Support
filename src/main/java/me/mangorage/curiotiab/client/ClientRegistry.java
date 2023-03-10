package me.mangorage.curiotiab.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientRegistry {
    public static KeyMapping useTiab =
            new KeyMapping("key.curiotiab.use", org.lwjgl.glfw.GLFW.GLFW_KEY_G, "key.categories.misc");


}
