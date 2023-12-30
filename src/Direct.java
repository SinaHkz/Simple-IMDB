import java.util.ArrayList;
import java.util.Scanner;

public class Direct {
    static Scanner scanner = new Scanner(System.in);
    public User user1;
    public User user2;
    ArrayList<DirectMessage> messages = new ArrayList<>();

    private Direct(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public static Direct creatNewDirect(User user1, User user2, String firstMessage) {
        Direct direct = new Direct(user1, user2);
        DirectMessage message = new DirectMessage(firstMessage, user1);
        direct.messages.add(message);
        return direct;
    }

    public void showDirect(User user) {
        if (this.messages.size() > 11)
            for (int i = this.messages.size() - 11; i <= this.messages.size() - 1; i++)
                System.out.println(messages.get(i).sender.getUserName() + ": " + messages.get(i).message);
        else
            for (int i = 0; i <= this.messages.size() - 1; i++)
                System.out.println(messages.get(i).sender.getUserName() + ": " + messages.get(i).message);
        while (true) {
            System.out.println("1.add message  2.back");
            int respond = Integer.parseInt(scanner.nextLine());
            if (respond == 1) {
                System.out.print("Enter your new message: ");
                String message = scanner.nextLine();
                DirectMessage message1 = new DirectMessage(message, user);
                this.messages.add(message1);
            } else
                return;
        }

    }
}
