
	
(function (){
	'use strict';
		var liveModule = angular.module('Live',[]);
		var training = undefined;	
		var g = undefined ;
		var temp_chart; 
		var freq_chart; 
		var speed_chart; 
		var altitude_chart;
		
		var marker = null;
		
		
		
		liveModule.controller('liveController',function($scope,$http){
			$scope.humidity_data = [] ;
			$scope.temp_data = [];
			$scope.speed_data = [];
			$scope.altitude_data = [];
			$scope.freq_data = [];
			
			$scope.altitude_opt = {
					
					drawPoints: true,
                    showRoller: true,
                    valueRange: [0, 400],
                    labels: ['Time', 'Wysokość'],
				 	highlightCallback: function(event, x, points, row, seriesName) {
				 		addMarker(points[0].idx);
			        },
			        unhighlightCallback: function(event) {
			            
			        	marker.setMap(null);
				 		
			        }
			};
			
			
			
			
			
			
			
			
			$scope.showBpm = true; 
			$scope.temperature = 0;
			
			angular.element(document).ready(function(){
				
				altitude_chart = new Dygraph(document.getElementById("div_g_altitude"),$scope.freq_data,$scope.altitude_opt);
				
			/*	freq_chart = new Dygraph(document.getElementById("div_g_kad"),$scope.freq_data,{
					
					drawPoints: true,
                    showRoller: true,
                    valueRange: [0, 200],
                    labels: ['Time', 'Kadencja'],
				 	highlightCallback: function(event, x, points, row, seriesName) {
				 		addMarker(points[0].idx);
			        },
			        unhighlightCallback: function(event) {
			            
			        	marker.setMap(null);
				 		
			        }
				});
				
				speed_chart = new Dygraph(document.getElementById("div_g_speed"),$scope.freq_data,{
					
					drawPoints: true,
                    showRoller: true,
                    valueRange: [0, 80],
                    labels: ['Time', 'Prędkość'],
				 	highlightCallback: function(event, x, points, row, seriesName) {
				 		addMarker(points[0].idx);
			        },
			        unhighlightCallback: function(event) {
			            
			        	marker.setMap(null);
				 		
			        }
				});
				
				 g = new Dygraph(document.getElementById("div_g"),$scope.humidity_data,{
					
					drawPoints: true,
                    showRoller: true,
                    valueRange: [0, 100],
                    labels: ['Time', 'Wilgotność'],
				 	highlightCallback: function(event, x, points, row, seriesName) {
				 		addMarker(points[0].idx);
			        },
			        unhighlightCallback: function(event) {
			            
			        	marker.setMap(null);
				 		
			        }
					
				});
				 
				 temp_chart = new Dygraph(document.getElementById("div_g_temp"),$scope.temp_data,{
						
						drawPoints: true,
	                    showRoller: true,
	                    valueRange: [-10, 45],
	                    labels: ['Time', 'Temperature'],
					 	highlightCallback: function(event, x, points, row, seriesName) {
					 		addMarker(points[0].idx);
				        },
				        unhighlightCallback: function(event) {			            
				        	marker.setMap(null);
					 		
				        }
						
					});*/
				
				
			});
			
			var mapOptions = {
                zoom: 18,
                center: new google.maps.LatLng(51.10090716666667,16.998682666666664),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }

            $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
			
			var poly = new google.maps.Polyline({
				strokeColor: '#00BFFF',
				strokeOpacity: 5.0,
				strokeWeight: 6,
				clickable: false,

			});
			
			
			
			poly.setMap($scope.map);
			

			
			//var ctx = document.getElementById('myChart').getContext('2d');
			
			var timer = setInterval(function(){getPath()},20000);
			
			
			//var myNewChart = new Chart(ctx).Line(data);
			//Chart.defaults.global.animation = false;
			getPath();
			
			
			function addMarker(index){
	        	
				if(marker != null){
					marker.setMap(null);
				}

				 marker = new google.maps.Marker({
				    position:  new google.maps.LatLng(training.trainingPoints[index].latitude,
				    		training.trainingPoints[index].longitude),
				    
				    map: $scope.map
				 });
				
				
			}
			$scope.toggleBpm = function(data,label,range){
				
				altitude_chart.updateOptions( { 'file': data , labels: label,valueRange: range} );
				
				$scope.showBpm = !$scope.showBpm;
			document.getElementById("chart_name").innerHTML = label[1];
				
			};
			function getPath(){
				var path = poly.getPath();
				
				
				$http.get('/training/read?id=1')
				.success(function(d){
					path.clear();
					$scope.humidity_data = [];
					$scope.temp_data = [];
					$scope.freq_data = [];
					$scope.speed_data = [];
					$scope.altitude_data = [];
					training = d;
					console.log(d);
					$scope.trainingDate = function(){
						var date = new Date(d.date);
						return date.toLocaleDateString() +' '+ date.toLocaleTimeString();
					}() ;
					
					console.log($scope.trainingDate);
					
					$scope.temperature = (d.totalDistance/1000).toFixed(3);
					d.trainingPoints.forEach(function(entry){
						//data.datasets[0].data.push();
						//data.labels.push();
						$scope.humidity_data.push([entry.id,entry.humidity]);
						$scope.temp_data.push([entry.id,entry.temperature]);
						$scope.freq_data.push([entry.id,entry.bpm]);
						$scope.speed_data.push([entry.id,entry.speed]);
						$scope.altitude_data.push([entry.id,entry.altitude]);
						if(entry.latitude != null ){
							path.push(new google.maps.LatLng(entry.latitude,entry.longitude));
						};
					});
					//	g.updateOptions( { 'file': $scope.humidity_data } );
					//	temp_chart.updateOptions( { 'file': $scope.temp_data } );
					//	freq_chart.updateOptions( { 'file': $scope.freq_data } );
					//	speed_chart.updateOptions( { 'file': $scope.speed_data } );
					//	altitude_chart.updateOptions( { 'file': $scope.altitude_data } );
				});

			};
			
			
	
			
		});
	
	
	
})();
