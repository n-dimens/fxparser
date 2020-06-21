package org.ndimens.fx.parser;

import java.io.FileInputStream;
import java.io.IOException;

public class FxAssembly {
    private DOSHeader dosHeader;
    private DOSStub dosStub;
    private PESignature peSignature;
    private COFFHeader coffHeader;

    protected FxAssembly() {
    }

    public PESignature getPeSignature() {
        return this.peSignature;
    }

    public COFFHeader getCoffHeader() {
        return this.coffHeader;
    }

    public static FxAssembly parse(FileInputStream stream) throws IOException {
        final var result = new FxAssembly();
        var buffer = new byte[64];
        stream.read(buffer);
        result.dosHeader = new DOSHeader(buffer.clone());

        buffer = new byte[64];
        stream.read(buffer);
        result.dosStub = new DOSStub(buffer.clone());

        buffer = new byte[4];
        stream.read(buffer);
        result.peSignature = new PESignature(buffer.clone());

        buffer = new byte[20];
        stream.read(buffer);
        result.coffHeader = new COFFHeader(buffer.clone());
        return result;
    }

    public void print() {
        if (!this.peSignature.isCorrect()) {
            System.out.println("[ERROR] PE Signature is not correct");
            this.peSignature.print();
        }

        System.out.println();
        this.coffHeader.print();
        if (!this.coffHeader.isCorrect()) {
            System.out.println("[ERROR] COFF Header is not correct");
        }
    }
}
