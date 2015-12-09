'use strict';

/**
 * @ngdoc function
 * @name favLangApp.controller:FavLangCtrl
 * @description
 * # FavLangCtrl
 * Controller of the favLangApp
 */
angular.module('favLangApp')
  .controller('FavLangCtrl', function (FavLangService, blockUI) {
    var viewModel = this;
	viewModel.getFavouriteLanguages = function() {
		viewModel.showResult=false;
		blockUI.start();
		FavLangService.getLanguages(viewModel.username).then(
		function(data){
			blockUI.stop();
			viewModel.showResult = true;
			if(data.errorMessage) {
				viewModel.showError = true;
				viewModel.showSuccess = false;
				viewModel.message = data.errorMessage;
			}
			else {
				viewModel.showSuccess = true;
				viewModel.showError = false;
				viewModel.message = data.languages.join(",");
			}
			
			
		}, function(data){
			blockUI.stop();
			viewModel.showResult = true;
			viewModel.showError = true;
			viewModel.showSuccess = false;
			viewModel.message = "Server error Occurred";
		})
	}
  });
