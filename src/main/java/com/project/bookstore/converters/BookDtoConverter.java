package com.project.bookstore.converters;

import com.project.bookstore.dto.BookDto;
import com.project.bookstore.entities.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookDtoConverter implements Function<Book, BookDto> {
    @Override
    public BookDto apply(Book book) {
        return new BookDto(book.getId(), book.getCategory().name(), book.getTitle(), book.getAuthor(), book.getPublisher().name(), book.getReleaseYear(), book.getPrice(), book.getAvailableQuantity(), book.getIsbn());
    }
}
