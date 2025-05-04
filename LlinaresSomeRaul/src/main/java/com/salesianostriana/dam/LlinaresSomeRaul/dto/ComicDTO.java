package com.salesianostriana.dam.LlinaresSomeRaul.dto;

import java.time.LocalDate;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class ComicDTO {

	private String name;
	private String syn;
	private double price;
	private int sales;
	private int pages;
	private double review;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dat;
	private String url;
	private Long category_id;


	//BUILDER
	public static Comic buildComic(ComicDTO dto, Category cat) {
		return Comic.builder()
				.name(dto.getName())
				.syn(dto.getSyn())
				.price(dto.getPrice())
				.sales(dto.getSales())
				.pages(dto.getPages())
				.review(dto.getReview())
				.dat(dto.getDat())
				.url(dto.getUrl())
				.category(cat)
				.build();
	}

	public static ComicDTO buildComicDTO(Comic c){
		return ComicDTO.builder()
				.name(c.getName())
				.syn(c.getSyn())
				.price(c.getPrice())
				.sales(c.getSales())
				.pages(c.getPages())
				.review(c.getReview())
				.dat(c.getDat())
				.url(c.getUrl())
				.category_id(c.getCategory().getId())
				.build();
	}
}

