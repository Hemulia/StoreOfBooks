package hh.palvelinohjelmoiti.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmoiti.bookstore.domain.Book;
import hh.palvelinohjelmoiti.bookstore.domain.BookRepository;
import hh.palvelinohjelmoiti.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository grepository;

	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {

		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public @ResponseBody Book saveBookRest(@RequestBody Book book) {
		return repository.save(book);
	}

	@RequestMapping(value = { "/add" })
	public String addBook(Model model) {

		model.addAttribute("book", new Book());
		model.addAttribute("categories", grepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET })
	public String update(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		return "updatebook";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)

	public String updatesave(Book book) {

		repository.save(book);

		return "redirect:/booklist";

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

}
