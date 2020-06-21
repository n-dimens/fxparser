package org.ndimens.fx.parser;

import java.io.FileInputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        final var fileName = "files/Domain.dll";
        try (var fr = new FileInputStream(fileName)) {
            final var assemblyInfo = FxAssembly.parse(fr);
            System.out.println("File: " + fileName);
            assemblyInfo.print();
        }
    }
}
