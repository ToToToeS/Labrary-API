package sia.bookstorageservice.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sia.bookstorageservice.Models.Books;

import java.sql.*;
import java.util.List;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Books> index() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM books",new BeanPropertyRowMapper<>(Books.class));
    }

    public Books show(int ID) throws SQLException {
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?",new Object[]{ID},
                new BeanPropertyRowMapper<>(Books.class)).stream().findAny().orElse(null);
    }

    public Books save(Books book) throws SQLException {
       jdbcTemplate.update("INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?, ?)",getId(book), book.getISBN(), book.getTitle(),
               book.getAuthor(), book.getYear(),book.getGenre(),  book.getPublisher(), book.getDescription());
       return book;
    }

    public void delete(int ID) throws SQLException {
        jdbcTemplate.update("DELETE FROM books WHERE ID=?", ID);
    }


    public Books update(int ID, Books otherbooks) throws SQLException {
    jdbcTemplate.update("UPDATE books SET isbn=?, title=?, author=?, year=?, genre=?, publisher=?, description=? WHERE ID=?",otherbooks.getISBN()
            ,otherbooks.getTitle(),otherbooks.getAuthor(), otherbooks.getYear(), otherbooks.getGenre(), otherbooks.getPublisher(), otherbooks.getDescription(), ID);
    return otherbooks;
    }


    private int getId(Books books) {
        return Math.abs(books.hashCode() % 1000000000);
    }
}
