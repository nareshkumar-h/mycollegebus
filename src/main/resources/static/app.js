var app = angular.module('myApp',["ngRoute"]);
app.constant("config", {
	apiUrl : "http://localhost:5000",
	baseUrl: '/'
});
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "_partials/login.html"
    })
    .when("/routes", {
        templateUrl : "_partials/routes.html",
        controller : "RouteController"
    })
     .when("/routes_stats", {
        templateUrl : "_partials/routes_stats.html",
        controller : "RouteStatsController"
    })
     .when("/route_capacity", {
        templateUrl : "_partials/route_capacity.html",
        controller : "RouteCapacityController"
    })
    .when("/students", {
        templateUrl : "_partials/students.html",
        controller : "StudentController"
    })
    .when("/student_routes", {
        templateUrl : "_partials/students_routes.html",
        controller : "StudentRouteController"
    });
    
});
    
app.controller('MainController' , function($scope){
	console.log("MainController");
	
	
});

app.controller('HeaderController' , function($rootScope, $scope, $location){
	console.log("HeaderController");
	
	if ( $rootScope.loggedInUser == null ) {
		var user = localStorage.getItem("LOGGED_IN_USER");
		if (user!=null ) {
			console.log("Set LocalStorage LoggedInUser details in rootScope");
			$rootScope.loggedInUser = JSON.parse(user);
		}
	}	

	$scope.logout = function() {
		console.log("Logout");
		localStorage.clear();
		$rootScope.loggedInUser = null;
		$location.path("/");
	}
	
	
});
app.controller('AuthController' , function($rootScope, $scope , $http , config, $location){
	console.log("AuthController");
	
	console.log("Auth:" + JSON.stringify(config));
	
	$scope.user = { "email" : "ashpeekay23@gmail.com", "password" : "pass123"};
	
	$scope.login = function() {
		var user = $scope.user;
		
		$http.post(config.apiUrl + "/auth/login", JSON.stringify(user) ).then ( function ( response){
			console.log("Login Response:" + JSON.stringify(response.data));			
			if ( response != null ){
				var loggedInUser = response.data;
				$rootScope.loggedInUser = loggedInUser;
				localStorage.setItem("LOGGED_IN_USER", JSON.stringify(loggedInUser));
				$location.path("/student_routes");
			}
		});
		
	}
	
	
});
app.controller("RouteController", function( $scope, $http){	

	$http.get("json/routes.json").then(function(response){
		$scope.routedetails = response.data;
		localStorage.setItem("ROUTE_DETAILS", JSON.stringify(response.data));
		//console.log(JSON.stringify(response));
	});
	
	$scope.loadRoutes = function(){
		console.log("LoadRoutes:" + $scope.route_no);
		var route_no = $scope.route_no;
		if ( route_no != null ) {
			$scope.routedetails = _.where( $scope.routedetails , {"route_no": route_no});
		}
		else
		{
			$scope.routedetails  = JSON.parse(localStorage.getItem("ROUTE_DETAILS"));
		}
	}
	
	$scope.getNoOfStudents = function(route_id) {
		
		var totalStudents = 0;
		/*$http.get("json/students_routes.json").then(function(response){
			var students = response.data;
			totalStudents = _.where( students , {"route_id" : route_id}).length;
			console.log("Total Students for route: " + route_id + ", count= " + totalStudents );
			//console.log(JSON.stringify(response));
		});*/
		
		return totalStudents;
		
	}
	
});

app.controller("RouteStatsController", function( $scope, $http){

	$scope.init = function () 
	{
		loadRoutes();
		loadStudentRoutes();
		
	}
	
	function loadRoutes() 
	{
		$http.get("json/routes.json").then(function(response){
			$scope.routedetails = response.data;
			localStorage.setItem("ROUTE_DETAILS", JSON.stringify(response.data));
			//console.log(JSON.stringify(response));
		});
	}
	
	function loadStudentRoutes(routes){
		
		var routeMap = {};
		$http.get("json/students_routes.json").then(function(response){
			var students = response.data;
			
			var routeIds = _.uniq(_.pluck(students, "route_id"));
			for( var i in routeIds){
				var routeId = routeIds[i];
				var studentsCount = _.where( students , {"route_id" : routeId}).length;
				routeMap[routeId] = studentsCount;
			}
					
		});
		$scope.routeMap = routeMap;
		console.log(JSON.stringify(routeMap));
	}	
	
	
	
});


