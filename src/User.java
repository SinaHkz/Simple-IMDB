import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person {
    static Scanner scanner = new Scanner(System.in);
    private String userName;
    private String password;
    private ArrayList<Person> following = new ArrayList<>();
    private ArrayList<FilmList> filmLists = new ArrayList<>();
    private String email;
    private String phoneNumber;
    private UserStatus status;
    private UserType userType;
    public ArrayList<Forum> forums = new ArrayList<>();
    public ArrayList<Rate> rates = new ArrayList<>(); //for accessing user rated movie data.

    public User(String name, String lastName, int age, String bioDetail, String userName, String password, String email, String phoneNumber, UserStatus status, UserType userType) {
        super(name, lastName, age, bioDetail);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserStatus getStatus() {
        return status;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public ArrayList<FilmList> getFilmLists() {
        return filmLists;
    }

    public ArrayList<Person> getFollowings() {
        return following;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static User login(ArrayList<User> users) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return checkUserLoginInfo(users, username, password);
    }


    static User checkUserLoginInfo(ArrayList<User> users, String username, String password) {
        for (User user : users) {
            if (user.getUserName().compareTo(username) == 0 && user.getPassword().compareTo(password) == 0) {
                return user;
            }
        }
        return null;
    }

    int findUserIndex(String username, ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().compareTo(username) == 0)
                return i;
        }
        return -1;
    }

    static ArrayList<String> getUserInfo() {
        ArrayList<String> data = new ArrayList<>();
        System.out.print("Enter your name: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your last name: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your age: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your username: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your password: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your email: ");
        data.add(scanner.nextLine());
        System.out.print("Enter your phone number: ");
        data.add(scanner.nextLine());
        return data;
    }

    static private User creatUser(String name, String lastName, int age, String userName, String password, String email, String phoneNumber) {
        return new User(name, lastName, age, "", userName, password, email, phoneNumber, UserStatus.ACTIVE, UserType.MEMBER);
    }

    public static User addUser() {
        ArrayList<String> info = getUserInfo();
        for (User user : Main.getUsers()) {
            if (user.getUserName().equals(info.get(3))) {
                System.out.println("This username has been taken!");
                System.out.println("------------------------------------------------------");
                throw new RuntimeException();
            }
        }
        User user = creatUser(info.get(0).toLowerCase(), info.get(1).toLowerCase(), Integer.parseInt(info.get(2)), info.get(3), info.get(4), info.get(5), info.get(6));
        Main.getUsers().add(user);
        Main.getPeople().add(user);
        System.out.println("user successfully added.");
        return user;
    }

    void deleteUser() {
        if (this.userType.compareTo(UserType.ADMIN) == 0) {
            System.out.println("Enter user username to delete:");
            String username = scanner.nextLine();
            int indx = findUserIndex(username, Main.getUsers());
            if (indx != -1) {
                Main.getUsers().remove(indx);
                System.out.println("user successfully deleted.");
            }
        } else
            System.out.println("You don't have deletion permission!");
    }

    void banUser() {
        if (this.userType.compareTo(UserType.ADMIN) == 0) {
            System.out.println("Enter user username to ban:");
            String username = scanner.nextLine();
            int inx = findUserIndex(username, Main.getUsers());
            if (inx != -1) {
                if (Main.getUsers().get(inx).getUserType().compareTo(UserType.ADMIN) != 0) {
                    Main.getUsers().get(inx).setStatus(UserStatus.BAN);
                    System.out.println("user successfully banned!");
                } else
                    System.out.println("Admin user can not be banned");
            }
        } else System.out.println("You don't have permission to ban users!");
    }

    void creatCustomList() {
        System.out.println("Enter your list name: ");
        String name = scanner.nextLine();
        filmLists.add(new FilmList(name, new ArrayList<>(), ListType.CUSTOM));
    }

    void showList() {
        int i;
        for (i = 0; i < filmLists.size(); i++)
            System.out.print(i + 1 + "." + filmLists.get(i).getListName() + "  ");
        System.out.println("0.back");
        int respond;
        try {
            respond = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            respond = 0;
        }
        if (respond != 0) {
            filmLists.get(respond - 1).showMovies(this, Main.getPeople());
        }
    }

    static void showUsers() {
        for (int i = 0; i < Main.getUsers().size(); i++) {
            System.out.println(i + 1 + "." + Main.getUsers().get(i).getUserName() + " : " + Main.getUsers().get(i).getUserType());
        }
        System.out.print("1.see user information  2.change user type");
        int respond = Integer.parseInt(scanner.nextLine());
        switch (respond) {
            case 1:
                try {
                    System.out.println("enter user number: ");
                    int userRespond = Integer.parseInt(scanner.nextLine());
                    System.out.println(Main.getUsers().get(userRespond - 1).toString());
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    return;
                }
                System.out.println("----------------------------------------");
                break;
            case 2:
                System.out.println("Enter user number: ");
                int userRespond = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter a type: 1.Member  2.Admin  3.Editor");
                int typeNum = Integer.parseInt(scanner.nextLine());
                Main.users.get(userRespond - 1).setUserType(UserType.getBsyValue(typeNum));

        }

    }

    void followUser() {
        System.out.print("Enter user username: ");
        String username = scanner.nextLine();
        for (User user : Main.users)
            if (user.getUserName().compareTo(username) == 0) {
                this.following.add(user);
                user.Followers.add(this);
                System.out.println("User followed.");
                return;
            }
        System.out.print("Username not found!");
    }

    void followCastPerson() {
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person last name: ");
        String lastName = scanner.nextLine();
        for (Person person : Main.people)
            if (!(person instanceof User) && person.getName().compareTo(name) == 0 && person.getLastName().compareTo(lastName) == 0) {
                this.following.add(person);
                person.Followers.add(this);
                return;
            }
        System.out.print("There is no such cast person with this name and last name!");

    }

    void showFollowing() {
        int i = 1;
        for (Person person : this.following) {
            if (person instanceof User)
                System.out.println(i + "." + person.getName() + " " + person.getLastName() + " : " + ((User) person).getUserName());
            else
                System.out.println(i + "." + person.getName() + " " + person.getLastName() + " : CAST");
            i++;
        }
        int respond = Integer.parseInt(scanner.nextLine());
        if (this.following.get(respond-1) instanceof User)
            System.out.println(((User) this.following.get(respond - 1)).showUserInfo());
        else
            System.out.println(this.following.get(respond - 1).showPersonInfo());
    }

    public void showFollower(){
        int i = 1;
        for (User user : this.Followers) {
            System.out.println(i + "." + user.getName() + " " + user.getLastName() + " : " + user.getUserName());
            i++;
        }
        int respond = Integer.parseInt(scanner.nextLine());
        System.out.println(((User) this.following.get(respond - 1)).showUserInfo());
    }

    public String showUserInfo() {
        return "name: " + this.getName() + '\n' +
                "lastName: " + this.getLastName() + '\n' +
                "bioDetail: " + this.getBioDetail() + '\n' +
                "filmLists: " + filmLists + '\n' +
                "userName: " + userName + '\n' +
                "email: " + email + '\n' +
                "phoneNumber: " + phoneNumber + '\n';
    }



    @Override
    public String toString() {
        return "name: " + this.getName() + '\n' +
                "lastName: " + this.getLastName() + '\n' +
                "age: " + this.getAge() + '\n' +
                "Followings: " + this.getFollowings() + '\n' +
                "Followers: " + this.getFollowers() + '\n' +
                "bioDetail: " + this.getBioDetail() + '\n' +
                "filmLists: " + filmLists + '\n' +
                "actingRecords: " + this.getActingRecords() + '\n' +
                "userName: " + userName + '\n' +
                "password: " + password + '\n' +
                "email: " + email + '\n' +
                "phoneNumber: " + phoneNumber + '\n' +
                "status: " + status + '\n';
    }
}
