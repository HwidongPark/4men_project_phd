/**
 * mymessage.js
 */

 document.addEventListener('DOMContentLoaded', function() {
    
    //
    const messageTitles = document.querySelectorAll('div.mymessage-title');
    const messageId = document.querySelectorAll('.mymessage-id');
    const messageWorkId = document.querySelectorAll('.mymessage-workid');
    
    console.log(messageId);
    
    
    for (let i = 0; i < messageTitles.length; i++) {
        // 각각 제목에 eventlistener등록
        messageTitles[i].addEventListener('click', function() {
           
           console.log('제목 클릭됨');
           
           window.open(`/fourmen/mypage/mymessage/detail?id=${messageId[i].innerText}`, "_blank "
           ,'width=700, height=1000');
           
        });
        
    }
     
 });