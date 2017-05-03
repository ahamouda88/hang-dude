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

		// Get Countries
		$scope.getCategories = function() {
			var response = GameBoardService.getCategories();
			response.success(function(data) {
				$scope.categories = data;
			});
		};
		
		// Add Board
		$scope.addBoard = function() {
			var boardRequest = {
				category: $scope.board.word.category,
				difficulty: $scope.board.word.difficulty,
				username: $scope.board.username
			};
			
			var response = GameBoardService.addBoard(boardRequest);
			response.success(function(data) {
				var currentboard = data;
				currentboard.image = images[data.numOfAttempts];
				
				$cookies.put(boardCookieKey, JSON.stringify(currentboard));
				$location.path('/board-page');
			});
			response.error(function(data) {
				$scope.board.error = {
					errorCode : -1,
					message : 'Failed to fetch word. Please try again!'
				};
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
	}

	application.controller('GameBoardController', GameBoardController);
})();