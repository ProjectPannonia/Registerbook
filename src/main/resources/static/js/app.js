var app = angular.module('memberregistrationsystem',['ngRoute','ngResource']);
    /*.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
*/
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
    }).when('/bandRegister',{
        templateUrl :'template/bandregister.html',
        controller : 'bandregister'
    }).otherwise({
       redirectTo : '/home',
       templateUrl : '/template/home.html',
        controller : 'home'
    });
});