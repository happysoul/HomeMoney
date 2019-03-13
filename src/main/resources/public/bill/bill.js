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

	$scope.bill={};
	
	$scope.uname="";
	//获取地址栏参数的方法
	if ($location.search().u) {
		$scope.bill.name = $location.search().u;
		$scope.uname=$location.search().u;
	}
	
	//下拉框
	$scope.bill.type=1;
	$scope.types=[{"id":0,"name":"未分类"},
		{"id":1,"name":"个人消费"},
		{"id":2,"name":"共同消费"},
		{"id":3,"name":"家庭成员消费(如代缴话费等)	"}];
	
	$scope.bill.signTime=new Date();
	$("input[name='bill.signTime']").datetimepicker({
		language: 'zh-CN',
//		minuteStep:30,
		minView:2,
		format: "yyyy-mm-dd",//hh:ii
		autoclose: true,
		todayBtn: true,
		todayHighlight:true
	}).on('changeDate', function(ev){
		if(ev.date){
			$scope.bill.signTime=ev.date;
		}
	});
	
	//重置
	$scope.reset=function(){
		if($scope.uname!='')$scope.bill.name=$scope.uname;
		$scope.bill.id="";
		$scope.bill.money="";
		$scope.bill.type=1;
		$scope.bill.prepaid=0;
		$scope.bill.mark="";
		$("input[name='bill.signTime']").datetimepicker('setDate',new Date());
	}
	$scope.reset();
	
	//编辑
	$scope.edit= function(id) {
		if (id>0) {
			$scope.bill.id = $scope.list[id-1].id;
			$scope.bill.name = $scope.list[id-1].name;
			$scope.bill.money = $scope.list[id-1].money;
			$scope.bill.mark = $scope.list[id-1].mark;
			$scope.bill.type = $scope.list[id-1].type;
			$scope.bill.prepaid=($scope.list[id-1].prepaid==0)?false:true;
			$("input[name='bill.signTime']").datetimepicker('setDate',new Date($scope.list[id-1].signTime));
			
		}
	};
	
	//保存
	$scope.save=function(){
		$scope.bill.prepaid=($scope.bill.prepaid)?1:0;
		$.post(ROOT + "/bill/save",$scope.bill,function(res){
			$scope.table();
		});
	}
	

	$scope.$watch('bill.money', function() {
		
	});
	
	$scope.table=function(){
		$http.get(ROOT + "/bill/getBills").success(function(res) {
			$scope.list = res.content;
		});
		$scope.reset();
	}
	$scope.table();
	
})
