package org.ndimens.fx.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PEHeader {
    private final String title = "PE Header";
    private short magic;
    private byte majorLinkerVersion;
    private byte minorLinkerVersion;
    private int sizeOfCode;
    private int sizeOfInitializedData;
    private int sizeOfUninitializedData;
    private int addressOfEntryPoint;
    private int baseOfCode;
    private int baseOfData;
    private long imageBase;
    private int sectionAlignment;
    private int fileAlignment;
    private short majorOperatingSystemVersion;
    private short minorOperatingSystemVersion;
    private short majorImageVersion;
    private short minorImageVersion;
    private short majorSubsystemVersion;
    private short minorSubsystemVersion;
    private int win32VersionValue;
    private int sizeOfImage;
    private int sizeOfHeaders;
    private int checkSum;
    private Subsystem subsystem;
    private short dllCharacteristics;
    private long sizeOfStackReserve;
    private long sizeOfStackCommit;
    private long sizeOfHeapReserve;
    private long sizeOfHeapCommit;
    private int loaderFlags;
    private int numberOfRvaAndSizes;
    private List<ImageDataDirectory> dataDirectoryTable;

    public PEHeader() {
        this.dataDirectoryTable = new ArrayList<>();
    }

    public static PEHeader read(InputStream stream, COFFHeader coffHeader) throws IOException {
        var is64bit = coffHeader.getMachineType() == Machine._AMD64;
        var result = new PEHeader();
        result.magic = ReadValue.getShort(stream.readNBytes(2));
        result.majorLinkerVersion = stream.readNBytes(1)[0];
        result.minorLinkerVersion = stream.readNBytes(1)[0];
        result.sizeOfCode = ReadValue.getInt(stream.readNBytes(4));
        result.sizeOfInitializedData = ReadValue.getInt(stream.readNBytes(4));
        result.sizeOfUninitializedData = ReadValue.getInt(stream.readNBytes(4));
        result.addressOfEntryPoint = ReadValue.getInt(stream.readNBytes(4));
        result.baseOfCode = ReadValue.getInt(stream.readNBytes(4));
        if (is64bit) {
            result.baseOfData = -1;
            result.imageBase = ReadValue.getLong(stream.readNBytes(8));
        }
        else {
            result.baseOfData = ReadValue.getInt(stream.readNBytes(4));
            result.imageBase = ReadValue.getInt(stream.readNBytes(4));
        }

        result.sectionAlignment = ReadValue.getInt(stream.readNBytes(4));
        result.fileAlignment = ReadValue.getInt(stream.readNBytes(4));
        result.majorOperatingSystemVersion = ReadValue.getShort(stream.readNBytes(2));
        result.minorOperatingSystemVersion = ReadValue.getShort(stream.readNBytes(2));
        result.majorImageVersion = ReadValue.getShort(stream.readNBytes(2));
        result.minorImageVersion = ReadValue.getShort(stream.readNBytes(2));
        result.majorSubsystemVersion = ReadValue.getShort(stream.readNBytes(2));
        result.minorSubsystemVersion = ReadValue.getShort(stream.readNBytes(2));
        result.win32VersionValue = ReadValue.getInt(stream.readNBytes(4));
        result.sizeOfImage = ReadValue.getInt(stream.readNBytes(4));
        result.sizeOfHeaders = ReadValue.getInt(stream.readNBytes(4));
        result.checkSum = ReadValue.getInt(stream.readNBytes(4));
        result.subsystem = Subsystem.getValue(ReadValue.getShort(stream.readNBytes(2)));
        result.dllCharacteristics = ReadValue.getShort(stream.readNBytes(2));
        if (is64bit) {
            result.sizeOfStackReserve = ReadValue.getLong(stream.readNBytes(8));
            result.sizeOfStackCommit = ReadValue.getLong(stream.readNBytes(8));
            result.sizeOfHeapReserve = ReadValue.getLong(stream.readNBytes(8));
            result.sizeOfHeapCommit = ReadValue.getLong(stream.readNBytes(8));
        }
        else {
            result.sizeOfStackReserve = ReadValue.getInt(stream.readNBytes(4));
            result.sizeOfStackCommit = ReadValue.getInt(stream.readNBytes(4));
            result.sizeOfHeapReserve = ReadValue.getInt(stream.readNBytes(4));
            result.sizeOfHeapCommit = ReadValue.getInt(stream.readNBytes(4));
        }

        result.loaderFlags = ReadValue.getInt(stream.readNBytes(4));
        result.numberOfRvaAndSizes = ReadValue.getInt(stream.readNBytes(4));

        // Data Directories
        for (int i = 0; i < result.numberOfRvaAndSizes; i++) {
            var dataDirectory = new ImageDataDirectory();
            dataDirectory.setVirtualAddress(ReadValue.getInt(stream.readNBytes(4)));
            dataDirectory.setSize(ReadValue.getInt(stream.readNBytes(4)));
            result.dataDirectoryTable.add(dataDirectory);
        }

        return result;
    }

    public void print() {
        System.out.println(this.title);
        System.out.println("----------------");
        System.out.printf("Magic: 0x%4s\n", Integer.toHexString(this.magic));
        System.out.printf("Linker version: %d:%d\n", this.majorLinkerVersion, this.minorLinkerVersion);
        System.out.printf("OS version: %d:%d\n", this.majorOperatingSystemVersion, this.minorOperatingSystemVersion);
        System.out.printf("Image version: %d:%d\n", this.majorImageVersion, this.minorImageVersion);
        System.out.printf("Subsystem: %s\n", this.subsystem);
        System.out.printf("DLL characteristics: 0x%4s\n", Integer.toHexString(this.dllCharacteristics & 0xffff));
        System.out.printf("Size of stack reserve: %d\n", this.sizeOfStackReserve);
        System.out.printf("Size of stack commit: %d\n", this.sizeOfStackCommit);
        System.out.printf("Size of heap reserve: %d\n", this.sizeOfHeapReserve);
        System.out.printf("Size of heap commit: %d\n", this.sizeOfHeapCommit);
        System.out.printf("Number of RVA and sizes: %d\n", this.numberOfRvaAndSizes);
    }
}
