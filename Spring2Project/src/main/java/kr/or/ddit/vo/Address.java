package kr.or.ddit.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Address {
	@NotBlank
	private String postCode;
	@NotBlank
	private String location;
}
