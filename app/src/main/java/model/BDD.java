package model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;

/**
 * BDD class is the link with Firebase Database
 * We can use it to ask request from there
 */
public class BDD {
    private FirebaseFirestore db;
    private HashMap <String, Book> books;
    private static User user;

    public BDD(FirebaseFirestore db) {
        this.db = db;
        this.books = new HashMap<>();
        user = new User();
    }

    public void setUser(final String email, final Callback<User> callback){
        db.collection("User").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    BDD.setUser(email,task.getResult().getString("login"));
                    Log.d("USER LOGIN",task.getResult().getString("login"));
                    callback.OnCallback(user);
                }
            }
        });
    }
    public static void setUser(String email,String login){
        user.setLogin(login);
        user.setEmail(email);
    }

    public static User getUser() {
        return user;
    }

    public void getAllBooks(){
        Log.d("USER FIREBASE","Passé");
        db.collection("Book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("FIREBASE DOCUMENTS",task.getResult().getDocuments().toString());

                    for(QueryDocumentSnapshot doc : task.getResult()){
                        Book book = new Book(doc.getString("title"),doc.getString("author"),
                                doc.getString("description"),doc.getString("type"));
                        books.put(doc.getId(),book);
                    }
                    Log.d("FIREBASE FIREBASE",books.toString());
                }else{
                    Log.d("Error getting documents: ", task.getException().toString());
                }
            }
        });
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }

    /**
     *
     */
    public void getLibrary(){
        Log.d("USER Library",user.getEmail());
        db.collection("User").document(user.getEmail()).collection("Library")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("USER Library","On complete");
                        if (task.isSuccessful()) {
                            Log.d("USER Library","Successfull");
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                Log.d("USER Library","create Library");
                                Library library = new Library(books.get(doc.getString("idBook")),doc.getLong("grade"),
                                        doc.getString("comment"), doc.getBoolean("bookmark"));
                                user.putLibrary(doc.getString("idBook"),(library));
                            }
                        }
                        Log.d("USER Library",user.getMyLib().toString());
                    }
                });

    }

}
