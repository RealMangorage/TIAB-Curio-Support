package org.mangorage.tiabcurio.common.client;


import net.minecraft.client.KeyMapping;

import static com.mojang.blaze3d.platform.InputConstants.KEY_G;
import static com.mojang.blaze3d.platform.InputConstants.KEY_H;
import static org.mangorage.tiabcurio.common.core.Translatable.*;

public class ClientRegistry {
    public final static KeyMapping useTiab = new KeyMapping(USE_KEY.getKey(), KEY_G, CATEGORY_KEY.getKey());
    public final static KeyMapping hideGUI = new KeyMapping(TOGGLE_KEY.getKey(), KEY_H, CATEGORY_KEY.getKey());
}
