class UserCtrl
    constructor: ($scope, @$log, $route, Users) ->
        @$log.info("Urraaaa1")
        glob = $scope.Glob
        $scope.users = []

        $scope.getUsers = () =>
            @$log.info("Urraaaa2")
            Users.query((data) =>
                @$log.info("ayyyyyy")
                if data
                    $scope.users = data
                    @$log.info($scope.users)
            ).$promise

        $scope.getUsers()

controllersModule.controller('UserCtrl', UserCtrl)

