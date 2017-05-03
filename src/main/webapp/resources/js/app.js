(function() {
	var application = angular.module('application', [ 'ngRoute', 'ngCookies' ]),
		boardCookieKey = 'currentboard';

	// Handle routes
	application.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : '/welcome-page'
		}).when('/board-page', {
			templateUrl : '/board-page',
			resolve : {
				factory : checkRouting
			}
		}).otherwise({
			redirectTo : '/'
		});
	} ]);

	var checkRouting = function($location, $cookies) {
		var currentboard = $cookies.get(boardCookieKey);
		if (currentboard) {
			return true;
		}
		$location.path("/");
	};
})();
