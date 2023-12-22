/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
     
     const marketBtnDeleteImage = document.querySelectorAll('.marketBtnDeleteImage');
     const workId = document.querySelector('#workid').value;
     const btnCancel = document.querySelector('#market-update-btn-cancel');
     const btnMarketPostUpdate = document.querySelector('#btn-market-update-submit');
     const updateForm = document.querySelector('#market-post-update-form');
     const filesBeDeleted = new Array();
     
     let numOfExistingFiles = 0;
     
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
         

     
     // 포스트에서 안보이도록 지움
     const deleteImageFromPost = (i) => {
         const workImage = marketPost.workImages[i];
         console.log(`imgId=${workImage.imgId}`);
         console.log(workImage);
         
         filesBeDeleted.push(workImage.imgId);
         console.log(`삭제할 이미지 리스트=${filesBeDeleted}`); //로컬에서 삭제할 파일리스트에 추가         
     }
     
     
     
     
    numOfExistingFiles = marketBtnDeleteImage.length;
    console.log(`numOfExistingFiles = ${numOfExistingFiles}`);
    
    for (let i = 0; i < numOfExistingFiles; i++) {
        marketBtnDeleteImage[i].addEventListener('click', () => {
        console.log(`${i} 삭제버튼 눌림`);
        marketBtnDeleteImage[i].parentNode.remove();
        deleteImageFromPost(i);
        
        numOfExistingFiles--;
        console.log(`이미지 삭제, 현재 파일 수 = ${numOfExistingFiles}`);
        }) 
    }
     
     
     
     // 취소버튼 이벤트리스너
     btnCancel.addEventListener('click', () => {
        history.back();
                  
     });
     
     
     // 수정버튼 이벤트 리스너
     btnMarketPostUpdate.addEventListener('click', async () => {
		const newlyAddedFiles = document.querySelectorAll('.files');
		let numOfNewFiles = 0;
		
		for (let file of newlyAddedFiles) {
			
			if (file.value == '') {
				continue;
			}
			
			numOfNewFiles++;
						
		}
		
		let numTotFiles = numOfExistingFiles + numOfNewFiles;
		console.log(`수정버튼 눌렀을 때 파일개수=${numTotFiles}`);
		
		if (numTotFiles >= 1) {
            // imgId만 알면 파일삭제 가능
            for (let fileToBeDeleted of filesBeDeleted) {
                const uri = `/fourmen/market/delete/image/${fileToBeDeleted}`;
                try {
                    const resp = await axios.delete(uri);
                    console.log(resp.data);
                } catch(e) {
                    console.log(`에러발생 ${e}`);
                }
            }
            
            updateForm.submit();
            
		} else {
			alert('반드시 하나 이상의 파일을 첨부해야 합니다.');
			return;
		}
	 });
	
     
 });