app.controller("RouteCapacityController", function( $scope, $http){

	$scope.init = function () 
	{
		loadStudentRoutes();
		loadRoutes();
	}
	
	$scope.selectRoute = function(routeId) {		
		loadStudentRoutes();
	}
	
	function loadRoutes() 
	{
		var routeDetails = [];
	
		var routeMap = $scope.routeMap;
		
		$http.get("json/routes.json").then(function(response){
			var routes = response.data;
			localStorage.setItem("ROUTE_DETAILS", JSON.stringify(response.data));
			
			
			var uniqRouteNos =  _.uniq( _.pluck(routes, "route_no"));
			var uniqRouteIds =  _.uniq( _.pluck(routes, "id"));
			console.log("Unique RouteNos: "+ JSON.stringify(uniqRouteIds));
			for(var i in uniqRouteNos){
				var routeNo = uniqRouteNos[i];
				
				var route_list = _.where(routes, {"route_no" : routeNo});
				console.log("Route List: "+ JSON.stringify(route_list) );
				if ( route_list && route_list.length >= 0 ) {
					var route_name = route_list[0].name +"  to " + route_list[route_list.length-1].name;
					console.log("Route name: " + route_name );
					
					var routeIds = _.pluck ( _.where(routes, {"route_no" : routeNo }) , "id");
					console.log("RouteNo: "+ routeNo + ", routeIds= " + JSON.stringify(routeIds) );
					var totalStudents = 0;
					for ( var j in routeIds) {
						var routeId = routeIds[j];
						var count = routeMap[routeId];
						if ( count ) {
							totalStudents += count;
						}
					}
					var obj = { "route_no" : routeNo , "route_name" : route_name , "count" : totalStudents};	
					routeDetails.push(obj);
					
				}		
			}
			
			
			//console.log(JSON.stringify(response));
		});
		$scope.routeDetails = routeDetails;
		
	}
	
	function loadStudentRoutes(routes){
		
		var routeMap = {};
		$http.get("json/students_routes.json").then(function(response){
			var students = response.data;
			
			
			var routeIds = _.uniq(_.pluck(students, "route_id"));
			for( var i in routeIds){
				var routeId = routeIds[i];
				var studentsCount = _.where( students , {"route_id" : routeId}).length;
				routeMap[routeId] = studentsCount;
			}
					
		});
		
		
		$scope.routeMap = routeMap;
		console.log(JSON.stringify(routeMap));
	}	
	
	
	
});

app.controller("StudentController", function( $scope, config, $http){	

	$http.get(config.apiUrl + "/users").then(function(response){
		$scope.students = response.data;
		console.log(JSON.stringify(response));
		localStorage.setItem("STUDENT_DETAILS", JSON.stringify(response.data));
		//console.log(JSON.stringify(response));
	});
	
	$http.get("json/students_routes.json").then(function(response){
		$scope.student_routes = response.data;
		localStorage.setItem("STUDENT_ROUTE_DETAILS", JSON.stringify(response.data));
		//console.log(JSON.stringify(response));
	});
	
	$scope.getRouteDetails = function(routeId) {
		var routes =  JSON.parse(localStorage.getItem("ROUTE_DETAILS"));
		var route = _.where(routes, {"id" : 1})[0];
		return "Route:" + route.route_no + ", Pickup Point- " + route.name + "( " + route.pickup_time + ")";
		
	}
	
});

app.controller("StudentRouteController", function( $scope, $http){	

	$http.get("json/students.json").then(function(response){
		$scope.students = response.data;
		localStorage.setItem("STUDENT_DETAILS", JSON.stringify(response.data));
		//console.log(JSON.stringify(response));
	});
	
	$http.get("json/students_routes.json").then(function(response){
		var student_routes = response.data;
		localStorage.setItem("STUDENT_ROUTE_DETAILS", JSON.stringify(response.data));
		var uniqueRoutes = _.where( student_routes, {})
		//console.log(JSON.stringify(response));
		
		$scope.student_routes = student_routes;
	});
	
	$scope.getRouteDetails = function(routeId) {
		var routes =  JSON.parse(localStorage.getItem("ROUTE_DETAILS"));
		var route = _.where(routes, {"id" : routeId})[0];
		return "Route:" + route.route_no + ", Pickup Point- " + route.name + "( " + route.pickup_time + ")";
		
	};
	
	$scope.getStudentName = function(id) {
		console.log("getStudentName: " + id);
		var students =  JSON.parse(localStorage.getItem("STUDENT_DETAILS"));		
		console.log("students"  + JSON.stringify(students));
		
		var student = _.where(students, {"id" : id})[0];
		
		return  student !=null ? student.name : "";
		
	};
});
