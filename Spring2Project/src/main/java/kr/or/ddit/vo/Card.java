package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Card {
	@NotBlank
	private String no;
	@Future
	private Date validMonth;
}
