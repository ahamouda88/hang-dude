(function() {
	var application = angular.module("application");

	// Game Board Controller
	function GameBoardController($scope, $rootScope, GameBoardService) {

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
				alert(JSON.stringify(data));
				$scope.currentboard = data;
			});
		};

		$scope.initWelcomePage = function() {
			$scope.getCategories();
		};
	}

	application.controller('GameBoardController', GameBoardController);
})();