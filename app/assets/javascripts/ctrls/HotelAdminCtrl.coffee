angular.module('myApp')
.controller 'HotelAdminCtrl', class
  ($scope, $http) ->
    $scope.options = url: url
    $scope.loadingFiles = true
    $http.get('/hotels/upload').then ((response) ->
      $scope.loadingFiles = false
      $scope.queue = response.data.files or []
      return
    ), ->
      $scope.loadingFiles = false

