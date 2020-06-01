app.controller('adminFunctions', function($scope,$http,$routeParams,$location,$route){
   
    $http({
           method : 'GET',
           url : 'http://localhost:8080/register/country/getAllCountries'
          }).then(function(response){
              $scope.allCountries = response.data;
    });

    $http({
          method : 'GET',
          url : 'http://localhost:8080/register/musicinstrument/getInstruments'
          }).then(function(response){
            $scope.allInstruments = response.data;
    });

    $scope.delete = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/country/deleteAllCountries'
        });
    };

    $scope.drop = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/country/drop'
        });
    };

    $scope.addNewInstrument = function(){
        $http({
            method : 'POST',
            url : 'http://localhost:8080/register/musicinstrument/createNewInstrument',
            data : $scope.instrument
        });

        $http({
            method : 'GET',
            url : 'http://localhost:8080/register/country/getAllCountries'
        }).then(function(response){
            $scope.allCountries = response.data;
        });
    };

    $scope.createFileFromMembers = function(){
        $http({
            method : 'GET',
            url : 'http://localhost:8080/register/member/writeMembersToFile/' + $scope.savingPath
        }).then(function(response){
            $scope.message = response.data;
            $scope.savingPath = null;
        });
    };

    $scope.dropInstruments = function(){
        $http({
            method : 'GET',
            url : 'http://localhost:8080/register/musicinstrument/dropInstruments'
        }).then(function(response){
            console.log(response.data);
        });
    };
    
    $scope.deleteInstruments = function(){

    };
});