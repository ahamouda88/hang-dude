(function() {
	var application = angular.module('application'),
		boardCookieKey = 'currentboard',
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
		         imageRootPath + 'image_9.jpeg'
		        ];

	// Game Board Controller
	function GameBoardController($scope, $rootScope, $cookies, $location, GameBoardService) {

		
		// Get Countries function
		$scope.getCategories = function() {
			var response = GameBoardService.getCategories();
			response.success(function(data) {
				$scope.categories = data;
			});
		};
		
		// Get current board
		$scope.getCurrentBoard = function() {
			var response = GameBoardService.getCurrentBoard();
			response.success(function(data) {
				
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
				setCurrentBoard(data);
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
				setCurrentBoard(data);
			});
		};
		
		$scope.loadCurrentBoard = function() {
			var board = $cookies.get(boardCookieKey);
			if(board){
				$rootScope.currentboard = JSON.parse(board);
			}
		};

		$scope.initWelcomePage = function() {
			$scope.getCategories();
			$scope.loadCurrentBoard();
		};
		
		setCurrentBoard = function(data) {
			$scope.currentboard = data;
			$scope.currentboard.image = images[data.numOfAttempts];
			
			$cookies.put(boardCookieKey, JSON.stringify($scope.currentboard));
		};
	}

	application.controller('GameBoardController', GameBoardController);
})();