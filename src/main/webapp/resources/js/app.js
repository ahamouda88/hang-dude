(function() {
	var application = angular.module('application', [ 'ngRoute', 'ngCookies' ]);

	// Handle routes
	application.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : '/welcomePage',
			resolve : {
				factory : checkRouting
			}
		}).otherwise({
			redirectTo : '/'
		});
	} ]);

	var checkRouting = function($rootScope, $location) {
		return true;
	};
})();
