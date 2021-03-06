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
      'main@':
        templateUrl: "assets/partials/main.tpl.html"
    data:
      pageTitle: 'Home'
  )
  $stateProvider.state("root.w-home",
    url: "/uhome"
    views:
      'main@':
        templateUrl: "assets/partials/main.tpl.html"
      'header@':
        templateUrl: 'assets/partials/wauth-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'hec'
    data:
      pageTitle: 'Home'

  )
  $stateProvider.state("root.signin",
    url: "/signin"
    views:
      'main@':
        controller: "LoginCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/signin.html"
      'header@':
        templateUrl: 'assets/partials/auth-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'hec'
      'footer@':
        templateUrl: 'assets/partials/auth-footer.tpl.html'
    data:
      pageTitle: "Sign up")
  $stateProvider.state("root.signup",
    url: "/signin"
    views:
      'main@':
        controller: "SignUpCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/signup.html"
      'header@':
        templateUrl: 'assets/partials/auth-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'hec'
      'footer@':
        templateUrl: 'assets/partials/auth-footer.tpl.html'
    data:
      pageTitle: "Sign up")
  $stateProvider.state("root.signout",
    url: "/signin"
    views:
      'main@':
        controller: "LoginCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/signin.html"
      'header@':
        templateUrl: 'assets/partials/auth-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'hec'
      'footer@':
        templateUrl: 'assets/partials/auth-footer.tpl.html'
    data:
      pageTitle: "Sign in")
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
        templateUrl: "assets/partials/search-result.html"
      'header@':
        templateUrl: 'assets/partials/header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
    data:
      pageTitle: 'Search Hotels'
  )
  $stateProvider.state("root.add",
    url: "/hotel/administration"
    views:
      'main@':
        controller: "HotelAdminCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/administration.html"
    'header@':
      templateUrl: 'assets/partials/admin-header.tpl.html'
      controller: 'HeaderCtrl'
      controllerAs: 'hec'
    'footer@':
      templateUrl: 'assets/partials/admin-footer.tpl.html'
    data:
      pageTitle: 'Administration Page'
  )
  $stateProvider.state("root.details",
    url: "/hotel?hotelId/details"
    views:
      'main@':
        controller: "HotelOrderedCtrl"
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
        controllerAs: 'hec'
      'footer@':
        templateUrl: 'assets/partials/admin-footer.tpl.html'
    data:
      pageTitle: 'Administration Page'
  )