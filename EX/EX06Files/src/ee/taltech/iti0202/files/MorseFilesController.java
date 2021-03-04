package ee.taltech.iti0202.files;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import ee.taltech.iti0202.files.output.OutputFilesWriter;

import java.util.List;
import java.util.Map;

public class MorseFilesController {

    public static void main(String[] args) {
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("morse.txt");
        lines.forEach(System.out::println); //lines in morse.txt which contains Morse codes
        System.out.println("__________________________");
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> lines2 = bufferReader.readTextFromFile("morse.txt");
        lines2.forEach(System.out::println); //lines in morse.txt which contains Morse codes
        System.out.println("__________________________");
        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);
        codes.forEach((key, value) -> System.out.println(key + " " + value)); //key and value
        System.out.println("__________________________");
        List<String> input = scanner.readTextFromFile("input.txt");
        input.forEach(System.out::println); //your input lines
        System.out.println(1);
        List<String> morseLines = translator.translateLinesToMorse(input);
        morseLines.forEach(System.out::println); //your input lines in Morse
        System.out.println("__________________________");
        List<String> normalLines = translator.translateLinesFromMorse(morseLines);
        normalLines.forEach(System.out::println); //your input lines in regular text
        System.out.println("__________________________");
        OutputFilesWriter writer = new OutputFilesWriter();
        System.out.println(writer.writeLinesToFile(normalLines, "output.txt")); //true
        //This should also create a new file/ write in an existing file
    }
}
