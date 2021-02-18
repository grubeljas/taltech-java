package ee.taltech.iti0202.socialnetwork.message;
import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {

    private String title, message;
    private User author;

    public Message(String title, String message, User author) {
        this.author = author;
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

}
