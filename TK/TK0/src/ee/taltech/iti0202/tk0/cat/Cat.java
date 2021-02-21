package ee.taltech.iti0202.tk0.cat;

public class Cat {

    private String name, color;
    private int age;

    /**
     * Conctruct.
     *
     * @param name name
     * @param age age
     * @param color colot
     */
    public Cat(String name, int age, String color) {
        this.color = color;
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor without age and color.
     *
     * @param name
     */
    public Cat(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convert to string.
     *
     * @return String
     */
    public String toString() {
        if (color == null) {
            return name;
        } else {
            return String.format("%1$s %2$s (%3$s)", color, name, age);
        }
    }

}
