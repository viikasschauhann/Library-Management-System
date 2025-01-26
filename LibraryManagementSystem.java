import java.util.*;

 class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isIssued;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isIssued = false;
    }

    // Getters and Setters
    public String getTitle(){
         return title;
    }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public boolean isIssued() { return isIssued; }
    public void issueBook() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    // @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Issued: " + isIssued;
    }
}

 class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Add book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Search for a book by title or author
    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                return book;
            }
        }
        return null;
    }

    // Issue a book to a user
    public boolean issueBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && !book.isIssued()) {
                book.issueBook();
                return true;
            }
        }
        return false;
    }

    // Return a book
    public boolean returnBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && book.isIssued()) {
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    // Display all available books
    public void displayAvailableBooks() {
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }

    // Display all issued books
    public void displayIssuedBooks() {
        for (Book book : books) {
            if (book.isIssued()) {
                System.out.println(book);
            }
        }
    }
}



public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Sample data
        library.addBook(new Book("Java Basics", "John Doe", "123456"));
        library.addBook(new Book("Data Structures", "Jane Smith", "789101"));
        
        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Issued Books");
            System.out.println("6. Search Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add Book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String ISBN = scanner.nextLine();
                    library.addBook(new Book(title, author, ISBN));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    // Issue Book
                    System.out.print("Enter ISBN to issue: ");
                    ISBN = scanner.nextLine();
                    if (library.issueBook(ISBN)) {
                        System.out.println("Book issued successfully!");
                    } else {
                        System.out.println("Book not available or already issued.");
                    }
                    break;

                case 3:
                    // Return Book
                    System.out.print("Enter ISBN to return: ");
                    ISBN = scanner.nextLine();
                    if (library.returnBook(ISBN)) {
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Book not found or not issued.");
                    }
                    break;

                case 4:
                    // Display Available Books
                    System.out.println("Available Books:");
                    library.displayAvailableBooks();
                    break;

                case 5:
                    // Display Issued Books
                    System.out.println("Issued Books:");
                    library.displayIssuedBooks();
                    break;

                case 6:
                    // Search Book
                    System.out.print("Enter title or author to search: ");
                    String keyword = scanner.nextLine();
                    Book foundBook = library.searchBook(keyword);
                    if (foundBook != null) {
                        System.out.println("Book Found: " + foundBook);
                    } else {
                        System.out.println("No book found with that title/author.");
                    }
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

