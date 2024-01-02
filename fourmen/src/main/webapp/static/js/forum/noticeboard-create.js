/**
 * noticeboard-create.js
 * /forum/noticeboard-create.jsp에 포함.
 * 새 포스트 작성 기능.
 */

// 제목 글자수가 maxlength를 넘어가면 경고창을 띄우는 함수.
function input_maxlength(obj) {
    let maxCharacters = parseInt(obj.getAttribute("maxlength"));
    let currentLength = obj.value.length; // 현재 입력된 글자수
    let remainingLength = maxCharacters - currentLength;
    
    if (currentLength > maxCharacters) {
        alert('글자 수 제한을 초과했습니다! (30자)');
        obj.value = obj.value.substring(0, maxCharacters);
        remainingLength = 0;
    }
}

// 본문 글자수가 maxlength를 넘어가면 경고창을 띄우고, 남은 글자수를 표시하는 함수.
function textarea_maxlength(obj) {
    let maxCharacters = parseInt(obj.getAttribute("maxlength"));
    let currentLength = obj.value.length; // 현재 입력된 글자수
    let remainingLength = maxCharacters - currentLength;
    
    if (currentLength > maxCharacters) {
        alert('글자 수 제한을 초과했습니다! (1000자)');
        obj.value = obj.value.substring(0, maxCharacters);
        remainingLength = 0;
    }

    // 남은 글자수를 표시하는 요소를 찾아서 업데이트
    const remainingCharsDisplay = document.getElementById('remaining-chars');
    remainingCharsDisplay.textContent = '총 ' + currentLength + '자' + ' / ' + maxCharacters + '자';
}

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // ** 이벤트 처리에 사용될 요소 찾기. **
    // form 요소 찾기.
    const form = document.querySelector('form#noticeboard-create-form');
    
    // 완료 버튼 찾기.
    const btnCreate = document.querySelector('button#noticeboard-create');
    
    // 포스트 제목을 가지고 있는 요소를 찾음.
    const inputTitle = document.querySelector('input#notice_title');
    
    // 포스트 내용을 가지고 있는 요소를 찾음.
    const textareaContent = document.querySelector('textarea#notice_content');
    
    // 현재 글자수와 남은 글자수를 표시할 요소 생성
    const maxCharacters = parseInt(textareaContent.getAttribute("maxlength"));
    const remainingCharsDisplay = document.createElement('div');
    remainingCharsDisplay.id = 'remaining-chars';
    remainingCharsDisplay.textContent = '총 ' + textareaContent.value.length + '자' + ' / ' + maxCharacters + '자';
    
    // 스타일 추가
    remainingCharsDisplay.style.textAlign = 'right'; // 글자수 표시 요소를 오른쪽 정렬

    // 요소 추가
    document.getElementById('noticeboard-create-content').appendChild(remainingCharsDisplay);
    
    // textareaContent에 이벤트 핸들러(리스너) 등록
    textareaContent.addEventListener('input', () => {
        textarea_maxlength(textareaContent);
    });
    
    // inputTitle에 이벤트 핸들러(리스너) 등록
    inputTitle.addEventListener('input', () => {
       input_maxlength(inputTitle); 
    });
    
    // 완료 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnCreate.addEventListener('click', () => {
        // 제목, 내용이 비어있는지 체크:
        if (inputTitle.value === '' || textareaContent.value === '') {
            alert('제목과 내용은 반드시 입력해주세요.');
            return;
        }
        // 제목, 내용이 비어있지 않으면:
        form.action = 'noticeboard-create'; // 폼(양식)을 제출(submit)할 요청 주소. 기본값은 현재 URL.
        form.method = 'post'; // 폼 요청 방식(get/post). 기본값은 'get'.
        form.submit(); // 폼 제출(서버로 요청 보냄).
        //-> localhost:8081/fourmen/forum/noticeboard-create 요청이 서버로 전달됨.
        //-> form.action: 서버에서 실제 작업을 처리하는 엔드포인트를 지정.
        //-> 엔드포인트: 클라이언트가 서버에게 요청을 보낼 수 있는 특정 URL 경로.
        //-> 클라이언트는 엔드포인트를 통해 서버에게 특정 작업을 요청하고, 
        //-> 서버는 해당 요청을 처리하고 응답을 반환함.
    });
});