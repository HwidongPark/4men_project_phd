document.addEventListener('DOMContentLoaded', function() {
    const btnNavToggle = document.querySelector('button.nav-collapse-btn');
    // const navItemsList = document.querySelectorAll('ul.nav-btn-lists');
    const navItemsList = document.querySelector('ul.nav-btn-lists');

     // 내정보 아이콘
    const myPageProfileIcon = document.querySelector("#my-page-profile-icon");
    const myPageOuterContainer = document.querySelector("#my-page-slide-outer-container");


    btnNavToggle.addEventListener('click', function() {
        console.log('내브 토글 clicked');
        //for (let navList of navItemsList) {
          //  navList.classList.toggle("nav-lists-hide");
        //}
        
        navItemsList.classList.toggle("nav-lists-hide");
        
        
    })

    
    myPageProfileIcon.addEventListener('click', function() {
        console.log("프로필 아이콘 버튼 누름");
        myPageOuterContainer.classList.toggle('my-page-open');
        myPageOuterContainer.classList.toggle('my-page-close');
    })
    
    
})