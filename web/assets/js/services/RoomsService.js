/**
 * @author giuliobosco
 * @version 1.1 (2019-05-04 - 2019-05-10)
 */

app.factory('RoomsService', ['$http', function($http) {
	var service = {};
	var urlBase = "/data/rooms";

	service.getRooms = function (version) {
		var url = urlBase + "?v=" + version;
		return $http({
			method: 'GET',
			url: url
		}).then(function (response){
			return response.data;
		},function (error){
			return error;
		});
	};

	return service;
}]);