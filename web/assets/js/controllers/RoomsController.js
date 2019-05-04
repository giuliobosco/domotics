/**
 * @author giuliobosco
 * @version 1.0 (2019-05-04 - 2019-05-04)
 */

app.controller('RoomsController', ['$scope', '$sce', 'RoomsService', function($scope, $sce, newsService) {
	newsService.then(function(data) {
		$scope.rooms = data.rooms;
	});
}]);