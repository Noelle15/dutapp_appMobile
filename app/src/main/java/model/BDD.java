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

    public BDD(FirebaseFirestore db) {
        this.db = db;
        this.books = new HashMap<>();
    }


    public void getAllBooks(){
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
