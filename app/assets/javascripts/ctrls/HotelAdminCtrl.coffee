angular.module('myApp')
.controller 'HotelAdminCtrl', class
  ($scope, $http) ->
    $scope.options = '/hotel/images'
    $scope.loadingFiles = true
    $http.get('/hotel/images').then ((response) ->
      $scope.loadingFiles = false
      $scope.queue = response.data.files or []
      return
    ), ->
      $scope.loadingFiles = false

