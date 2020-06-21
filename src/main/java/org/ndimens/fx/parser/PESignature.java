package org.ndimens.fx.parser;

public class PESignature {
    private final String title = "PE Signature";
    private byte[] buffer;

    public PESignature(byte[] buffer) {
        this.buffer = buffer;
    }

    public boolean isCorrect() {
        var p = (char)this.buffer[0];
        var e = (char)this.buffer[1];
        return p == 'P' && e == 'E' && this.buffer[2] == 0 && this.buffer[3] == 0;
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
