package sia.bookstorageservice.DAO;

import org.springframework.stereotype.Component;
import sia.bookstorageservice.Models.Books;
import sia.bookstorageservice.Models.Genre;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksDAO {

    int BOOKS_COUNT;
    List<Books> shelf = new ArrayList<>();

    public List<Books> index() {
        return shelf;
    }

    public Books show(int ID) {
    return shelf.stream().filter(book -> book.getID() == ID).findAny().orElse(null);
    }

    public void save(Books books) {
        books.setID(++BOOKS_COUNT);
        shelf.add(books);
    }

    public void delete(int ID) {
        shelf.removeIf(el -> el.getID() == ID);
    }

    public void update(int ID, Books otherbooks) {
        Books book = show(ID);

        book.setISBN(otherbooks.getISBN());
        book.setAuthor(otherbooks.getAuthor());
        book.setDescription(otherbooks.getDescription());
        book.setYear(otherbooks.getYear());
        book.setGenre(otherbooks.getGenre());
        book.setPublisher(otherbooks.getPublisher());
        book.setTitle(otherbooks.getTitle());
    }
}
