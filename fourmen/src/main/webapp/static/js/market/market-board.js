/**
 * market-board
 */

document.addEventListener("DOMContentLoaded", () => {
	
	const createBtn = document.querySelector('.market-create-btn-container button');
	
	createBtn.addEventListener('click', () => {
		console.log('clicked');
		location.href="/fourmen/market/create";
	})
	
})