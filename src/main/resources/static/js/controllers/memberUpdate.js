app.controller('memberUpdate', function($scope,$http,$routeParams,$location,$route){
    $scope.memberId = $routeParams.id;
    $http({
            method : 'GET',
            url : 'http://localhost:8080/register/member/' + $scope.memberId
    }).then(function(response){
            $scope.member = response.data;
    });

    $scope.submitMemberForm = function(){
        $http({
                method : 'PUT',
                url : 'http://localhost:8080/register/member/' + $scope.memberId,
                data : $scope.member,
        }).then(function(response){
                $location.path("/list-all-members");
                $route.reload();
        },function(errResponse){
                $scope.errorMessage = errResponse.data.errorMessage;
        });
    };
});