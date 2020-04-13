
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

app.controller('registermembercontroller', function($scope,$http,$location,$route){
    $scope.submitMemberForm = function(){
        method : 'POST',
        url : 'http://localhost:8080/'
    };
});

app.controller('memberdetailscontroller', function($scope,$http,$location,$route){

});

app.controller('searchmembercontroller', function($scope,$http,$location,$route){

});