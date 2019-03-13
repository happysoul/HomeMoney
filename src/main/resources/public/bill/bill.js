var myApp = angular.module('myApp', []);

// 获取地址栏参数的方式
myApp.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		// 必须配置为false，否则<base href=''>这种格式带base链接的地址才能解析
		requireBase : false
	});
} ]);


myApp.controller('billCtrl', function($scope, $http, $filter, $location) {

	$http.get(ROOT + "/bill/getBills").success(function(response) {
		$scope.list = response.content;
	});

	//获取地址栏参数的方法
	$scope.uname = '';
	if ($location.search().u) {
		$scope.uname = $location.search().u;
	}

	$scope.fName = '';
	$scope.lName = '';
	$scope.passw1 = '';
	$scope.passw2 = '';

	$scope.edit = true;
	$scope.error = false;
	$scope.incomplete = false;
	
	$scope.edit= function(id) {
		if (id == 'new') {
			$scope.edit = true;
			$scope.incomplete = true;
			$scope.fName = '';
			$scope.lName = '';
		} else {
			$scope.edit = false;
			$scope.fName = $scope.list[id - 1].fName;
			$scope.lName = $scope.list[id - 1].lName;
		}
	};
	
	$scope.reset=function(){
		$scope.incomplete = true;
		$scope.edit = false;
		
	};

	$scope.$watch('passw1', function() {
		$scope.test();
	});
	$scope.$watch('passw2', function() {
		$scope.test();
	});
	$scope.$watch('fName', function() {
		$scope.test();
	});
	$scope.$watch('lName', function() {
		$scope.test();
	});

	$scope.test = function() {
		if ($scope.passw1 !== $scope.passw2) {
			$scope.error = true;
		} else {
			$scope.error = false;
		}
		$scope.incomplete = false;
		if ($scope.edit
				&& (!$scope.fName.length || !$scope.lName.length
						|| !$scope.passw1.length || !$scope.passw2.length)) {
			$scope.incomplete = true;
		}
	};
})
