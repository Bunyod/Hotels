angular.module('myApp.controllers')
.controller 'HotelsDetailsCtrl', class
    constructor: ($scope, $log, $route, Hotels) ->
        vm = @
        glob = $scope.Glob
        vm.Hotel = {}

        vm.getHotels = () =>
            Hotels.details(hotelId, (data) =>
                $log.info(data)
                if data
                    vm.Hotel = data
                    $log.info(vm.Hotel)
            ).$promise
        vm
