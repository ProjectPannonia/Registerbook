app.controller('searchDropdown',function($scope,$http,$location,$route){
    $scope.names = ["Id","Name","Band","City","Country","Instrument"];

    $scope.push = function(){

        $scope.property = [$scope.selectedName,$scope.content];

        if($scope.content != null && $scope.selectedName != null){
             $http({
                    method : 'POST',
                    url : 'http://localhost:8080/register/member/searchproperty',
                    data : $scope.property,
                    }).then(function(response){
                         $scope.searched = response.data;
                    },function(errResponse){
                         $scope.errorMessage = errResponse.data.errorMessage;
                    });
        }
    };
});