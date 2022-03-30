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
import hh.palvelinohjelmoiti.bookstore.domain.User;
import hh.palvelinohjelmoiti.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository grepository,
			UserRepository rerepository) {
		return (args) -> {

			Category c1 = new Category("Draama");
			Category c2 = new Category("Tietokirjallisuus");
			Category c3 = new Category("Runo");

			grepository.save(c1);
			grepository.save(c2);
			grepository.save(c3);

			Book b1 = new Book("Hyvän Historia", "Rutger Bergman", 2019, "978-952-300-676-8", 15.99,
					grepository.findByName("Runo").get(0));
			Book b2 = new Book("The Tipping Point", "Malcom Gladwell", 2013, "978-0-349-11346-3", 12.99,
					grepository.findByName("Tietokirjallisuus").get(0));
			Book b3 = new Book("Luonnon Voimat", "Jane Harper", 2016, "978-952-442-741-6", 12.90,
					grepository.findByName("Draama").get(0));

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

			User user1 = new User("user", "$2a$10$6rboTwIjPPKUjkqONZgS1u6kHVtzNPpYYk5kFzJb.EMbyUfbJ9Tbu", "USER");
			User user2 = new User("admin", "$2a$10$Acjj5KO/SnKe6bi/p.GDCub3CFPROvzfroUs6fFmz9X4cicnWOz1u", "ADMIN");

			rerepository.save(user1);
			rerepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

				for (Category category : grepository.findAll()) {
					log.info(category.toString());
				}

			}
			;
		};
	}

}
