package org.mangorage.tiabcurio.common.network.core;

import java.util.function.Supplier;

public interface IHandler {
    void handle(Supplier<Context> ctx);
}
