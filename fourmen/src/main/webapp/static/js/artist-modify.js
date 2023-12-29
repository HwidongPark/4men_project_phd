document.addEventListener("DOMContentLoaded", () => {
			
    const btnDelete = document.querySelector('#btnImgDelete');
   	const isFileDeleted = document.querySelector('#isFileDeleted');
    const original_img = document.querySelector('#original_img')
    
    console.log(original_img.value);
    
    console.log(original_img.value == '');
    
    if(original_img.value == '') {
		btnDelete.disabled = true; 
	}
    
    console.log(isFileDeleted.value);
    
	btnDelete.addEventListener('click', () => {

		const result = confirm('정말 삭제 할까요...?');

		console.log(`confirm result = ${result}`);

		if (result) {
			// 화면에서 이미지 숨기기 (화면에서만 삭제된 것처럼 보이기...)
			btnDelete.parentNode.parentNode.classList.add('d-none');
			isFileDeleted.value = 'YES';
			console.log(isFileDeleted.value);
		}
	});
	
});