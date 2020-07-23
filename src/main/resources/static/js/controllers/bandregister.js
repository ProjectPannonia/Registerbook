app.controller('bandregister', function ($scope,$http,$location,$route) {
    $scope.myNewFile;
    $scope.add = function() {
        var f = document.getElementById('file').files[0],
            r = new FileReader();

        r.onloadend = function(e) {
            var data = e.target.result;
            //send your binary data via $http or $resource or do anything else with it
        }

        r.readAsBinaryString(f);
        console.log(r);
        $http({
            method : 'POST',
            url : 'http://localhost:8080/register/band/',
            data : r
        }).then(function (response) {
            console.log(response)
        });
    };
    $scope.uploadFile = function () {
        var file = $scope.myFile;
        var r = new FileReader();
        r.readAsBinaryString(file);
        console.log('file is ' );
        console.dir(file);
        var newObject  = {
            'lastModified'     : file.lastModified,
            'lastModifiedDate' : file.lastModifiedDate,
            'name'             : file.name,
            'size'             : file.size,
            'type'             : file.type
        };
        JSON.stringify(newObject);
        console.log(newObject);
        $http({
            method : 'POST',
            url : 'http://localhost:8080/register/band/',
            data : r
        }).then(function (response) {
            console.log(response)
        });
        //var uploadUrl = "/fileUpload";
        //fileUpload.uploadFileToUrl(file, uploadUrl);
        /*$http({
            method : 'POST',
            url : 'http://localhost:8080/register/member/',
            data : $scope.bandlogo
        }).then(function (response) {

        });
        */
         //var logo = $scope.myFile;
         //console.log(logo);
    };


    /*
        service('fileUpload', ['$https:', function ($https:) {
            this.uploadFileToUrl = function(file, uploadUrl) {
                var fd = new FormData();
                fd.append('file', file);

                $https:.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .success(function() {
                    })
                    .error(function() {
                    });
            }
        }]);
        */

});