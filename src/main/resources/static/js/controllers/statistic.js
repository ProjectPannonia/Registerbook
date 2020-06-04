app.controller('statistics',function($scope,$http,$location,$route){
    var nation = null;
    $http({
            method : 'GET',
            url : 'http://localhost:8080/register/member/statistics'
          }).then(function(response){
            $scope.statistics = response.data;
            let membersPerCountryArray = $scope.statistics.membersPerCountry;
            let membersPerCountryLength = membersPerCountryArray.length;
            let resultArr = [];
            resultArr.push(['Task','Hours per day']);

            for(var i = 0; i < membersPerCountryLength; i++){
                let temporaryArr = [membersPerCountryArray[i].country,membersPerCountryArray[i].number];
                resultArr.push(temporaryArr);
              };
            
            google.charts.load("current", {packages:["corechart"]});
                            google.charts.setOnLoadCallback(drawChart);
                            function drawChart() {
                            
                              var data = google.visualization.arrayToDataTable(
                                resultArr
                              );

                              var options = {
                                title: 'Members by country',
                                pieHole: 0.4,
                              };

                              var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                              chart.draw(data, options);
                            }
          });
});