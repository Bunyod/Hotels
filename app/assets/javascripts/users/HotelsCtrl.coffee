angular.module('myApp.controllers')
.controller 'HotelsCtrl', class
    constructor: ($log, $scope, $state, Hotels) ->
        vm = @
        glob = $scope.Glob
        vm.hotels = {}
        vm.premiums = {}

        vm.getHotels = () =>
            Hotels.list((data) =>
                $log.info(data)
                if data
                    vm.hotels = data
                    $log.info(vm.hotels)
            ).$promise

        vm.getPremiumHotels = () =>
            Hotels.premiums((data) =>
                $log.info(data)
                if data
                    vm.premiums= data
                    $log.info($scope.premiums)
            ).$promise

        vm.getCities = () =>
            Hotels.cities((data) =>
                $log.info(data)
                if data
                    glob.Cities = data
                    $log.info(glob.Cities)
            ).$promise

        vm.getCities()
        vm.getHotels()
        vm.getPremiumHotels()
        vm

