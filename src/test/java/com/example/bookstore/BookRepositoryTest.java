package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Pet Cemetary");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Stephen King");
    }
	
	@Test
    public void createNewBook() {
    	Book book = new Book("To kill a Mockingbird", "Harper Lee", 1961, 989568, 28.75, new Category("Drama"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }  
	
}
