/**
 * @author giuliobosco
 * @version 1.0 (2019-05-04 - 2019-05-04)
 */

app.factory('RoomsService', ['$http', function($http) {
	return $http({
		method: 'GET',
		url: 'data_/rooms'
	}).then(function (response){
		return response.data;
	},function (error){
		return error;
	});
}]);