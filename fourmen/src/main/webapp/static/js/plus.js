/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	
	const uploadPhoto = document.querySelector('span#exhibiton-upload-photo');
	//const btnAddPhoto = document.querySelector('.market-create-add-phooto');
	const btnplus = document.querySelector('button#btn-exhibition-plus');
	const form = document.querySelector('form#exhibition-plus-form');
	let numOfFileInput = 1;
	

	
	/*
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
	*/
	
	// 제출버튼
	btnplus.addEventListener('click', () => {
		const files = document.querySelectorAll('.files');
		let numOfFileUploading = 0;
		
		for (let file of files) {
			
			if (file.value == '') {
				continue;
			}
			
			numOfFileUploading++;
			
			
		}
		
		if (numOfFileUploading >= 1) {
			form.submit();
		} else {
			alert('반드시 하나 이상의 파일을 첨부해야 합니다');
		}
				
	})
	
	
	
		
})