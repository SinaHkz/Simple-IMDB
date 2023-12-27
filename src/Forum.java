import java.util.ArrayList;
import java.util.Scanner;


public class Forum {
    static Scanner scanner = new Scanner(System.in);
    String forumData;
    User writer;
    ArrayList<MessageToForum> forumMessages = new ArrayList<>();

    public Forum(String forumData, User writer) {
        this.forumData = forumData;
        this.writer = writer;
        writer.forums.add(this);
    }

    private static String getForumInfo() {
        System.out.println("Enter your question or the topic you want to discuss: ");
        return scanner.nextLine();
    }

    public static void makeForum(User user) {
        String data = getForumInfo();
        Forum forum = new Forum(data, user);
        Main.forums.add(forum);
    }

    public static void showForums(User user) {  //add the while loop for forum.
        for (int i = 0; i < Main.forums.size(); i++) {
            System.out.println(i + 1 + ". " + Main.forums.get(i).showForum());
        }
        System.out.println("Select forum to see the detail: ");
        try {
            int respond = Integer.parseInt(scanner.nextLine());
            Forum forum = Main.forums.get(respond - 1);
            while (true) {
                System.out.println(forum.toString());
                System.out.println("1.add a message  2.reply a message  0.exit");
                respond = Integer.parseInt(scanner.nextLine());
                switch (respond) {
                    case 1:
                        forum.addMessage(user);
                        break;
                    case 2:
                        System.out.print("Enter the message number: ");
                        respond = Integer.parseInt(scanner.nextLine());
                        forum.forumMessages.get(respond - 1).reply(user);
                        break;
                }
                if (respond == 0)
                    break;
            }

        } catch (Exception e) {
            System.out.println("invalid input");
        }


    }

    private String showForum() {
        return this.writer.getUserName() + ": " + this.forumData;
    }

    private void addMessage(User user) {
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        MessageToForum messageToForum = new MessageToForum(message, user, this);
        this.forumMessages.add(messageToForum);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.writer.getUserName() + ": " + this.forumData + "\n");
        for (int i = 0; i < this.forumMessages.size(); i++) {
            str.append("\t").append(i + 1).append(". ").append(forumMessages.get(i).toString()).append("\n");
        }
        return str.toString();
    }
}
