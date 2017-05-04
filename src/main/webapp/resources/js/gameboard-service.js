(function() {
	var application = angular.module("application"), 
		gameBoardPath = '/api/boards',
		currentBoardPath = gameBoardPath + '/current';

	// Game Board Service
	function GameBoardService($http) {

		// Return word categories
		this.getCategories = function() {
			return $http.get('/categories');
		};

		// Return current board
		this.getCurrentBoard = function() {
			return $http.get(currentBoardPath);
		};
		
		// Return all boards
		this.getAllBoards = function() {
			return $http.get(gameBoardPath);
		};

		// Add Board
		this.addBoard = function(data) {
			if (data === undefined) return;

			return $http.post(currentBoardPath, data);
		};

		// Add Character
		this.addCharacter = function(character) {
			if (character === undefined) return;
			
			return $http.get(currentBoardPath + '/' + character);
		};
	}

	application.service("GameBoardService", GameBoardService);
})();