/**
 * myinfo.js
 */

document.addEventListener('DOMContentLoaded', function() {
    
    const form = document.querySelector('#myinfo-form');
    
    
    const fileInput = document.querySelector('#myinfo-profile-image-file');
    
    // existingSFileName은 myinfo.jsp에 정의해 놓음
    let newSFileName = '';
    const fileNameDiv = document.querySelector('#myinfo-file-name');
    
    const btnUpdate = document.querySelector('#btnUpdate');
    const btnDelete = document.querySelector('#btnDelete');
    
    const password = document.querySelector('#password');
    const passwordConfirm = document.querySelector('#passwordConfirm');
    const realPassword = document.querySelector('#realPassword');
    
    const profileImage = document.querySelector('#myinfo-profile-image');
    
    //비밀번호 변경 모달 관련
    const btnChangePassword = document.querySelector('#btnChangePassword');
    const modalPassword = document.querySelector('#modalPassword');
    const modalPasswordConfirm = document.querySelector('#modalPasswordConfirm');
    const modalNewPassword = document.querySelector('#modalNewPassword');
    const modalNewPasswordConfirm = document.querySelector('#modalNewPasswordConfirm');
    
    const checkPasswords = function() {
        if (password.value == '' || passwordConfirm.value == '') {
            alert('비밀번호를 입력해주세요');
            return;
        } else if (password.value != passwordConfirm.value) {
            alert('비밀번호가 서로 일치하지 않습니다.');
            return;
        } else if (password.value != realPassword.value) {
            alert('비밀번호가 틀립니다.');
            return;
        } else {
           // TODO:함수받아서 해도 될듯
        }
    }
    
    
    // 업데이트 이벤트 리스너
    btnUpdate.addEventListener('click', function() {
        if (password.value == '' || passwordConfirm.value == '') {
            alert('비밀번호를 입력해주세요');
            return;
        } else if (password.value != passwordConfirm.value) {
            alert('비밀번호가 서로 일치하지 않습니다.');
            return;
        } else if (password.value != realPassword.value) {
            alert('비밀번호가 틀립니다.');
            return;
        } else {    // 비밀번호 일치, 올바를 때
            // TODO: 새로운 저장파일이 빈 문자열 아닐 시axios로 새로운 저장파일 삭제 후 submit처리
             if (newSFileName != existingSFileName && newSFileName != '') {
                  url = `/fourmen/mypage/temp-profile/delete/${newSFileName}`;
                  
                 axios.delete(url)
                .then(response => {
                    // 파일 업로드 성공 시 동작
                    newSFileName = response.data;
                    console.log(`${response.data}`);
                 })
                 .catch(error => {
                    console.log(`ERROR!!!!! ${error}`);
                 });
                 
                 newSFileName = existingSFileName;   
             }
             
             // 기존에 저장돼 있던 프로필 파일 로컬에서 삭제.. 만약 default이미지일 시 제외
             if (existingSFileName != 'default_user.png' && existingSFileName != newSFileName && newSFileName != '') {
                 axios.delete(`/fourmen/mypage/temp-profile/delete/${existingSFileName}`)
                 .then((response) => {
                      console.log(`${response.data}`);
                  })
                 .catch((error) => {
                      console.log(`ERROR!!!!! ${error}`);
                  });                 
             }

            
                        
            form.submit();
        }                
            

        
    });
    
    
    // 삭제 이벤트 리스너
    btnDelete.addEventListener('click', function() {
        if (password.value == '' || passwordConfirm.value == '') {
            alert('비밀번호를 입력해주세요');
            return;
        } else if (password.value != passwordConfirm.value) {
            alert('비밀번호가 서로 일치하지 않습니다.');
            return;
        } else if (password.value != realPassword.value) {
            alert('비밀번호가 틀립니다.');
            return;
        } else {
            // 만약 새로운 파일 업로드 했을 경우 임시 이미지 삭제.
            if (newSFileName != existingSFileName && newSFileName != '') {
                  url = `/fourmen/mypage/temp-profile/delete/${newSFileName}`;
                  
                 axios.delete(url)
                .then(response => {
                    // 파일 업로드 성공 시 동작
                    console.log(`${response.data}`);
                 })
                 .catch(error => {
                    console.log(`ERROR!!!!! ${error}`);
                 });
                 
                 
            }
            
            // 기존에 저장돼 있던 프로필 파일 로컬에서 삭제.. 만약 default이미지일 시 제외
            if (existingSFileName != 'default_user.png') {
                axios.delete(`/fourmen/mypage/temp-profile/delete/${existingSFileName}`)
                .then((response) => {
                     console.log(`${response.data}`);
                 })
                .catch((error) => {
                     console.log(`ERROR!!!!! ${error}`);
                 });                 
            }
            
             
           // 이미지 삭제 후 탈퇴
           location.href="/fourmen/mypage/myinfo/delete";
        }
    });
    
    
    // file input이벤트 리스너
    fileInput.addEventListener('change', function() {
                
        //이미지 파일만 업로드할 수 있도록 함
        // 파일 확장자 가져옴
        const extension = fileInput.value.substring(fileInput.value.lastIndexOf('.'));
        
        // 이미지 파일이 아닌 경우 파일입력 못하도록 막음
        if (extension != '.apng' && extension != '.gif' && extension != '.ico' && extension != '.cur' && extension != '.jpg'
            && extension != '.jpeg' && extension != '.jfif' && extension != '.pjpeg' && extension != '.pjp' && extension != '.png' && extension != '.svg' && extension != '') {
                
            alert('이미지 파일만 추가할 수 있습니다.');
            fileInput.value = '';
            
            newSFileName = existingSFileName;
            profileImage.src = `/fourmen/image/${ newSFileName }`;
            
            return;            
            
        }
        
        
        // 파일 이름 가져옴
        const fileName = fileInput.value.replace(/^.*[\\\/]/, '');
        
        // 파일 가져옴
        const file = fileInput.files[0];
        
        const formData = new FormData();
        // formData에 파일 추가
        formData.append('file', file);
        
        
        if (fileInput.value != '') {    // 파일 업로드 했을 시
            fileNameDiv.innerHTML = `파일명: ${ fileName }`;                    
            
            axios.post('/fourmen/mypage/temp-profile', formData, {
                  headers: {
                    'Content-Type': 'multi part/form-data'
                  }
            }).then(response => {
              // 파일 업로드 성공 시 동작
              newSFileName = response.data;
              console.log(`${response.data}`);
              
              profileImage.src = `/fourmen/image/${ newSFileName }`;
              
            }).catch(error => {
              console.log(`ERROR!!!!! ${error}`);
            });
            
            
        } else {    // 업로드 안했을 시
             fileNameDiv.innerHTML = '';
             
             url = `/fourmen/mypage/temp-profile/delete/${newSFileName}`;
             axios.delete(url)
            .then(response => {
                // 파일 업로드 성공 시 동작
                console.log(`${response.data}`);
             })
             .catch(error => {
                console.log(`ERROR!!!!! ${error}`);
             });
            
            // TODO: axios로 newSFileName의 DB지우기
            newSFileName = existingSFileName;
            profileImage.src = `/fourmen/image/${ newSFileName }`;

        }                
        
    });
    
    
    
    // 페이지 벗어날 때 만약 새로운 프로필 임시 저장 파일이 있을 경우 삭제
    window.addEventListener('beforeunload', function() {
        
        // 만약 새로운 파일 업로드 했을 경우 임시 이미지 삭제.
        if (newSFileName != existingSFileName && newSFileName != '') {
              url = `/fourmen/mypage/temp-profile/delete/${newSFileName}`;
              
             axios.delete(url)
            .then(response => {
                // 파일 업로드 성공 시 동작
                newSFileName = response.data;
                console.log(`${response.data}`);
             })
             .catch(error => {
                console.log(`ERROR!!!!! ${error}`);
             });   
         }
        
    });
    
    
    
    //모달 페스워드 변경 관련
    btnChangePassword.addEventListener('click', function() {
       
       const passwordValue = modalPassword.value;
       const passwordConfirmValue = modalPasswordConfirm.value;
       const newPasswordValue = modalNewPassword.value;
       const newPasswordConfirmValue = modalNewPasswordConfirm.value;

       
       if (passwordValue == '' || passwordConfirm == '' || newPasswordValue == ''
            || newPasswordConfirmValue == '') {
            
            alert('모든 칸을 다 입력해야 합니다.');
            return;
                
        } else if (newPasswordValue != newPasswordConfirmValue) {
            
            alert('변경할 두 비밀번호가 일치하지 않습니다.');
            return;
            
        } else if (passwordValue != passwordConfirmValue) {
            
            alert('입력하신 현 비밀번호가 서로 일치하지 않습니다.');
            return;
                        
        } else if (passwordValue != realPassword.value) {
            
            alert('비밀번호가 일치하지 않습니다.');
            return;
            
        } else {    // 위의 모든 조건들이 충족할 때 
            
            const proceed = confirm('정말 비밀번호를 변경하시겠습니까?');
            
            if (proceed) {
                
                data = {
                    newPassword: newPasswordValue
                }
            
                axios.post('/fourmen/mypage/myinfo/changepassword', data)
                    .then((response) => {
                        if (response.data == 1) {   // 비밀번호 변경 성공
                            alert('비밀번호 변경 성공');
                            location.reload();
                        } else if (response.data == 0) {    // 비밀번호 변경 실패
                            alert('비밀번호 변경 실패');
                            location.reload();
                        }
                    })
                    .catch((error) => {
                        console.log(`ERROR!!!!! ${error}`);
                    })
                
            } else {
                
                location.reload();
                
            }
            
            
            
                       
        }
       
        
    });
    
    // TODO: 시간있으면 axios로 password가져와서 맞는지 비교하는거 하기.. 일단은 그냥 함
    // TODO: 시간 있으면 중복되는 코드 메서드만들어서 구현..

}) // DOMLoaded 끝
