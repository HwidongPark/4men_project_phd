document.addEventListener('DOMContentLoaded', function() {
    const btnNavToggle = document.querySelector('button.nav-collapse-btn');
    const navItemsList = document.querySelectorAll('ul.nav-btn-lists');

    btnNavToggle.addEventListener('click', function() {
        console.log('내브 토글 clicked');
        for (let navList of navItemsList) {
            navList.classList.toggle("nav-lists-hide");
        }
        
    })


})