package org.ndimens.fx.parser;

public enum Machine {
    _UNKNOWN(0),
    _I386(0x014c),
    _AMD64(0x8664);

    private final short value;

    Machine(int value) {
        this.value = (short) value;
    }

    static Machine getValue(short value) {
        switch (value) {
            case (short) 0x014c:
                return _I386;
            case (short) 0x8664:
                return _AMD64;
            default:
                return _UNKNOWN;
        }
    }
}
