var app = angular.module('app', []);

app.controller('homeController', function($scope, $http) {

	$scope.textTranslation = function(){
		if(!$scope.inputText){
			alert("Text field is empty. Please type any text before trying to translate.");
		}else{
			$http.get("/textTranslation/" + $scope.inputText).then(function(response) {
				$scope.translationResult = response.data;
			});
		}
	};
	
	$scope.conceptExpansion = function(){
		if(!$scope.inputConception){
			alert("Concept field is empty. Please type any concept before trying to expand it");
		}else{
			$http.get("/conceptExpansion/" + $scope.inputConception).then(function(response) {
				if (response.data.length > 0){
					var text = '';
					for (i = 0; i < response.data.length; i++) { 
					    text += response.data[i].name + "\r\n";
					}
				}
				$scope.conceptionResult = text;
			});
		}
	};
	
	$scope.saveConception = function(){
		if($scope.inputConception && $scope.conceptionResult){
			var sentence = $scope.conceptionResult.replace(/\n/g,"---");
			$http.post("/save/" + $scope.inputConception + "/" + sentence).then(function(response) {
				alert("Concept Saved");
				$scope.conceptionResult = "";
				$scope.inputConception = "";
			});
		}else{
			alert("Concept field or Concept Expansion field is empty. Please get any concept from Watson before trying to save it.");
		}
	};
	
	$scope.searchConception = function(){
		if(!$scope.inputConception){
			alert("Concept field is empty. Please type any concept before the search.");
		}else{
			$http.get("/search/" + $scope.inputConception).then(function(response) {
				if(!response.data){
					alert("Conception not found on Database.");
				}else{
					$scope.conceptionResult = response.data.conceptExpasion.replace(/---/g,"\r\n");
				}
			});
		}
	};
});