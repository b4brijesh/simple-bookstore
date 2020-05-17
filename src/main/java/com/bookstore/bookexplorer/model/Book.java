package com.bookstore.bookexplorer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue //(generator = "sequence", strategy = GenerationType.AUTO)
    //@SequenceGenerator(name = "generator", allocationSize = 10)
    private Long Id;

    @Column(unique = true)
    @NotBlank(message = "Book ISBN must be specified")
    private String ISBN;

    @NotBlank(message = "Book title must be specified")
    private String title;

    @NotBlank(message = "Book author must be specified")
    private String author;

    private Integer copies;

    @NotNull(message = "Book price must be specified")
    private BigDecimal price;

    protected Book() {
        // for JPA implementation provider - Hibernate
    }

}
