package ee.taltech.iti0202.hello;
import java.util.Scanner;
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
        return "Hello, " + name + "!";
    }

    /**
     * The main method which is executed when the program is executed.
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        Scanner nObj = new Scanner(System.in);
        String name = nObj.nextLine();
        if (name.equals(new String())) {
            System.out.println("Hello!");
        } else {
            System.out.println(getGreeting(name));
        }
    }
}
