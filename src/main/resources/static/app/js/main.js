var banka = angular.module("Banka",["ngRoute"]);

banka.controller("HomeCtrl", function($scope, $http, $location){
	
	var urlRacuni = "/api/racuni";
	var urlBanke = "/api/banke";
	var urlTipovaRacuna = "/api/tipoviracuna";
	
	$scope.racuni = [];
	$scope.banke = [];
	$scope.tipoviRacuna = [];
	
	$scope.newRacun = {};
	$scope.newRacun.imeIPrezime = "";
	$scope.newRacun.jmbg = "";
	$scope.newRacun.bankaId = "";
	$scope.newRacun.brojRacuna = "";
	$scope.newRacun.tipRacunaId = "";	
	
	$scope.search = {};
	$scope.search.bankaId = "";
	$scope.search.jmbg = "";

	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getRacuni = function(){
		
		var config = {params:{}};
		
		if($scope.search.bankaId != ""){
			config.params.bankaId = $scope.search.bankaId;
		}
		if($scope.search.jmbg != ""){
			config.params.jmbg = $scope.search.jmbg;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(urlRacuni, config);
		promise.then(
			function success(res){
				$scope.racuni = res.data;
				$scope.totalPages = res.headers("totalPages");
				
			},
			function error(res){
				alert("Couldn't fetch racuni.");
			}
		);
	}
	
	getRacuni();
	
	var getBanke = function(){
		var promise = $http.get(urlBanke);
		promise.then(
			function success(res){
				$scope.banke = res.data;
			},
			function error(res){
				alert("Couldn't fetch banke.");
			}
		);
	}
	
	getBanke();
	
	$scope.getTipoviRacuna = function(){
		var promise = $http.get(urlTipovaRacuna);
		promise.then(
			function success(res){
				$scope.tipoviRacuna = res.data;
			},
			function error(res){
				alert("Couldn't fetch tipovi.");
			}
		);
	}
	

	$scope.doAdd = function(){
		
		$http.post(urlRacuni, $scope.newRacun).then(
			function success(res){
				getRacuni();
				
				$scope.newRacun = {};
			},
			function error(){
				alert("Couldn't save the racun");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/racuni/edit/" + id);
	}
	
	$scope.goToPrenos = function(){
		$location.path("/racuni/prenos/");
	}
	

	$scope.doSearch = function(){
		console.log($scope.search);
		$scope.pageNum = 0;
		getRacuni();
	}
	
	$scope.doResetSearch = function(){
		$scope.search.bankaId = "";
		$scope.search.jmbg = "";
		$scope.pageNum = 0;
		getRacuni();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getRacuni();
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(urlRacuni + "/" + id);
		promise.then(
			function success(){
				getRacuni();
			},
			function error(){
				alert("Račun ne može biti obrisan. (Stanje na računu mora biti 0 dinara.)");
			}
		);
	}
	
});

banka.controller("RacunEditCtrl", function($scope, $http, $routeParams, $location){
	
	var urlRacuna = "/api/racuni/" + $routeParams.id;
	var urlBanke = "/api/banke";
	
	$scope.racun = {};
	$scope.racun.imeIPrezime = "";
	$scope.racun.JMBG = "";
	$scope.racun.brojRacuna = "";
	$scope.racun.stanjeNaRacunu = "";
	
	$scope.banke = [];
	
	var getBanke= function(){
		var promise = $http.get(urlBanke);
		promise.then(
			function success(res){
				$scope.banke = res.data;
				getRacun();
			},
			function error(res){
				alert("Couldn't fetch banke.");
			}
		);
	}
	
	var getRacun = function(){
		var promise = $http.get(urlRacuna);
		promise.then(
			function success(res){
				$scope.racun = res.data;
			},
			function error(res){
				alert("Couldn't fetch racun.");
			}
		);
	}
	
	getBanke();
			
	$scope.doEdit = function(){
		$http.put(urlRacuna, $scope.racun).then(
			function success(){
				$location.path("/");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});

banka.controller("PrenosSredstavaCtrl", function($scope, $http, $location){
	
	var urlPrenos = "/api/racuni/prenos";

	$scope.prenos = {};
	$scope.prenos.saRacun = "";
	$scope.prenos.naRacun = "";
	$scope.prenos.iznos = "";
	
	$scope.prenesi = function(){
		
		$http.post(urlPrenos, $scope.prenos).then(
			function success(res){
				alert("Prenos uspesan.")
				$location.path("/");
				
			},
			function error(){
				alert("Prenos neuspesan");
			}
		);
	}
});


banka.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
		})
		.when('/racuni/prenos', {
			templateUrl : '/app/html/nalog-za-prenos.html'
		})
		.when('/racuni/edit/:id', {
			templateUrl : '/app/html/racun-edit.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);