/**
 * market-create.js
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const uploadPhotoContainer = document.querySelector('.market-upload-photos');
	const btnAddPhoto = document.querySelector('.market-create-add-phooto');
	let numOfPhotos = 1;
	
	const btnCancel = document.querySelector('.market-create-cancel');
	
	
	btnAddPhoto.addEventListener('click', () => {
		
		if (numOfPhotos < 20) {
			uploadPhotoContainer.innerHTML += `<br><input type="file" name="files">`;	
		} else {
			alert('20개이상 사진첨부 불가');
		}
		
		numOfPhotos++;
	})
	
	btnCancel.addEventListener('click', () => {
		console.log('취소클릭');
		location.href = "/fourmen/market";
	})
	
})