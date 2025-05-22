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

@Controller
public class BooksController {

    @Autowired
    public final BooksDAO booksDAO;

    public BooksController(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @GetMapping("/")
    public String MainMenu(@ModelAttribute("newBook") Books newBook) {
        return "home";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("newBook") Books books) throws SQLException {
        booksDAO.save(books);
        return "redirect:/AllBooks";
    }

    @GetMapping("/AllBooks")
    public String index(Model model) throws SQLException {
        //Получить все книги из DAO
        model.addAttribute("shelf",booksDAO.index());
        return "AllBooks";
    }

    @GetMapping("/AllBooks/{ID}")
    public  String show(@PathVariable("ID") int ID, Model model) throws SQLException {
        model.addAttribute("book",booksDAO.show(ID));
            model.addAttribute("shelf", booksDAO);
        return "Book";
    }

    @GetMapping("/AllBooks/edit/{ID}")
    public String edit(@PathVariable("ID") int ID, Model model) throws SQLException {
        model.addAttribute("book", booksDAO.show(ID));
        return "edit";
    }

    @PatchMapping("/AllBooks/{ID}")
    public String update(@PathVariable("ID") int ID, @ModelAttribute("book") @Valid Books books, BindingResult bindingResult) throws SQLException {
        booksDAO.update(ID, books);
        return "redirect:/AllBooks/{ID}";
    }

    @DeleteMapping("/{ID}")
    public String delete(@PathVariable("ID") int ID) throws SQLException {
        booksDAO.delete(ID);
        return "redirect:/AllBooks";
    }
}
