import java.util.*;

public class Movie {
    static Scanner scanner = new Scanner(System.in);
    private String title;
    private String posterLink;
    private String trailerLink;
    private String plotSummery;
    private Genre genre;
    private Date releaseDate;
    private ArrayList<ActingRecord> actingRecords = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Rate> rates = new ArrayList<>();
    private ArrayList<MovieReport> movieReports = new ArrayList<>();

    public Movie(String title, String posterLink, String trailerLink, String plotSummery, Genre genre, Date releaseDate) {
        this.title = title;
        this.posterLink = posterLink;
        this.trailerLink = trailerLink;
        this.plotSummery = plotSummery;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public String getPlotSummery() {
        return plotSummery;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArrayList<ActingRecord> getActingRecords() {
        return actingRecords;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public ArrayList<MovieReport> getMovieReports() {
        return movieReports;
    }

    void show(User user) {
        while (true) {
            System.out.println(this.title);
            System.out.println(averageRate());
            System.out.println("genre: " + this.genre);
            System.out.println("release year: " + this.releaseDate.toString());
            System.out.println(this.plotSummery);
            System.out.print("Cast: ");
            for (int i = 0; i < actingRecords.size(); i++)
                System.out.print(i + 1 + "." + actingRecords.get(i).getPerson().getName() + " " + actingRecords.get(i).getPerson().getLastName() + ":" + actingRecords.get(i).getRole());
            System.out.print("\n\n");
            if (user.getUserType().compareTo(UserType.EDITOR) == 0) {
                System.out.println("Do you want to suggest an edit:(y/n)");
                String respond = scanner.nextLine();
                if (respond.compareTo("y") == 0) {
                    try {
                        this.editMovieByEditor();
                    } catch (Exception e) {
                        System.out.println("something wrong");
                        return;
                    }
                }
            }
            System.out.println("1.Rate the movie  2.read reviews  3.write a review  4.report the movie  5.show reports  6.add movie to your lists  7.add a Cast  0.back");
            try {
                int respond = Integer.parseInt(scanner.nextLine());
                switch (respond) {
                    case 0:
                        return;
                    case 1:
                        rateMovie(user);
                        break;
                    case 2:
                        showReviews(user);
                        break;
                    case 3:
                        writeReview(user);
                        break;
                    case 4:
                        reportMovie(user);
                        break;
                    case 5:
                        showMovieReports();
                        break;
                    case 6:
                        this.addToList(user);
                        break;
                    case 7:
                        this.addCast();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                return;
            }
        }
    }

    double averageRate() {
        double average = 0;
        for (Rate rate : rates) {
            average += rate.getStar().getValue();
        }
        if (rates.size() != 0)
            average /= rates.size();
        return average;
    }

    void rateMovie(User user) {
        for (Rate rate : rates) {
            if (rate.getRater() == user) {
                System.out.println("Your rate has been recorded!  You can't rate the movie twice!");
                return;
            }
        }
        System.out.println("What's your rate(1 to 10)? ");
        int star = Integer.parseInt(scanner.nextLine());
        if (star > 10 || star < 1)
            System.out.println("Invalid input!");
        else {
            Rate rate = new Rate(Star.getBsyValue(star), user, this);
            this.rates.add(rate);
            user.rates.add(rate);
            System.out.println("Your rate successfully recorded.\n");
        }
    }

    void showReviews(User user) {
        int i;
        for (i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).isSpoilerAlert())
                System.out.println(i + 1 + "." + "SPOILER ALERT: " + reviews.get(i).getReview() + " | " + reviews.get(i).getReviewer().getUserName() + " | " + reviews.get(i).isHelpful());
            else
                System.out.println(i + 1 + "." + reviews.get(i).getReview() + " | " + reviews.get(i).getReviewer().getUserName() + " | " + reviews.get(i).isHelpful());
        }
        System.out.println("1.report a review  2.show review reports  3.delete review  4.editReview  5.like review  6.dislike review  0.back");
        int respond = Integer.parseInt(scanner.nextLine());//ERROR
        if (respond != 0) {
            System.out.println("Enter a review number:");
            int num = Integer.parseInt(scanner.nextLine());
            switch (respond) {
                case 1:
                    reviews.get(num - 1).reportReview(user);
                    break;
                case 2:
                    reviews.get(num - 1).showReviewReports();
                    break;
                case 3:
                    deleteReview(num - 1);
                    break;
                case 4:
                    reviews.get(num - 1).editReview();
                    break;
                case 5:
                    reviews.get(num - 1).likeReview(user);
                    break;
                case 6:
                    reviews.get(num - 1).dislikeReview(user);
                    break;
                default:
                    break;
            }
        }

    }

    void writeReview(User user) {
        System.out.println("Write your review:");
        String review = scanner.nextLine();
        System.out.println("Does your review has spoiler alert?(y/n)");
        String spoilerAlert = scanner.nextLine();
        if (spoilerAlert.compareTo("y") == 0)
            reviews.add(new Review(review, user, true, new ArrayList<>(), new ArrayList<>()));
        else
            reviews.add(new Review(review, user, false, new ArrayList<>(), new ArrayList<>()));
    }

    void reportMovie(User user) {
        System.out.println("What's your report type?\n1.spam  2.hate speech  3.false information  4.spoiler");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.println("If you want to add something else type it or press enter.");
        String moreInfo = scanner.nextLine();
        movieReports.add(new MovieReport(ReportType.getBsyValue(type), moreInfo, user, ReportStatus.NOTSEEN));
    }

    void showMovieReports() {
        System.out.println(movieReports.size());
        for (MovieReport report : movieReports) {
            System.out.println("Type: " + report.getReportType() + " Reporter: " + report.getReporter().getUserName() + "\n");
        }
    }

    void deleteReview(int indx) {
        reviews.remove(indx);
        System.out.println("Review deleted successfully.\n");
    }

    void addToList(User user) {
        boolean flaq = false;
        System.out.println("Enter your list name: ");
        String listName = scanner.nextLine();
        for (FilmList filmList : user.getFilmLists())
            if (filmList.getListName().compareTo(listName) == 0) {
                filmList.addMovieToList(this);
                flaq = true;
            }
        if (flaq)
            System.out.println("Movie successfully added.");
        else
            System.out.println("There is no such film list.");
        System.out.println("--------------------------------------------");
    }

    void addCast() {
        for (int i = 0; i < Main.getPeople().size(); i++)
            System.out.print(i + 1 + "." + Main.getPeople().get(i).getName() + " " + Main.getPeople().get(i).getLastName() + "  ");
        System.out.print("\nWhich person do you want to add as a cast? ");

        try {
            int respond = Integer.parseInt(scanner.nextLine());
            System.out.print("1.DIRECTOR  2.ACTOR  3.PRODUCER\n");
            int respond2 = Integer.parseInt(scanner.nextLine());
            ActingRecord actingRecord = new ActingRecord(Role.getByValue(respond2), Main.getPeople().get(respond - 1));
            this.actingRecords.add(actingRecord);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid input!");
            System.out.println("---------------------------------------------");
        }

    }

    public static Movie creatMovie() {
        try {
            System.out.print("name: ");
            String name = scanner.nextLine();
            for (Movie movie : Main.movies)
                if (movie.title.compareTo(name) == 0)
                    return null;
            System.out.print("poster link: ");
            String posterLink = scanner.nextLine();
            System.out.print("trailer link: ");
            String trailerLink = scanner.nextLine();
            System.out.print("movie summery: ");
            String plotSummery = scanner.nextLine();
            System.out.print("movie genre: 1.ACTION 2.COMEDY 3.DRAMA 4.SCIENCE_FICTION 5.FAMILY 6.BIOGRAPHY 7.ROMANCE 8.ANIMATION");
            int respond = Integer.parseInt(scanner.nextLine());
            Genre genre = Genre.getByValue(respond);
            System.out.println("release year: ");
            Date date = new Date(Integer.parseInt(scanner.nextLine()));
            return new Movie(name, posterLink, trailerLink, plotSummery, genre, date);
        } catch (Exception e) {
            System.out.println("invalid input");
            return null;
        }
    }

    public static Movie findMovie() {
        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        for (int i = 0; i < Main.movies.size(); i++) {
            if (Main.movies.get(i).title.compareTo(name) == 0)
                return Main.movies.get(i);
        }
        return null;
    }

    public void editMovie() {
        System.out.println("Which property do you want to edit ?");
        System.out.println("1.title  2.poster link  3.trailer link  4.summery  5.genre  6.release year");
        int respond = Integer.parseInt(scanner.nextLine());
        switch (respond) {
            case 1:
                System.out.print("name: ");
                String name = scanner.nextLine();
                for (Movie movie : Main.movies)
                    if (movie.title.compareTo(name) == 0)
                        break;
                this.title = name;
                return;
            case 2:
                System.out.print("poster link: ");
                this.posterLink = scanner.nextLine();
                return;
            case 3:
                System.out.print("trailer link: ");
                this.trailerLink = scanner.nextLine();
                return;
            case 4:
                System.out.print("movie summery: ");
                this.plotSummery = scanner.nextLine();
                return;
            case 5:
                System.out.print("movie genre: 1.ACTION 2.COMEDY 3.DRAMA 4.SCIENCE_FICTION 5.FAMILY 6.BIOGRAPHY 7.ROMANCE 8.ANIMATION");
                int genreRespond = Integer.parseInt(scanner.nextLine());
                this.genre = Genre.getByValue(genreRespond);
                return;
            case 6:
                System.out.println("release year: ");
                this.releaseDate = new Date(Integer.parseInt(scanner.nextLine()));
        }
    }

    private Movie(Movie movie) {
        this.title = movie.title;
        this.posterLink = movie.posterLink;
        this.trailerLink = movie.trailerLink;
        this.plotSummery = movie.plotSummery;
        this.genre = movie.genre;
        this.releaseDate = movie.releaseDate;
        this.actingRecords = movie.actingRecords;
        this.reviews = movie.reviews;
        this.rates = movie.rates;
        this.movieReports = movie.movieReports;
    }

    public void editMovieByEditor() {
        Movie movie = new Movie(this);
        movie.editMovie();
        HashMap<Movie, Movie> editedMovie = new HashMap<>();
        Main.editeSuggestions.put(this, movie);
    }

    public static Movie findMovie(String title) {
        for (Movie movie : Main.movies)
            if (movie.title.compareTo(title) == 0)
                return movie;
        return null;
    }

    public static void showMovieSuggestion() {
        for (Movie movie : Main.editeSuggestions.keySet())
            System.out.println(movie.title);
        System.out.print("Enter movie name to check the suggestion: ");
        String movieName = scanner.nextLine();
        Movie movie = findMovie(movieName);
        if (movie != null) {
            try {
                Movie editedMovie = Main.editeSuggestions.get(movie);
                System.out.println(editedMovie.toString() + "\n");
                System.out.println(movie.toString() + "\n");
                System.out.println("Do you want to apply changes?(y/n)");
                String respond = scanner.nextLine();
                if (respond.compareTo("y") == 0) {
                    Main.movies.set(Main.movies.indexOf(movie), editedMovie);
                    System.out.println("Successfully changed");
                } else
                    return;
            } catch (Exception e) {
                return;
            }
        }
    }

    public static void searchMovie(User user) {
        System.out.println("1.search movie title  2.categories movies by genre");
        int respond = Integer.parseInt(scanner.nextLine());
        switch (respond) {
            case 1:
                System.out.println("Enter movie title: ");
                String name = scanner.nextLine();
                for (Movie movie : Main.movies)
                    if (movie.title.compareTo(name) == 0) {
                        movie.show(user);
                        return;
                    }
                System.out.println("There is no such movie.");
                break;
            case 2:
                System.out.println("1.ACTION 2.COMEDY 3.DRAMA 4.SCIENCE_FICTION 5.FAMILY 6.BIOGRAPHY 7.ROMANCE 8.ANIMATION");
                int genreRespond = Integer.parseInt(scanner.nextLine());
                Genre genre = Genre.getByValue(genreRespond);
                showMovieByGenre(genre,user);
        }
    }

    public static void showMovieByGenre(Genre genre, User user) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (Movie movie : Main.movies) {
            if (movie.getGenre().compareTo(genre) == 0)
                movieArrayList.add(movie);
        }
        for (int i = 0; i < movieArrayList.size(); i++)
            System.out.println(i + 1 + ". " + movieArrayList.get(i).getTitle());
        int movieRespond = Integer.parseInt(scanner.nextLine());
        movieArrayList.get(movieRespond - 1).show(user);
    }

    private int evaluateRates() {
        if (rates.size() == 0)
            return 0;
        int finalRate = 0;
        for (Rate rate : rates) {
            finalRate += rate.getStar().getValue();
        }
        return finalRate;
    }

    public static void showTopRatedMovies(User user) {
        ArrayList<Movie> movies = new ArrayList<>(Main.movies);
        movies.sort((Comparator.comparingInt(Movie::evaluateRates)));
        Collections.reverse(movies);
        for (int i = 0; i < movies.size(); i++) {
            System.out.print(i + 1 + "." + movies.get(i).getTitle() + " " + movies.get(i).evaluateRates() + "    ");
        }
        System.out.println("0.back");
        int respond = Integer.parseInt(scanner.nextLine());
        if (respond == 0)
            return;
        try {
            movies.get(respond - 1).show(user);
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    public static void recommendationEngine(User user) {
        ArrayList<Integer> resultCount = new ArrayList<>();
        Integer temp;
        for (int i = 0; i < Genre.values().length; i++)
            resultCount.add(0);
        if (user.getFilmLists().size() != 0) {
            for (FilmList list : user.getFilmLists()) {
                for (Movie movie : list.getMovies()) {
                    temp = resultCount.get(movie.getGenre().getValue() - 1);
                    resultCount.set(movie.getGenre().getValue() - 1, temp + 1);
                }
            }
        }
        if (user.getRates().size() != 0) {
            for (Rate rate : user.getRates()) {
                if (rate.getStar().getValue() > 7) {
                    temp = resultCount.get(rate.getMovie().getGenre().getValue() - 1);
                    resultCount.set(rate.getMovie().getGenre().getValue() - 1, temp + 1);
                }
            }
        }
        int max = resultCount.get(0);
        int indx = 0;
        for (int i = 1; i < resultCount.size(); i++) {
            if (resultCount.get(i) > max) {
                max = resultCount.get(i);
                indx = i;
            }
        }
        if (resultCount.get(indx) != 0)
            showMovieByGenre(Genre.getByValue(indx + 1),user);
        else
            showTopRatedMovies(user);
    }

    @Override
    public String toString() {
        System.out.println(this.title);
        System.out.println(averageRate());
        System.out.println("genre: " + this.genre);
        System.out.println("release year: " + this.releaseDate.toString());
        System.out.println(this.plotSummery);
        System.out.print("Cast: ");
        return null;
    }
}
