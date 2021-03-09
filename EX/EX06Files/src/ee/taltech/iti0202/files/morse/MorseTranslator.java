package ee.taltech.iti0202.files.morse;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class MorseTranslator {

    public Map<String, String> dictionaryFromWords = new HashMap<>();
    public Map<String, String> dictionaryFromMorse = new HashMap<>();

    /**
     * Add morse info to dict.
     *
     * @param lines
     * @return
     */
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

    /**
     * Translate lines to morse.
     *
     * @param lines
     * @return
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morses = new LinkedList<>();
        for (String line: lines) {
            morses.add(translateLineToMorse(line));
        }
        return morses;
    }

    /**
     * Translate lines to words.
     *
     * @param lines
     * @return
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> morses = new LinkedList<>();
        if (lines.size() == 0) {
            return lines;
        }
        for (String line: lines) {
            morses.add(translateLineFromMorse(line));
        }
        return morses;
    }

    /**
     * Translate line to words.
     *
     * @param line
     * @return
     */
    private String translateLineToMorse(String line) {
        String translated = "";
        if (line.length() == 0) {
            return "";
        }
        for (int i = 0; i < line.length(); ++i) {
            String letter = String.valueOf(line.charAt(i)).toLowerCase();
            if (dictionaryFromWords.containsKey(letter)) {
                translated += dictionaryFromWords.get(letter) + " ";
            } else if (line.charAt(i) == ' ') {
                translated = translated.substring(0, translated.length() - 1);
                translated += "\t";
            }
        }
        return translated.substring(0, translated.length() - 1);
    }

    /**
     * Translate line from morse to characters.
     *
     * @param line
     * @return string.
     */
    private String translateLineFromMorse(String line) {
        String translated = new String();
        if (line.length() == 0) {
            return "";
        }
        for (String word: line.split("\t")) {
            for (String c: word.split(" ")) {
                if (dictionaryFromMorse.containsKey(c)) {
                    translated += dictionaryFromMorse.get(c);
                }
            }
            translated += " ";
        }
        return translated.substring(0, translated.length() - 1);
    }
}
