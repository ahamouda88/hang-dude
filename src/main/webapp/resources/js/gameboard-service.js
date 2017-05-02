(function() {
	var application = angular.module("application"), gameBoardPath = '/api/board';

	// Game Board Service
	function GameBoardService($http) {

		// Return word categories
		this.getCategories = function() {
			return $http.get(gameBoardPath + '/categories');
		};
	}

	application.service("GameBoardService", GameBoardService);
})();