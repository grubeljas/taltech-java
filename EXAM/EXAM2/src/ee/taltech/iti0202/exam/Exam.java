package ee.taltech.iti0202.exam;

public class Exam {

    private static final int SIXTY = 60;
    private static final int TWENTYFOUR = 24;

    /**
     * Write a  method that finds correct difference between two timestamps. Timestamps are given in format HH:MM where
     * HH is two-digit hour marker and MM is two-digit
     * minutes marker. Result must be also in format HH:MM. Difference means basically "time2" minus "time1".
     * timeDiff("10:00", "10:00") => "00:00"
     * timeDiff("10:00", "11:01") => "01:01"
     * timeDiff("10:00", "09:01") => "23:01"
     * timeDiff("10:00", "08:59") => "22:59"
     * timeDiff("10:01", "10:00") => "23:59"
     *
     * @param time1 time as HH:MM
     * @param time2 time as HH:MM
     * @return time difference as HH:MM
     */
    public static String timeDiff(String time1, String time2) {
        String[] seperatedTime1 = time1.split(":");
        String[] seperatedTime2 = time2.split(":");
        int hours1 = Integer.parseInt(seperatedTime1[0]);
        int minutes1 = Integer.parseInt(seperatedTime1[1]);
        int hours2 = Integer.parseInt(seperatedTime2[0]);
        int minutes2 = Integer.parseInt(seperatedTime2[1]);

        if (minutes2 < minutes1) {
            minutes2 += SIXTY;
            hours2--;
        }

        if (hours2 < hours1) {
            hours2 += TWENTYFOUR;
        }
        hours2 = hours2 - hours1;
        minutes2 = minutes2 - minutes1;

        String hours =  String.valueOf(hours2);
        String minutes =  String.valueOf(minutes2);
        if (hours.length() < 2) {
            hours = "0" + hours;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        return hours + ":" + minutes;
    }

    /**
     * Given a string, encode the string using Run-length encoding.
     * RLE is a form of data compression, where runs (consecutive data elements)
     * are replaced by just one data value and count.
     * <p>
     * encode("TalTech") => "Taltech"
     * encode("TTU") => "2TU"
     * encode("WWWABBBA") => "3WA3BA"
     * encode("  ") => "2 "
     *
     * @param data string to encode
     * @return encoded string
     */
    public static String encode(String data) {
        char currentSymbol, previousSymbol = '1';
        String symbol;
        String encoded = "";
        int counter = 0;
        for (int i = 0; i < data.length(); i++) {
            currentSymbol = data.charAt(i);
            if (previousSymbol != '1' && currentSymbol != previousSymbol) {
                encoded = addValue(encoded, previousSymbol, counter);
                counter = 1;
            } else {
                counter++;
            }
            if (i == data.length() - 1) {
                encoded = addValue(encoded, currentSymbol, counter);
            }
            previousSymbol = currentSymbol;
        }
        return encoded;
    }

    /**
     * Add to the string character and number if counter is more than 1.
     * @param encoded
     * @param symbol1
     * @param counter
     * @return
     */
    public static String addValue(String encoded, char symbol1, int counter) {
        String symbol = String.valueOf(symbol1);
        if (counter == 1) {
            encoded += symbol;
        } else {
            encoded += counter + symbol;
        }
        return encoded;
    }
}
