package com.itwill.fourmen.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.ServletContext;

public class RequestContextHelper {

	/*
	 *  메서드마다 HttpServletRequest를 파라미터로 선언해서
	 *  작성하기 귀찮기에 따로 Util 클래스를 작성함...
	 *  근데 artistUpdate에 적용하니 java.nio.file.NoSuchFileException이 
	 *  발생함... 나중에 다시 적용해봐야지....
	 */
	
	
	/**
     * 현재 요청의 ServletRequestAttributes를 가져오는 메서드
     * @return 현재 요청의 ServletRequestAttributes
     */
    public static ServletRequestAttributes getCurrentRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 현재 요청의 ServletContext를 가져오는 메서드
     * @return 현재 요청의 ServletContext
     */
    public static ServletContext getCurrentServletContext() {
        return getCurrentRequestAttributes().getRequest().getServletContext();
    }

    // 다른 유용한 메서드들도 추가 가능
	
}
