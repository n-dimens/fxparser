package org.ndimens.fx.parser;

public class DOSHeader {
    private final String title = "MS DOS Header";
    private byte[] buffer;

    public DOSHeader(byte[] buffer) {
        this.buffer = buffer;
    }

    public void print() {
        System.out.println(this.title);
        System.out.println("----------------");
        final int lineLength = 8;
        var counter = 0;
        for (var b : this.buffer) {
//            System.out.printf("%4d ", Byte.toUnsignedInt(b));
            System.out.printf("%8s ", Integer.toHexString(Byte.toUnsignedInt(b)));
            counter += 1;
            if (counter == lineLength) {
                System.out.print('\n');
                counter = 0;
            }
        }

        System.out.println('\n');
    }
}
