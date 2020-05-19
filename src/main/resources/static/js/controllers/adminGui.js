app.controller('admingui', function($scope,$http,$routeParams,$location,$route){
    $scope.allCountries;

    $http({
           method : 'GET',
           url : 'http://localhost:8080/register/country/adminGuiRest'
           }).then(function(response){
              $scope.allCountries = response.data;
           });

    $http({
          method : 'GET',
          url : 'http://localhost:8080/register/musicinstrument/getCountries'
    });
    $scope.delete = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/country/deleteAllCountries'
        })
    };
    $scope.drop = function(){
        $http({
            method : 'DELETE',
            url : 'http://localhost:8080/register/country/drop'
        })
    };
    $scope.addNewCountry = function(){
        let actualInstrument = $scope.newInstrument;

        $http({
            method : 'POST',
            url : 'http://localhost:8080/register/musicinstrument/createNewInstruemnt',
            data : actualInstrument
        })
        console.log(actualInstrument);
    };
});