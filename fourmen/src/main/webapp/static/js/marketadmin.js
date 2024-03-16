/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#market-admin-delete');
	const btndeletes = document.querySelectorAll('button#btndelete');
	const btndeletex= document.querySelectorAll('button#recent');
	for (let btn of btndeletes) {
		btn.addEventListener('click', (e) => {
			const title = e.target.getAttribute('data-exname');
			console.log(`title = ${title}`);
			
			const result = confirm('상품을 삭제하시겠습니까?');
			if (result) {
				alert('상품이 삭제되었습니다');
				location.href = `marketadmin/delete?title=${title}`;
			}
		});
	}

});