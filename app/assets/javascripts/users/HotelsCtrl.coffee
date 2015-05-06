class HotelsCtrl
    constructor: ($scope, @$log, $route, Hotels) ->
        glob = $scope.Glob
        $scope.hotels = {}
        $scope.premiums = {}

        $scope.getHotels = () =>
            Hotels.list((data) =>
                @$log.info(data)
                if data
                  $scope.hotels = data
                  @$log.info($scope.hotels)
            ).$promise

        $scope.getPremiumHotels = () =>
            Hotels.premiums((data) =>
                @$log.info(data)
                if data
                    $scope.premiums= data
                    @$log.info($scope.premiums)
            ).$promise

        $scope.getCities = () =>
            Hotels.cities((data) =>
                @$log.info(data)
                if data
                    glob.Cities = data
                    @$log.info(glob.Cities)
            ).$promise

        $scope.getCities()
        $scope.getHotels()
        $scope.getPremiumHotels()

controllersModule.controller('HotelsCtrl', HotelsCtrl)

