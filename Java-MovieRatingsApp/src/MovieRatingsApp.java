import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MovieRatingsApp {
	private static List<User> users = new ArrayList<>();
	private static List<Movie> movies = new ArrayList<>();
	private static User currentUser;
	
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        if (currentUser == null) {
	        	System.out.println();
	        	System.out.println(ColorPrinter.colorTextCyan("*===================*"));
	            System.out.println(ColorPrinter.colorTextCyan("| Movie Ratings App |"));
	            System.out.println(ColorPrinter.colorTextCyan("|   1. Register     |"));
	            System.out.println(ColorPrinter.colorTextCyan("|   2. Login        |"));
	            System.out.println(ColorPrinter.colorTextCyan("|   3. Exit         |"));
	            System.out.println(ColorPrinter.colorTextCyan("*===================*"));
	            System.out.println();
	            System.out.print(ColorPrinter.colorTextGreen("Select an option: "));
	            
	
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	
	            switch (choice) {
	                case 1:
	                    registerUser(scanner);
	                    break;
	                case 2:
	                    loginUser(scanner);
	                    break;
	                case 3:
	                    System.out.println(ColorPrinter.colorTextYellow("Exiting the application."));
	                    System.exit(0);
	                default:
	                    System.out.println(ColorPrinter.colorTextRed("Invalid choice. Please try again."));
	            }
	        } else {
	        	System.out.println();
	            System.out.println(ColorPrinter.colorTextCyan("Logged in as: " + currentUser.getEmail()));
	        	System.out.println(ColorPrinter.colorTextCyan("*=============================*"));
	            System.out.println(ColorPrinter.colorTextCyan("|  1. View Movies and Ratings |"));
	            System.out.println(ColorPrinter.colorTextCyan("|  2. Rate a Movie            |"));
	            System.out.println(ColorPrinter.colorTextCyan("|  3. Add a Movie             |"));
	            System.out.println(ColorPrinter.colorTextCyan("|  4. Logout                  |"));
	            System.out.println(ColorPrinter.colorTextCyan("*=============================*"));
	            System.out.println();
	            System.out.print(ColorPrinter.colorTextGreen("Select an option: "));
	
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            System.out.println();
	            
	            switch (choice) {
	                case 1:
	                    viewMovies();
	                    break;
	                case 2:
	                    rateMovie(scanner);
	                    break;
	                case 3:
	                    addMovie(scanner);
	                    break;
	                case 4:
	                    currentUser = null;
	                    System.out.println(ColorPrinter.colorTextYellow("Logged out."));
	                    break;
	                default:
	                    System.out.println(ColorPrinter.colorTextRed("Invalid choice. Please try again."));
	            }
	        }
	    }
}

    private static void registerUser(Scanner scanner) {
        System.out.print(ColorPrinter.colorTextGreen("Enter your email: "));
        String email = scanner.nextLine();

        if (isValidEmail(email)) {
            System.out.print(ColorPrinter.colorTextGreen("Enter a password: "));
            String password = scanner.nextLine();

            System.out.print(ColorPrinter.colorTextGreen("Confirm password: "));
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                users.add(new User(email, password));
                System.out.println(ColorPrinter.colorTextYellow("Registration successful."));
            } else {
                System.out.println(ColorPrinter.colorTextRed("Passwords do not match. Registration failed."));
            }
        } else {
            System.out.println(ColorPrinter.colorTextRed("Invalid email format. Registration failed."));
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static void loginUser(Scanner scanner) {
        System.out.print(ColorPrinter.colorTextGreen("Enter your email: "));
        String email = scanner.nextLine();
        System.out.print(ColorPrinter.colorTextGreen("Enter your password: "));
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            	currentUser = user;
                System.out.println(ColorPrinter.colorTextYellow("Login successful."));
                return;
            }
        }
        System.out.println(ColorPrinter.colorTextRed("Invalid email or password. Login failed."));
    }

    private static void viewMovies() {
        if (movies.isEmpty()) {
            System.out.println(ColorPrinter.colorTextRed("No movies available."));
            return;
        }

        System.out.println(ColorPrinter.colorTextCyan("List of Movies:"));
        for (Movie movie : movies) {
            System.out.printf(ColorPrinter.colorTextCyan("%s - Average Rating: %.2f (%d ratings)%n"), movie.getTitle(),
                    movie.getAverageRating(), movie.getnumRatings());
        }
    }
    

    private static void addMovie(Scanner scanner) {
        System.out.print(ColorPrinter.colorTextGreen("Enter the title of the movie: "));
        String title = scanner.nextLine();

        Movie newMovie = new Movie(title);
        movies.add(newMovie);

        System.out.println(ColorPrinter.colorTextYellow("Movie added successfully."));
    }

    private static void rateMovie(Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println(ColorPrinter.colorTextRed("No users registered. Please register first."));
            return;
        }

        System.out.print(ColorPrinter.colorTextGreen("Enter your email: "));
        String email = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.print(ColorPrinter.colorTextGreen("Enter the movie title: "));
                String movieTitle = scanner.nextLine();

                for (Movie movie : movies) {
                    if (movie.getTitle().equals(movieTitle)) {
                        System.out.print(ColorPrinter.colorTextGreen("Enter your rating (0 - 5): "));
                        int rating = scanner.nextInt();

                        if (rating >= 0 && rating <= 5) {
                            movie.rateMovie(rating);
                            System.out.println(ColorPrinter.colorTextYellow("Rating successful."));
                        } else {
                            System.out.println(ColorPrinter.colorTextRed("Invalid rating. Please enter a value between 0 and 5."));
                        }
                        return;
                    }
                }
                System.out.println(ColorPrinter.colorTextRed("Movie not found."));
                return;
            }
        }
        System.out.println(ColorPrinter.colorTextRed("User not found. Please register or login first."));
    }
}