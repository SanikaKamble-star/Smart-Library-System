import java.util.*;

class Book {
    String title;
    String author;
    int totalCopies;
    int availableCopies;

    public Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        } else {
            return false;
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return String.format("'%s' by %s | Available copies: %d/%d", title, author, availableCopies, totalCopies);
    }
}

public class SmartLibrarySystem {
    private static Map<String, Book> books = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Library Management System!");

        // Preload some books
        books.put("Java Programming", new Book("Java Programming", "James Gosling", 3));
        books.put("Data Structures", new Book("Data Structures", "Robert Lafore", 2));
        books.put("Operating Systems", new Book("Operating Systems", "Abraham Silberschatz", 1));

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");

            System.out.print("Enter choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> viewAllBooks();
                case 6 -> {
                    System.out.println("Thank you for using Smart Library System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter number of copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine();

        if (books.containsKey(title)) {
            System.out.println("Book already exists. Updating copies.");
            books.get(title).totalCopies += copies;
            books.get(title).availableCopies += copies;
        } else {
            books.put(title, new Book(title, author, copies));
            System.out.println("Book added successfully.");
        }
    }

    private static void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        Book book = books.get(title);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found in library.");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();

        Book book = books.get(title);
        if (book == null) {
            System.out.println("Book not available.");
            return;
        }

        if (book.borrowBook()) {
            System.out.println("You have successfully borrowed '" + title + "'.");
        } else {
            System.out.println("Sorry, no copies available right now.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        Book book = books.get(title);
        if (book == null) {
            System.out.println("This book doesn't belong to our library.");
            return;
        }

        book.returnBook();
        System.out.println("Thank you for returning '" + title + "'.");
    }

    private static void viewAllBooks() {
        System.out.println("Books available in library:");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }
}
