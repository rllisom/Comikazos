package com.salesianostriana.dam.LlinaresSomeRaul.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
	@Lob
	private String syn;
	private Double price;
	private Integer sales;
	private Integer pages;
	private Double review;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
	@Lob
	private String url;
	@Lob
	private String textImg;
	@ManyToOne
	private Category category;

	// HELPERS

	public void addToComic(Category c) {
		this.category = c;
		c.getListComic().add(this);
	}

	public void removeFromComic(Category cNueva) {
		this.category.getListComic().remove(this);
		this.addToComic(cNueva);
	}

	public double getDiscount() {
		double rate = 0.95;

		if (hasDiscount()) {
			return price * rate;
		} else {
			return price;
		}
	}

	public boolean hasDiscount() {
		return releaseDate != null && ChronoUnit.DAYS.between(releaseDate, LocalDate.now()) > 50;
	}
}
