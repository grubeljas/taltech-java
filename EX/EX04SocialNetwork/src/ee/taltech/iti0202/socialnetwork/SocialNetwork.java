package ee.taltech.iti0202.socialnetwork;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SocialNetwork {

    private Set<Group> groups = new HashSet<>();

    public void registerGroup(Group group) {
        groups.add(group);
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Feed getFeedForUser(User user) {
        Iterator<Group> it = getGroups().iterator();
        Set<Message> history = new HashSet<Message>();
        while (it.hasNext()) {
            Group group = it.next();
            if (group.getParticipants().contains(user)) {
                history.addAll(group.getMessages());
            }
        }
        return new Feed(user, history);
    }
}
