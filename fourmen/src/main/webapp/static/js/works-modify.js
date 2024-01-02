/**
 * works-modify.js
 * 
 * 작품의 삭제와 수정 기능.
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	
	const btnDelete = document.querySelector('button#btnDelete');
	
	const worksId = document.querySelector('input#worksid').value;
	const userId = document.querySelector('input#userid').value;
	
	btnDelete.addEventListener('click', () => {
		const result = confirm('정말 삭제할까요?');
		console.log(`confirm result = ${result}`);
		
		if(result){
			console.log(worksId);
			
			const uri = `../api/works/${worksId}`;
			
			axios.delete(uri)
			.then((response) => {
				console.log(response);
				
				if(response.data === 1){
					alert('작품 삭제 완료');
					window.location.href = `/fourmen/artist/artist_details?userid=${userId}`;
				}
			}).catch((error) => {
				console.log(error);
			});
		} 
	});
});