var app = angular.module('memberegistrationsystem',['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider.when('/list-all-members',{
        templateUrl : '/template/listmember.html',
        controller : 'listmembercontroller'
    })
});