var myApp = angular.module('myApp', []);

myApp.controller('instrumentController', ['$scope', '$http', '$window', function($scope, $http, $window) {
		$scope.instrument = {id: "", name:"", type: "", eqSettings: []};
		$scope.bandSets = [["PEAK", "SHELF"], ["HI", "LOW"], ["NONE"]];


		$scope.getInstrument = function(instrumentId, type) {
	    	// Send the request
	    	$scope.instrument.id = instrumentId;
		
	    	$http.get('/music/instruments/data/' + instrumentId + '_' + type)
	    	.then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
	    		delete response.data['new'];
	    		$scope.instrument = response.data;
	    	}, function errorCallback(response) {
		   	    // This callback will be called asynchronously when an error 
	    		// occurs or the server returns response with an error status
		    	$window.location.href = '/music/?lang=' + $window.language;
	    	});
	    }

		$scope.getNew = function() {
	    	// Send the request
	    	$http.get('/music/instruments/data/1_ei')
	    	.then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
	    		response.data.id = 0;
	    		response.data.name = "";
	    		delete response.data['new'];
	    		$scope.instrument = response.data;
	    	}, function errorCallback(response) {
		   	    // This callback will be called asynchronously when an error 
	    		// occurs or the server returns response with an error status
		    	$window.location.href = '/music/?lang=' + $window.language;
	    	});
		}
		
		$scope.update = function() {
	        var data = $scope.instrument;
	        
	        for (var i = 0; i < data.eqSettings.length; ++i) {
	        	if (!data.eqSettings[i].active) {
	        		data.eqSettings[i].active = false;
	        	}
	        }

	        $http.post('/music/instruments/update', data).then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
		    	$window.location.href = '/music/instruments/view/' + data.id + '_' + data.type 
		    			+ '?lang=' + $window.language;
	    	}, function errorCallback(response) {
	    	    // This callback will be called asynchronously when an error 
	    		// occurs or the server returns response with an error status
		    	$window.location.href = '/music?lang=' + $window.language;
	    	});
		}
		
		$scope.save = function() {
	        var data = $scope.instrument;
	        
	        for (var i = 0; i < data.eqSettings.length; ++i) {
	        	if (!data.eqSettings[i].active) {
	        		data.eqSettings[i].active = false;
	        	}
	        }

	        $http.post('/music/instruments/add', data).then(function successCallback(response) {
	    	    // This callback will be called asynchronously when the 
	    		// response is available
		    	$window.location.href = '/music/instruments/view/' + response.data + '_' + data.type 
		    			+ '?lang=' + $window.language;
	    	}, function errorCallback(response) {
	    	    // This callback will be called asynchronously when an error 
	    		// occurs or the server returns response with an error status
		    	$window.location.href = '/music?lang=' + $window.language;
	    	});
		}
	    
	    $scope.options = function(bandType) {
	    	if (bandType == "LOW" || bandType == "HI") {
	    		return $scope.bandSets[0];
	    	}
	    	if (bandType == "LOW_MID") {
	    		return $scope.bandSets[1];
	    	}

    		return $scope.bandSets[2];
	    }

}]);

setLanguage = function(language, search, tbId) {
	window.language = language;
	
	if (search) {
		// Input text box will be appended at the end automatically
		$(document).ready(function() {
		  $('#'+tbId).DataTable({
			destroy: true,
			bPaginate: false,
			info: false,
		    oLanguage: {
		      sSearch: search
		    }
		  });
		});
	}
}
