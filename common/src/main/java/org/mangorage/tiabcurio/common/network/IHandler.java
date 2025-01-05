package org.mangorage.tiabcurio.common.network;

import java.util.function.Supplier;

public interface IHandler {
    void handle(Supplier<Context> ctx);
}
