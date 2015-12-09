'use strict';

/**
 * @ngdoc overview
 * @name favLangApp
 * @description
 * # favLangApp
 *
 * Main module of the application.
 */
angular
  .module('favLangApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
	'blockUI'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'FavLangCtrl',
        controllerAs: 'favLangController'
      })
  });
