
app.controller('listmembercontroller',function($scope,$http,$location,$route){
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

app.controller('memberdetailscontroller', function($scope,$http,$routeParams,$location,$route){
    $scope.memberId = $routeParams.id;
    $http({
        method : 'GET',
        url : 'http://localhost:8080/register/member/' + $scope.memberId
    }).then(function(response){
        $scope.member = response.data;
    });
    $scope.submitMemberForm = function(){
        $http({
            method : 'POST',
            url : 'http://localhost:8080/register/member/',
            data : $scope.member,
        }).then(function(response){
            $location.path("/list-all-members");
            $route.reload();
        },function(errResponse){
            $scope.errorMessage = errResponse.data.errorMessage;
        });
    };
});

app.controller('searchmembercontroller', function($scope,$http,$location,$route){
    $scope.searchbyname = function(){
        let a = $scope.name;
        console.log(a);
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

app.controller('searchdropdown',function($scope,$http,$location,$route){
    $scope.names = ['Elso','masodik','harmadik','negyedik'];
    var a = $scope.selectedName;
    console.log(a);
    $scope.push = function(){
        var a = $scope.selectedName;
            console.log(a);
    };
});