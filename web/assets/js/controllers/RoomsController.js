/**
 * @author giuliobosco
 * @version 1.0.1 (2019-05-04 - 2019-05-08)
 */

app.controller('RoomsController', ['$scope', '$sce', 'RoomsService', function($scope, $sce, newsService) {
	newsService.then(function(data) {
		if (data.logout && data.logout == 'logout') {
			window.location = "index.html#!/login";
		}

		$scope.rooms = data.rooms;
	});
}]);