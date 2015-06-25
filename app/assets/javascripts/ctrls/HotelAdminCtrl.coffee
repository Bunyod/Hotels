angular.module('myApp.controllers')
.controller 'HotelAdminCtrl', class
  constructor: ($scope, $log, $http, Hotels) ->
      vm = @
      glob = $scope.Glob
      vm.hotelTypes = {}
      $scope.options = '/hotel/images'
      $scope.loadingFiles = true
      $http.get('/hotel/images').then ((response) ->
          $scope.loadingFiles = false
          $scope.queue = response.data.files or []
          return
      ), ->

      $scope.loadingFiles = false

      vm.createHotel = (hotel) =>
          Hotels.add(hotel, (data) =>
            if data
              $state.go('root.administration', hotel)
          ).$promise

      vm.getHotelTypes = () =>
          Hotels.hotelTypes((data) =>
              if data
                  vm.hotelTypes = data
                  $log.info(vm.hotelTypes[0].name)
          ).$promise

      vm.getHotelTypes()
      vm



