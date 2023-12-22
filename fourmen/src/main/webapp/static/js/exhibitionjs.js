/**
 * 전시회 페이지 js 입니다
 */

 document.addEventListener('DOMContentLoaded', ()=>{
	const form = document.querySelector('form#search-form');
	const btnsearch = document.querySelector('button#btnSearch');
	const startdate = document.querySelector('input#startdate-select');
	const enddate = document.querySelector('input#enddate-select');
	var selectcategory = document.getElementById('selectCategory');
	const searchkeyword = document.querySelector('input#search-keyword');
	btnsearch.addEventListener('click',(e)=>{
		e.preventDefault();
		if(selectcategory.value === 'location'){
		 	if(searchkeyword.value !== '서울' &&
		 	searchkeyword.value !== '부산' &&
		 	searchkeyword.value !== '전남' &&
		 	searchkeyword.value !== '전북' &&
		 	searchkeyword.value !== '경남' &&
		 	searchkeyword.value !== '경북' &&
		 	searchkeyword.value !== '대전' &&
			searchkeyword.value !== '대구' &&
		 	searchkeyword.value !== '경기' &&
		 	searchkeyword.value !== '충북' &&
		 	searchkeyword.value !== '충남' &&
		 	searchkeyword.value !== '강원' ){
				 alert('위치명을 ex) 서울, 경기, 전남, 대전 등으로 입력해주세요.');
				 return;
			 }
		}
		
  
       
          self.location = "exhibition" + '${pageMaker.makeQuery(1)}' + "&category=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#search-keyword').val()
          + "&startdate=" + encodeURIComponent($('#startdate-select').val())+ "&enddate=" + encodeURIComponent($('#enddate-select').val()));
   
  
		form.submit();
		
	});
 });