package org.ndimens.fx.parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ReadValue {
    public static short getShort(byte[] buffer) {
        return getShort(buffer, 0);
    }

    public static short getShort(byte[] buffer, int offset) {
        ByteBuffer wrapped = ByteBuffer.wrap(buffer, offset, 2);
        wrapped.order(ByteOrder.LITTLE_ENDIAN);
        return wrapped.getShort();
    }

    public static int getInt(byte[] buffer) {
        return getInt(buffer, 0);
    }

    public static int getInt(byte[] buffer, int offset) {
        ByteBuffer wrapped = ByteBuffer.wrap(buffer, offset, 4);
        wrapped.order(ByteOrder.LITTLE_ENDIAN);
        return wrapped.getInt();
    }

    public static long getLong(byte[] buffer) {
        ByteBuffer wrapped = ByteBuffer.wrap(buffer, 0, 8);
        wrapped.order(ByteOrder.LITTLE_ENDIAN);
        return wrapped.getLong();
    }
}
