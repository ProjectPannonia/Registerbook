app.controller('newmemberregistration',function($scope,$http,$location,$route){
    $scope.instrument = ['Guitar','Bass Guitar','Drum','Singer','Violin','Harmonica','Rhythm guitar','Keyboard instruments'];
    $scope.countrie = ['Germany','Sweden','Hungary','UK','USA','Norway','Finnland','Canada'];
        $http({
                method : 'GET',
                url : 'http://localhost:8080/register/member/getCountries'
        }).then(function(response){
            $scope.countries = response.data;
        });
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
