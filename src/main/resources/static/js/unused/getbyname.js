app.controller('searchmembercontroller', function($scope,$http,$location,$route){
    $scope.searchbyname = function(){
        let a = $scope.name;
        $http({
                method : 'GET',
                url : 'http://localhost:8080/register/member/searchname/' + a
        }).then(function(response){
                $scope.searchedMembers = response.data;
        });
    }
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
        $scope.resetForm = function(){
                $scope.member = null;
        }
});