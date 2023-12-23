/**
 * faqboard-detail.js
 * /forum/faqboard-detail.jsp에 포함.
 * 포스트 삭제, 업데이트 기능.
 * 목록 버튼 클릭하면 이전 페이지로 돌아가기...
 */

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // ** 이벤트 처리에 사용될 요소 찾기. **
    // 포스트 고유 아이디(번호(faq_id))를 가지고 있는 요소 찾기.
    const faqId = document.querySelector('input#faq_id');
        
    // 목록 버튼 찾기.
    const btnList = document.querySelector('button#faqboard-view-btnList');
    
    // 수정 버튼 찾기
    const btnModify = document.querySelector('button#faqboard-modify');
    
    // 삭제 버튼 찾기
    const btnDelete = document.querySelector('button#faqboard-delete');
    
    // 목록 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnList.addEventListener('click', () => {
        location.href = '/fourmen/forum/faqboard';
    });
    
    // 삭제 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
        if (result) { // result === true: 사용자가 [확인(Yes)]을 선택하면
            location.href = `faqboard-detail/delete?faq_id=${faqId.value}`; // delete 요청을 보냄.
            // location.href = `faqboard-detail/delete?id=${inputId.value}`; 와
            // location.href = `/faqboard-detail//delete?faq_id=${inputId.value}`;의 차이: URL 경로의 시작 부분에서 차이가 있음.
            //-> /이 없는 주소는 현재 페이지의 URL을 기준으로 상대적인 경로를 나타냄. 현재 페이지의 URL에서 faqboard-detail/delete?faq_id=${faqId.value}를 추가하는 페이지로 이동.
            //-> /이 없는 주소의 요청 URL은 http://localhost:8081/fourmen/forum/faqboard-detail/delete?id=() 임!
            //-> /이 있는 주소는 절대 경로를 사용함. 이는 도메인의 루트(http://localhost:8081/)에서부터 faqboard-detail/delete?faq_id=${faqId.value}를 추가하는 페이지로 이동.
            //-> /이 있는 주소의 요청 URL은 http://localhost:8081/faqboard-detail/delete?faq_id=() 임!
    }
    //-> location.href: 현재 창의 URL을 변경하는 역할.
    //-> location.href에 delete?id=${faqId.value}를 할당하여 페이지를 이동.
    //-> faqID.value는 포스트 고유 아이디(번호)를 나타냄.
    //-> localhost:8081/fourmen/delete?id=(포스트 고유 아이디(번호)) 요청이 서버로 전달됨.
    });
});