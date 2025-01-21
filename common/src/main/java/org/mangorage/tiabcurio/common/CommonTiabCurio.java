package org.mangorage.tiabcurio.common;

import org.mangorage.tiab.common.api.ITiabItemSearch;

public abstract class CommonTiabCurio {
    private static ITiabItemSearch curioItemStackSearch;

    public static ITiabItemSearch getCurioItemStackSearch() {
        return curioItemStackSearch;
    }

    public CommonTiabCurio(ITiabItemSearch search) {
        CommonTiabCurio.curioItemStackSearch = search;
    }
}
