app.controller('adminFunctions', function($scope,$http,$routeParams,$location,$route){
    $scope.allCountries;

    $http({
           method : 'GET',
           url : 'http://localhost:8080/register/country/adminGuiRest'
           }).then(function(response){
              $scope.allCountries = response.data;
           });

    $http({
          method : 'GET',
          url : 'http://localhost:8080/register/musicinstrument/getInstruments'
          )}.then(function(response)){
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
            data : $scope.musicinstrument
        });

        console.log($scope.newInstrument);
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
});