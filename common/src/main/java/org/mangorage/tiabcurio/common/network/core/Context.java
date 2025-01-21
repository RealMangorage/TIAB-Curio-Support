package org.mangorage.tiabcurio.common.network.core;

import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public record Context(ServerPlayer player, Consumer<Boolean> handled) { }
