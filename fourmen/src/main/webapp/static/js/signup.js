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
			
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
	function checkPassword(e){
		if(e.target.value===''){
			pwdChecked = false;
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
		if(e.target.value===''){
			nameChecked = false;
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
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	function checkPhone(e){
		if(e.target.value===''){
			phoneChecked = false;
		} else {
			phoneChecked = true;
		}
		if(idChecked && pwdChecked && nameChecked && nicknameChecked && phoneChecked && emailChecked){
			btnSignup.classList.remove('disabled');
		} else {
			btnSignup.classList.add('disabled');
		}
	}
	
	function checkEmail(e){
		if(e.target.value===''){
			emailChecked = false;
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