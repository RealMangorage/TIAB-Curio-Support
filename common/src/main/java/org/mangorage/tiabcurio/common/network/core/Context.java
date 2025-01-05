package org.mangorage.tiabcurio.common.network.core;

import net.minecraft.server.level.ServerPlayer;

public abstract class Context {
    abstract public ServerPlayer getSender();
    abstract public void setPacketHandled(boolean flag);
}
