(function(){
	
	
	'use strict';
	
	var trainingsModule = angular.module('Trainings',[]);
	
	trainingsModule.controller('trainingsController',function($scope,$http){
		
		$http.get('/training/read?id=1')
		.success(function(d){
			
		}() ;
			
			
		
		
	});
	
	
})();