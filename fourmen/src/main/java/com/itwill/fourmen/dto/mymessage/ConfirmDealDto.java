package com.itwill.fourmen.dto.mymessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmDealDto {
	
	private Long workId;
	private String buyerId;

}
