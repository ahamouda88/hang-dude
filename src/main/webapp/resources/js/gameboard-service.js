(function() {
	var application = angular.module("application"), 
		gameBoardPath = '/api/board';

	// Game Board Service
	function GameBoardService($http) {

		// Return word categories
		this.getCategories = function() {
			return $http.get(gameBoardPath + '/categories');
		};

		// Return current board
		this.getCurrentBoard = function() {
			return $http.get(gameBoardPath);
		};

		// Add Board
		this.addBoard = function(data) {
			if (data === undefined) return;

			return $http.post(gameBoardPath, data);
		};

		// Add Character
		this.addCharacter = function(character) {
			if (character === undefined) return;

			// TODO: Will add logic to check if success or number of attemps are over! (Here or Controller?)
			return $http.get(gameBoardPath + '/' + character);
		};
	}

	application.service("GameBoardService", GameBoardService);
})();