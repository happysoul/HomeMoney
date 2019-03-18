var myApp = angular.module('myApp', []);

// 获取地址栏参数的方式
myApp.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		// 必须配置为false，否则<base href=''>这种格式带base链接的地址才能解析
		requireBase : false
	});
} ]);


myApp.controller('billCtrl', function($scope, $http, $filter, $compile, $location) {
	_fmts='yyyy-MM-dd';
	_fmt='yyyy-MM-dd HH:mm:ss';
	
	$scope.bill={};
	_tableId="#tb_bill";
	_model="#bill_model";
	
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
		{"id":3,"name":"家庭成员消费(如代缴话费等)"}];
	
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
			$scope.bill.signTime=$filter('date')(ev.date, _fmt);
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
	$scope.edit= function(index) {
		$scope.bill.id = $scope.list[index].id;
		$scope.bill.name = $scope.list[index].name;
		$scope.bill.money = $scope.list[index].money;
		$scope.bill.mark = $scope.list[index].mark;
		$scope.bill.type = $scope.list[index].type;
		$scope.bill.prepaid=($scope.list[index].prepaid==0)?false:true;
		$("input[name='bill.signTime']").datetimepicker('setDate',new Date($scope.list[index].signTime));
		$(_model).modal("show");
	};
	
	$scope.showModal=function(){
		console.log("add");
		$scope.reset();
		$(_model).modal("show");
	};
	
	//保存
	$scope.save=function(){
		$scope.bill.prepaid=($scope.bill.prepaid)?1:0;
//		$scope.bill.signTime=$scope.bill.signTime.getTime();
		$scope.bill.signTime=$filter('date')($scope.bill.signTime, _fmt);
		$.post(ROOT + "/bill/save",$scope.bill,function(res){
			$(_tableId).bootstrapTable("refresh");
		});
	}
	

	$scope.$watch('bill.money', function() {
		
	});
	
	
	$(_tableId).bootstrapTable({
		url: ROOT + "/bill/getBills",		//请求后台的URL（*）
		responseHandler: function(data){
			$scope.list=data.records;
			$.each(data.records, function (i, row) {
	           row._id = row.id;
	        });
			return (data)?{"rows":data.records,"total":data.total}:{"rows":[],"total":0};
        },
        method: 'get',                      //请求方式（*）
        contentType : "application/x-www-form-urlencoded",   //必须有
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "desc",                   //排序方式
        queryParams: function(param){return {"page":this.pageNumber,"size":this.pageSize}},//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10,50,100],        //可供选择的每页的行数（*）
        search: false,						//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: false,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        pagination:true,
        columns: [{
        		checkbox: true,
	        }, {
	        	align: 'center',
	            field: 'name',
	            title: '姓名'
	        }, {
	        	align: 'center',
	            field: 'money',
	            title: '花费'
	        }, {
	        	align: 'center',
	            field: 'signTime',
	            title: '记录时间',
	            formatter:function(time){
					return $filter('date')(time, _fmts);
				}
	        }, {
	        	align: 'center',
	            field: 'createTime',
	            title: '创建时间',
	            formatter:function(time){
					return $filter('date')(time, _fmts);
				}
	        }, {
	        	align: 'center',
	            field: '_id',
	            title: '操作',
	            visible:($scope.uname!='')?true:false,
	            formatter:function(value, row, index){
	            	return '<span class="_operate" data="'+row._id+'" index="'+index+'"></span>';
	            	//<button class="btn btn-primary" onclick="edit('+row._id+')">编辑</button>';
	            }
	        }],
        onLoadSuccess:function(data){
        	$("._operate").each(function(i,o){
        		$(o).append($compile('<button class="btn btn-primary" ng-click="edit('+$(o).attr("index")+')">编辑</button>')($scope));
        	});
        }
    });
	
	
})

