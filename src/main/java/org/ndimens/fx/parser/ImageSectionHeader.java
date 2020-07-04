package org.ndimens.fx.parser;

public class ImageSectionHeader {
    public String Name;
    public int Misc; // PhysicalAddress/VirtualSize
    public int VirtualAddress;
    public int SizeOfRawData;
    public int PointerToRawData;
    public int PointerToRelocations;
    public int PointerToLinenumbers;
    public short NumberOfRelocations;
    public short NumberOfLinenumbers;
    public int Characteristics;
}
