package sia.bookstorageservice.Models;

import org.springframework.validation.annotation.Validated;

public class Books {
    int ID;
    private String ISBN;
    private String title;
    private String author;
    private int year;
    private String genre;
    private String publisher;
    private String description;

    public Books() {}


    public Books(int ID, String ISBN, String title, String author, int year, String genre, String publisher, String description) {
        this.ID = ID;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setID(int ID) { this.ID = ID; }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public int getID() {
        return ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() { return genre;}

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
}

