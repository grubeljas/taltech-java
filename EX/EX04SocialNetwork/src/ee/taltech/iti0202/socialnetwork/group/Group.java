package ee.taltech.iti0202.socialnetwork.group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Group {

    private String nameOfGroup;
    private final User owner;
    private List<Message> messages;
    private Set<User> members;

    public Group(String name, User owner) {
        this.nameOfGroup = name;
        this.owner = owner;
        this.messages = new LinkedList<>();
        this.members = new HashSet<>();
        members.add(owner);
    }

    public String getName() {
        return nameOfGroup;
    }

    public void setName(String name) {
        nameOfGroup = name;
    }

    public User getOwner() {
        return owner;
    }

    public void addUser(User user) {
        members.add(user);
    }

    public Set<User> getParticipants() {
        return members;
    }

    public void publishMessage(Message message) {
        if (members.contains(message.getAuthor())) {
            messages.add(message);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }
}
