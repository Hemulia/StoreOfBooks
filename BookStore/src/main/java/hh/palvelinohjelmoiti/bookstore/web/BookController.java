package hh.palvelinohjelmoiti.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.palvelinohjelmoiti.bookstore.domain.Book;

@Controller
public class BookController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Paikka Vapaana", "J.K Rowling", 2010, "9789520427406", 15.90));
		books.add(new Book("Luonnon Voimat", "Jane Harper", 2016, "5789524427416", 12.90));
		model.addAttribute("books", books);
		return "booklist";
	}

}
