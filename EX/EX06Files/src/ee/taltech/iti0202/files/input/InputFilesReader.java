package ee.taltech.iti0202.files.input;

import java.util.List;

public interface InputFilesReader {

    /**
     * Read file by line.
     *
     * @param filename
     * @return list.
     */
    List<String> readTextFromFile(String filename);
}
