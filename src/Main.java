import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Movie> movies = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Person> people = new ArrayList<>();
    static ArrayList<Forum> forums = new ArrayList<>();
    static HashMap<Movie,Movie> editeSuggestions = new HashMap<>();

    public static ArrayList<Movie> getMovies() {
        return movies;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void main(String[] args) {
        users.add(new User("sina", "Hz", 20, "", "sina", "23", "", "", UserStatus.ACTIVE, UserType.ADMIN));
        users.add(new User("ali", "naderi", 20, "", "ali", "12", "", "", UserStatus.ACTIVE, UserType.MEMBER));
        users.add(new User("mmd", "naderi", 20, "", "mmd", "12", "", "", UserStatus.ACTIVE, UserType.EDITOR));
        people.add(users.get(0));
        people.add(users.get(1));
        movies.add(new Movie("Inception", "", "", "Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people's dreams and steal their secrets from their subconscious.", Genre.ACTION, new Date(2014)));
        movies.add(new Movie("Inception 2", "", "", "Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people's dreams and steal their secrets from their subconscious.", Genre.COMEDY, new Date(2014)));
        movies.add(new Movie("Inception 3", "", "", "Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people's dreams and steal their secrets from their subconscious.", Genre.COMEDY, new Date(2014)));
        while (true) {
            User user = null;
            while (user == null) {
                System.out.print("1.log in  2.sign up  0.exit\n--->");

                try {
                    int respond = Integer.parseInt(scanner.nextLine());
                    if (respond == 0)
                        return;
                    if (respond == 1) {
                        user = User.login(users);
                        if (user == null) {
                            System.out.println("User not found!");
                        } else
                            break;
                    } else if (respond == 2) {
                        user = User.addUser();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid input!");
                    return;
                }
            }
            boolean flaq = true;
            if (user.getUserType().compareTo(UserType.ADMIN) == 0) {
                while (flaq) {
                    flaq = adminHomePage(user);
                    //showUsers(users);
                }
            } else if (user.getUserType().compareTo(UserType.MEMBER) == 0) {
                while (flaq)
                    flaq = userHomePage(user);
                //        else if (user.getUserType().compareTo(UserType.EDITOR)==0)
                //            while (flaq)
                //                flaq = editorHomePage(user);
            }else if (user.getUserType().compareTo(UserType.EDITOR) == 0){
                while (flaq)
                    flaq = editorHomePage(user);
            }
        }
    }


    static boolean adminHomePage(User admin) {
        System.out.println("1.add user  2.delete user  3.ban user  4.show users  5.show film list  6.creatList  7.show people\n" +
                "8.see forums  9.make a forum  10.add movie  11.edit movie  12.show movies by rates  13.follow a user  14.follow an actor\n" +
                "15.show following  16.show followers 17.show movie edit suggestions  18.recommended movies   19.search  0.log out");
        Movie[] suggestedMovie = {null, null, null};
        System.out.println("Newest Movies: ");
        for (int i = 0; i < movies.size() && i < 3; i++) {
            System.out.print(i + 20 + "." + movies.get(i).getTitle() + " ");
            suggestedMovie[i] = movies.get(i);
        }
        System.out.println();
        int respond;
        try {
            respond = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return false;
        }
        if (admin.getStatus().compareTo(UserStatus.BAN) == 0) {
            System.out.println("Access denied! your account has been banned.");
            return true;
        }

        switch (respond) {
            case 0:
                return false;
            case 1:
                User.addUser();
                return true;
            case 2:
                admin.deleteUser();
                return true;
            case 3:
                admin.banUser();
                return true;
            case 4:
                User.showUsers();
                return true;
            case 5:
                admin.showList();
                return true;
            case 6:
                admin.creatCustomList();
                return true;
            case 7:
                Person.showPerson();
                return true;
            case 8:
                Forum.showForums(admin);
                return true;
            case 9:
                Forum.makeForum(admin);
                return true;
            case 10:
                Movie movie = Movie.creatMovie();
                if (movie != null)
                    movies.add(movie);
                return true;
            case 11:
                Movie movie1 = Movie.findMovie();
                if (movie1 != null)
                    movie1.editMovie();
                return true;
            case 12:
                Movie.showTopRatedMovies(admin);
                return true;
            case 13:
                admin.followUser();
                return true;
            case 14:
                admin.followCastPerson();
                return true;
            case 15:
                admin.showFollowing();
                return true;
            case 16:
                admin.showFollower();
                return true;
            case 17:
                Movie.showMovieSuggestion();
                return true;
            case 18:
                Movie.recommendationEngine(admin);
                return true;
            case 19:
                Movie.searchMovie(admin);
                return true;
            case 20:
                suggestedMovie[0].show(admin);
                return true;
            case 21:
                suggestedMovie[1].show(admin);
                return true;
            case 22:
                suggestedMovie[2].show(admin);
                return true;
        }
        return false;
    }

    static boolean userHomePage(User user) {
        System.out.println("1.show film list  2.creatList  3.show people 4.see forums  5.make a forum\n" +
                "6.show movies by rates  7.follow a user  8.follow an actor/director\n" +
                "9.show following  10.show followers 11.recommended movies  12.search  0.log out");
        Movie[] suggestedMovie = {null, null, null};
        System.out.println("Movies: ");
        for (int i = 0; i < movies.size() && i < 3; i++) {
            System.out.print(i + 13 + "." + movies.get(i).getTitle() + " ");
            suggestedMovie[i] = movies.get(i);
        }
        System.out.println();
        int respond;
        try {
            respond = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return false;
        }
        if (user.getStatus().compareTo(UserStatus.BAN) == 0) {
            System.out.println("Access denied! your account has been banned.");
            return true;
        }

        switch (respond) {
            case 0:
                return false;
            case 1:
                user.showList();
                return true;
            case 2:
                user.creatCustomList();
                return true;
            case 3:
                Person.showPerson();
                return true;
            case 4:
                Forum.showForums(user);
                return true;
            case 5:
                Forum.makeForum(user);
                return true;
            case 6:
                Movie.showTopRatedMovies(user);
                return true;
            case 7:
                user.followUser();
                return true;
            case 8:
                user.followCastPerson();
                return true;
            case 9:
                user.showFollowing();
                return true;
            case 10:
                user.showFollower();
                return true;
            case 11:
                Movie.recommendationEngine(user);
            case 12:
                Movie.searchMovie(user);
                return true;
            case 13:
                suggestedMovie[0].show(user);
                return true;
            case 14:
                suggestedMovie[1].show(user);
                return true;
            case 25:
                suggestedMovie[2].show(user);
                return true;
        }
        return false;
    }

    static boolean editorHomePage(User editor) {
        System.out.println("1.show film list  2.creatList  3.show people 4.see forums  5.make a forum\n" +
                "6.show movies by rates  7.follow a user  8.follow an actor/director\n" +
                "9.show following  10.show followers  11..recommended movies  12.search  0.log out\n" +
                "(to suggest an edit for movie open movie!)");
        Movie[] suggestedMovie = {null, null, null};
        System.out.println("Movies: ");
        for (int i = 0; i < movies.size() && i < 3; i++) {
            System.out.print(i + 13 + "." + movies.get(i).getTitle() + " ");
            suggestedMovie[i] = movies.get(i);
        }
        System.out.println();
        int respond;
        try {
            respond = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return false;
        }
        if (editor.getStatus().compareTo(UserStatus.BAN) == 0) {
            System.out.println("Access denied! your account has been banned.");
            return true;
        }

        switch (respond) {
            case 0:
                return false;
            case 1:
                editor.showList();
                return true;
            case 2:
                editor.creatCustomList();
                return true;
            case 3:
                Person.showPerson();
                return true;
            case 4:
                Forum.showForums(editor);
                return true;
            case 5:
                Forum.makeForum(editor);
                return true;
            case 6:
                Movie.showTopRatedMovies(editor);
                return true;
            case 7:
                editor.followUser();
                return true;
            case 8:
                editor.followCastPerson();
                return true;
            case 9:
                editor.showFollowing();
                return true;
            case 10:
                editor.showFollower();
                return true;
            case 11:
                Movie.recommendationEngine(editor);
            case 12:
                Movie.searchMovie(editor);
                return true;
            case 13:
                suggestedMovie[0].show(editor);
                return true;
            case 14:
                suggestedMovie[1].show(editor);
                return true;
            case 15:
                suggestedMovie[2].show(editor);
                return true;
        }
        return false;
    }

}
