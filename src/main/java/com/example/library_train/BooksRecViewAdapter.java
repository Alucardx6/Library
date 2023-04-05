package com.example.library_train;

import static com.example.library_train.BookActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {

    private static final String TAG = "BooksRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private final Context context;
    private final String parentActivity;

    public BooksRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder.txtName.setText(books.get(position).getName());

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, books.get(holder.getAdapterPosition()).getId());
            context.startActivity(intent);
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDesc.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyRead") || parentActivity.equals("wantToRead") || parentActivity.equals("currentlyReading") || parentActivity.equals("favoriteBooks")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(view -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete " + books.get(holder.getAdapterPosition()).getName() + " ?");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (parentActivity.equals("alreadyRead")) {
                            if (Utils.getInstance(context).removeFromAlreadyRead(books.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (parentActivity.equals("wantToRead")) {
                            if (Utils.getInstance(context).removeFromWantToRead(books.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (parentActivity.equals("currentlyReading")) {
                            if (Utils.getInstance(context).removeFromCurrentlyReading(books.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (parentActivity.equals("favoriteBooks")) {
                            if (Utils.getInstance(context).removeFromFavoriteBooks(books.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    builder.setNegativeButton("No", (dialogInterface, i) -> {
                    });

                    builder.create().show();
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtAuthor, txtDesc, btnDelete;
        private ImageView downArrow, upArrow, imgBook;
        private RelativeLayout expandedRelLayout;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtBookName);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDesc = itemView.findViewById(R.id.txtShortDesc);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            imgBook = itemView.findViewById(R.id.imgBook);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);

            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            parent = itemView.findViewById(R.id.parent);

            downArrow.setOnClickListener(view -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            upArrow.setOnClickListener(view -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
