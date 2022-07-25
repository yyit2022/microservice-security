package com.yyit.mss.lesson04.sample01.resource;

import com.yyit.mss.lesson04.sample01.resource.entities.Book;
import com.yyit.mss.lesson04.sample01.resource.entities.BookRepository;
import com.yyit.mss.lesson04.sample01.resource.entities.OIDCConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    @CrossOrigin(origins = "http://localhost:3000")
    public Collection<Book> getBooks() {
        System.out.println("Executing GET /books");
        return repository.findAll();
    }

    @GetMapping("/.well-known/openid-configuration")
    @CrossOrigin(origins = "http://localhost:3000")
    public OIDCConfig getOIDCConfig() {

        OIDCConfig oidcConfig = new OIDCConfig();

        oidcConfig.setToken_endpoint("http://localhost:8081/oauth/token");
        oidcConfig.setIssuer("http://localhost:8081/");
        oidcConfig.setAuthorization_endpoint("http://localhost:8081/oauth/authorize");
        oidcConfig.setUserinfo_endpoint("http://localhost:8081/api/users/me");

        return oidcConfig;
    }

}