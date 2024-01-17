/*
package com.ECESWA.ElectReg.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class ReadWrite {
    Path inputFile = Paths.get("input.txt");
    Path outputFile = Paths.get("output.txt");

        try

    {
        // Reading from a file
        List<String> lines = null;
        try {
            lines = Files.readAllLines(inputFile, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Writing to a file
        String newLine = "This is a new line to add to the output file.";
        lines.add(newLine);

        // You can also use Files.write to write the updated lines back to the file
        try {
            Files.write(outputFile, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //System.out.println("File reading and writing completed successfully!");
    }catch(IOException exception){
            exception.printStackTrace();
    }
}
*/
