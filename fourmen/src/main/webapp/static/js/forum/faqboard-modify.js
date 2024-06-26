/**
 * faqboard-modify.js
 * /forum/freeboard-modify.jsp 에 포함.
 * 포스트 수정 완료 기능.
 * 입력 글자 수를 제한하는 기능.
 */

// 제목 글자수가 maxlength를 넘어가면 경고창을 띄우는 함수.
function input_maxlength(obj) {
    let maxCharacters = parseInt(obj.getAttribute("maxlength"));
    let currentLength = obj.value.length; // 현재 입력된 글자수
    let remainingLength = maxCharacters - currentLength;

    if (currentLength > maxCharacters) {
        alert('글자 수 제한을 초과했습니다! (공백 포함 40자)');
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
        alert('글자 수 제한을 초과했습니다! (공백 포함 1000자)');
        obj.value = obj.value.substring(0, maxCharacters);
        remainingLength = 0;
    }

    // 남은 글자수를 표시하는 요소를 찾아서 업데이트
    const remainingCharsDisplay = document.getElementById('remaining-chars');
    remainingCharsDisplay.textContent = '총 ' + currentLength + '자' + ' / ' + maxCharacters + '자';
}

// addEventListener: HTML 문서가 로드되면 실행할 함수 등록.
document.addEventListener('DOMContentLoaded', () => {
    // 이벤트 처리에 사용될 요소 찾기...
    // 포스트 제목을 가지고 있는 요소를 찾음.
    const faq_title = document.querySelector('input#faqboard-view-modify-title');

    // 포스트 내용을 가지고 있는 요소를 찾음.
    const faq_content = document.querySelector('textarea#faqboard-view-detail-content');
    
    // 포스트 번호(id)를 가지고 있는 요소를 찾음.
    const faq_id = document.querySelector('input#faq_id').value;
    
    // 수정 완료 버튼 찾기.
    const btnModify = document.querySelector('button#btn-faqboard-modify');
    
    // 삭제 버튼 찾기
    const btnDelete = document.querySelector('button#btn-faqboard-delete');
    
    // 목록 버튼 찾기.
    const btnList = document.querySelector('button#faqboard-view-btnList');
    
    // 현재 글자수와 남은 글자수를 표시할 요소 생성
    const maxCharacters = parseInt(faq_content.getAttribute("maxlength"));
    const remainingCharsDisplay = document.createElement('div');
    remainingCharsDisplay.id = 'remaining-chars';
    remainingCharsDisplay.textContent = '총 ' + faq_content.value.length + '자' + ' / ' + maxCharacters + '자';
    
    // 스타일 추가
    remainingCharsDisplay.style.textAlign = 'right'; // 글자수 표시 요소를 오른쪽 정렬
    remainingCharsDisplay.style.marginBottom = '17.5px'; // 글자수 표시 요소에 마진 설정

    // 요소 추가
    document.querySelector('.faqboard-view-detail').appendChild(remainingCharsDisplay);

    // faq_content에 이벤트 핸들러(리스너) 등록
    faq_content.addEventListener('input', () => {
        textarea_maxlength(faq_content);
    });

    // faq_title에 이벤트 핸들러(리스너) 등록
    faq_title.addEventListener('input', () => {
        input_maxlength(faq_title);
    });
    
    // 목록 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnList.addEventListener('click', () => {
        location.href = '/fourmen/forum/faqboard';
    });
    
    // 삭제 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
        if (result) { // result === true: 사용자가 [확인(Yes)]을 선택하면
            location.href = `faqboard-modify/delete?faq_id=${faq_id}`; // delete 요청을 보냄.
        }
    });
    
    // 수정 완료 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnModify.addEventListener('click', () => {
        // 이벤트 처리에 사용될 요소 찾기...
        // 포스트 제목을 가지고 있는 요소를 찾음.
        const faq_title = document.querySelector('input#faqboard-view-modify-title').value;

        // 포스트 내용을 가지고 있는 요소를 찾음.
        const faq_content = document.querySelector('textarea#faqboard-view-detail-content').value;
        
        // 제목 내용이 비어있는지 체크:
        if (faq_title === '' || faq_content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return; // 함수 종료.
        }

        const data = { faq_id, faq_title, faq_content };
        console.log(data);

        const result = confirm("정말 업데이트할까요?");
        if (result) {
            // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
            axios.post(`../forum/faqboard-modify/${faq_id}`, data) // post 방식의 Ajax 요청으로 data를 보냄.
                .then((response) => {
                    console.log(response);
                    if (response.data === 1) {
                        alert('글 업데이트 성공!');
                        location.href = '/fourmen/forum/faqboard'
                    }
                }) // 성공 응답이 왔을 때 실행할 콜백 등록.
                .catch((error) => {
                    console.log(error);
                }); // 실패 응답일 때 실행할 콜백 등록.
        } // end function registerComment
    
    });

});