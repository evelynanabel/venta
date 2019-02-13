var app = angular.module("musuario", []);
app.controller("usuarioCtrl", ['$scope', '$http', function ($scope, $http)
{

// Variable inicial para el indice del array
    var idx=0;
    $scope.objmax= {};
    $scope.usuarios = [];
    $scope.usuarioActual = {};

    $scope.vista = "formulario";
    $scope.navegacion = true;
    $scope.edit = false;
    $scope.sololectura = true;
    $scope.botones ="noeditable";



    $http({
        method: 'GET',
        url: '/apiusuario/all'
    }).success(function (data) {
        $scope.usuarios = data;
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
            url: '/apiusuario/all'
        }).success(function (data) {
            $scope.usuarios = data;
        });
    };


    $scope.nuevo = function () {
    	$scope.usuarioActual.username=" ";
    	$scope.usuarioActual.password=" ";
    	$scope.usuarioActual.nombre = " ";
    	$scope.usuarioActual.apellidos = " ";
    	$scope.usuarioActual.dni = " ";
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
        var usuarioArray = eval($scope.usuarios);
        for (var i = 0; i < usuarioArray.length; i++) {
            if (usuarioArray[i].id === id) {
                idx = i;
                $scope.usuarioActual = $scope.usuarios[idx];
                break;
            }
        }
        $scope.vista = "formulario";
    };



    $scope.guardar = function () {

        if ($scope.edit === false) {
            $http({
                method: 'POST',
                url: '/apiusuario/save',
                data: {
                	 'username': $scope.usuarioActual.username,
                	 'password': $scope.usuarioActual.password,
                    'nombre': $scope.usuarioActual.nombre,
                    'apellidos': $scope.usuarioActual.apellidos,
                    'dni':$scope.usuarioActual.dni
                    
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
                url: '/apiusuario/update/' + $scope.usuarioActual.id,
                data: {
                	'username': $scope.usuarioActual.username,
               	 'password': $scope.usuarioActual.password,
                   'nombre': $scope.usuarioActual.nombre,
                   'apellidos': $scope.usuarioActual.apellidos,
                   'dni':$scope.usuarioActual.dni,
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
                 url: '/apiusuario/erase/' + $scope.usuarioActual.id,
                 
                data: {
                    'id' : $scope.usuarioActual.id,
                    'estado': $scope.usuarioActual.estado,
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
        $scope.usuarioActual = $scope.usuarios[idx];
    };
    

     $scope.ultimo = function () {
        //El ultimo elemento se determina por la longitud del array (lenght)
        idx = $scope.usuarios.length - 1;
        $scope.usuarioActual = $scope.usuarios[idx];
    };
}]);
