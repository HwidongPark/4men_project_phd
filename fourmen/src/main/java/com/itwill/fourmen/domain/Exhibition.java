package com.itwill.fourmen.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Exhibition {
	private String id;
	private String name;
	private String location;
	private LocalDate startdate;
	private LocalDate enddate;
	private String photo;
	private String site;
}
