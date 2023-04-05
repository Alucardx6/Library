package com.example.library_train;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAllBooks, btnCurrentlyReading, btnWishlist, btnAlreadyRead, btnFavorites, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyReaded);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnFavorites = findViewById(R.id.btnFavorites);
        btnAbout = findViewById(R.id.btnAbout);

        btnAllBooks.setOnClickListener(this);
        btnCurrentlyReading.setOnClickListener(this);
        btnAlreadyRead.setOnClickListener(this);
        btnWishlist.setOnClickListener(this);
        btnFavorites.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnAllBooks:
                intent = new Intent(this, AllBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAlreadyReaded:
                intent = new Intent(this, AlreadyReadBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnWishlist:
                intent = new Intent(this, WantToReadActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFavorites:
                intent = new Intent(this, FavoriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCurrentlyReading:
                intent = new Intent(this, CurrentlyReadingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAbout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Designed and Developed with Love by Sepehr at ABYX.ir\n" +
                        "Check our website for more awesome applications:");
                builder.setNegativeButton("Dismiss", (dialogInterface, i) -> {
                });

                builder.setPositiveButton("Visit", (dialogInterface, i) -> {
                    Intent intent1 = new Intent(MainActivity.this, WebsiteActivity.class);
                    intent1.putExtra("url", "https://abyx.ir/");
                    startActivity(intent1);
                });
                builder.create().show();
                break;
        }
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure, you want to exit?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> finish());
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });

        builder.create().show();
    }
}