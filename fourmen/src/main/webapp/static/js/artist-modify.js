document.addEventListener("DOMContentLoaded", () => {
			
    const btnDelete = document.querySelector('#btnImgDelete');
    const btnUpdate = document.querySelector('#btnUpdate');
    const userid = document.querySelector('input#userid').value;
    const updateForm = document.querySelector('#artistModifyForm');
    const filesBeDeleted = new Array();
    
    
	btnDelete.addEventListener('click', () => {

		const result = confirm('정말 삭제 할까요...?');

		console.log(`confirm result = ${result}`);

		if (result) {
			// 화면에서 이미지 숨기기 (화면에서만 삭제된 것처럼 보이기...)
			btnDelete.parentNode.parentNode.classList.add('d-none');

			filesBeDeleted.push(btnDelete.parentNode.parentNode.querySelector('input#original_img').value);
			console.log(`삭제할 이미지 = ${filesBeDeleted}`);
		}
	});
	
	console.log(btnUpdate);
	btnUpdate.addEventListener('click', (e) => {
		e.preventDefault();
		//alert('aaa');
		
		console.log('ttttt');
		
		// 수정 버튼 클릭시 서버로 Patch 요청을 보냄
		fetch(`updateImageNull/${userid}`, {
			method: 'PATCH',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
			
			body: JSON.stringify(
				{
					orignial_img: null
				}
			)
		})
			.then(response => {
				if (!response.ok) {
					throw new Error('네트워크 RESPONSE NOT OK');
				}
				return response.json();
			})
			.then(data => {
				console.log('이미지 업데이트... :', data);
				
				// fetch 요청이 성공적으로 완료된 후에 리다이렉트
        		window.location.href = 'artist_details?userid=' + userid; // 여기에 리다이렉트할 페이지 URL을 넣어주세요
				
			})
			.catch(error => {
				console.error('fetch에 문제가 발생했습니다.', error);
			});
	});
    
});