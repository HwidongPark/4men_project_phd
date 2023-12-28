/**
 * forum-category-bold-style.js
 * forum에 관한 jsp 중에서 detail 제외한 모든 jsp에 포함.
 * 클릭한 카테고리의 bold & underline 유지. (어느 메뉴 / 어느 페이지를 선택했는지 알기 위함)
 */

// 클릭한 카테고리의 bold & underline 유지 함수
function change_category_style(event) {
    const categoryButtons = document.querySelectorAll('.category-button');

    // 모든 카테고리 요소의 스타일 초기화
    categoryButtons.forEach(function(button) {
        button.style.fontWeight = 'normal';
        button.style.borderBottom = 'none';
        button.style.color = 'initial';
    });

    // 클릭한 카테고리 요소에 bold 및 underline 스타일 적용
    event.target.style.fontWeight = 'bold';
    event.target.style.color = 'black';
    event.target.style.borderBottom = '2.5px solid black';

    // 클라이언트의 상태 정보를 localStorage에 저장
    localStorage.setItem('selectedMenu', event.target.textContent);
}

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // ** 이벤트 처리에 사용될 요소 찾기. **
    const categoryButtons = document.querySelectorAll('.category-button');
    categoryButtons.forEach(button => {
        button.addEventListener('click', change_category_style);
    });
    
    // 페이지가 redirect된 경우, localStorage에 저장된 클라이언트의 상태 정보를 확인하고 bold 스타일을 다시 적용
    const selectedMenu = localStorage.getItem('selectedMenu');
    if (selectedMenu) {
        categoryButtons.forEach(button => {
            if (button.textContent === selectedMenu) {
                button.style.fontWeight = 'bold';
                button.style.color = 'black';
                button.style.borderBottom = '2.5px solid black';
            }
        });
    }
   
    // 현재 URL과 localStorage에 저장된 URL이 같으면 bold 스타일을 다시 적용
    if (window.location.href === localStorage.getItem('selectedURL')) {
        categoryButtons.forEach(button => {
            if (button.textContent === selectedMenu) {
                button.style.fontWeight = 'bold';
                button.style.color = 'black';
                button.style.borderBottom = '2.5px solid black';
            }
        });
    } else {
        localStorage.setItem('selectedURL', window.location.href);
    }
});