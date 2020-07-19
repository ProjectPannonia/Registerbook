var app = angular.module('memberregistrationsystem',['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider.when('/list-all-members',{
        templateUrl : '/template/listMembers.html',
        controller : 'listMembers'
    }).when('/update-member/:id',{
        templateUrl : '/template/memberUpdate.html',
        controller : 'memberUpdate'
    }).when('/search-drop-down',{
        templateUrl : 'template/searchDropdown.html',
        controller : 'searchDropdown'
    }).when('/new-member-registration',{
        templateUrl : 'template/memberRegistration.html',
        controller : 'memberRegistration'
    }).when('/statistics',{
        templateUrl : 'template/statistics.html',
        controller : 'statistics'
    }).when('/adminGuiPage',{
        templateUrl : 'template/adminFunctions.html',
        controller : 'adminFunctions'
    }).when('/loginPage',{
        templateUrl : 'template/loginPage.html',
        controller : 'loginPage'
    }).otherwise({
       redirectTo : '/home',
       templateUrl : '/template/home.html',
        controller : 'home'
    });
});