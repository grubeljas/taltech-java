
package ee.taltech.iti0202.idcode;

public class IdCode {

    private final int sEVEN = 7;
    private final int fRIDAY = 13;
    private final int eLEVEN = 11;
    private final int twothousand = 2000;
    private final String idCodeValue;
    enum Gender {
        MALE, FEMALE
    }

    /**
     * Method returns the id code.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    /**
     * Method sets the id code.
     *
     * @param idCodeValue id.
     */
    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        boolean gender = isGenderNumberCorrect();
        boolean year = isYearNumberCorrect();
        return isControlNumberCorrect() && isDayNumberCorrect() && isMonthNumberCorrect() && year && gender;
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        Gender gender = getGender();
        String place = getBirthPlace();
        int year = getFullYear();
        String fullyear = Integer.toString(year);
        String month = idCodeValue.substring(3, 5);
        String day = idCodeValue.substring(5, sEVEN);
        return String.format("This is a %1$s born on %2$s.%3$s.%4$s in %5$s", gender, day, month, fullyear, place);
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = Integer.parseInt(gender);
        if (intgender % 2 == 1) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        int taru1 = 270;
        int tartu2 = 370;
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = Integer.parseInt(gender);
        String year = idCodeValue.substring(1, 3);
        int intyear = Integer.parseInt(year);
        String queue = idCodeValue.substring(sEVEN, 10);
        int intqueue = Integer.parseInt(queue);
        if (intyear >= fRIDAY && intgender > 4 || intqueue > 710 || intqueue == 0) {
            return "unknown";
        } else if (0 < intqueue && intqueue < eLEVEN) {
            return "Kuressaare";
        } else if (10 < intqueue && intqueue < 21 || taru1 < intqueue && intqueue <= tartu2) {
            return "Tartu";
        } else if (20 < intqueue && intqueue < 221 || 470 < intqueue && intqueue < 491) {
            return "Tallinn";
        } else if (220 < intqueue && intqueue <= taru1) {
            return "Kohtla-Järve";
        } else if (490 < intqueue && intqueue < 521) {
            return "Paide";
        } else if (tartu2 < intqueue && intqueue < 421) {
            return "Narva";
        } else if (420 < intqueue && intqueue < 471) {
            return "Pärnu";
        } else if (520 < intqueue && intqueue < 571) {
            return "Rakvere";
        } else if (570 < intqueue && intqueue < 601) {
            return "Valga";
        } else if (600 < intqueue && intqueue < 651) {
            return "Viljandi";
        } else {
            return "Võru";
        }
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = (Integer.parseInt(gender) - 1) / 2;
        String year = idCodeValue.substring(1, 3);
        int intyear = Integer.parseInt(year);
        int fullyear;
        switch (intgender) {
            case 0: fullyear = twothousand - 200;
                break;
            case 1: fullyear = twothousand - 100;
                break;
            case 2: fullyear = twothousand;
                break;
            default: fullyear = 0;
                break;
        }
        return intyear + fullyear;
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = Integer.parseInt(gender);
        return intgender > 0 && intgender < sEVEN;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        int today = twothousand + 1;
        String year = idCodeValue.substring(1, 3);
        int intyear = Integer.parseInt(year);
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = Integer.parseInt(gender);
        return intgender <= 4 || intyear <= today;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        String month = idCodeValue.substring(3, 5);
        int intmonth = Integer.parseInt(month);
        return intmonth > 0 && intmonth < fRIDAY;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        int lastday;
        String month = idCodeValue.substring(3, 5);
        int intmonth = Integer.parseInt(month);
        String day = idCodeValue.substring(5, sEVEN);
        int intday = Integer.parseInt(day);
        switch (intmonth) {
            case 1: case 3: case 5: case sEVEN: case 8: case 10: case 12:
                lastday = 31;
                break;
            case 4: case 6: case 9: case eLEVEN:
                lastday = 30;
                break;
            case 2:
                if (isLeapYear(getFullYear())) {
                    lastday = 29;
                } else {
                    lastday = 28;
                }
                break;
            default:
                throw new IllegalArgumentException(Integer.toString(intday));
        }
        return intday < lastday + 1;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        int six = sEVEN - 1; // if number's place is hugher than 6, make multiplayer smaller
        int nine = sEVEN + 2;
        int counter = 0; int n; int lastn;
        String last = idCodeValue.substring(10);
        int intlast = Integer.parseInt(last);
        for (int i = 0; i < getIdCodeValue().substring(0, eLEVEN).length(); i++) {
            if (i == nine) {
                n = 1;
            } else {
                n = i + 1;
            }
            int number = Character.getNumericValue(getIdCodeValue().charAt(i));
            counter += number * n;
        }
        if (counter % eLEVEN == 10) {
            counter = 0;
            for (int i = 0; i < getIdCodeValue().substring(0, eLEVEN).length(); i++) {
                if (i > six) {
                    n = i - six;
                } else {
                    n = i + 3;
                }
                int number = Character.getNumericValue(getIdCodeValue().charAt(i));
                counter += number * n;
            }
            if (counter % eLEVEN == 10) {
                lastn = 0;
            } else {
                lastn = counter % eLEVEN;
            }
        } else {
            lastn = counter % eLEVEN;
        }
        return intlast == lastn;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear year
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        int fOURLEAP = 4;
        int hUNDRED = 100;
        return fullYear % (fOURLEAP * hUNDRED) == 0 || fullYear % fOURLEAP == 0 && fullYear % hUNDRED != 0;
    }

    /**
     * Run tests.
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("37605030299");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}
