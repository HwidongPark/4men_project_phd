/**
 * market-create.js
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const uploadPhotoContainer = document.querySelector('.market-upload-photos');
	const btnAddPhoto = document.querySelector('.market-create-add-phooto');
	const btnSubmit = document.querySelector('#btn-market-create-submit');
	const createForm = document.querySelector('#market-post-create-form');
	let numOfFileInput = 1;
	
	const btnCancel = document.querySelector('.market-create-cancel');
	
	
	btnAddPhoto.addEventListener('click', () => {
		
		if (numOfFileInput < 20) {
			// uploadPhotoContainer.innerHTML += `<br><input type="file" name="files">`;
			let newFileInput = document.createElement('input');
			newFileInput.type = 'file';
			newFileInput.name = 'files';
			newFileInput.classList.add('files');
			
			let lineBreaker = document.createElement('br');
						
			uploadPhotoContainer.append(lineBreaker, newFileInput);
			
		} else {
			alert('20개이상 사진첨부 불가');
		}
		
		numOfFileInput++;
	})
	
	// 취소버튼
	btnCancel.addEventListener('click', () => {
		console.log('취소클릭');
		location.href = "/fourmen/market";
	})
	
	// 제출버튼
	btnSubmit.addEventListener('click', () => {
		const files = document.querySelectorAll('.files');
		let numOfFileUploading = 0;
		
		for (let file of files) {
			
			if (file.value == '') {
				continue;
			}
			
			numOfFileUploading++;
			
			
		}
		
		if (numOfFileUploading >= 1) {
			createForm.submit();
		} else {
			alert('반드시 하나 이상의 파일을 첨부해야 합니다.');
		}
				
	})
	
	
	
		
})