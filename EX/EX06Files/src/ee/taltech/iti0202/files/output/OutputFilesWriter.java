package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.FileReaderException;

import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFilesWriter {

    /**
     * Write smt in file and if file does not exist return false.
     *
     * @param lines
     * @param filename
     * @return
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        if (filename == null) {
            return false;
        }
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line: lines) {
                writer.write(line + "\n");
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
