/**
 * noticeboard-create.js
 * /forum/noticeboard-create.jsp에 포함.
 * 새 포스트 작성 기능.
 */

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // ** 이벤트 처리에 사용될 요소 찾기. **
    // form 요소 찾기.
    const form = document.querySelector('form#noticeboard-create-form');
    
    // 포스트 고유 아이디(번호(post_id))를 가지고 있는 요소 찾기.
    const postId = document.querySelector('input#post_id');
        
    // 완료 버튼 찾기.
    const btnCreate = document.querySelector('button#noticeboard-create');
    
    // 포스트 제목을 가지고 있는 요소를 찾음.
    const inputTitle = document.querySelector('input#notice_title');
    
    // 포스트 내용을 가지고 있는 요소를 찾음.
    const textareaContent = document.querySelector('textarea#notice_content');
    
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