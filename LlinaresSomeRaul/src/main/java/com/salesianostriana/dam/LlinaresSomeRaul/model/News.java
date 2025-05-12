package com.salesianostriana.dam.LlinaresSomeRaul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class News {

    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateNew;
    private String imgNew;
    private String title;
    private String description;
}
