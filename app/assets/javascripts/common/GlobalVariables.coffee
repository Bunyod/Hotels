angular.module('globalVariables', []).run ($rootScope) ->
    $rootScope.Glob =
        Users: []
        Cities: []
        Hotels: []
        Permission: window.Permission

    window.Permission = undefined