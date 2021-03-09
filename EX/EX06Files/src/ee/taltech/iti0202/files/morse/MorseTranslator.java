package ee.taltech.iti0202.files.morse;

import java.util.*;

public class MorseTranslator {

    public Map<String, String> dictionaryFromWords = new HashMap<>();
    public Map<String, String> dictionaryFromMorse = new HashMap<>();


    public Map<String, String> addMorseCodes(List<String> lines) {
        Map<String, String> dictionary = new HashMap<>();
        for (String line: lines) {
            String[] symbols = line.split(" ");
            dictionary.put(symbols[0].toLowerCase(), symbols[1]);
            dictionaryFromWords.put(symbols[0].toLowerCase(), symbols[1]);
            dictionaryFromMorse.put(symbols[1], symbols[0].toLowerCase());
        }
        return dictionary;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morses = new LinkedList<>();
        for (String line: lines) {
            morses.add(translateLineToMorse(line));
        }
        return morses;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> morses = new LinkedList<>();
        for (String line: lines) {
            morses.add(translateLineFromMorse(line));
        }
        return morses;
    }

    private String translateLineToMorse(String line) {
        String translated = new String();
        for (int i = 0; i < line.length(); ++i) {
            if (dictionaryFromWords.containsKey(String.valueOf(line.charAt(i)))) {
                translated += dictionaryFromWords.get(String.valueOf(line.charAt(i))) + " ";
            } if (line.charAt(i) == ' ') {
                translated += "\t";
            }
        }
        return translated;
    }

    /**
     * Translate line from morse to characters.
     *
     * @param line
     * @return string.
     */
    private String translateLineFromMorse(String line) {
        String translated = new String();
        for (String word: line.split("\t")) {
            for (String c: word.split(" ")) {
                if (dictionaryFromMorse.containsKey(c)) {
                    translated += dictionaryFromMorse.get(c);
                }
            }
            translated += " ";
        }
        return translated;
    }
}