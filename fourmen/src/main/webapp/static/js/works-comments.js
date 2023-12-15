/**
 * 아티스트 -> 작품에 달릴 댓글에 대한 JS 파일
 */

document.addEventListener('DOMContentLoaded' , () => {
	
	// bootstrap 모듈의 Collapse 생성자를 호출해서 bootstrap Collapse 객체 생성.
	const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});
	// JS 객체 생성 => {} toggle false => 접혀있는 상태.
	
	const btnToggleComment =document.querySelector('button#btnToggleComment');
	
	btnToggleComment.addEventListener('click', () => {
		
		bsCollapse.toggle();
		
		if(btnToggleComment.textContent === '작품 댓글 달기...'){
			btnToggleComment.textContent = '댓글 가리기...';
		} else {
			btnToggleComment.textContent = '작품 댓글 달기...';
		}
		
	});
	
});