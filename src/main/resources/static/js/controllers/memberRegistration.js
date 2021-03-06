app.controller('memberRegistration',function($scope,$http,$location,$route){
        $http({
                method : 'GET',
                url : 'http://localhost:8080/register/country/getCountries'
        }).then(function(response){
            $scope.countries = response.data;
        });
        $http({
                method : 'GET',
                url : 'http://localhost:8080/register/musicinstrument/getInstruments'
        }).then(function(response){
                    $scope.instruments = response.data;
        });

    $scope.submitMemberForm = function(){
            $http({
                    method : 'POST',
                    url : 'http://localhost:8080/register/member/',
                    data : $scope.member
            }).then(function(response){
                    $location.path("/list-all-members");
                    $route.reload();
            },function(errorResponse){
                    $scope.errorMessage = errResponse.data.errorMessage;
            });
    };

    $scope.resetForm = function(){
            $scope.member = null;
    };
});
