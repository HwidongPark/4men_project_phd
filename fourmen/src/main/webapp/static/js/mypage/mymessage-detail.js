/**
 * mymessage-detail.js
 */

 document.addEventListener('DOMContentLoaded', function() {
     const btnReply = document.querySelector('#mymessage-reply');
     const messageId = document.querySelector('#messageId');
     
     console.log(`messageId=${messageId.value}`);
     
     btnReply.addEventListener('click', function() {
         
         window.open(`/fourmen/mypage/mymessage/reply?id=${ messageId.value }`, "_blank "
           ,'width=700, height=1000');
         
     })
 });