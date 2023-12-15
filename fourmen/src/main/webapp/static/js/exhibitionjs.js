/**
 * 전시회 페이지 js 입니다
 */

 document.addEventListener('DOMContentLoaded', ()=>{
	 const goButton = document.querySelector('button#goButton');
	 goButton.addEventListener('click',()=>{
		if(true){
			window.location.href="exhibition2"
		}
	 });
	 
	 const backButton = document.querySelector('button#backButton');
	 backButton.addEventListener('click',()=>{
		if(true){
			 window.location.href ="exhibition"
		}
	 });
	 
	 const selectCategory = document.querySelector('select#selectCategory');
	 selectCategory.addEventListener('click', ()=>{
		  const searchKeyword = document.querySelector('input#search-keyword');
		 if(selectCategory.ariaSelected=='전시회'){
			 searchKeyword.ariaPlaceholderr ='테스트';
		 }
	 });
	
 });