package sia.bookstorageservice.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

public class Books {
    int ID;
    @NotEmpty(message = "ISBN should not be empty")
    @Size(min = 1, max = 30, message = "Size ISBN not correct")
    private String ISBN;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 9, max = 9, message = "Size title not correct")
    private String title;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Size title not correct")
    private String author;

    @Min(value = 0, message = "Age should be greater then 0")
    private int year;

    @NotEmpty(message = "Genre should not be empty")
    @Size(min = 1, max = 30, message = "Genre title not correct")
    private String genre;

    @NotEmpty(message = "Publisher should not be empty")
    @Size(min = 1, max = 30, message = "Publisher title not correct")
    private String publisher;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 1, max = 500, message = "Description title not correct")
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return ID == books.ID && year == books.year && Objects.equals(ISBN, books.ISBN) && Objects.equals(title, books.title) && Objects.equals(author, books.author) && Objects.equals(genre, books.genre) && Objects.equals(publisher, books.publisher) && Objects.equals(description, books.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, ISBN, title, author, year, genre, publisher, description);
    }
}

