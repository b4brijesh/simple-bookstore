package com.bookstore.bookexplorer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue
    @JsonIgnore
    private Long Id;

    @Column(unique = true)
    @NotBlank(message = "Book ISBN must be specified")
    private String ISBN;

    @NotBlank(message = "Book title must be specified")
    private String title;

    @NotBlank(message = "Book author must be specified")
    private String author;

    @JsonIgnore
    private Integer copies = 0;

    @NotNull(message = "Book price must be specified")
    private BigDecimal price;

    protected Book() {
        // for JPA implementation provider - Hibernate
    }

}
