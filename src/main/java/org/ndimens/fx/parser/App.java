package org.ndimens.fx.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
//        final var fileName = "files/Domain.dll";
        final var fileName = "files/Visio.exe";
        FxAssembly assemblyInfo;
        try (var fr = new FileInputStream(fileName)) {
            assemblyInfo = FxAssembly.parse(fr);
            System.out.println("File: " + fileName);
            assemblyInfo.print();
        }

//        var textSectionHeader = assemblyInfo.sectionHeaders.get(".text");
//        if (textSectionHeader != null) {
//            try (var fr = new FileInputStream(fileName)) {
//                fr.skip(textSectionHeader.PointerToRawData);
//                var textSection = fr.readNBytes(textSectionHeader.SizeOfRawData);
//                try (FileOutputStream fos = new FileOutputStream(fileName + textSectionHeader.Name)) {
//                    fos.write(textSection);
//                }
//            }
//        }
    }
}
