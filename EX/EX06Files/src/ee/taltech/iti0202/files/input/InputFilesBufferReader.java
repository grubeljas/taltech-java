package ee.taltech.iti0202.files.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException{
        Path path = Paths.get(filename);
        List<String> lines = new LinkedList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                lines.add(line);
            }
        } catch (IOException e) {
            try {
                throw new FileReaderException("No such file", new Throwable());
            }
            catch (FileReaderException e1) {
                System.out.println("Error reading file:" + e1.getMessage());
                e.printStackTrace();
            }
        }
        return lines;
    }
}
