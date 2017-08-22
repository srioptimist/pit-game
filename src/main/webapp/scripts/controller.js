var pitsController = angular.module('pitsController', []);

pitsController.controller("pitsCtrl",['$scope','$http',function($scope,$http){

    $scope.pitsOfPlayer1 = [];
    $scope.pitsOfPlayer2 = [];
    $scope.playerInActionWin = false;
    $scope.extraMove=false;
    $scope.togglePlayerAction=true;

    $scope.move = function(player,startPosition){

        $http.get("rest/game/"+player+"/"+startPosition)
            .success(
                function(response) {
                    $scope.pitsOfPlayer1 = response.pitsOfPlayer1;
                    $scope.pitsOfPlayer2 = response.pitsOfPlayer2;
                    $scope.playerInActionWin = response.playerInActionWin;
                    $scope.extraMove = response.extraMove;
                    if($scope.extraMove){
                        alert(player+ " has a extra move");
                    }else {
                        $scope.togglePlayerAction = (player === 'player2');
                    }
                    if($scope.playerInActionWin){
                        alert("Congrats "+player+" you win!!!");
                    }
                }
            ).error(function() {
                    alert("Oops! Something wrong, start all over again.");
                    $scope.initialize();
        })
    };

    $scope.initialize = function(){
        $scope.pitsOfPlayer1 = [];
        $scope.pitsOfPlayer2 = [];
        for (i = 1; i <= 6; i++) {
            $scope.pitsOfPlayer1.push(6);
            $scope.pitsOfPlayer2.push(6);
        }
        $scope.pitsOfPlayer1.push(0);
        $scope.pitsOfPlayer2.push(0);
    };

}]);
