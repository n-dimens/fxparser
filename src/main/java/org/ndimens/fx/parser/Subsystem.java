package org.ndimens.fx.parser;

public enum Subsystem {
    UNKNOWN((short)0),
    NATIVE((short)1),
    WINDOWS_GUI((short)2),
    WINDOWS_CUI((short)3),
    OS2_CUI((short)5),
    POSIX_CUI((short)7),
    NATIVE_WINDOWS((short)8),
    WINDOWS_CE_GUI((short)9);

    private final short value;

    Subsystem(short value) {
        this.value = value;
    }

    static Subsystem getValue(short value) {
        switch (value) {
            case (short) 1:
                return NATIVE;
            case (short) 2:
                return WINDOWS_GUI;
            case (short) 3:
                return WINDOWS_CUI;
            case (short) 5:
                return OS2_CUI;
            case (short) 7:
                return POSIX_CUI;
            case (short) 8:
                return NATIVE_WINDOWS;
            case (short) 9:
                return WINDOWS_CE_GUI;
            default:
                return UNKNOWN;
        }
    }
}
