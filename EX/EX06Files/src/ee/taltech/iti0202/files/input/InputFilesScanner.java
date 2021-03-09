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
            while (scanner.hasNext()) {
                if (scanner.hasNext("\n")) {
                    lines.add("");
                    scanner.nextLine();
                } else {
                    lines.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            try {
                throw new FileReaderException("No such file", new Throwable());
            } catch (FileReaderException e1) {
                System.out.println("Error reading file:" + e1.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        return lines;
    }
}
