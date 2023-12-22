/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
     
     const marketBtnDeleteImage = document.querySelectorAll('.marketBtnDeleteImage');
     const workId = document.querySelector('#workid').value;
     const btnCancel = document.querySelector('#market-update-btn-cancel');
     const btnMarketPostUpdate = document.querySelector('#btn-market-update-submit');
     const updateForm = document.querySelector('#market-post-update-form');
     
     let numOfFiles = 0;
     
     // 아직 사용방법 찾지 못함;
     let marketPost = null;     
     
     

         
	 // 포스트에 대한 정보를 가져옴 활용못함
	 const uri = `/fourmen/market/fetch/${workId}`;
	 console.log(`uri=${uri}`);
	          
	 axios.get(uri)
	    .then((response) => {
	        console.log('받아오기 성공',response.data);
	        marketPost = response.data;

	        
	    })
	    .catch((error) => {
	        console.log(error);
	    })
         

     
         
     const deleteImageFromPost = (i) => {
         const workImage = marketPost.workImages[i];
         console.log(`imgId=${workImage.imgId}`);
         console.log(workImage);
         const uri = `/fourmen/market/delete/image/${workImage.imgId}`;
                  
         axios.delete(uri)
            .then((response) => {
                console.log('수행결과', response.data);
            })
            .catch((error) => {
                console.log(error);
            })
         
     }
     
     
    numOfFiles = marketBtnDeleteImage.length;
    console.log(`numOfExistingFiles = ${numOfFiles}`);
    
    for (let i = 0; i < numOfFiles; i++) {
        marketBtnDeleteImage[i].addEventListener('click', () => {
        console.log(`${i} 삭제버튼 눌림`);
        marketBtnDeleteImage[i].parentNode.remove();
        deleteImageFromPost(i);
        
        numOfFiles--;
        console.log(`이미지 삭제, 현재 파일 수 = ${numOfFiles}`);
        }) 
    }
     
     
     
     // 취소버튼 이벤트리스너
     btnCancel.addEventListener('click', () => {
         history.back();         
     });
     
     
     // 수정버튼 이벤트 리스너
     btnMarketPostUpdate.addEventListener('click', () => {
		const newlyAddedFiles = document.querySelectorAll('.files');
		
		for (let file of newlyAddedFiles) {
			
			if (file.value == '') {
				continue;
			}
			
			numOfFiles++;
			
			
		}
		
		console.log(`수정버튼 눌렀을 때 파일개수=${numOfFiles}`);
		
		if (numOfFiles >= 1) {
			updateForm.submit();
		} else {
			alert('반드시 하나 이상의 파일을 첨부해야 합니다.');
		}
	 });
	
     
 });