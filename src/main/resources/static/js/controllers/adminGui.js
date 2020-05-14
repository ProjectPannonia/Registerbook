app.controller('admingui', function($scope,$http,$routeParams,$location,$route){
    $scope.load = function(){
    $http({
                method : 'GET',
                url : 'http://localhost:8080/register/member/adminGuiRest'
        }).then(function(response){
                $scope.allCountries = response.data;
        });
    };


});