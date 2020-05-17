app.controller('admingui', function($scope,$http,$routeParams,$location,$route){
    $scope.allCountries;
    $http({
           method : 'GET',
           url : 'http://localhost:8080/register/member/adminGuiRest'
           }).then(function(response){
              $scope.allCountries = response.data;
           });
    $scope.delete = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/member/deleteAllCountries'
        })
    };
    $scope.drop = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/member/drop'
        })
    };
});