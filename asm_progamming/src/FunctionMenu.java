import java.sql.*;
import java.util.Scanner;
public class FunctionMenu {
    protected String jdbc = "jdbc:mysql://localhost:3306/db_book_store";
    protected String username = "root";
    protected String password = "";
    protected Connection connection;

    protected FunctionMenu() {
        try {
            connection = DriverManager.getConnection(jdbc, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected int m;
    protected void showFunctionMenu() {
        System.out.println("1. Filter books by author");
        System.out.println("2. Filter books by genre");
        System.out.println("3. Sort books by publication year in descending order");
        System.out.println("4. Sort books by descending price");
        System.out.println("0. Back menu");
        System.out.print("Choose a function: ");
    }
    protected void checkM(int m, Scanner input) {
        while(m < 0 || m > 4) {
            System.out.print("Function not yet developed, choose again: ");
            m = input.nextInt();
        }
        this.m = m;
    }
    //case1-------------
    protected void filterBooksByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author);
            ResultSet query = statement.executeQuery();

            System.out.println("------ List of books by author " + author + " ------");
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", "Code", "Title", "Author", "Publication_year", "Genre", "Publisher", "Selling_price", "Page_number");
            while (query.next()) {
                String code = query.getString("book_code");
                String title = query.getString("book_title");
                String authorName = query.getString("author");
                int publicationYear = query.getInt("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                int sellingPrice = query.getInt("selling_price");
                int pageNumber = query.getInt("page_number");

                System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", code, title, authorName, publicationYear, genre, publisher, sellingPrice, pageNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //case2-------------
    protected void filterBooksByGenre(String category) {
        String sql = "SELECT * FROM books WHERE genre = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category);
            ResultSet query = statement.executeQuery();

            System.out.println("------ List of books in category: " + category + " ------");
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", "Code", "Title", "Author", "Publication_year", "Genre", "Publisher", "Selling_price", "Page_number");
            while (query.next()) {
                String code = query.getString("book_code");
                String title = query.getString("book_title");
                String author = query.getString("author");
                int publicationYear = query.getInt("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                int sellingPrice = query.getInt("selling_price");
                int pageNumber = query.getInt("page_number");

                System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", code, title, author, publicationYear, genre, publisher, sellingPrice, pageNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //case3-------------
    protected void sortBooksByPublicationYearDesc() {
        String sql = "SELECT * FROM books ORDER BY publication_year DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(sql);

            System.out.println("------ List of books sorted by publication year descending ------");
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", "Code", "Title", "Author", "Publication_year", "Genre", "Publisher", "Selling_price", "Page_number");
            while (query.next()) {
                String code = query.getString("book_code");
                String title = query.getString("book_title");
                String author = query.getString("author");
                int publicationYear = query.getInt("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                int sellingPrice = query.getInt("selling_price");
                int pageNumber = query.getInt("page_number");

                System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", code, title, author, publicationYear, genre, publisher, sellingPrice, pageNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //case4-------------
    protected void sortBooksByPriceDesc() {
        String sql = "SELECT * FROM books ORDER BY selling_price DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(sql);

            System.out.println("------ List of books sorted by price descending ------");
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", "Code", "Title", "Author", "Publication_year", "Genre", "Publisher", "Selling_price", "Page_number");
            while (query.next()) {
                String code = query.getString("book_code");
                String title = query.getString("book_title");
                String author = query.getString("author");
                int publicationYear = query.getInt("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                int sellingPrice = query.getInt("selling_price");
                int pageNumber = query.getInt("page_number");

                System.out.printf("%-5s | %-10s | %-10s | %-20s | %-15s | %-20s | %-15s | %-12s\n", code, title, author, publicationYear, genre, publisher, sellingPrice, pageNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void function(int m, Scanner input) {
        switch(m) {
            case 1:
                input.nextLine(); // Đọc dòng trống
                System.out.print("Enter the author's name: ");
                String authorName = input.nextLine();
                filterBooksByAuthor(authorName);
                break;
            case 2:
                input.nextLine(); // Đọc dòng trống
                System.out.print("Enter the genre: ");
                String category = input.nextLine();
                filterBooksByGenre(category);
                break;
            case 3:
                sortBooksByPublicationYearDesc();
                break;
            case 4:
                sortBooksByPriceDesc();
                break;
        }
        if(m != 0) {
            System.out.print("Click 1 return menu, click 0 back to main menu: ");
            m = input.nextInt();
            while(m < 0 || m > 1) {
                System.out.print("Click 1 return menu, click 0 back to main menu: ");
                m = input.nextInt();
            }
            this.m = m;
        }
    }
    protected void returnFunctionMenu(Scanner input) {
        do{
            showFunctionMenu();
            m = input.nextInt();
            checkM(m, input);
            function(m, input);
        }while(m != 0);
    }
}