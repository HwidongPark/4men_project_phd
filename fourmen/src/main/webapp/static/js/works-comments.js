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

	const modalReply = new bootstrap.Modal('div#replyCommentModal', { backdrop: true});
	
	console.log(modalReply);
	
	const btnUpdateComment = document.querySelector('button#btnUpdateComment');
	console.log(btnUpdateComment);
	
	btnUpdateComment.addEventListener('click', updateComment);
	
	const btnReplyComment = document.querySelector('button#btnReplyComment');
	console.log(btnReplyComment);
	
	btnReplyComment.addEventListener('click', registerReplyComment);
	
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

	async function registerReplyComment(e){
		
		const worksid = document.querySelector('input#worksid').value;
		const comment_content = document.querySelector('textarea#modalReplyCommentText').value;
		const comment_writer = document.querySelector('input#replyCommentWriter').value;
		const parent_comment_id = document.querySelector('input#modalReplyCommentId').value;
		
		console.log(worksid);
		console.log(comment_content);
		console.log(comment_writer);
		console.log(parent_comment_id);
		
		if(comment_content == ''){
			alert('댓글을 입력하세요');
			return;
		}
		if(!confirm('답글을 저장할까요??')){
			return;
		}
		
		const data  = {worksid, comment_content, comment_writer, parent_comment_id}
		console.log(data);
		 // POST 방식으로 전달
		await
			axios.post(`../api/comment`, data)
			.then((response) => {
				console.log(response);
				if(response.data === 1){
					alert('댓글 등록 했습니다...!');

					modalReply.hide();
					
					getAllComments();
				}
			}).catch((error) => {
				console.log(error);
			});
	} // end of registerReplyComment
	
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
			console.log('for문을 돌린 결과 = ' + comment);

			const currentTime = new Date(comment.modified_time);
			const koreanTime = new Date(currentTime.getTime() + (9 * 60 * 60 * 1000));
			const formattedTime = koreanTime.toLocaleString('ko-KR', {timeZone: 'Asia/Seoul'});
	
			if(comment.parent_comment_id === 0){
				htmlStr += `
					<div class = "card card-body my-2">
					
							<div class="pb-2 border-bottom">
								<span class = "d-none">${comment.comment_id}</span>
								<span class = "fs-5 fw-bold">${comment.comment_writer}</span>
								<span class = "ps-2 text-secondary">${formattedTime}</span>	
							</div>
						
							<div class = "my-3 p-2"><span class = "fs-5">${comment.comment_content}</span></div>
						
							<div class = "gap-1 d-flex justify-content-md-end">
						`;

						if(signedInUser !== null){
							htmlStr += `
									<button class = "btnCommentReply btn btn-outline-secondary btn-sm"
									data-id="${comment.comment_id}">답글 달기</button>	
								`
						}
				
						htmlStr += `<div>`;
						if (comment.comment_writer === signedInUser || signedInUser === 'admin') {
						htmlStr += ` 
									<button class="btnCommentDelete btn btn-outline-secondary btn-sm"
									data-id="${comment.comment_id}">삭제</button>
									<button class="btnCommentModify btn btn-outline-secondary btn-sm"
									data-id="${comment.comment_id}">수정</button>
								</div>`;
						}
					htmlStr += '</div>';	
				}	
		}
		divComments.innerHTML = htmlStr;
		const btnReply = document.querySelectorAll('button.btnCommentReply');
		for(let btn of btnReply) {
			if(signedInUser){
				btn.addEventListener('click', replyCommentModal);
			} else {
				 btn.addEventListener('click', () => {
			            alert('답글은 로그인한 유저만 가능합니다...!');
			        });
			} 
		}
		
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
	
	async function replyCommentModal(e) {
		
		const comment_id = e.target.getAttribute('data-id');
		
		try{
			 const response = await axios.get(`../api/comment/${comment_id}`);
		        console.log(response);

		        const comment_Id = response.data.comment_id;
		        
		        document.querySelector('input#modalReplyCommentId').value = comment_Id;
		        
		        modalReply.show(); // 대댓글을 작성할 모달 창을 보여줍니다.
		        
		} catch(error) {
			console.log(error);
		}
			
	}
	
	const btnDeleteAllComments = document.querySelector('button#btnDelete');
	btnDeleteAllComments.addEventListener('click', (e) => {
		
		const worksid = document.querySelector('input#worksid');
		const uri = `../api/comment/all/${worksid}`;
		
		axios.delete(uri)
		.then((response) => {
			console.log(response);
			
			if(response.data === 1){
				alert('모든 댓글 삭제 완료...!');
			}
		}).catch((error) => {
			console.log(error);
		});	
		
	}); // end btnDeleteAllcomments Event.
	
});