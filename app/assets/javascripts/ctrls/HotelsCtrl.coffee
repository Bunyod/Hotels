angular.module('myApp.controllers')
.controller 'HotelsCtrl', class
    constructor: ($log, $scope, $state, Hotels) ->
        vm = @
        glob = $scope.Glob
        vm.hotels = {}
        vm.premiums = {}
        $scope.price = []

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

        vm.getRoomTypes = () =>
            Hotels.roomTypes((data) =>
                $log.info(data)
                if data
                    glob.Rooms = data
                    $log.info(glob.Rooms)
            ).$promise

        vm.getHotelTypes = () =>
            Hotels.hotelTypes((data) =>
                $log.info(data)
                if data
                    glob.HotelTypes = data
                    $log.info(glob.HotelTypes)
            ).$promise

        vm.getPrices = () =>
            Hotels.prices((data) =>
                $log.info(data)
                if data
                    $log.info("started")
                    for pr in data
                        $scope.price.top = pr.top
                        $log.info(pr.top)
                        $scope.price.bottom = pr.bottom
                        $scope.price.name = pr.name.substring(10, pr.name.length)
                        $log.info("finished")
                        glob.Prices.push $scope.price
                $log.info(glob.Prices)
            ).$promise

        vm.getCities()
        vm.getHotels()
        vm.getHotelTypes()
        vm.getPrices()
        vm.getRoomTypes()
        vm.getPremiumHotels()
        vm.showDetails = (hotelId) ->
            $state.go('root.details', hotelId)
        vm

