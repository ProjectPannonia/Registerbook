app.controller('listMembers',function($scope,$http,$location,$route){
    $http({
            method : 'GET',
            url : 'http://localhost:8080/register/member/'
    }).then(function(response){
            $scope.members = response.data;
    });

    $scope.editMember = function(memberId){
        $location.path("/update-member/" + memberId);
    }
    $scope.deleteMember = function(memberId){
        $http({
                method : 'DELETE',
                url : 'http://localhost:8080/register/member/' + memberId
        }).then(function(response){
                $location.path("/list-all-members");
                $route.reload();
        });
    }
});