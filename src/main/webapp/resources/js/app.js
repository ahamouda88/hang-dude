(function() {
	var application = angular.module('application', [ 'ngRoute', 'ngCookies' ]);

	// Handle routes
	application.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : '/boardPage',
			resolve : {
				factory : checkRouting
			}
		}).when('/login', {
			templateUrl : '/boardPage'
		}).otherwise({
			redirectTo : '/'
		});
	} ]);

	var checkRouting = function($rootScope, $location) {
		return true;
	};
})();
