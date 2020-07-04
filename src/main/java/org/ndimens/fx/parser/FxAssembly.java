package org.ndimens.fx.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FxAssembly {
    private DOSHeader dosHeader;
    private DOSStub dosStub;
    private PESignature peSignature;
    private COFFHeader coffHeader;
    private PEHeader peHeader;
    public Map<String, ImageSectionHeader> sectionHeaders;

    protected FxAssembly() {
        this.sectionHeaders = new HashMap<>();
    }

    public PESignature getPeSignature() {
        return this.peSignature;
    }

    public COFFHeader getCoffHeader() {
        return this.coffHeader;
    }

    public static FxAssembly parse(InputStream stream) throws IOException {
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

        result.peHeader = PEHeader.read(stream, result.coffHeader);

        // Section Headers
        for (int i = 0; i < result.coffHeader.getNumberOfSections(); i++) {
            var sectionHeader = new ImageSectionHeader();
            sectionHeader.Name = ReadValue.getString(stream.readNBytes(8));
            sectionHeader.Misc = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.VirtualAddress = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.SizeOfRawData = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.PointerToRawData = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.PointerToRelocations = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.PointerToLinenumbers = ReadValue.getInt(stream.readNBytes(4));
            sectionHeader.NumberOfRelocations = ReadValue.getShort(stream.readNBytes(2));
            sectionHeader.NumberOfLinenumbers = ReadValue.getShort(stream.readNBytes(2));
            sectionHeader.Characteristics = ReadValue.getInt(stream.readNBytes(4));
            result.sectionHeaders.put(sectionHeader.Name, sectionHeader);
        }

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

        this.peHeader.print();

        for(var section: this.sectionHeaders.values()) {
            System.out.println("Section: " + section.Name + " (" + section.SizeOfRawData / 1024 + " KB)");
            System.out.println("Pointer: " + section.PointerToRawData);
        }
    }
}
