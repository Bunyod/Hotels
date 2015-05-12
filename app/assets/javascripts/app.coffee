dependencies = [
    'globalVariables',
    'ngGrid',
    'ui.router',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
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

@commonModule = angular.module('myApp.common', [])
angular.module('myApp.controllers', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])




