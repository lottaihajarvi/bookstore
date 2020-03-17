package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Scifi"));
			
			brepository.save(new Book("The Hobbit", "J.R.R Tolkien", 1937, 9783168, 29.95, crepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Pet Semetary", "Stephen King", 1983, 4567165, 15.95, crepository.findByName("Horror").get(0)));
			
			User user1 = new User("user", "$2b$10$hOkFjRPJ/X4WWU61yfY3E.9jYC4OdsQDF1hGEEb6WzXxVVZac0Ffm", "blaablaa@hmail.com", "USER");
			User user2 = new User("admin", "$2b$10$.Aply6F9ac2sL1NW3iTLbeWfUBtkciaEnidv79ltRmirlytV05qc.", "jepjep@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
