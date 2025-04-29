package com.salesianostriana.dam.LlinaresSomeRaul.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@AllArgsConstructor
@NoArgsConstructor 
@Data
@Builder
public class Comic {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String syn;
	private double price;
	private int sales;
	private int pages;
	private double review;
	private LocalDate dat;
	private String url;
	@ManyToOne
	private Category category;

}
