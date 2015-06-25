angular.module('myApp.controllers')
.controller 'LoginCtrl', class
  constructor: ($log, $scope, $state, Users) ->
      vm = @

      vm.signIn = (credential) =>
          login = credential.login
          pass = credential.password
          if !login or !pass
              alert("Please enter login and password.")
              return no

          loginPass =
              login: login
              password: pass

          $log.info(loginPass)

          Users.signIn(loginPass, (data) =>
              if data
                  $state.go('root.home')
              else
                  alert("Incorrect login or password.")
          ).$promise