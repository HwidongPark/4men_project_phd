/**
 * bold-test01.js
 * 자바스크립트를 사용하여 현재 페이지의 URL과 로컬 저장소에 저장된 선택된 URL을 비교하고,
 * 두 URL이 일치하는 경우 특정 동작(메뉴 bold) 수행.
 * 클릭한 카테고리의 bold & underline 유지. (어느 메뉴 / 어느 페이지를 선택했는지 알기 위함)
 */

// 클릭한 카테고리 요소의 상태를 추적하기 위한 변수 선언
let selectedCategory;

// 클릭한 카테고리 요소의 상태를 추적
function change_category_style(event) {
    selectedCategory = event.target;

    // 모든 카테고리 요소의 스타일 초기화
    const categoryButtons = document.querySelectorAll('.category-button');
    categoryButtons.forEach(button => {
        button.style.fontWeight = 'normal';
        button.style.borderBottom = 'none';
        button.style.color = 'initial';
    });

    // 클릭한 카테고리 요소에 bold 및 underline 스타일 적용
    selectedCategory.style.fontWeight = 'bold';
    selectedCategory.style.color = 'black';
    selectedCategory.style.borderBottom = '2.5px solid black';

    // 클라이언트의 상태 정보를 localStorage에 저장
    selectedCategory.dataset.selected = true;

    // 페이지가 redirect된 경우, localStorage에 저장된 클라이언트의 상태 정보를 확인하고 bold 스타일을 다시 적용
    if (window.location.href === localStorage.getItem('selectedURL')) {
        categoryButtons.forEach(button => {
            if (button.textContent === selectedCategory.textContent) {
                button.style.fontWeight = 'bold';
                button.style.color = 'black';
                button.style.borderBottom = '2.5px solid black';
            }
        });
    }
}