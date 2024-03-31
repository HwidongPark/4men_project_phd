/**
 * 
 *
 */
document.addEventListener('DOMContentLoaded',()=>{
	const form= document.querySelector('form#detail-user');

	const inputuserid = document.querySelector('input#userid');
	
	const inputpassword = document.querySelector('input#password');
	
	const inputname = document.querySelector('input#name');
	
	const inputnickname = document.querySelector('input#nickname');
	
	const inputphone = document.querySelector('input#phone');
	
	const inputemail = document.querySelector('input#email');
	
	const inputgrade = document.querySelector('input#grade');
	
	const btndelete = document.querySelector('button#btndelete');
	
	const btnupdate = document.querySelector('button#btnupdate');
	
	btndelete.addEventListener('click',()=>{
		const result =confirm('회원탈퇴를 허용하시겠습니까?');
		if(result) {
			alert('회원탈퇴가 완료되었습니다');
			location.href=`delete?userid=${inputuserid.value}`;
		}
	});
	
	btnupdate.addEventListener('click',()=>{
		if(inputname.value ==='' || inputnickname.value==='' || inputphone.value==='' || inputemail.value==='' || inputgrade.value===''){
			alert('회원정보를 모두 입력해야 합니다');
			return;
		}
		const result = confirm('회원정보를 수정하시겠습니까?');
		if(result){
			form.action = 'update';
			form.method = 'post';
			form.submit();
			alert('회원수정이 완료되었습니다');
		}
		
	});
	
});