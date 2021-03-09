package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputFilesLines implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {
        Path path = Paths.get(filename);
        try {
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            return lines;
        } catch (IOException e) {
            throw new FileReaderException("No such file", new Throwable());
        }
    }
}
