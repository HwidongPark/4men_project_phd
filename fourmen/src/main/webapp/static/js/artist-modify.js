/**
 * artist-modify.js
 * 
 * /artist/artist_modify.jsp에 포함
 * 
 * 아티스트의 정보를 업테이트 하는 기능
 */

//TODO: text-area의 있는 ${artist.artist_bio_kor}, ${artist.artist_bio_eng}를 지워야한다.
//자꾸 걔네를 가져가서 dto가 인식하지 못하는 상황이 벌어지고 있음...


 document.addEventListener("DOMContentLoaded", () => {
    // form 요소를 찾음.
    const form = document.querySelector('form#postModifyForm');
    // 글 번호(id)를 가지고 있는 요소를 찾음.
    // const inputUserId = document.querySelector('input#userid');

    // 포스트 제목(title)을 가지고 있는 요소를 찾음.
    const textAreaBioKor = document.querySelector('textarea#artist_bio_kor');

    // 포스트 내용(content)를 가지고 있는 요소를 찾음.
    const textAreaBioEng = document.querySelector('textarea#artist_bio_eng');
    
    // 이미지 업로드인데 이미지 업로드까지 업데이트 할 필요가 있을까???...

    // 수정 완료 버튼 찾기.
    const btnUpdate = document.querySelector('input#btnUpdate');

    // 수정 완료 버튼의 클릭 이벤트 리스너를 등록
    // JS가 form을 어떻게 submit 하느냐
    btnUpdate.addEventListener('click', () => {
        // 제목, 내용이 비어있는 지 체크:
        // value 만 사용. innerHTML 사용 X
        if (textAreaBioKor.value === '' || textAreaBioEng.value === '') {
            alert('소개글 내용은 반드시 입력해야 합니다.');
            return; // 함수 종료
        }

        const result = confirm('정말 수정할까요?');

        if (result) {
            form.action = 'update'; // 폼 양식을 제출(submit)할 주소. 기본 값은 현재 URL.
            form.method = 'post'; // 폼 요청 방식 get or post. 기본값은 'get'.
            form.submit(); // 폼 제출(서버로 요청 보냄)
        }

    });


});