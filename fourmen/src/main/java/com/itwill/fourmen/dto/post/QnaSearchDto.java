package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.board.Criteria;

import lombok.Data;

@Data
public class QnaSearchDto {
	private String category;
	private String keyword;
	private int rowStart;
	private int postsPerPage;
}
