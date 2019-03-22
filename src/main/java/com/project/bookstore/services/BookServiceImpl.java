package com.project.bookstore.services;

import com.project.bookstore.dto.BookDto;
import com.project.bookstore.entities.Book;
import com.project.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Function<Book, BookDto> bookDtoConverter;

    @Autowired
    private Function<BookDto, Book> bookConverter;

    @Override
    public List<BookDto> findAll() {
        Iterable<Book> books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), true).map(bookDtoConverter).collect(Collectors.toList());

    }

    @Override
    public Optional<BookDto> findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookDto bookDto = bookDtoConverter.apply(book.get());
            return Optional.of(bookDto);
        }
        return Optional.empty();
    }

    @Override
    public void save(BookDto bookDto) {
        Book book = bookConverter.apply(bookDto);
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
