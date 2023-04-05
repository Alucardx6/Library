package com.example.library_train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class WantToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        ImageView nothingFound = findViewById(R.id.imgNothingFound);
        ConstraintLayout consLayout = findViewById(R.id.consLayout);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this, "wantToRead");
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());

        if (recyclerView.getAdapter().getItemCount() == 0) {
            nothingFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            Snackbar snackbar = Snackbar.make(consLayout, "You dont have any book here!", BaseTransientBottomBar.LENGTH_INDEFINITE);
            snackbar.setAction("Okay!", view -> snackbar.dismiss());
            snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}