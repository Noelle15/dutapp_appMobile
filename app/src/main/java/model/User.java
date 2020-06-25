package model;

import java.util.HashMap;

/**
 * User class
 */
public class User {
    private String login;
    private HashMap<String,Library> myLib;

    /**
     * @param login
     * @param myLib HashMap<String,Library>, with String id of Book
     */
    public User(String login, HashMap<String,Library> myLib) {
        this.login = login;
        this.myLib = myLib;
    }
}
