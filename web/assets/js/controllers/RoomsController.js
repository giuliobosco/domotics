/**
 * @author giuliobosco
 * @version 1.1 (2019-05-04 - 2019-05-09)
 */

app.controller('RoomsController', ['$scope', '$sce', 'RoomsService', function ($scope, $sce, roomsService) {
	function load() {
		roomsService.then(function (data) {
			if (data.logout && data.logout == 'logout') {
				window.location = "index.html#!/login";
			}

			$scope.rooms = data.rooms;
		});
		console.log('ok');
	}

	load();
	setInterval(load, 5000);
}]);