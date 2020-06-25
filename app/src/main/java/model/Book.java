package model;

/**
 * Book class
 * @author : Gaelle
 */
public class Book {
    private String title;
    private String author;
    private String description;
    private String type;

    /**
     * Constructor of a Book
     * @param title
     * @param author
     * @param description
     * @param type
     */
    public Book(String title, String author, String description, String type){
        this.title = title;
        this.author = author;
        this.description = description;
        this.type = type;
    }

    /**
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return author
     */
    public String getAuthor(){
        return author;
    }

    /**
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return type
     */
    public String getType(){
        return type;
    }
}
