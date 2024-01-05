/**
 * mymessage-detail.js
 */

 document.addEventListener('DOMContentLoaded', function() {
     const btnReply = document.querySelector('#mymessage-reply');
     const btnMakeDeal = document.querySelector('#mymessage-make-deal');
     const messageId = document.querySelector('#messageId');
     const messageWorkid = document.querySelector('.mymessage-workid');
     
     const offeredPrice = document.querySelector('#offered-price');
     
     // console.log(`buyerId=${buyerId}`);
     
     
    // number타입 글자 수 제한하는 함수
    const limitNumberLength = function(maxLength, element, event) {

        if (element.value.length > maxLength) {
            element.value = element.value.slice(0,maxLength-1) + event.data;
            
             
        }
        
    }
    
    
    // 제안가격 입력글자 수 제한
    offeredPrice.addEventListener('input', function(event) {
       
       limitNumberLength(11, offeredPrice, event);
        
    });
     
          
     // 답변하기 버튼 event listener
     btnReply.addEventListener('click', function() {
         
         window.open(`/fourmen/mypage/mymessage/reply?id=${ messageId.value }`, "_blank "
           ,'width=700, height=1000');
         
     })
     
     
     // 거래 승인 event listener
     btnMakeDeal.addEventListener('click', function() {
        console.log(`buyerId=${ buyerId }`);
         const proceed = confirm('정말 거래를 확정하겠습니까?');
         
         if (proceed) {
            
            data = {
                workId: workId,
                buyerId: buyerId
            };
            
            axios.post('/fourmen/market/confirm-deal', data)
                .then((response) => {
                    if(response.data == 1) {
                        alert('거래확정 성공');
                        location.reload();
                    } else {
                        alert('거래확정 실패');
                    }
                })
                .catch((error) => {
                    console.log(`ERROR!!: ${ error }`);
                })
                
         }
         
         
     });
     
     
 });