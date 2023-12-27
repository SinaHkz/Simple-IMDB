import java.util.ArrayList;
import java.util.Scanner;

public abstract class ForumMessage {
    static Scanner scanner = new Scanner(System.in);
    String message;
    User creator;
    ArrayList<MessageToMessage> messageToMessages = new ArrayList<>();

    public ForumMessage(String message, User creator) {
        this.message = message;
        this.creator = creator;
    }

    public void reply(User user) {
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        message += '\t' + message;
        this.messageToMessages.add(new MessageToMessage(message, user));
    }
}
