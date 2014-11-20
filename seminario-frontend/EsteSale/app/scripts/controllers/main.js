'use strict';

/**
 * @ngdoc function
 * @name esteSaleApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the esteSaleApp
 */
angular.module('esteSaleApp')
    .controller('MainCtrl', function ($scope) {
        $scope.StringService = {
            searchPlaceholder: 'Busca el transporte de tu interes',
            topServiceLabel: 'Top 5: Servicios en Buenos Aires'
        };

        $scope.topServices = [
            {id: 100, label: "Linea Mitre, Ramal Tigre", status: 'nosale'},
            {id: 101, label: "Linea Mitre, Ramal Suarez", status: 'sale'},
            {id: 102, label: "Linea Mitre, Ramal Mitre", status: 'nosale'},
            {id: 105, label: "Linea C", status: 'nosale'},
            {id: 103, label: "Linea Belgrano Norte", status: 'nosale'}
        ]

    }
);
