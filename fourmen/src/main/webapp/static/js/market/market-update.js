/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
     
     const marketBtnDeleteImage = document.querySelectorAll('.marketBtnDeleteImage');
     const workId = document.querySelector('#workId').value;
     const btnCancel = document.querySelector('#market-update-btn-cancel');
     
     // 아직 사용방법 찾지 못함;
     let marketPost = null;
     console.log('업데이트 자스옴');
     
     
     

         
 // 포스트에 대한 정보를 가져옴
 const uri = `/fourmen/market/fetch/${workId}`;
 console.log(`uri=${uri}`);
          
 axios.get(uri)
    .then((response) => {
        console.log('받아오기 성공',response.data);
        marketPost = response.data;
        
             for (let i = 0; i < marketPost.workImages.length; i++) {
                 marketBtnDeleteImage[i].addEventListener('click', () => {
                 console.log(`${i} 삭제버튼 눌림`);
                 deleteImageFromPost(i);
                 }) 
             }
        
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
     
     
     for (let deleteBtn of marketBtnDeleteImage) {
         deleteBtn.addEventListener('click', () => {
            console.log('업데이트 페이지 삭제버튼 클릭');
            deleteBtn.parentNode.remove();
            
         });
     }
     
     
     
     // 취소버튼 이벤트리스너
     btnCancel.addEventListener('click', () => {
         history.back();         
     })
     

     
 });