/**
 * line-control.js
 */

 document.addEventListener('DOMContentLoaded', function() {
     
     const btnReadMore = document.querySelector('.btn-read-more');
     const artistBio = document.querySelector('#artist-bio');
     
     const divHeight = artistBio.offsetHeight;
     console.log(divHeight);
     
     
    
    
    // 한줄 높이
     const lineHeight = parseFloat(window.getComputedStyle(artistBio).lineHeight);
     console.log(lineHeight);
     
     // 줄 갯수 계산
     let totLines = Math.ceil(divHeight / lineHeight);
     
     if (totLines > 10) {
         artistBio.style.maxHeight = `${ lineHeight * 10 }px`;
         console.log(artistBio.style.maxHeight);
         artistBio.style.overflow = 'hidden';
         
         btnReadMore.style.display = 'block';
         
     }
     
     
     btnReadMore.addEventListener('click', function() {
         let btnText = btnReadMore.innerHTML;
         
         console.log('더보기 버튼 클릭');
         
         if (btnText === 'Read More') {     // 글 더보기
             
             console.log('더보기 버튼 클릭 안쪽');
             artistBio.style.maxHeight = 'none';
             btnReadMore.innerHTML = 'Read Less';
             btnText = 'Read Less';
             
             
         } else if (btnText === 'Read Less') {  // 글 보기 줄이기
             
             console.log('줄이기 버튼 안쪽');
             artistBio.style.maxHeight = `${ lineHeight * 10 }px`;
             btnReadMore.innerHTML = 'Read More';
             

             
         }
         
     })
     
 })