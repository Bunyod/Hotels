angular.module('myApp.controllers')
.controller 'SearchResultCtrl', class
    constructor: ($scope, @$log, $route, $location, $stateParams, Search) ->
        glob = $scope.Glob
        $scope.hotels = []
        $scope.cityId = 1
        searchParams =
            hotelTypeId: 1
            starRating: 5
        searchParams.cityId = $stateParams.cityId
        searchParams.checkInDate = $stateParams.checkInDate
        searchParams.checkOutDate = $stateParams.checkOutDate

        Search.findHotel({cityId: $scope.cityId}, searchParams).$promise
        .then(
            (data) =>
                $scope.hotels = data.rows
        ,
            (error) =>
                @$log.error "Unable to find hotels: #{error}"
        )

