package org.ndimens.fx.parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class COFFHeader {
    private final String title = "COFF Header";
    private byte[] buffer;
    private Machine machineType;
    private short numberOfSections;
    private int timeDateStamp;
    private int pointerToSymbolTable;
    private int numberOfSymbols;
    private short sizeOfOptionalHeader;
    private short characteristics;

    public COFFHeader() {
        this.buffer = new byte[20];
        this.pointerToSymbolTable = 0;
        this.numberOfSymbols = 0;
    }

    public COFFHeader(byte[] buffer) {
        this.buffer = buffer;
        this.machineType = Machine.getValue(ReadValue.getShort(buffer, 0));
        this.numberOfSections = ReadValue.getShort(buffer, 2);
        this.timeDateStamp = ReadValue.getInt(buffer, 4); // TODO: Преобразовать в дату-время
        this.pointerToSymbolTable = ReadValue.getInt(buffer, 8);
        this.numberOfSymbols = ReadValue.getInt(buffer, 12);
        this.sizeOfOptionalHeader = ReadValue.getShort(buffer, 16);
        this.characteristics = ReadValue.getShort(buffer, 18);
    }

    public Machine getMachineType() {
        return this.machineType;
    }

    public void print() {
        System.out.println(this.title);
        System.out.println("----------------");
        System.out.printf("Machine Type: %4s\n", getMachineType());
        System.out.printf("Number of Sections: %4d\n", this.numberOfSections);
        System.out.printf("Time/Date Stamp: %4d\n", this.timeDateStamp);
        System.out.printf("Pointer to Symbol Table: %4d\n", this.pointerToSymbolTable);
        System.out.printf("Number of Symbols: %4d\n", this.numberOfSymbols);
        System.out.printf("Size of Optional Header: %4d\n", this.sizeOfOptionalHeader);
        System.out.printf("Characteristics: 0x%4s\n", Integer.toHexString(this.characteristics));
        System.out.println('\n');
    }

    public boolean isCorrect() {
        return this.pointerToSymbolTable == 0 && this.numberOfSymbols == 0;
    }
}
