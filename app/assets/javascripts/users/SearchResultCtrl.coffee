class SearchResultCtrl
    constructor: ($scope, @$log, $route, $location, $routeParams, Search) ->
        glob = $scope.Glob
        $scope.hotels = []
        $scope.cityId = 1
        searchParams =
            hotelTypeId: 1
            starRating: 5
        searchParams.cityId = $routeParams.cityId
        searchParams.checkInDate = $routeParams.checkInDate
        searchParams.checkOutDate = $routeParams.checkOutDate

        Search.findHotel({cityId: $scope.cityId}, searchParams).$promise
        .then(
            (data) =>
                $scope.hotels = data.rows
        ,
            (error) =>
                @$log.error "Unable to find hotels: #{error}"
        )

controllersModule.controller('SearchResultCtrl', SearchResultCtrl)

