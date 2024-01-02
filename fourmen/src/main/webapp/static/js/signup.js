/**
 * 
 */
document.addEventListener('DOMContentLoaded',()=>{
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  container.classList.remove("right-panel-active");
});
	
	let idChecked = false;
	let pwdChecked = false;
	let nameChecked = false;
	let nicknameChecked = false;
	let phoneChecked = false;
	let emailChecked= false;
	
	const btnSignup = document.querySelector('button#btnSignup');
	
	const inputUserid = document.querySelector('input#userid');
	inputUserid.addEventListener('change',checkUserid);
	const inputPassword = document.querySelector('input#password');
	inputPassword.addEventListener('change',checkPassword);
	const inputName = document.querySelector('input#name');
	inputName.addEventListener('change',checkName);
	const inputNickname = document.querySelector('input#nickname');
	inputNickname.addEventListener('change',checkNickname);
	const inputPhone = document.querySelector('input#phone');
	inputPhone.addEventListener('change',checkPhone);
	const inputEmail = document.querySelector('input#email');
	inputEmail.addEventListener('change',checkEmail);
	
	async function checkUserid(e){
		const userid=e.target.value;
		const uri = `checkid?userid=${userid}`;
		const response= await axios.get(uri);
		
		const checkIdResult = document.querySelector('div#checkIdResult');
		if(e.target.value.length <=30){
		if(response.data ==='Y'){
			idChecked = true;
			checkIdResult.innerHTML = '사용 가능한 아이디 입니다';
			checkIdResult.classList.remove('text-danger');
			checkIdResult.classList.add('text-success');
		} else{
			idChecked = false;
			checkIdResult.innerHTML = '중복되는 아이디 입니다';
			checkIdResult.classList.remove('text-success');
			checkIdResult.classList.add('text-danger');
			
		}}else{
			idChecked = false;
			e.target.value=null;
			alert('올바른 아이디 형태가 아닙니다');
			return;
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
	function checkPassword(e){
		if(e.target.value==='' || e.target.value.length >=30){
			pwdChecked = false;
			e.target.value=null;
			alert('올바른 비밀번호 형태가 아닙니다');
			return;
		} else {
			pwdChecked = true;
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
	
	function checkName(e){
		if(e.target.value==='' || e.target.value.length >=20){
			nameChecked = false;
			e.target.value=null;
			alert('올바른 이름 형태가 아닙니다');
			return;
		} else {
			nameChecked = true;
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
async function checkNickname(e){
		const nickname=e.target.value;
		const uri = `checknickname?nickname=${nickname}`;
		const response= await axios.get(uri);
		
		const checkNicknameResult = document.querySelector('div#checkNicknameResult');
		if(e.target.value.length <=30){
		if(response.data ==='Y'){
			nicknameChecked = true;
			checkNicknameResult.innerHTML = '사용 가능한 닉네임 입니다';
			checkNicknameResult.classList.remove('text-danger');
			checkNicknameResult.classList.add('text-success');
		} else{
			nicknameChecked = false;
			checkNicknameResult.innerHTML = '중복되는 닉네임 입니다';
			checkNicknameResult.classList.remove('text-success');
			checkNicknameResult.classList.add('text-danger');
		}} else{
			nicknameChecked = false;
			e.target.value=null;
			alert('올바른 닉네임 형태가 아닙니다');
			return;
			
		}
		
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	function checkPhone(e){

  	if( e.target.value >= 0 && e.target.value <= 1000000000000){
		 if(e.target.value.length >=9){
			phoneChecked = true; 
		 } else{
			 phoneChecked = false;
			 e.target.value=null;
			 alert('올바르지 않은 전화번호입니다 . -제외하고 입력해주세요');
			 return;
			 
		 }
    	
  	}else{
		  
		  phoneChecked = false;
		  e.target.value=null;
  	alert('전화번호로 입력해주세요');
  	return;
	}
		
	   /*if(e.target.value===''){
			phoneChecked = false;
		} else {
			phoneChecked = true;
		}*/
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
	
	function checkEmail(e){
		if(e.target.value==='' || e.target.value.length >=30){
			emailChecked = false;
			e.target.value=null;
			alert('30자 이내로 이메일 작성해주세요');
			return;
		} else {
			emailChecked = true;
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
});