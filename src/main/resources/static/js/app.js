var app = angular.module('memberregistrationsystem',['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider.when('/list-all-members',{
        templateUrl : '/template/listmember.html',
        controller : 'listmembercontroller'
    }).when('/register-new-member',{
        templateUrl : '/template/memberregistration.html',
        controller : 'registermembercontroller'
    }).when('/update-member/:id',{
        templateUrl : '/template/memberupdate.html',
        controller : 'memberdetailscontroller'
    }).when('/search-a-member',{
        templateUrl : 'template/searchmember.html',
        controller : 'searchmembercontroller'
    }).otherwise({
       redirectTo : '/home',
       templateUrl : '/template/home.html'
    });
});