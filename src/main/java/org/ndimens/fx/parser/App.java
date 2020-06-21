package org.ndimens.fx.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
//        var fileName = "Visio.exe";
        var fileName = "files/Domain.dll";
        var dosHeader = new byte[64];
        var dosStub = new byte[64];
        var peSignature = new byte[4];
        var coffHeader = new byte[20];
        try (var fr = new FileInputStream(fileName)) {
            fr.read(dosHeader);
            fr.read(dosStub);
            fr.read(peSignature);
            fr.read(coffHeader);
        }

        System.out.println("File: " + fileName);

        printBlock("MS DOS Header", dosHeader);
        printBlock("PE Signature", peSignature);
        printBlock("COFF Header", coffHeader);
    }

    private static void printBlock(String title, byte[] data) {
        System.out.println(title);
        System.out.println("----------------");

//        String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');

        final int lineLength = 8;
        var counter = 0;
        for (var b : data) {
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
