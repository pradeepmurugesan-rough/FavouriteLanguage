'use strict';

/**
 * @ngdoc function
 * @name favLangApp.Service:FavLangService
 * @description
 * # FavLangService
 * Service of the favLangApp
 */
angular.module('favLangApp')
  .service('FavLangService', function ($q, $http) {
	
    this.getLanguages = function(username) {

      var deferred = $q.defer();
      var req = {
        method: 'GET',
        url: "/rest/favouriteLanguage/" + username
      };
      $http(req)
        .success(function(data) {
          deferred.resolve(data);
        })
        .error(function(data) {
          deferred.reject({
            error: data
          });
        });
      return deferred.promise;
    };
  });
