import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private String lastName;
    private int age;
    public ArrayList<User> Followers = new ArrayList<>();
    private String bioDetail;
    private ArrayList<ActingRecord> actingRecords = new ArrayList<>();

    public Person(String name, String lastName, int age, String bioDetail) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.bioDetail = bioDetail;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<User> getFollowers() {
        return Followers;
    }

    public String getBioDetail() {
        return bioDetail;
    }

    public ArrayList<ActingRecord> getActingRecords() {
        return actingRecords;
    }


    static void showPerson() {
        for (int i = 0; i < Main.getPeople().size(); i++) {
            System.out.println(i + 1 + "." + Main.getPeople().get(i).getName() + " : " + Main.getPeople().get(i).getLastName() + " age: " + Main.getPeople().get(i).getAge());
        }
        System.out.print("if you want to see a person information enter its number otherwise 0: ");
        try {
            int respond = Integer.parseInt(scanner.nextLine());
            if (respond == 0)
                return;
            System.out.println(Main.getPeople().get(respond - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        System.out.println("----------------------------------------");
    }

    public String showPersonInfo() {
        return "name: " + this.getName() + '\n' +
                "lastName: " + this.getLastName() + '\n' +
                "bioDetail: " + this.getBioDetail() + '\n' +
                "actingRecords: " + this.getActingRecords() + '\n';
    }
}
