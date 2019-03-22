package com.project.bookstore.controllers.web;

import com.project.bookstore.dto.BookDto;
import com.project.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin.dom.css.Rect;

import java.util.List;
import java.util.Optional;

@Controller
public class BookControler {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/list")
    public String listBooks(Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/book")
    public String showBook(@RequestParam(name = "id") Long id, Model model) {
        Optional<BookDto> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        } else {
            model.addAttribute("errorMessage", "There is not book with id= " + id);

        }
        return "bookView";
    }

    @PostMapping("/book/save")
    public String saveBook(@ModelAttribute(name = "book") BookDto bookDto) {
        bookService.save(bookDto);
        return "redirect:/book/list";

    }

    @GetMapping("/book/add")
    public String addBook(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        return "bookView";

    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.delete(id);
        return "redirect:/book/list";

    }

    @GetMapping("/book/edit")
    public String findBookById(@RequestParam Long id, Model model) {
        Optional<BookDto> bookDto = bookService.findById(id);

        if (bookDto != null) {
            model.addAttribute("book", bookDto);

        } else {
            model.addAttribute("errorMessage", "There is no book with id=" + id);
        }
        return "bookView";

    }


}
