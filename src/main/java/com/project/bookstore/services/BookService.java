package com.project.bookstore.services;

import com.project.bookstore.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {

    public List<BookDto> findAll();

    public Optional<BookDto> findById(Long id);

    public void save(BookDto bookDto);
    public void delete (Long id);


}
