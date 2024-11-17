import java.sql.*;
import java.util.Scanner;

public class BookStore extends FunctionMenu {
    protected String jdbc = "jdbc:mysql://localhost:3306/db_book_store";
    protected String username = "root";
    protected String password = "";
    protected Connection connection;

    protected BookStore() {
        try {
            connection = DriverManager.getConnection(jdbc, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void showBookMenu() {
        System.out.println("1. Add new book");
        System.out.println("2. Update book information");
        System.out.println("3. Delete book");
        System.out.println("4. Show all books");
        System.out.println("0. Back to menu");
        System.out.print("Choose a function: ");
    }

    protected void insertBook(int code, String title, String author, int publication_year, String genre, String publisher, int selling_price, int page_number) {
        String sql = "INSERT INTO books (book_code, book_title, author, publication_year, genre, publisher, selling_price, page_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, code);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setInt(4,publication_year);
            statement.setString(5,genre);
            statement.setString(6,publisher);
            statement.setInt(7,selling_price);
            statement.setInt(8,page_number);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //case 1---------------
    protected void addBook(Scanner input) {
        System.out.print("Enter the number of books to add: ");
        int sum = input.nextInt();
        input.nextLine();
        for (int i = 0; i < sum; i++) {
            System.out.println("Information of book " + (i + 1));
            System.out.print("Book code: ");
            int code = input.nextInt();
            System.out.print("Book_title: ");
            input.nextLine();
            String title = input.nextLine();
            System.out.print("Book author: ");
            String author = input.nextLine();
            System.out.print("publication_year: ");
            int publication_year = input.nextInt();
            input.nextLine();
            System.out.print("genre: ");
            String genre = input.nextLine();
            System.out.print("publisher: ");
            String publisher = input.nextLine();
            System.out.print("selling_price: ");
            int selling_price = input.nextInt();
            System.out.print("page_number: ");
            int page_number = input.nextInt();
            insertBook(code, title, author, publication_year, genre, publisher, selling_price, page_number);
            System.out.println("Added new book successfully");
        }
    }
    //case 2
    protected void updateBook(String code, String title, String author, int publication_year, String genre, String publisher, int selling_price, int page_number) {
        String sql = "UPDATE books SET book_title = ?, author = ?, publication_year = ?, genre = ?, publisher = ?, selling_price = ?, page_number = ? WHERE book_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, publication_year);
            statement.setString(4, genre);
            statement.setString(5, publisher);
            statement.setInt(6, selling_price);
            statement.setInt(7, page_number);
            statement.setString(8, code);  // Corrected order for the WHERE clause
            statement.executeUpdate();

            System.out.println("Updated book successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //case 3
    protected void deleteBook(String code) {
        String sql = "DELETE FROM books WHERE book_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted book successfully");
            } else {
                System.out.println("Failed to delete book");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //case 4-------------
    protected void showListBook() {
        String sql = "SELECT * FROM books";
        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(sql);
            System.out.println("------List of books------");
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", "Code", "Title", "Author", "Publication_year", "Genre", "Publisher", "Selling_price", "Page_number");
            while (query.next()) {
                String code = query.getString("book_code");
                String title = query.getString("book_title");
                String author = query.getString("author");
                int publication_year = query.getInt("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                int selling_price = query.getInt("selling_price");
                int page_number = query.getInt("page_number");
                System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", code, title, author, publication_year, genre, publisher, selling_price, page_number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void book(int m, Scanner input) {
        switch (m) {
            case 1:
                addBook(input);
                break;
            case 2:
                input.nextLine(); // Consume the newline character left by previous input
                System.out.println("Enter the book code to update:");
                String updateCode = input.nextLine();
                System.out.println("Enter new book name: ");
                String newName = input.nextLine();
                System.out.println("Enter new author: ");
                String newAuthor = input.nextLine();
                System.out.print("Enter new publication_year: ");
                int newPublication_year = input.nextInt();

                input.nextLine(); // Consume the newline character left by previous input
                System.out.print("Enter new genre: ");
                String newGenre = input.nextLine();

                System.out.print("Enter new publisher: ");
                String newPublisher = input.nextLine();
                System.out.print("Enter new selling_price: ");
                int newSelling_price = input.nextInt();
                System.out.print("Enter new page_number: ");
                int newPage_number = input.nextInt();
                updateBook(updateCode, newName, newAuthor, newPublication_year, newGenre, newPublisher, newSelling_price, newPage_number);
                break;


            case 3:
                System.out.println("Enter the book code to delete:");
                input.nextLine(); // Đọc dòng trống
                String deleteCode = input.nextLine();
                deleteBook(deleteCode);
                break;
            case 4:
                showListBook();
                break;
        }
        if (m != 0) {
            System.out.print("Press 1 to return to the book menu, press 0 to go back to the main menu: ");
            m = input.nextInt();
            while (m < 0 || m > 1) {
                System.out.print("Press 1 to return to the book menu, press 0 to go back to the main menu: ");
                m = input.nextInt();
            }
            this.m = m;
        }
    }

    protected void returnBookMenu(Scanner input) {
        do {
            showBookMenu();
            m = input.nextInt();
            checkM(m, input);
            book(m, input);
        } while (m != 0);
    }
}
