package org.ndimens.fx.parser;

public class ImageDataDirectory {
    private int virtualAddress; // RVA
    private int size;

    public int getVirtualAddress() {
        return virtualAddress;
    }

    public void setVirtualAddress(int virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
