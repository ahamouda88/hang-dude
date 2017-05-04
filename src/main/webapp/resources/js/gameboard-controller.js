(function() {
	var application = angular.module('application'),
		imageRootPath = 'resources/images/',
		maxAttempts = 8,
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
			// Only add characters if word is not completed
			if($scope.currentboard.completed || $scope.currentboard.failed) return;
			
			var character = element.currentTarget.value,
				response = GameBoardService.addCharacter(character);
			
			response.success(function(data) {
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

		// Loading all boards currently being played
		$scope.loadAllBoards = function() {
			var response = GameBoardService.getAllBoards();
			response.success(function(data) {
				$scope.allBoards = data;
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
			/*
			 * If number of attempts is reached, then display the last hangdude image, or
			 * if word is completed then display the last image of the array, otherwise
			 * display the image based on the num of attempts
			 */
			$rootScope.currentboard.image = board.failed ? images[images.length - 2] : 
											board.completed ? images[images.length - 1] : images[board.numOfAttempts];
		};
	}

	application.controller('GameBoardController', GameBoardController);
})();