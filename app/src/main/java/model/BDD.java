package model;

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

    public BDD(FirebaseFirestore db) {
        this.db = FirebaseFirestore.getInstance();
        this.books = new HashMap<>();
    }


    public void getAllBooks(){
        Log.d("USER FIREBASE","Passé");
        db.collection("Book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("USER FIREBASE","Passé2");

                    Log.d("USER FIREBASE",task.getResult().toString());
                    Log.d("USER FIREBASE",task.getResult().getDocumentChanges().toString());
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        Book book = new Book(doc.getString("title"),doc.getString("author"),
                                doc.getString("description"),doc.getString("type"));
                        Log.d("USER FIREBASE","Passé3");
                        books.put(doc.getId(),book);
                    }
                }
            }
        });
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }

    /**
     * @param login of User
     */
    public void getLibrary(String login){
        db.collection("Book").whereEqualTo("idUser", login)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(DocumentSnapshot doc : Objects.requireNonNull(task.getResult()).getDocuments()){
                                Library library = new Library(books.get(doc.getString("idBook")),doc.getLong("grade"),
                                        doc.getString("comment"), doc.getBoolean("bookmark"));
                            }
                        }
                    }
                });
    }

}
