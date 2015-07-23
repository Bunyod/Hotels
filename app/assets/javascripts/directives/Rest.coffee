angular.module('myApp')
.factory('Users', ['$resource', ($resource) ->
    $resource('/users/:id', id: '@id',
        {
            'update':'method': 'PUT',
            'query': {method: 'GET'}
            'roles': {method: 'GET', url: '/users/roles', isArray: true},
            'states': {method: 'GET', url: '/users/states', isArray: true},
            'signIn': {method: 'POST', url: '/users/signIn'},
            'signOut': {method: 'POST', url: '/users/signOut'}
        }
    )
])

.factory('Roles', ['$resource', ($resource) ->
    $resource('/users/roles/:id', id: '@id',
        'query': {method: 'GET', isArray: yes}
    )
])

.factory('Search', ['$resource', ($resource) ->
    $resource('/search/params/:cityId', cityId: '@cityId',
        {
          'query': {method: 'GET', isArray: yes},
          'findHotel': {method: 'GET', url: '/search/params'}
        }
    )
])

.factory('Hotels', ['$resource', ($resource) ->
    $resource('/hotels/:id', id: '@id',
        {
          'update':'method': 'PUT',
          'query': {method: 'GET', url: '/hotels/list', isArray: true}
          'list': {method: 'GET', url: '/hotels/list', isArray: true},
          'premiums': {method: 'GET', url: '/hotels/premiums', isArray: true},
          'add': {method: 'POST', url: 'hotels/add'},
          'cities': {method: 'GET', url:'/search/cities', isArray: true},
          'roomTypes': {method: 'GET', url:'/hotels/roomTypes/list', isArray: true},
          'hotelTypes': {method: 'GET', url:'hotelTypes/list', isArray: true},
          'prices': {method: 'GET', url:'/price/list', isArray: true},
          'details': {method: 'GET', url:'/hotel/:id/details'},
          'administration': {method: 'POST', url:'/hotel/administration'}
        }
    )
])
