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

		$scope.initWelcomePage = function() {
			$scope.getCategories();
		};
	}

	application.controller('GameBoardController', GameBoardController);
})();