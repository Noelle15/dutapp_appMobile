package model;

/**
 * Library class is the link between User and Book
 */
public class Library {
    private Book book;
    private int grade;
    private String comment;
    private boolean bookmark;

    /**
     * @param book class
     * @param grade start to 1
     * @param comment
     */
    public Library(Book book, int grade, String comment) {
        this.book = book;
        this.grade = grade;
        this.comment = comment;
    }

    /**
     * @return Book reference
     */
    public Book getBook() {
        return book;
    }

    /**
     * @return int grade, start to 1 finish to 5
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @return String comment
     */
    public String getComment() {
        return comment;
    }
}
