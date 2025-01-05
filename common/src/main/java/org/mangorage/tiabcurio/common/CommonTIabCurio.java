package org.mangorage.tiabcurio.common;

import org.mangorage.tiab.common.api.ITiabItemSearch;

public abstract class CommonTIabCurio {
    private static ITiabItemSearch curioItemStackSearch;

    public static ITiabItemSearch getCurioItemStackSearch() {
        return curioItemStackSearch;
    }

    public CommonTIabCurio(ITiabItemSearch search) {
        CommonTIabCurio.curioItemStackSearch = search;
    }
}
