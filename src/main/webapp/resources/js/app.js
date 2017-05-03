(function() {
	var application = angular.module('application', [ 'ngRoute', 'ngCookies' ]);

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

	var checkRouting = function($location, $rootScope) {
		if ($rootScope.currentboard) {
			return true;
		}
		$location.path("/");
	};
})();
