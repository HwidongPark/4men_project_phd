document.addEventListener("DOMContentLoaded", () => {
    const deletedImageIds = new Array();
	const worksid = document.getElementById('worksid').value;

    document.addEventListener('click', (event) => {
        const clickedElement = event.target;

        if (clickedElement.matches('#btnImgDelete')) {
            const confirmation = confirm('정말 삭제하시겠습니까?');

            if (confirmation) {
                const row = clickedElement.closest('tr');
                row.classList.add('d-none');

                const imgId = row.querySelector('input[name="imgid"]').value;
                console.log(imgId);
                deletedImageIds.push(imgId);
            }
        }
    });

    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();

        const formData = new FormData(document.getElementById('worksModifyForm'));

        // 파일 업로드 요소 추가
      //  const fileInput = document.querySelector('input[type="file"]');
      //  if (fileInput.files.length > 0) {
      //      for (let i = 0; i < fileInput.files.length; i++) {
      //          formData.append('upload_files', fileInput.files[i]);
      //      }
      //  }

        // deletedImageIds를 JSON 형태로 추가
        formData.append('deletedImageIds', JSON.stringify(deletedImageIds));

        fetch(`/fourmen/artist/works_modify?worksid=${worksid}`, {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
				console.log(response);
                //return response.json();
                window.location.href = `/fourmen/artist/artist_works?worksid=${worksid}`;
                return;
            }
            throw new Error('네트워크 응답 NOT OK.');
        })
        .catch(error => {
            console.error('처리 중 오류가 발생했습니다:', error);
            // 처리 실패 시 에러 처리
        });
    });
});
