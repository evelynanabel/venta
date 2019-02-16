var app = angular.module("mfactura", []);
app.controller("facturaCtrl", ['$scope', '$http', function ($scope, $http)
{

// Variable inicial para el indice del array
    var idx=0;
    $scope.objmax= {};
    $scope.facturas = [];
    $scope.facturaActual = {};

    $scope.vista = "formulario";
    var f = new Date();
    $scope.fechaActual = addZero(f.getDate()) + "-" + addZero(f.getMonth() +1) + "-" + f.getFullYear();
    $scope.navegacion = true;
    $scope.edit = false;
    $scope.sololectura = true;
    $scope.botones ="noeditable";

    $scope.detalle= [];
	$scope.listaDetalle= [];



    $http({
        method: 'GET',
        url: '/apifactura/all'
    }).success(function (data) {
        $scope.facturas = data;
    });

    //Datos para la lista desplegable
    $http({
        method: 'GET',
        url: '/apicliente/all'
    }).success(function (data) {
        $scope.categorias = data;
    });


      $scope.listar = function () {
        $http({
            method: 'GET',
            url: '/apifactura/all'
        }).success(function (data) {
            $scope.facturas = data;
        });
    };


    $scope.nuevo = function () {
    	 	
    	$scope.facturaActual.idcliente= " ";
    	$scope.facturaActual.fecha= " ";
    	$scope.facturaActual.nrofactura = " ";
    	
        $scope.botones ="editable";
        $scope.sololectura = false;
        $scope.navegacion = false;
        $scope.facturaActual.detalles=$scope.detalle;
        $scope.facturaActual.cliente=$scope.clienteActual;
    };

    $scope.buscar = function () {
        $scope.vista = "listado";
    };

    $scope.editar = function () {
        $scope.edit = true;
        $scope.botones ="editable";
        $scope.sololectura = false;
        $scope.navegacion = false;
    };

    $scope.cancelar = function () {
        $scope.edit = false;
        $scope.botones ="noeditable";
        $scope.sololectura = true;
        $scope.navegacion = true;
        $scope.listar();
        $scope.primero();
    };

    $scope.seleccionar = function (id) {
        var idx = -1;
        var facturaArray = eval($scope.facturas);
        for (var i = 0; i < facturaArray.length; i++) {
            if (facturaArray[i].id === id) {
                idx = i;
                $scope.facturaActual = $scope.facturas[idx];
                break;
            }
        }
        $scope.vista = "formulario";
    };



    $scope.guardar = function () {

        if ($scope.edit === false) {
            $http({
                method: 'POST',
                url: '/apifactura/save',
                data: {
                    'idcliente': $scope.facturaActual.idcliente,
                    'fecha': $scope.facturaActual.fecha,
                    'nrofactura':$scope.facturaActual.nrofactura
                    
                    
                     }
            }).success(function (data, status, headers, config) {

                    //toastr.success('Exito.', 'Registro Guardado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });
        }
        if ($scope.edit === true) {
            $http({

                method: 'PUT',
                url: '/apifactura/update/' + $scope.facturaActual.id,
                data: {
                	 'idcliente': $scope.facturaActual.idcliente,
                    'fecha': $scope.facturaActual.fecha,
                    'nrofactura':$scope.facturaActual.nrofactura
                }
            }).success(function (data, status, headers, config) {

                    // toastr.success('Exito.', 'Registro Actualizado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });

        }


        $scope.primero();
        $scope.botones ="noeditable";
        $scope.sololectura = true;
        $scope.navegacion = true;
    };

    $scope.borrar = function () {
        var x = confirm("Esta seguro de dar de baja este registro");
        if (x) {
            $http({
            	 method: 'PUT',
                 url: '/apifactura/erase/' + $scope.facturaActual.id,
                 
                data: {
                    'id' : $scope.facturaActual.id,
                    'estado': $scope.facturaActual.estado,
                }
            }).success(function (data, status, headers, config) {

                    //toastr.success('Exito.', 'Registro Eliminado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });
        }
        else {
        }

        $scope.primero();

    };

    $scope.primero = function () {
        idx = 0;
        $scope.facturaActual = $scope.facturas[idx];
    };
    

     $scope.ultimo = function () {
        //El ultimo elemento se determina por la longitud del array (lenght)
        idx = $scope.facturas.length - 1;
        $scope.facturaActual = $scope.facturas[idx];
    };
    function addZero(i) {
	    if (i < 10) {
	        i = '0' + i;
	    }
	    return i;
	}
}]);
