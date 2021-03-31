package ee.taltech.iti0202.kt1.mail;
public class Letter {

    String recipient, destination, address;

    /**
     * Create a new letter with recipient, destination city and address.
     */
    public Letter(String recipient, String destinationCity, String address) {
        this.address = address;
        this.destination = destinationCity;
        this.recipient = recipient;
    }
    public String getDestinationCity() {
        return destination;
    }
    public void setDestinationCity(String destinationCity) {
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
    }

    /**
     * String representation of the letter.
     * The format is: City: %s, Address: %s, Recipient: %s
     */
    public String toString() {
        return String.format("City: %s, Address: %s, Recipient: %s", destination, address, recipient);
    }
}
