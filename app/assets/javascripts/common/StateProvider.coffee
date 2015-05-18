angular.module('myApp')
.config ($stateProvider, $urlRouterProvider, $httpProvider) ->

  $urlRouterProvider.otherwise '/home'
  $httpProvider.defaults.useXDomain = true
  $httpProvider.defaults.withCredentials = true
  delete $httpProvider.defaults.headers.common['X-Requested-With']

  $stateProvider.state("root",
    url: ""
    abstract: true
    views:
      header:
        templateUrl: "/assets/partials/header.tpl.html"
        controller: "HeaderCtrl"
        controllerAs: "vm"
      footer:
        templateUrl: "/assets/partials/footer.tpl.html"
  )
  $stateProvider.state("root.home",
    url: "/home"
    views:
      "main@":
        templateUrl: "assets/partials/main.tpl.html"
    data:
      pageTitle: 'Home'
  )
  $stateProvider.state("root.about",
    url: "/about"
    views:
      'main@':
        controller: "UserCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/authors.html"
    data:
      pageTitle: "About")
  $stateProvider.state("root.services",
    url: "/services"
    views:
      'main@':
        controller: "ServicesCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/services.html"
    data:
      pageTitle: 'About'
  )
  $stateProvider.state("root.search",
    url: "/search?cityId&checkInDate&checkOutDate"
    views:
      'main@':
        controller: "SearchResultCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/search_result.html"
      'header@':
        templateUrl: 'assets/partials/header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
    data:
      pageTitle: 'Search Hotels'
  )
  $stateProvider.state("root.details",
    url: "/details?cityId"
    views:
      'main@':
        controller: "HotelDetailsCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/single.html"
      'header@':
        templateUrl: 'assets/partials/inside-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
    data:
      pageTitle: 'Hotel Details'
  )
  $stateProvider.state("root.hotels",
    url: "/hotels/add"
    views:
      'main@':
        controller: "HotelAdminCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/add-hotel.html"
      'header@':
        templateUrl: 'assets/partials/admin-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
    data:
      pageTitle: 'Adminstration Page'
  )