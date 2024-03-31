/**
 * navigation-bold-style.js
 * navigation.jspf에 포함.
 * 클릭한 메뉴바의 bold & underline 유지. (어느 메뉴 / 어느 페이지를 선택했는지 알기 위함)
 */

// 클릭한 메뉴바의 bold & underline 유지 함수
function change_style(event) {
    const navButtons = document.querySelectorAll('.nav-button');

    // 모든 메뉴바 요소의 스타일 초기화
    navButtons.forEach(function(button) {
        button.style.fontWeight = 'normal';
        button.style.textDecoration = 'none';
        button.style.textUnderlineOffset = 'initial';
        button.style.color = 'initial';
    });

    // 클릭한 메뉴바 요소에 bold 및 underline 스타일 적용
    event.target.style.fontWeight = 'bold';
    event.target.style.color = 'black';
    event.target.style.textDecoration = 'underline solid 2.85px';
    event.target.style.textUnderlineOffset = '1.495rem';

    // 클라이언트의 상태 정보를 localStorage에 저장
    localStorage.setItem('selectedMenu', event.target.textContent);
}

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // ** 이벤트 처리에 사용될 요소 찾기. **
    const navButtons = document.querySelectorAll('.nav-button');
    navButtons.forEach(button => {
        button.addEventListener('click', change_style);
    });

    // 페이지가 redirect된 경우, localStorage에 저장된 클라이언트의 상태 정보를 확인하고 bold 스타일을 다시 적용
    const selectedMenu = localStorage.getItem('selectedMenu');
    if (selectedMenu) {
        navButtons.forEach(button => {
            if (button.textContent === selectedMenu) {
                button.style.fontWeight = 'bold';
                button.style.color = 'black';
                button.style.textDecoration = 'underline solid 2.85px';
                button.style.textUnderlineOffset = '1.495rem';
            }
        });
    }
});