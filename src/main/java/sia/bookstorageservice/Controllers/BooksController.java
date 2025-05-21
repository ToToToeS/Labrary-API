package sia.bookstorageservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.bookstorageservice.DAO.BooksDAO;
import sia.bookstorageservice.Models.Books;

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

    @PostMapping()
    public String create(@ModelAttribute("newBook") Books books) {
        booksDAO.save(books);
        return "redirect:/AllBooks";
    }

    @GetMapping("/AllBooks")
    public String index(Model model) {
        //Получить все книги из DAO
        model.addAttribute("shelf",booksDAO.index());
        return "AllBooks";
    }

    @GetMapping("/AllBooks/{ID}")
    public  String show(@PathVariable("ID") int ID, Model model) {
        model.addAttribute("book",booksDAO.show(ID));
        model.addAttribute("shelf", booksDAO);
        return "Book";
    }

    @PostMapping("/AllBooks/{book}")
    public  String showID(@PathVariable("book") Books book, Model model) {
        model.addAttribute("book",booksDAO.show(book.getID()));
        model.addAttribute("shelf", booksDAO);
        return "Book";
    }

    @GetMapping("/AllBooks/edit/{ID}")
    public String edit(@PathVariable("ID") int ID, Model model) {
        model.addAttribute("book", booksDAO.show(ID));
        return "edit";
    }

    @PatchMapping("/AllBooks/{ID}")
    public String update(@PathVariable("ID") int ID, @ModelAttribute("book") Books books) {
        booksDAO.update(ID, books);
        return "redirect:/AllBooks/{ID}";
    }

    @DeleteMapping("/{ID}")
    public String delete(@PathVariable("ID") int ID) {
        booksDAO.delete(ID);
        return "redirect:/AllBooks";
    }
}
