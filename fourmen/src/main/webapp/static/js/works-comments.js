/**
 * 아티스트 -> 작품에 달릴 댓글에 대한 JS 파일
 * 
 * 댓글 목록 접기 / 펼치기
 * 
 * 댓글 등록
 * 
 * 댓글 수정
 * 
 * 댓글 삭제
 * 
 */
document.addEventListener('DOMContentLoaded', () => {

	// bootstrap 모듈의 Collapse 생성자를 호출해서 bootstrap Collapse 객체 생성.
	const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });
	// JS 객체 생성 => {} toggle false => 접혀있는 상태.

	const btnToggleComment = document.querySelector('button#btnToggleComment');

	btnToggleComment.addEventListener('click', () => {

		bsCollapse.toggle();

		if (btnToggleComment.textContent === '감상평 남기기') {
			btnToggleComment.textContent = '감상평 닫기';

			getAllComments();

		} else {
			btnToggleComment.textContent = '감상평 남기기';
		}
	});

	const btnAddComment = document.querySelector('button#btnAddComment');

	btnAddComment.addEventListener('click', registerComment);

	const modal = new bootstrap.Modal('div#commentModal', { backdrop: true });

	console.log(modal);

	const btnUpdateComment = document.querySelector('button#btnUpdateComment');
	console.log(btnUpdateComment);
	
	btnUpdateComment.addEventListener('click', updateComment);
	
	async function updateComment(e) {
		
		const comment_id = document.querySelector('input#modalCommentId').value;
		const comment_content = document.querySelector('textarea#modalCommentText').value;
		
		console.log(comment_id);
		console.log(comment_content);
		
		if(comment_content == '') {
			alert('댓글을 입력하세요....');
			return;
		}
		
		if(!confirm('수정한 내용을 저장할까요??..')){
			return;
		}
		
		
		await
			axios.put(`../api/comment/${comment_id}`, {comment_content})
			.then((response) => {
				console.log(response);
				
				if(response.data === 1) {
					alert('댓글 수정 성공...!');
					
					modal.hide();
					
					getAllComments();
				}
			}).catch((error) => {
				console.log(error);
			});
	}

	function registerComment(e) {
		const worksid = document.querySelector('input#worksid').value;
		const comment_content = document.querySelector('textarea#comment_content').value;
		const comment_writer = document.querySelector('input#comment_writer').value;

		if (comment_content === '') {
			alert('댓글을 입력하세요...!');
			return;
		}

		const data = { worksid, comment_content, comment_writer }
		console.log(data);

		axios.post('../api/comment', data)
			.then((response) => {
				console.log(response);
				if (response.data === 1) {
					alert('댓글 등록 했습니다...!');
					document.querySelector('textarea#comment_content').value = '';
					getAllComments();
				}
			}).catch((error) => {
				console.log(error);
			});
	}// end of RegisterComment Funtion


	function getAllComments() {
		// 댓글 목록을 요청하기 위한 WORKSID 
		const worksid = document.querySelector('input#worksid').value;

		const uri = `../api/comment/all/${worksid}`;

		axios.get(uri)
			.then((response) => {
				console.log(response);

				makeCommentElements(response.data);
			}).catch((error) => {
				console.log(error);
			});
	}// end of GetAllComments Function.

	function makeCommentElements(data) {
		const divComments = document.querySelector('div#comments');

		let htmlStr = '';
		for (let comment of data) {
			console.log(comment);

			const currentTime = new Date(comment.modified_time);
			const koreanTime = new Date(currentTime.getTime() + (9 * 60 * 60 * 1000));
			const formattedTime = koreanTime.toLocaleString('ko-KR', {timeZone: 'Asia/Seoul'});
	
			htmlStr += `
			<div class = "card card-body my-2">
			
					<div class="pb-2 border-bottom">
						<span class = "d-none">${comment.comment_id}</span>
						<span class = "fs-4">${comment.comment_writer}</span>
						<span class = "text-secondary">${formattedTime}</span>	
					</div>
				
				<div class = "my-3 p-2"><span class = "fs-4">${comment.comment_content}</span></div>		
			`;

			if (comment.comment_writer === 'TEST') {
				htmlStr += `
					<div class = "mt-1 d-grid gap-1 d-md-flex justify-content-md-end">
						<button class="btnCommentDelete btn btn-outline-secondary btn-sm"
						data-id="${comment.comment_id}">삭제</button>
						<button class="btnCommentModify btn btn-outline-secondary btn-sm"
						data-id="${comment.comment_id}">수정</button>
					</div>`;
			}
			htmlStr += '</div>';
		}
		divComments.innerHTML = htmlStr;
		const btnDeletes = document.querySelectorAll('button.btnCommentDelete');
		for (let btn of btnDeletes) {
			btn.addEventListener('click', deleteComment);
		}

		const btnModifies = document.querySelectorAll('button.btnCommentModify');
		for (let btn of btnModifies) {
			btn.addEventListener('click', showCommentModal);
		}
	}// end of makeCommentElements
	
	function deleteComment(e) {
		console.log(e.target);
		
		
		
		const result = confirm('정말 삭제 할까요???...');
		if(!result) {
			return;
		}
		
		const comment_id = e.target.getAttribute('data-id');
		
		console.log(comment_id);
		console.log(typeof comment_id);
		
		const uri = `../api/comment/${comment_id}`;
		axios.delete(uri)
		.then((response) => {
			console.log(response);
			
			if(response.data === 1){
				alert('댓글 삭제 완료...!');
				getAllComments();
			}
		}).catch((error) => {
			console.log(error);
		});	
	}// end of function deleteComment().
	
	async function showCommentModal(e) {
		
		const comment_id = e.target.getAttribute('data-id');
		
		try{
			const response = await axios.get(`../api/comment/${comment_id}`);
			console.log(response);
			
			const comment_Id = response.data.comment_id;
			const comment_Content = response.data.comment_content;
			
			document.querySelector('input#modalCommentId').value = comment_Id;
			document.querySelector('textarea#modalCommentText').value = comment_Content;
			
			modal.show();
		} catch(error){
			console.log(error);
		}
		
	}// end of showCommentModal
	
});