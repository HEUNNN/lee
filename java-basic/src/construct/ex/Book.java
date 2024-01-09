package construct.ex;

public class Book {
    String title;
    String author;
    int page;

    public Book() {
        this.title = "";
        this.author = "";
        this.page = 0;
    }

    public Book(String title, String author, int page) {
        this.title = title;
        this.author = author;
        this.page = page;
    }

    public Book(String title, String author) {
        this(title, author, 0);
    }


    public void displayInfo() {
        System.out.println("제목: " + title + ", 저자: " + author + ", 페이지 수: " + page);
    }
}
