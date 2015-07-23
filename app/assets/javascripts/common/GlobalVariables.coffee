angular.module('globalVariables', []).run ($rootScope) ->
    $rootScope.Glob =
        Users: []
        Cities: []
        Rooms: []
        HotelTypes: []
        Prices: []
        Hotels: []
        Permission: window.Permission

    window.Permission = undefined