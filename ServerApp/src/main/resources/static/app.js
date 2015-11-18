
var app = angular.module('bike-app',['ngRoute','Live','Trainings']);
app.config(function($routeProvider){
	$routeProvider
		.when('/',{
			templateUrl:'home.html'
		})
		
		.when('/trainings',{
			templateUrl:'trainings.html'
		})
		
		.when('/live',{
			templateUrl:'live.html',
			
		});
});

app.controller('MainControler',function($scope){

   
});