package com.yyit.mss.lesson04.sample01.resource.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:3000")
public interface BookRepository extends JpaRepository<Book, Long> {

}
