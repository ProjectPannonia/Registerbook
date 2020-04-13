app.controller('listmembercontroller',function($scope,$http,$location,$route){
    $http({
        method : 'GET',
        url : 'http://localhost:8080/register/member'
    }).then(function(response){
        $scope.members = response.data;
    })

    //edit
    //delete
});