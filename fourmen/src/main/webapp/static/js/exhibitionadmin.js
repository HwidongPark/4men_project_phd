/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#exhibition-admin');
	const btndeletes = document.querySelectorAll('button#btndelete');

	for (let btn of btndeletes) {
		btn.addEventListener('click', (e) => {
			const exhibitionName = e.target.getAttribute('data-exname');
			console.log(`exhibitionName = ${exhibitionName}`);
			
			const result = confirm('전시회를 삭제하시겠습니까?');
			if (result) {
				alert('전시회가 삭제되었습니다');
				location.href = `exhibitionadmin/delete?name=${exhibitionName}`;
			}
		});
	}


});