function hideNav(){
    document.getElementById("mainNav").style.visibility = "hidden";
    document.getElementById("triggerShow").style.visibility = "visible";
    document.getElementById("triggerHide").style.visibility = "hidden";
}

function showNav(){
    document.getElementById("mainNav").style.visibility = "visible";
    document.getElementById("triggerShow").style.visibility = "hidden";
    document.getElementById("triggerHide").style.visibility = "visible";
}

function addRoom(name, temp, light1, light2){

    $scope.id = 0;
    document.getElementById("roomTitle").innerHTML = name;
    document.getElementById("roomTemperature").innerHTML = temp;
    document.getElementById("roomLight1").checked = light1;
    document.getElementById("roomLight2").checked = light2;
}

var app = angular.module("App", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl : "AulaModel.htm"
        });
});


angular.module('ngRepeat', []).controller('repeat', function($scope) {
    $scope.rooms = [
        {name:'Aula 323', temp: 21},
        {name:'Aula 423', temp: 22},
        {name:'Aula 422', temp: 23}
    ];

    $scope.lights = [
        {desc:'porta', value: true},
        {desc:'finestra', value: false},
    ]
});

function hideContent() {
    if(document.getElementById("aulaSettings").style.display != "none"){
        document.getElementById("aulaSettings").style.display = "none";
        document.getElementById("id").style.borderRadius = "15px";
    }else{
        document.getElementById("aulaSettings").style.display = "block";
        document.getElementById("id").style.borderRadius = "15px 15px 0px 0px";
    }
}