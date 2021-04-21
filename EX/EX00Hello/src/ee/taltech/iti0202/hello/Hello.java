package ee.taltech.iti0202.hello;
/**
 * Simple class to test whether you get feedback.
 */
public class Hello {

    /**
     * Returns a greeting for the given person.
     *
     * If name is empty string, then returns "Hello!".
     * Otherwise returns "Hello, [name]!"
     *
     * @param name Name of the person.
     * @return Greeting
     */
    public static String getGreeting(String name) {
        if (name.equals("")) {
            return "Hello!";
        } else {
            return "Hello, " + name + "!";
        }
    }

    public static float random(float n) {
        float x = n;
        for (int i = 0; i < 10; i++) {
            x = 1 + 1 / x;
            System.out.println(x);
        }
        return x;
    }

    /**
     * The main method which is executed when the program is executed.
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        System.out.println(getGreeting(""));
        System.out.println(getGreeting("Java"));
        System.out.println(random(-0.618f));
    }
}
