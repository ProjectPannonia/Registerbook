app.controller('registermembercontroller', function($scope,$http,$location,$route){
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
    }
});