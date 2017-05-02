(function() {
	alert("asdasd");
	var application = angular.module('application', [ 'ngRoute', 'ngCookies' ]);

	// Handle routes
	application.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : '../../WEB-INF/views/homepage.html',
//			resolve : {
//				factory : checkRouting
//			}
		}).otherwise({
			redirectTo : '/'
		});
	} ]);

	// Check first if currentUser is authenticated, before doing the route
	/*var checkRouting = function($rootScope, $location) {
		if ($rootScope.authenticated) {
			return true;
		} else {
			$location.path("/login");
			return false;
		}
	};*/
})();
