package hh.palvelinohjelmoiti.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmoiti.bookstore.domain.Book;
import hh.palvelinohjelmoiti.bookstore.domain.BookRepository;
import hh.palvelinohjelmoiti.bookstore.domain.Category;
import hh.palvelinohjelmoiti.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository grepository) {
		return (args) -> {
			Book b1 = new Book("Hyvän Historia", "Rutger Bergman", 2019, "978-952-300-676-8", 15.99);
			Book b2 = new Book("The Tipping Point", "Malcom Gladwell", 2013, "978-0-349-11346-3", 12.99);
			Book b3 = new Book("Luonnon Voimat", "Jane Harper", 2016, "978-952-442-741-6", 12.90);

			Category c1 = new Category("Scifi");
			Category c2 = new Category("Kauhu");
			Category c3 = new Category("Runo");

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

			grepository.save(c1);
			grepository.save(c2);
			grepository.save(c3);

			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

			for (Category category : grepository.findAll()) {
				log.info(category.toString());
			}

		};
	}

}
