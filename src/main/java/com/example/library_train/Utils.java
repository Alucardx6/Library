package com.example.library_train;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private final SharedPreferences sharedPreferences;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T2/images/I/81WaPTDslGL.jpg",
                "A work of maddening brilliance.", "A young woman named Aomame follows a taxi driver's " +
                "enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 - \"Q\" is for ?. A world that bears a question.\n" +
                "\n" +
                "Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled.\n" +
                "\n" +
                "As Aomame's and Tengo's narratives converge over the course of this single year, we learn of the profound and tangled connections that bind them ever closer: a beautiful, dyslexic teenage girl with a unique vision; a mysterious religious cult that instigated a shoot-out with the metropolitan police; a reclusive, wealthy dowager who runs a shelter for abused women; a hideously ugly private investigator; a mild-mannered yet ruthlessly efficient bodyguard; and a peculiarly insistent television-fee collector.\n" +
                "\n" +
                "A love story, a mystery, a fantasy, a novel of self-discovery, a dystopia to rival George Orwell's, 1Q84 is Haruki Murakami's most ambitious undertaking yet: an instant best seller in his native Japan, and a tremendous feat of imagination from one of our most revered contemporary writers."));
        books.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://m.media-amazon.com/images/I/71P6eSzlNdL.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", "Camus undertakes the task of answering what he considers to be the only question of philosophy that matters: Does the realization of the meaninglessness and absurdity of life necessarily require suicide?\n" +
                "\n" +
                "He begins by describing the following absurd condition: we build our life on the hope for tomorrow, yet tomorrow brings us closer to death and is the ultimate enemy; people live their lives as if they were not aware of the certainty of death. Once stripped of its common romanticism, the world is a foreign, strange and inhuman place; true knowledge is impossible and rationality and science cannot explain the world: their stories ultimately end in meaningless abstractions, in metaphors. This is the absurd condition and \"from the moment absurdity is recognized, it becomes a passion, the most harrowing of all.\"\n" +
                "\n" +
                "It is not the world that is absurd, nor human thought: the absurd arises when the human need to understand meets the unreasonableness of the world, when the \"appetite for the absolute and for unity\" meets \"the impossibility of reducing this world to a rational and reasonable principle.\"\n" +
                "\n" +
                "He then characterizes several philosophies that describe and attempt to deal with this feeling of the absurd, by Martin Heidegger, Karl Jaspers, Lev Shestov, Søren Kierkegaard, and Edmund Husserl. All of these, he claims, commit \"philosophical suicide\" by reaching conclusions that contradict the original absurd position, either by abandoning reason and turning to God, as in the case of Kierkegaard and Shestov, or by elevating reason and ultimately arriving at ubiquitous Platonic forms and an abstract god, as in the case of Husserl."));

        books.add(new Book(3, "Vladimir", "Julia May Jonas", 256, "https://ik.imagekit.io/panmac/tr:di-placeholder_portrait_aMjPtD9YZ.jpg,tr:w-350,f-jpg,pr-true/edition/9781529080445.jpg",
                "New York Times * “Timely, whip-smart, and darkly funny.” —People (Book of the Week)",
                "“When I was a child, I loved old men, and I could tell that they also loved me.” And so we are introduced to our narrator who’s “a work of art in herself” (The Washington Post): a popular English professor whose charismatic husband at the same small liberal arts college is under investigation for his inappropriate relationships with his former students. The couple have long had a mutual understanding when it comes to their extra-marital pursuits, but with these new allegations, life has become far less comfortable for them both. And when our narrator becomes increasingly infatuated with Vladimir—a celebrated, married young novelist who’s just arrived on campus—their tinder box world comes dangerously close to exploding.\n" +
                        "\n" +
                        "“Timely, whip-smart, and darkly funny” (People), Vladimir takes us into charged territory, where the boundaries of morality bump up against the impulses of the human heart. This edgy, uncommonly assured debut perfectly captures the personal and political minefield of our current moment, exposing the nuances and the grey area between power and desire."));

        books.add(new Book(4, "Novel Trust", "Hernan Diaz", 416, "https://m.media-amazon.com/images/I/4113sPK7UFL._SX317_BO1,204,203,200_.jpg",
                "“Trust,” by Hernan Diaz, examines the human costs of wealth in a novel that keeps revising itself.",
                "“The secret of all great fortunes, when there’s no obvious explanation for them, is always some forgotten crime.” These words come from “Le Père Goriot” (1835), Honoré de Balzac’s great novel about the mysteries of Paris, and in English they’re most often quoted without the qualifying phrase in the middle. After all, what counts as an obvious explanation? The ownership of land? Balzac’s society might have thought so; now we ask how that land was first acquired. Innovation? Maybe, but take a closer look at the human costs and natural resources needed to bring ideas to market.\n" +
                        "\n" +
                        "Of course, we also have to consider who’s speaking. Balzac puts those words in the mouth of a master criminal, and then adds a final twist. The crime has been “forgotten, mind you, because it’s been properly handled,” the bodies neatly disposed of and the bank notes washed clean.\n" +
                        "\n" +
                        "That’s the hope anyway — or the fear, depending on whose side you’re on — and that’s the world Hernan Diaz explores in “Trust,” his intricate, cunning and consistently surprising second novel. Trust: both a moral quality and a financial arrangement, as though virtue and money were synonymous. The term also has a literary bearing: Can we trust this tale? Is this narrator reliable? Diaz breaks the book into four sections, and the title of the first one is similarly ambiguous, echoing that of the whole work. It’s called “Bonds” and presented as a novel written in the third person by someone named Harold Vanner. We won’t know who he is until the later sections, many pages after “Bonds” is over; let’s call him a forgotten novelist, whose case has been properly handled.\n" +
                        "\n" +
                        "“Bonds” begins comfortably, its assured prose the appropriate instrument for its tale of a well-upholstered life: “Because he had enjoyed almost every advantage since birth, one of the few privileges denied to Benjamin Rask was that of a heroic rise.” We are in old-money New York in the last years of the 19th century, and though this world will remind readers of Edith Wharton, Diaz has a much keener interest in just how that money works. Industrialists have replaced merchants as the city’s rulers, and will in turn be replaced by financiers. Rask comes from a family of tobacco traders, but he hates “the primitive sucking and puffing” that a good cigar requires. As soon as his father dies, he sells out and begins to play the market: to play it not as one plays golf or baseball but as a musician plays an instrument, caressing its strings, lightly pressing this key or that."));

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));

        editor.apply();
    }

    public static Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }

        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.apply();

                return true;
            }
        }

        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.apply();

                return true;
            }
        }

        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.apply();

                return true;
            }
        }

        return false;
    }

    public boolean addToFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.apply();

                return true;
            }
        }

        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {

            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.apply();

                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.apply();

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.apply();

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.apply();

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
