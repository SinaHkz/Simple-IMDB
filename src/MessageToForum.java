import java.util.ArrayList;

public class MessageToForum extends ForumMessage {
    Forum forum;


    public MessageToForum(String message, User creator, Forum forum) {
        super(message, creator);
        this.forum = forum;
    }

    public String toString() {
        if (messageToMessages.size() == 0)
            return this.creator.getUserName() + ": " + this.message;
        String str = this.creator.getUserName() + ": " + this.message;
        for (MessageToMessage messageToMessage : messageToMessages) str += "\n\t" + messageToMessage.toString();
        return str;
    }
}
