Route =
  Home: "/"
  Hotels:
    List: '/hotels/list'
    TopList: '/hotels/premiums'
    Create: '/hotels/create'
    Details: '/hotel/details'
  Users:
    List: '/users/list'
    Authors: '/users/authors'
  Search:
    Result: '/hotels/search'

angular.module('myApp.routeConfig', [])
.config ($routeProvider) ->
  $routeProvider
  .when(Route.Home, {
      templateUrl: '/assets/partials/home.html'
    })
  .when(Route.Hotels.List, {
      templateUrl: '/assets/partials/home.html'
    })
  .when(Route.Hotels.TopList, {
      templateUrl: '/assets/partials/premium_hotels.html'
    })
  .when(Route.Hotels.Create, {
      templateUrl: '/assets/partials/add_hotel.html'
    })
  .when(Route.Hotels.Details, {
      templateUrl: '/assets/partials/single.html'
    })
  .when(Route.Users.List, {
      templateUrl: '/assets/partials/authors.html'
    })
  .when(Route.Users.Authors, {
      templateUrl: '/assets/partials/authors.html'
    })
  .when(Route.Search.Result, {
      templateUrl: '/assets/partials/search_result.html'
    })
  .otherwise({redirectTo: Route.Home})

angular.module('myApp.routeConfig').run ($rootScope, $window) ->
  $rootScope.Glob.Route = Route



