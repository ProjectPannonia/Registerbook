app.controller('home',function($scope,$http,$location,$route){
    $http({
        method : 'GET',
        url : 'http://localhost:8080/register/band/'
    }).then(function(response){
        $scope.bands = response.data;
    });

    $scope.createBand = function(name,imagePath){
      $http({
          method : 'POST',
          url : 'http://localhost:8080/register/band',
          data : $scope.band
      });
    };
});