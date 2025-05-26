package sia.bookstorageservice.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sia.bookstorageservice.DAO.BooksDAO;
import sia.bookstorageservice.Models.Books;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    @Mock
    private BooksDAO booksDAO;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() throws SQLException {
        Books book1 = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");
        Books book2 = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");
        List<Books> expectedBooks = Arrays.asList(book1, book2);

        when(booksDAO.index()).thenReturn(expectedBooks);

        List<Books> actualBooks = booksController.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
        verify(booksDAO, times(1)).index();
    }

    @Test
    void getBook_ShouldReturnBook() throws SQLException {
        int bookId = 1;
        Books expectedBook = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");

        when(booksDAO.show(bookId)).thenReturn(expectedBook);

        Books actualBook = booksController.getBook(bookId);

        assertEquals(expectedBook, actualBook);
        verify(booksDAO, times(1)).show(bookId);
    }

    @Test
    void createBook_ShouldSaveAndReturnBook() throws SQLException {
        Books newBook = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");
        Books savedBook = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");

        when(booksDAO.save(any(Books.class))).thenReturn(savedBook);

        Books result = booksController.createBook(newBook);

        assertEquals(savedBook, result);
        verify(booksDAO, times(1)).save(newBook);
    }

    @Test
    void updateBook_ShouldUpdateAndReturnBook() throws SQLException {
        int bookId = 1;
        Books updatedBook = new Books(5000, "123456789", "Title", "Author", 2025, "genre", "publisher", "description");

        when(booksDAO.update(bookId, updatedBook)).thenReturn(updatedBook);
        when(booksDAO.show(bookId)).thenReturn(updatedBook);

        Books result = booksController.updateBook(updatedBook, bookId);

        assertEquals(updatedBook, result);
        verify(booksDAO, times(1)).update(bookId, updatedBook);
        verify(booksDAO, times(1)).show(bookId);
    }

    @Test
    void deleteBook_ShouldCallDeleteMethod() throws SQLException {
        int bookId = 1;
        doNothing().when(booksDAO).delete(bookId);

        booksController.deleteBook(bookId);

        verify(booksDAO, times(1)).delete(bookId);
    }

    @Test
    void getAllBooks_ShouldThrowExceptionWhenDatabaseFails() throws SQLException {
        when(booksDAO.index()).thenThrow(new SQLException("Database error"));

        assertThrows(SQLException.class, () -> booksController.getAllBooks());
        verify(booksDAO, times(1)).index();
    }
}