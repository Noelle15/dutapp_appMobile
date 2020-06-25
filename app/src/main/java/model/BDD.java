package model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
        db.collection("Book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(DocumentSnapshot doc : Objects.requireNonNull(task.getResult()).getDocuments()){
                        Book book = new Book(doc.getString("title"),doc.getString("author"),
                                doc.getString("description"),doc.getString("type"));
                        books.put(doc.getId(),book);
                    }
                }
            }
        });
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }
}
