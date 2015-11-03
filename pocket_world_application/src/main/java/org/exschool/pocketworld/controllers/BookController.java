package org.exschool.pocketworld.controllers;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.exschool.pocketworld.model.Book;
import org.exschool.pocketworld.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BookController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/")
	public String showIndexPage(Model model){
		LOGGER.info(model.toString());
		return "index";
	}
	
	@RequestMapping(value = "/list")
	public String listBooks(@RequestParam Map<String,String> allRequestParams,Model model){
		List<Book> books = null;
		final String TITLE = "title";
		LOGGER.info("Input model:" + model.toString());
		LOGGER.info("Requested params:" + allRequestParams);
		
		if(allRequestParams.containsKey(TITLE)){
			books = bookService.getByTitle(allRequestParams.get(TITLE));
		}else{
			books = bookService.allBooks();
		}
		model.addAttribute("books",books);
		LOGGER.info("Output model:" + model.toString());
		return "books";
	}
	
	@RequestMapping(value = "/add")
	@Transactional
	public String addBook(Model model){
		LOGGER.info("Input model:" + model.toString());
		Book book = new Book();
		String title = "Title_"+ System.currentTimeMillis();
		book.setTitle(title);
		bookService.save(book);
		model.addAttribute("title",title);
		LOGGER.info("Output model:" + model.toString());
		return "book";
	}
	
	
}
