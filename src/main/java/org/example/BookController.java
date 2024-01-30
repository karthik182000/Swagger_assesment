package org.example;


import org.example.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookController {

    private List<Book>books=new ArrayList<>();
    @GetMapping("api/books")
    public List<Book>  getAllBooks(){
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable Long id){
        Book book=books.stream().filter(b->b.getId().equals(id)).findFirst().orElse(null);
        if(book!=null){
            return ResponseEntity.ok(book);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book ){
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created book Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedBook(@PathVariable Long id,@RequestBody Book updatedBook){
        Book existingBook = books.stream().filter(f->f.getId().equals(id)).findFirst().orElse(null);
        if (existingBook !=null){
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setId(updatedBook.getId());
            existingBook.setPrice(updatedBook.getPrice());
            return ResponseEntity.ok().body("book updated successfully ");
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
