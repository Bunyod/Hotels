angular.module('myApp.controllers')
.controller 'SearchCtrl', class
    constructor: ($log, $scope, $state) ->
        glob = $scope.Glob
        $scope.searchParams = {}
        $scope.cityId = 1

        $scope.selectedDate = new Date
        $scope.selectedDateAsNumber = Date.UTC(1986, 1, 22)
        $scope.fromDate = new Date
        $scope.untilDate = new Date

        $scope.getType = (key) ->
            Object::toString.call $scope[key]

        $scope.clearDates = ->
            $scope.selectedDate = null

        $scope.sendSearchParams = () =>
            $scope.searchParams =
                hotelTypeId: 1
                starRating: 5
            $scope.searchParams.cityId = parseInt($scope.cityId)
            $scope.searchParams.checkInDate = $scope.fromDate
            $scope.searchParams.checkOutDate= $scope.untilDate
            $log.info($scope.searchParams)
            $state.go('root.search', $scope.searchParams)
#        vm

