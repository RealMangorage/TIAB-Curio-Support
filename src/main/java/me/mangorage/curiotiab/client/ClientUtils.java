package me.mangorage.curiotiab.client;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.mutable.MutableInt;
import java.util.List;

import static java.lang.Math.min;

public class ClientUtils {
    public static int getClampedWidth(int x, int screenwidth, int width) {
        return min(x, screenwidth - width);
    }

    public static int getClampedHeight(int y, int screenheight, int height) {
        return getClampedWidth(y, screenheight, height);
    }

    public static int getMaxStringsWidth(List<Component> strings, Font font) {
        MutableInt value = new MutableInt(0);
        strings.forEach(s -> {
            int c = font.width(s);
            if (c > value.getValue())
                value.setValue(c);
        });
        return value.getValue();
    }
}
