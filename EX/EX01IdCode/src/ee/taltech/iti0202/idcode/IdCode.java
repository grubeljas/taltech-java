
package ee.taltech.iti0202.idcode;

public class IdCode {

    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TWELVE = 12;
    private static final int FRIDAY = 13;
    private static final int ELEVEN = 11;
    private static final int MONTH = 30;
    private static final int TWOTHOUSAND = 2000;
    private static final int[] TOWNS = {10, 20, 220, 270, 370, 420, 470, 490, 520, 570, 600, 650, 710};
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
        String day = idCodeValue.substring(5, SEVEN);
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
        String gender = getIdCodeValue().substring(0, 1);
        int intgender = Integer.parseInt(gender);
        String year = idCodeValue.substring(1, 3);
        int intyear = Integer.parseInt(year);
        String queue = idCodeValue.substring(SEVEN, TOWNS[0]);
        int intqueue = Integer.parseInt(queue);
        if (intyear >= FRIDAY && intgender > 4 || intqueue > TOWNS[TWELVE] || 0 == intqueue) {
            return "unknown";
        } else if (0 < intqueue && intqueue <= TOWNS[0]) {
            return "Kuressaare";
        } else if (TOWNS[0] < intqueue && intqueue <= TOWNS[1] || TOWNS[3] < intqueue && intqueue <= TOWNS[4]) {
            return "Tartu";
        } else if (TOWNS[1] < intqueue && intqueue <= TOWNS[2] || TOWNS[SIX] < intqueue && intqueue <= TOWNS[SEVEN]) {
            return "Tallinn";
        } else if (TOWNS[2] < intqueue && intqueue <= TOWNS[3]) {
            return "Kohtla-Järve";
        } else if (TOWNS[SEVEN] < intqueue && intqueue <= TOWNS[EIGHT]) {
            return "Paide";
        } else if (TOWNS[4] < intqueue && intqueue <= TOWNS[5]) {
            return "Narva";
        } else if (TOWNS[5] < intqueue && intqueue <= TOWNS[SIX]) {
            return "Pärnu";
        } else if (TOWNS[EIGHT] < intqueue && intqueue <= TOWNS[NINE]) {
            return "Rakvere";
        } else if (TOWNS[NINE] < intqueue && intqueue <= TOWNS[10]) {
            return "Valga";
        } else if (TOWNS[10] < intqueue && intqueue <= TOWNS[11]) {
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
            case 0: fullyear = TWOTHOUSAND - 100 * 2;
                break;
            case 1: fullyear = TWOTHOUSAND - 100;
                break;
            case 2: fullyear = TWOTHOUSAND;
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
        return intgender > 0 && intgender < SEVEN;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        int today = TWOTHOUSAND + 1;
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
        return intmonth > 0 && intmonth < FRIDAY;
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
        String day = idCodeValue.substring(5, SEVEN);
        int intday = Integer.parseInt(day);
        switch (intmonth) {
            case 1: case 3: case 5: case SEVEN: case EIGHT: case 10: case TWELVE:
                lastday = MONTH + 1;
                break;
            case 4: case SIX: case NINE: case ELEVEN:
                lastday = MONTH;
                break;
            case 2:
                if (isLeapYear(getFullYear())) {
                    lastday = MONTH - 1;
                } else {
                    lastday = MONTH - 2;
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
        int six = SEVEN - 1; // if number's place is hugher than 6, make multiplayer smaller
        int nine = SEVEN + 2;
        int counter = 0; int n; int lastn;
        String last = idCodeValue.substring(10);
        int intlast = Integer.parseInt(last);
        for (int i = 0; i < getIdCodeValue().substring(0, ELEVEN).length(); i++) {
            if (i == nine) {
                n = 1;
            } else {
                n = i + 1;
            }
            int number = Character.getNumericValue(getIdCodeValue().charAt(i));
            counter += number * n;
        }
        if (counter % ELEVEN == TOWNS[0]) {
            counter = 0;
            for (int i = 0; i < getIdCodeValue().substring(0, ELEVEN).length(); i++) {
                if (i > six) {
                    n = i - six;
                } else {
                    n = i + 3;
                }
                int number = Character.getNumericValue(getIdCodeValue().charAt(i));
                counter += number * n;
            }
            if (counter % ELEVEN == TOWNS[0]) {
                lastn = 0;
            } else {
                lastn = counter % ELEVEN;
            }
        } else {
            lastn = counter % ELEVEN;
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
