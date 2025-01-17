package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {
        Path path = Paths.get(filename);
        List<String> lines = new LinkedList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                System.out.println(nextLine);
                if (nextLine.isBlank()) {
                    lines.add("");
                } else {
                    lines.add(nextLine);
                }
            }
        } catch (IOException e) {
            throw new FileReaderException("No such file", new IOException());
        }
        return lines;
    }
}
