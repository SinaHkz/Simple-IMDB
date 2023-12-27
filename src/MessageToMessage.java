import java.util.ArrayList;

public class MessageToMessage extends ForumMessage {
//    ArrayList<ForumMessage> forumMessages = new ArrayList<>();
//    i delete this part (reply a message that replied another message) because in TerminalUI it's not easy to work with and access messages. but my design ca handle this feature.

    public MessageToMessage(String message, User creator) {
        super(message, creator);
    }


    public String toString(){
        return "\t" + this.creator.getUserName() + ": " + this.message;
    }
}
