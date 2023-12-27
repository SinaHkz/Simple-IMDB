import java.util.ArrayList;
import java.util.Scanner;

public class FilmList {
    Scanner scanner = new Scanner(System.in);
    String listName;
    ArrayList<Movie> movies;
    ListType listType;

    public FilmList(String listName, ArrayList<Movie> movies, ListType listType) {
        this.listName = listName;
        this.movies = movies;
        this.listType = listType;
    }

    public String getListName() {
        return listName;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public ListType getListType() {
        return listType;
    }

    void showMovies(User user,ArrayList<Person> personArrayList) {
        for (int i = 0; i < movies.size(); i++)
            System.out.print(i + 1 + "." + movies.get(i).getTitle() + "  ");
        System.out.println("0.back");
        int respond;
        try {
            respond = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        if (respond == 0)
            return;
        movies.get(respond - 1).show(user);
    }

    void addMovieToList(Movie movie) {
        movies.add(movie);
    }

}
