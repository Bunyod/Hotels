angular.module('myApp.controllers')
.controller 'HotelsDetailsCtrl', class
    constructor: ($scope, @$log, $route, Hotels) ->
        vm = @
        glob = $scope.Glob
        vm.Hotel = {}
#        $scope.hotels = {}
#        $scope.premiums = {}

        vm.getHotels = () =>
            Hotels.details(hotelId, (data) =>
                @$log.info(data)
                if data
                    vm.Hotel = data
                    @$log.info(vm.Hotel)
            ).$promise
        vm
#
#        $scope.getPremiumHotels = () =>
#            Hotels.premiums((data) =>
#                @$log.info(data)
#                if data
#                    $scope.premiums= data
#                    @$log.info($scope.premiums)
#            ).$promise
#
#        $scope.getCities = () =>
#            Hotels.cities((data) =>
#                @$log.info(data)
#                if data
#                    glob.Cities = data
#                    @$log.info(glob.Cities)
#            ).$promise
#
#        $scope.getCities()
#        $scope.getHotels()
#        $scope.getPremiumHotels()
