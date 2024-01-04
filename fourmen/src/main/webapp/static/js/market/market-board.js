/**
 * market-board
 */

document.addEventListener("DOMContentLoaded", () => {
	
	const createBtn = document.querySelector('.market-create-btn-container button');
	
	createBtn.addEventListener('click', () => {
		
		if (grade == '아티스트') {
            location.href="/fourmen/market/create";
            return;    
        } else {
            alert('아티스트만 글 작성 할 수 있습니다.');
            return;
        }
		
	})
	
})