package org.exschool.pocketworld.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.exschool.pocketworld.model.Book;
import org.exschool.pocketworld.service.BookService;
import org.exschool.pocketworld.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/index")
	public String showIndexPage(Model model){
		return "index";
	}
	
	@RequestMapping(value = "/list")
	public String listChildren(Model model){
		
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books",books);
		return "books";
	}
	
	@RequestMapping(value = "/add")
	@Transactional
	public String addBook(Model model){
		Book book = new Book();
		String title = "Title_"+ System.currentTimeMillis();
		book.setTitle(title);
		bookService.create(book);
		model.addAttribute("title",title);
		return "book";
	}
	
	
}
