package com.salesianostriana.dam.LlinaresSomeRaul.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@AllArgsConstructor
@NoArgsConstructor 
@Data
public class Comic {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String desc;
	private double price;
	private int sales;
	private double review;
	private LocalDate fec;
	@ManyToOne
	private Category category;

}
