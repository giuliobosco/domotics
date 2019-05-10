/**
 * @author giuliobosco
 * @version 1.2 (2019-05-04 - 2019-05-10)
 */

app.controller('RoomsController', ['$scope', '$sce', 'RoomsService', function ($scope, $sce, roomsService) {

	var versionCount = 0;
	function load() {
		roomsService.getRooms(versionCount).then(function (data) {
			if (data.logout && data.logout == 'logout') {
				window.location = "index.html#!/login";
			}

			$scope.rooms = data.rooms;
			console.log(data);
		});
		versionCount++;
	}

	load();
	setInterval(load, 5000);
}]);