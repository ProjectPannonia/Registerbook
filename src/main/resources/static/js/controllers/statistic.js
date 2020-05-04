app.controller('statistics',function($scope,$http,$location,$route){
    var nation = null;
    $http({
            method : 'GET',
            url : 'http://localhost:8080/register/member/statistics'
          }).then(function(response){
            $scope.statistics = response.data;
            google.charts.load("current", {packages:["corechart"]});
                            google.charts.setOnLoadCallback(drawChart);
                            function drawChart() {
                              var data = google.visualization.arrayToDataTable([
                                ['Task', 'Hours per Day'],
                                ['German',    $scope.statistics.germanBands],
                                ['Swedish',   $scope.statistics.swedenBands],
                                ['Hungarian',  $scope.statistics.hungarianBands],
                                ['British', $scope.statistics.ukBands],
                                ['American',    $scope.statistics.usaBands],
                                ['Norwegian', $scope.statistics.norwayBands],
                                ['Finnish', $scope.statistics.finnBands],
                                ['Canadian', $scope.statistics.canadianBands]
                              ]);

                              var options = {
                                title: 'Members by country',
                                pieHole: 0.4,
                              };

                              var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                              chart.draw(data, options);
                            }
          });

          console.log("A nation értéke: " + nation);

});