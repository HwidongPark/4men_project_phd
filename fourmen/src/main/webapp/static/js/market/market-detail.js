/**
 * market-detail.js
 */
document.addEventListener('DOMContentLoaded', function() {
    const carouselImagesWrapper = document.querySelector('.carousel-images-wrapper');
    const carouselImageContainer = document.querySelector('.carousel-images-container');
    // 각각 사진의 div리스트
    const eachImageDivs = document.querySelectorAll('.carousel-each-image');
    // 각각 사진들 리스트
    const carouselImages = document.querySelectorAll('.carousel-image');
    //버튼 리스트
    const slideButtons = document.querySelectorAll('.slide-button');
    
    // 슬라이드 사진들
    const imageSlideItems = document.querySelectorAll('.image-slide-item');
    
    // 삭제버튼
    const btnMarketDelete = document.querySelector('#market-btn-delete');
    const btnMarketUpdate = document.querySelector('#market-btn-update');
    
    // workId
    const workId = document.querySelector('#workId');
    console.log(workid.innerText);
    // 사진 리스트 폭 초기화
    const numOfPhotos = carouselImages.length;
    console.log(numOfPhotos);
    carouselImagesWrapper.style.width = `${ numOfPhotos * 100 }%`;
    
    // 사진 인덱스
    let photoIndex = 0;

    let scrollbarPosition = 0;
    
	
    // 해당 사진에 테두리로 보여주기
    const giveSlidePhotoBorder = (direction) => {
		switch(direction) {
			case -1: 	// 이전 버튼
				if (photoIndex > 0) {
					photoIndex--;
					for (let imageSlideItem of imageSlideItems) {
						imageSlideItem.style.border = 'none';
					}
					imageSlideItems[`${photoIndex}`].style.border = '4px solid black';
				}
				break;			
			case 1: 	// 다음 버튼
				if (photoIndex < numOfPhotos - 1) {					
					photoIndex++;
					for (let imageSlideItem of imageSlideItems) {
						imageSlideItem.style.border = 'none';
					}
					imageSlideItems[`${photoIndex}`].style.border = '4px solid black';					
				}
				break;			
			default:
				console.log("에러");
		}
	}

    // 버튼 활성화 비활성화
    const deactivateSlideButtons = (isTrue) => {
        slideButtons.forEach((button) => {
            button.disabled = isTrue;
        })
    }


    // 모든 사진 다 보여주기
    const showAllPhotos = () => {
        for (let index = 0; index < numOfPhotos; index++) {
            carouselImages[index].style.visibility = 'visible';
        }
    }

    // 해당 인덱스의 사진만 보여주기
    const showOnlyThePhoto = (photoIndex) => {
        setTimeout(() => {
            for (let index = 0; index < numOfPhotos; index++) {
                // console.log(`index=${index}반복문 안`)
                if (photoIndex === index) {
                    // console.log(`index=${index} 보이게함`)
                    carouselImages[photoIndex].style.visibility = 'visible';
                } else {
                    // console.log(`index=${index} 안보이게함`);
                    carouselImages[index].style.visibility = 'hidden';
                }
            }
        }, 800)
        
    }
    

	
	// 버튼 움직이기
    for (let button of slideButtons) {
        button.addEventListener('click', () => {
            showAllPhotos();
            const direction = button.id === 'prev-slide' ? -1 : 1;
            const scrollAmount = eachImageDivs[0].getBoundingClientRect().width * direction;
            
            carouselImageContainer.scrollBy({left: scrollAmount, behavior: "smooth"});

            // 아래 작은사진 테두리 효과 줌, photoIndex조절
            giveSlidePhotoBorder(direction);

            // TODO:여기와
            setTimeout(function() {
                let scrollMaxWidth = carouselImageContainer.scrollWidth - carouselImageContainer.clientWidth;
                scrollbarPosition = carouselImageContainer.scrollLeft / scrollMaxWidth;
                // console.log(`scrollbarPosition=${scrollbarPosition}`);
                deactivateSlideButtons(false);  // 버튼 다시 활성화
            }, 800)
            
            showOnlyThePhoto(photoIndex);
            // console.log(`photoIndex=${photoIndex}`);
            deactivateSlideButtons(true);   // 일단 버튼 잠시 비활성화시키고

        })
    }


    carouselImageContainer.addEventListener('scroll', () => {
        // 스크롤 최대 폭
        let scrollMaxWidth = carouselImageContainer.scrollWidth - carouselImageContainer.clientWidth;
                        
        slideButtons[0].style.display = carouselImageContainer.scrollLeft <= 0 ? 'none' : 'block';
        slideButtons[1].style.display = Math.ceil(carouselImageContainer.scrollLeft) >= scrollMaxWidth ? 'none' : 'block';
    })
	
	
	// 스크린 사이즈 변화마다 사진폭 바꾸기
	window.addEventListener('resize', (e) => {
        let scrollMaxWidth = carouselImageContainer.scrollWidth - carouselImageContainer.clientWidth;
        // console.log(`scrollbar position = ${scrollbarPosition}`); 
		    // 사진 리스트 폭 초기화
    	carouselImageContainer.scrollTo({left: scrollbarPosition * scrollMaxWidth, behavior: 'smooth'});
		// console.log("크기 변하는 중");
	})
        
  
    
    
    btnMarketUpdate.addEventListener('click', () => {
        
    });
    

})