package com.example.library_train;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1)
            {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);

                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);


        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);

        bookImage = findViewById(R.id.imgBook);
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b: wantToReadBooks)
        {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }

        if(existInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(view -> {
                if (Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                    Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks)
        {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
                break;
            }
        }

        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(view -> {
                if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                    Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean existInfavoriteBooks = false;

        for (Book b: favoriteBooks)
        {
            if (b.getId() == book.getId()) {
                existInfavoriteBooks = true;
                break;
            }
        }

        if(existInfavoriteBooks){
            btnAddToFavorite.setEnabled(false);
        }else{
            btnAddToFavorite.setOnClickListener(view -> {
                if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)){
                    Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks)
        {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }

        if(existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(view -> {
                if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                    Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }
}