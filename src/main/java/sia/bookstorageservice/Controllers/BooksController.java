package sia.bookstorageservice.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sia.bookstorageservice.DAO.BooksDAO;
import sia.bookstorageservice.Models.Books;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class BooksController {

    @Autowired
    public final BooksDAO booksDAO;

    public BooksController(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @GetMapping("/books")
    public List<Books>getAllBooks() throws SQLException {
        return booksDAO.index();
    }

    @GetMapping("books/{ID}")
    public Books getBook(@PathVariable("ID") int ID) throws SQLException {
        return booksDAO.show(ID);
    }

    @PostMapping("/books")
    public Books createBook(@RequestBody Books books) throws SQLException {
        booksDAO.save(books);
        return books;
    }

    @PatchMapping("/books/{ID}")
    public Books updateBook(@RequestBody Books newBooks, @PathVariable("ID") int id) throws SQLException {
        booksDAO.update(id, newBooks);
        return booksDAO.show(id);
    }

    @DeleteMapping("/book/{ID}")
    public void deleteBook(@PathVariable("ID") int id) throws SQLException {
        booksDAO.delete(id);
    }
}
