package model;

/**
 * Library class is the link between User and Book
 */
public class Library {
    private Book book;
    private long grade;
    private String comment;
    private boolean bookmark;

    /**
     * @param book class
     * @param grade start to 1
     * @param comment
     */
    public Library(Book book, long grade, String comment, Boolean bookmark) {
        this.book = book;
        this.grade = grade;
        this.comment = comment;
        this.bookmark = bookmark;
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
    public long getGrade() {
        return grade;
    }

    /**
     * @return String comment
     */
    public String getComment() {
        return comment;
    }
}
