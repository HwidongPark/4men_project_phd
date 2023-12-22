document.addEventListener("DOMContentLoaded", () => {
			
    const btnDelete = document.querySelector('#btnImgDelete');
    
    const userid = document.querySelector('input#userid');
    
    btnDelete.addEventListener('click', () => {
        
        const result = confirm('정말 삭제 할까요...?');
        
        console.log(`confirm result = ${result}`);
        
        if(result) {
            // 화면에서 이미지 숨기기 (화면에서만 삭제된 것처럼 보이기...)
            btnDelete.parentNode.parentNode.remove();
        }
    });
    
});