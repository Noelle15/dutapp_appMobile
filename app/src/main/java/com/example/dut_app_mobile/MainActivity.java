package com.example.dut_app_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dut_app_mobile.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import model.BDD;
import model.Callback;
import model.User;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private BDD bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_logout = findViewById(R.id.btn_logout);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_livre, R.id.navigation_decouvrir, R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);

        // BDD class
        bdd = new BDD(FirebaseFirestore.getInstance());
        bdd.getAllBooks();

        //Connexion
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        if(mAuth.getCurrentUser()== null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            bdd.setUser( mAuth.getCurrentUser().getEmail(), new Callback<User>() {
                @Override
                public void OnCallback(User user) {
                    bdd.getLibrary();
                }
            });

        }

        Log.d("Map books",bdd.getBooks().toString());
        Log.d("MyLib books",bdd.getUser().getMyLib().toString());

        //logOut
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
