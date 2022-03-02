package hh.palvelinohjelmoiti.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.palvelinohjelmoiti.bookstore.domain.Book;
import hh.palvelinohjelmoiti.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {

		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = { "/add" })
	public String addBook(Model model) {

		model.addAttribute("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@PathVariable("id") Long bookId, Book book) {
		repository.findById(bookId);
		repository.save(book);
		return "updatebook";
	}

}
