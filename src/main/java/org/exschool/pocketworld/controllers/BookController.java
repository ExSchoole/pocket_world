package org.exschool.pocketworld.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.exschool.pocketworld.model.Book;
import org.exschool.pocketworld.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/")
	public String showIndexPage(Model model){
		return "index";
	}
	
	@RequestMapping(value = "/list")
	public String listChildren(Model model){
		logger.info("/list is invoked on a controller");
		List<Book> books = bookService.allBooks();
		model.addAttribute("books",books);
		return "books";
	}
	
	@RequestMapping(value = "/add")
	@Transactional
	public String addBook(Model model){
		logger.info("/add is invoked on a controller");
		Book book = new Book();
		String title = "Title_"+ System.currentTimeMillis();
		book.setTitle(title);
		bookService.save(book);
		model.addAttribute("title",title);
		return "book";
	}
	
	
}
