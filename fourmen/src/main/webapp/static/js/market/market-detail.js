/**
 * market-detail.js
 */
document.addEventListener('DOMContentLoaded', function() {
    // 찜하기, 찜 취소, 쪽지보내기 버튼
    const btnRequestDeal = document.querySelector('#market-request-deal');    
    const btnAddWishList = document.querySelector('#market-add-to-wishlist');
    const btnRemoveWishList = document.querySelector('#market-remove-from-wishlist');
        
    // 거래요청 모달
    const modalBody = document.querySelector('.modal-to-show');
    const requestDealForm = document.querySelector('#request-deal-form');
    
    // 모달 거래요청 보내기 버튼
    const btnSendRequest = document.querySelector('#market-send-request');
    // 모달 닫기 버튼
    const btnCloseModal = document.querySelector('#market-request-deal-close');
    
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
    const workId = document.querySelector('#workid');
    console.log(workid.innerText);
    
    // 게사자 아이디
    const writerId = document.querySelector('#artist-userid');
    
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
    
    
    // 찜하기
    btnAddWishList.addEventListener('click', function() {       
       console.log('찜하기 클릭');
       console.log(`signedInUser=${signedInUser}`);
       
       if (signedInUser == "") {    // 로그인 안했을 시 로그인시킴
           const target = encodeURIComponent(`/fourmen/market/detail?workid=${ workId.value }`);
           console.log(`target=${target}`);
           
           location.href=`/fourmen/signup?target=${target}`;
           
           return;
       } else if (signedInUser == writerId.value) {   // 본인 게시글일시 본인글임을 알리고 끝냄
           alert('본인글은 찜할 수 없습니다.');
           return;
       }
       
       const data = {
           userId: signedInUser,
           workId: workId.value
       };
       
       axios.post('/fourmen/market/wishlist', data)
       .then((response) => {
           console.log(`response=${response.data}`);
           if (response.data === 1) {
               alert('찜하기 성공');
               location.reload();
           } else if (response.data === 0) {
               alert('이미 찜한 게시글입니다.');
           }
       })
       .catch ((error) => {
           console.log(`에러발생: ${error}`);
       })
       
    });       

    
    // 찜 취소
    btnRemoveWishList.addEventListener('click', function() {
		
	    console.log('찜하기 취소 클릭');
        console.log(`signedInUser=${signedInUser}`);
       
        if (signedInUser == "") {    // 로그인 안했을 시 로그인시킴
            alert("로그인되지 않았습니다.");
            location.reload();
                       
            return;
        }
       
        const data = {
            userId: signedInUser,
            workId: workId.value
        };
       
        axios.post('/fourmen/market/wishlist/remove', data)
        .then((response) => {
            console.log(`response=${response.data}`);
            if (response.data === 1) {
                alert('찜하기 취소 성공');
                location.reload();
            } else if (response.data === 0) {
                alert('찜하기 취소 실패. 다시 시도해주세요.');
                location.reload();
            }
        })
       .catch ((error) => {
           console.log(`에러발생: ${error}`);
       })
				
	});
    

    // 거래요청 버튼 클릭    
    btnRequestDeal.addEventListener('click', function() {
                
        if (signedInUser == "") {    // 로그인 안했을 시 로그인시킴
            const target = encodeURIComponent(`/fourmen/market/detail?workid=${ workId.value }`);
            console.log(`target=${target}`);
            
            location.href=`/fourmen/signup?target=${target}`;
            
            modalBody.setAttribute('id', '');
            
            return;
        } else if (signedInUser == writerId.value) {  // 본인글일 시 경고메세지 띄움
            
            modalBody.setAttribute('id', '');
            
            alert('본인글에 거래요청을 보낼 수 없습니다.');
            
            location.reload();
            return;
        } else {
            modalBody.setAttribute('id', 'requestDeal');
            btnRequestDeal.click();
        }
        
    });

    
    
    // 거래요청 보내기 버튼 클릭
    btnSendRequest.addEventListener('click', function() {
        const messageTitle = document.querySelector('#market-message-title');
        const messageContent = document.querySelector('#market-message-content');
        const messagePriceOffered = document.querySelector('#market-message-price-offered');
        
        console.log(`제시가격=${messagePriceOffered.value}`);
        
        const data = {
            title: messageTitle.value,
            content: messageContent.value,
            price_offered: messagePriceOffered.value,
            recipient: writerId.value,
            sender: signedInUser,
            workId: workId.value
        }
        
        if (messageTitle.value == '' || messageContent.value == '' || messagePriceOffered.value == '') {    // 빈칸이 있을 경우 빈칸 입력시
            alert('제목, 내용, 제시가격을 모두 입력해야 합니다.');
            
            return;
            
        } else {    // 모두 입력한 경우에만 메세지 보낼 수 있도록 함
            axios.post('/fourmen/mypage/message', data)
            .then((response) => {
                
                btnCloseModal.click();
                
            })
            .catch((error) => {
                
                console.log(`에러 발생!! ERROR: ${error}`);
                
            })
        }
        

        
    });
    
    
}); // DOMLoaded 이벤트리스너 끝