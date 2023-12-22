/**
 * works-modify.js
 * 
 * 작품의 삭제와 수정 기능.
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	
	const btnDelete = document.querySelector('button#btnDelete');
	
	const worksId = document.querySelector('input#worksid');
	
	btnDelete.addEventListener('click', () => {
		
		const result = confirm('정말 삭제할까요?');
		console.log(`confirm result = ${result}`);
		
		if(result){
			location.href = `delete?worksid=${worksId.value}`;
		}
	});
	
});