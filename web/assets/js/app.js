var app = angular.module('ViewsAPP', ['ngRoute','ngSanitize']);

app.config(function ($routeProvider) {
	// index
	$routeProvider.when('/', {
		templateUrl: 'views/index.html'
	});

	// login
	$routeProvider.when('/login', {
		templateUrl: 'views/login.html'
	});

	// rooms
	$routeProvider.when('/rooms', {
		templateUrl: 'views/rooms.html'
	});

	// else
	$routeProvider.otherwise({
		redirectTo: '/'
	});
}).run(function ($rootScope, $route) {
	$rootScope.$route = $route;
});