package model;

import java.util.HashMap;

/**
 * User class
 */
public class User {
    private String login;
    private String email;
    private HashMap<String,Library> myLib;

    /**
     */
    public User() {
        this.myLib = new HashMap<>();
    }
    public void putLibrary(String idBook, Library library){
        this.myLib.put(idBook, library);
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public String getLogin(){
        return login;
    }

    public HashMap<String, Library> getMyLib() {
        return myLib;
    }
}
