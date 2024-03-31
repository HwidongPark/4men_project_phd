/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	
	const userid = document.querySelector('input#userid').value;
	const btnAdmin = document.querySelector('button#btnAdmin');
	
	btnAdmin.addEventListener('click', () => {
		const result = confirm('정말 아티스트를 삭제할까요?');
		console.log(`confirm result = ${result}`);
		
		if(result){
			const uri = `../api/artist/artist_details/${userid}`;
			
			axios.delete(uri)
			.then((response) => {
				console.log(response);
				
				if(response.data === 1){
					alert('아티스트 삭제 완료');
					window.location.href = `/fourmen/artistadmin`;
				}
			}).catch((error) => {
				console.log(error);
			});
		} 
	}); 
	
});
