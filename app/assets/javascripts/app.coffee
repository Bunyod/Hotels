dependencies = [
    'globalVariables',
    'ngRoute',
    'ngGrid',
    'ui.router',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig',
    'ngResource',
    'ui.sortable',
    'angular-growl',
    'ngAnimate',
    'mgcrea.ngStrap'
]

app = angular.module('myApp', dependencies)

app.config ($datepickerProvider) ->
    angular.extend $datepickerProvider.defaults,
        dateFormat: 'dd/MM/yyyy'
        startWeek: 1

app.config ($stateProvider, $urlRouterProvider, $httpProvider) ->

    $urlRouterProvider.otherwise '/'
    $httpProvider.defaults.useXDomain = true
    $httpProvider.defaults.withCredentials = true
    delete $httpProvider.defaults.headers.common['X-Requested-With']

#    $httpProvider.interceptors.push 'trendster.errorInterceptor'
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
    ).state("root.home",
        url: "/home"
        views:
            "main@":
                templateUrl: "assets/partials/main.tpl.html"
        data:
            pageTitle: 'Home'
    )

@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])




