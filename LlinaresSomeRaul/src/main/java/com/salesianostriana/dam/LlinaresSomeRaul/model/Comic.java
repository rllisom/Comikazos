package com.salesianostriana.dam.LlinaresSomeRaul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @AllArgsConstructor
@NoArgsConstructor 
@Data
public class Comic {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String editor;
	private String desc;
	private double price;
	
}
