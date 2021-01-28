
package ee.taltech.iti0202.idcode;

public class IdCode {

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

    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        return isControlNumberCorrect() && isDayNumberCorrect() && isMonthNumberCorrect() && isYearNumberCorrect() && isGenderNumberCorrect();
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
        String full_year = Integer.toString(year);
        String month = idCodeValue.substring(3, 5);
        String day = idCodeValue.substring(5, 7);
        return String.format("This is a %1$s born on %2$s.%3$s.%4$s in %5$s.", gender, day, month, full_year, place);
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        String gender = getIdCodeValue().substring(0, 1);
        int int_gender = Integer.parseInt(gender);
        if (int_gender % 2 == 1) {
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
        int int_gender = Integer.parseInt(gender);
        String year = idCodeValue.substring(1, 3);
        int int_year = Integer.parseInt(year);
        String queue = idCodeValue.substring(7, 10);
        int int_queue = Integer.parseInt(queue);
        if (int_year >= 13 && int_gender > 4 || int_queue > 710 || int_queue == 0) {
            return "unknown";
        } else if (0 < int_queue && int_queue < 11) {
            return "Kuressaare";
        } else if (10 < int_queue && int_queue < 21 || 270 < int_queue && int_queue < 371) {
            return "Tartu";
        } else if (20 < int_queue && int_queue < 221 || 470 < int_queue && int_queue < 491) {
            return "Tallinn";
        } else if (220 < int_queue && int_queue < 271) {
            return "Kohtla-Järve";
        } else if (490 < int_queue && int_queue < 521) {
            return "Paide";
        } else if (370 < int_queue && int_queue < 421) {
            return "Narva";
        } else if (420 < int_queue && int_queue < 471) {
            return "Pärnu";
        } else if (520 < int_queue && int_queue < 571) {
            return "Rakvere";
        } else if (570 < int_queue && int_queue < 601) {
            return "Valga";
        } else if (600 < int_queue && int_queue < 651) {
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
        int int_gender = (Integer.parseInt(gender) - 1) / 2;
        String year = idCodeValue.substring(1, 3);
        int int_year = Integer.parseInt(year);
        int full_year;
        switch (int_gender) {
            case 0: full_year = 1800;
                break;
            case 1: full_year = 1900;
                break;
            case 2: full_year = 2000;
                break;
            default: full_year = 0;
                break;
        }
        return int_year + full_year;
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        String gender = getIdCodeValue().substring(0, 1);
        int int_gender = Integer.parseInt(gender);
        return int_gender > 0 && int_gender < 7;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        String year = idCodeValue.substring(1, 3);
        int int_year = Integer.parseInt(year);
        String gender = getIdCodeValue().substring(0, 1);
        int int_gender = Integer.parseInt(gender);
        return int_gender <= 4 || int_year <= 2021;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        String month = idCodeValue.substring(3, 5);
        int int_month = Integer.parseInt(month);
        return int_month > 0 && int_month < 13;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        int last_day;
        String month = idCodeValue.substring(3, 5);
        int int_month = Integer.parseInt(month);
        String day = idCodeValue.substring(5, 7);
        int int_day = Integer.parseInt(day);
        switch (int_month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                last_day = 31;
                break;
            case 4: case 6: case 9: case 11:
                last_day = 30;
                break;
            case 2:
                if (isLeapYear(getFullYear())) {
                    last_day = 29;
                } else {
                    last_day = 28;
                }
                break;
            default:
                last_day = 0;
                break;
        }
        return 0 < int_month && int_day < last_day + 1;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        int counter = 0; int n; int last_n;
        String last = idCodeValue.substring(10);
        int int_last = Integer.parseInt(last);
        for (int i = 0; i < getIdCodeValue().substring(0, 11).length(); i++)
        {
            if (i == 9){
                n = 1;
            } else {
                n = i + 1;
            }
            int number = Character.getNumericValue(getIdCodeValue().charAt(i));
            counter += number * n;
        }
        if (counter % 11 == 10) {
            counter = 0;
            for (int i = 0; i < getIdCodeValue().substring(0, 11).length(); i++)
            {
                if (i > 6){
                    n = i - 6;
                } else {
                    n = i + 3;
                }
                int number = Character.getNumericValue(getIdCodeValue().charAt(i));
                counter += number * n;
            }
            if (counter % 11 == 10) {
                last_n = 0;
            } else {
                last_n = counter % 11;
            }
        } else {
            last_n = counter % 11;
        }
        return int_last == last_n;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear year
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        int FOUR_LEAP = 4;
        int HUNDRED = 100;
        return fullYear % (FOUR_LEAP * HUNDRED) == 0 || fullYear % FOUR_LEAP == 0 && fullYear % HUNDRED != 0;
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
