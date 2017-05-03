(function() {
	var application = angular.module('application'),
		imageRootPath = 'resources/images/',
		images =[
		         imageRootPath + 'image_1.jpeg',
		         imageRootPath + 'image_2.jpeg',
		         imageRootPath + 'image_3.jpeg',
		         imageRootPath + 'image_4.jpeg',
		         imageRootPath + 'image_5.jpeg',
		         imageRootPath + 'image_6.jpeg',
		         imageRootPath + 'image_7.jpeg',
		         imageRootPath + 'image_8.jpeg',
		         imageRootPath + 'image_9.jpeg',
		         imageRootPath + 'success_image.jpeg'
		        ];

	// Game Board Controller
	function GameBoardController($scope, $rootScope, $location, GameBoardService) {

		// Get Categories function
		$scope.getCategories = function() {
			var response = GameBoardService.getCategories();
			response.success(function(data) {
				$scope.categories = data;
			});
		};
		
		// Add Board function
		$scope.addBoard = function() {
			var boardRequest = {
				category: $scope.board.word.category,
				difficulty: $scope.board.word.difficulty,
				username: $scope.board.username
			};
			
			var response = GameBoardService.addBoard(boardRequest);
			response.success(function(data) {
				$scope.setCurrentBoard(data);
				$location.path('/board-page');
			});
			response.error(function(data) {
				$scope.board.error = {
					errorCode : -1,
					message : 'Failed to fetch word. Please try again!'
				};
			});
		};
		
		// Add Character function
		$scope.addCharacter = function(element){
			var character = element.currentTarget.value,
				response = GameBoardService.addCharacter(character);
			
			response.success(function(data) {
				// TODO: Will add logic to check if success or number of attempts are over!
				$scope.setCurrentBoard(data);
			});
		};
		
		// Loading current board function
		$scope.loadCurrentBoard = function() {
			var response = GameBoardService.getCurrentBoard();
			response.success(function(data) {
				$scope.setCurrentBoard(data);
			});
		};

		$scope.initWelcomePage = function() {
			$scope.getCategories();
			$scope.loadCurrentBoard();
		};
		
		// Setting current board game function
		$scope.setCurrentBoard = function(board) {
			if(!board) return;
			
			$rootScope.currentboard = board;
			$rootScope.currentboard.image = images[board.numOfAttempts];
		};
	}

	application.controller('GameBoardController', GameBoardController);
})();