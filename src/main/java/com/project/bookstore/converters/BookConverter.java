package com.project.bookstore.converters;

import com.project.bookstore.dto.BookDto;
import com.project.bookstore.entities.Book;
import com.project.bookstore.entities.Category;
import com.project.bookstore.entities.Publisher;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookConverter implements Function<BookDto, Book> {

    @Override
    public Book apply(BookDto bookDto) {

        String category = bookDto.getCategory();
        Category categoryBook = Category.valueOf(category);

        String publisher = bookDto.getPublisher();
        Publisher publisherBook = Publisher.valueOf(publisher);

        return new Book(bookDto.getId(), bookDto.getIsbn(), categoryBook, bookDto.getTitle(),
                bookDto.getAuthor(), publisherBook, bookDto.getReleaseYear(), bookDto.getPrice(), bookDto.getAvailableQuantity());
    }
}
