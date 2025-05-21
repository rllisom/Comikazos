package com.salesianostriana.dam.LlinaresSomeRaul.dto;

import java.time.LocalDate;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class ComicDTO {

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
				.releaseDate(dto.getReleaseDate())
				.url(dto.getUrl())
				.textImg(dto.getTextImg())
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
				.releaseDate(c.getReleaseDate())
				.url(c.getUrl())
				.textImg(c.getTextImg())
				.category_id(c.getCategory().getId())
				.build();
	}
}

