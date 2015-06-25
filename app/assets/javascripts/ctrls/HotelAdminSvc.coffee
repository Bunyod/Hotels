angular.module('myApp')
.factory 'HotelAdminSvc', ($http) ->
  new class
    vm = @
    vm.uploadFileToUrl = (file, uploadUrl) ->
      fd = new FormData
      fd.append 'file', file
      $http.post(uploadUrl, fd,
        transformRequest: angular.identity
        headers: 'Content-Type': undefined).success(->
      ).error ->