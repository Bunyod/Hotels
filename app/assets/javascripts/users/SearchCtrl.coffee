class SearchCtrl
    constructor: ($scope, @$log, $route, Search, $location) ->
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
            searchParams =
                hotelTypeId: 1
                starRating: 5
            searchParams.cityId = parseInt($scope.cityId)
            searchParams.checkInDate = $scope.fromDate
            searchParams.checkOutDate= $scope.untilDate
            @$log.info(searchParams)
            $location.path("/hotels/search").search({
                cityId: searchParams.cityId, checkInDate: searchParams.checkInDate,
                checkOutDate: searchParams.checkOutDate
            })
            #
#            searchPrms = Search.findHotel({cityId: $scope.cityId}, searchParams).$promise
#            .then(
#                (data) =>
#                    glob.Hotels = data
#                    @$log.info(glob.Hotels)
##                    $location.path("/hotels/search").search({
##                        hotelTypeId: searchParams.hotelTypeId, starRating: searchParams.starRating,
##                        cityId: searchParams.cityId, checkInDate: searchParams.checkInDate,
##                        checkOutDate: searchParams.checkOutDate
##                    })
##                    $location.path(glob.Route.Search.Result).replace().reload(true)
##                    window.location = "/#" + $scope.Glob.Route.Search.Result
##                    window.location.reload()
#            ,
#                (error) =>
#                    @$log.error "Unable to find Hotels: #{error}"
#            )

controllersModule.controller('SearchCtrl', SearchCtrl)

