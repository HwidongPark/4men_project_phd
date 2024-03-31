/**
 * market-create.js
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const uploadPhotoContainer = document.querySelector('.market-upload-photos');
	
	const files = document.querySelector('.files');
	const btnAddPhoto = document.querySelector('.market-create-add-phooto');
	
	const btnSubmit = document.querySelector('#btn-market-create-submit');
	const createForm = document.querySelector('#market-post-create-form');
	let numOfFileInput = 1;
	
	const btnCancel = document.querySelector('.market-create-cancel');
	
	// number타입의 숫자들의 길이제한을 위해 elements들을 읽어옴
	const priceInput = document.querySelector('#market-price');
	const yearInput = document.querySelector('#market-created-year');
	const dimensions = document.querySelectorAll('.market-dimension');
	
	
	// number타입 글자 수 제한하는 함수
	const limitNumberLength = function(maxLength, element, event) {

        if (element.value.length > maxLength) {
            element.value = element.value.slice(0,maxLength-1) + event.data;
            
             
        }
        
    }
	
	
	// 첫 파일 input에 이벤트 리스너 등록
	files.addEventListener('change', function() {
        
        // 파일 확장자 가져옴
        const extension = files.value.substring(files.value.lastIndexOf('.'));
        
        // 이미지 파일이 아닌 경우 파일입력 못하도록 막음
        if (extension != '.apng' && extension != '.gif' && extension != '.ico' && extension != '.cur' && extension != '.jpg'
            && extension != '.jpeg' && extension != '.jfif' && extension != '.pjpeg' && extension != '.pjp' && extension != '.png' && extension != '.svg' && extension != '') {
                
            alert('이미지 파일만 추가할 수 있습니다.');
            files.value = '';
            
            return;            
            
        }
        
    });
	
	
	// 추가버튼 누르면 파일타입의 input하나씩 추가해주는 이벤트 리스너
	btnAddPhoto.addEventListener('click', () => {
		
		if (numOfFileInput < 20) {
			// uploadPhotoContainer.innerHTML += `<br><input type="file" name="files">`;
			
			let newFileInput = document.createElement('input');
			newFileInput.type = 'file';
			newFileInput.name = 'files';
			newFileInput.classList.add('files');
			
			// 새로 추가한 파일 input에서도 이벤트 리스너 등록시킴
			newFileInput.addEventListener('change', function() {
                
                 // 파일 확장자 가져옴
                const extension = newFileInput.value.substring(newFileInput.value.lastIndexOf('.'));
                
                // 이미지 파일이 아닌 경우 파일입력 못하도록 막음
                if (extension != '.apng' && extension != '.gif' && extension != '.ico' && extension != '.cur' && extension != '.jpg'
                    && extension != '.jpeg' && extension != '.jfif' && extension != '.pjpeg' && extension != '.pjp' && extension != '.png' && extension != '.svg' && extension != '' ) {
                        
                    alert('이미지 파일만 추가할 수 있습니다.');
                    newFileInput.value = '';
                    
                    return;                                
                }
                
            });
			
			
			let lineBreaker = document.createElement('br');
						
			uploadPhotoContainer.append(lineBreaker, newFileInput);
			
			
		} else {
			alert('20개이상 사진첨부 불가');
		}
		
		numOfFileInput++;
	})
	
	
	// 숫자타입 문자 수 제한 이벤트 리스너들 등록
    priceInput.addEventListener('input', function(event) {
       
       limitNumberLength(11, priceInput, event);
        
    });
    
    yearInput.addEventListener('input', function() {
        limitNumberLength(4, yearInput, event);
    });
    
    
    for (const dimension of dimensions) {
        
        dimension.addEventListener('input', function() {
            limitNumberLength(4, dimension, event);
        });
        
    }
	
	
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
				
	});
	
	
	
		
})