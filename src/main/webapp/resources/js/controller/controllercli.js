var app = angular.module("mcliente", []);
app.controller("clienteCtrl", ['$scope', '$http', function ($scope, $http)
{

// Variable inicial para el indice del array
    var idx=0;
    $scope.objmax= {};
    $scope.clientes = [];
    $scope.clienteActual = {};

    $scope.vista = "formulario";
    $scope.navegacion = true;
    $scope.edit = false;
    $scope.sololectura = true;
    $scope.botones ="noeditable";



    $http({
        method: 'GET',
        url: '/apicliente/all'
    }).success(function (data) {
        $scope.clientes = data;
    });

   //Datos para la lista desplegable
    $http({
        method: 'GET',
        url: '/apicategoria/all'
    }).success(function (data) {
        $scope.categorias = data;
    });


    $scope.listar = function () {
        $http({
            method: 'GET',
            url: '/apicliente/all'
        }).success(function (data) {
            $scope.clientes = data;
        });
    };


    $scope.nuevo = function () {
    	 	
    	$scope.clienteActual.nombre = " ";
    	$scope.clienteActual.apellido = " ";
    	$scope.clienteActual.direccion = " ";
    	$scope.clienteActual.dni = " ";
        $scope.botones ="editable";
        $scope.sololectura = false;
        $scope.navegacion = false;
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
        var clienteArray = eval($scope.clientes);
        for (var i = 0; i < clienteArray.length; i++) {
            if (clienteArray[i].id === id) {
                idx = i;
                $scope.clienteActual = $scope.clientes[idx];
                break;
            }
        }
        $scope.vista = "formulario";
    };



    $scope.guardar = function () {

        if ($scope.edit === false) {
            $http({
                method: 'POST',
                url: '/apicliente/save',
                data: {
                    'nombre': $scope.clienteActual.nombre,
                    'apellido': $scope.clienteActual.apellido,
                    'direccion':$scope.clienteActual.direccion,
                    'dni':$scope.clienteActual.dni
                    
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
                url: '/apicliente/update/' + $scope.clienteActual.id,
                data: {
                	 'nombre': $scope.clienteActual.nombre,
                	 'apellido': $scope.clienteActual.apellido,
                     'direccion':$scope.clienteActual.direccion,
                     'dni':$scope.clienteActual.dni,
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
                 url: '/apicliente/erase/' + $scope.clienteActual.id,
                 
                data: {
                    'id' : $scope.clienteActual.id,
                    'estado': $scope.clienteActual.estado,
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
        $scope.clienteActual = $scope.clientes[idx];
    };
    

     $scope.ultimo = function () {
        //El ultimo elemento se determina por la longitud del array (lenght)
        idx = $scope.clientes.length - 1;
        $scope.clienteActual = $scope.clientes[idx];
    };
}]);
