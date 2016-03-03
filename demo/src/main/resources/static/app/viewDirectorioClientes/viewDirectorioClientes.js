'use strict';

angular.module('myApp.viewDirectorioClientes', ['ngRoute'])              
        
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewDirectorioClientes', {
                    templateUrl: 'viewDirectorioClientes/viewDirectorioClientes.html',
                    controller: 'DirectorioClientesCtrl'
                });
            }])

        .controller('DirectorioClientesCtrl', ['$scope', '$mdDialog', '$mdMedia', 'Clients', function ($scope, $mdDialog, $mdMedia, Clients) {
                Clients.get(function(data){ 
                    $scope.listadoClientes=data; 
                });
                
                $scope.status = '  ';
                $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
                $scope.showAlert = function(ev, client) {
                  // Appending dialog to document.body to cover sidenav in docs app
                  // Modal dialogs should fully cover application
                  // to prevent interaction outside of dialog
                  $mdDialog.show(
                    $mdDialog.alert()
                      .parent(angular.element(document.querySelector('#popupContainer')))
                      .clickOutsideToClose(true)
                      .title(client.name)
                      .textContent(client.profileDescription)
                      .ariaLabel('Alert Dialog')
                      .ok(' OK ')
                      .targetEvent(ev)
                  );
                };
                
                
            }]);
