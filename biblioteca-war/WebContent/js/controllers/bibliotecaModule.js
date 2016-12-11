var moduleBiblioteca = angular.module("biblioteca", ["ngRoute"]);
moduleBiblioteca.config(function($routeProvider) {
	  
	$routeProvider
    .when("/", {
        templateUrl : "livros.html",
        controller : "livroController"        	
    })
    .when("/livros", {
        templateUrl : "livros.html",
        controller : "livroController"
    })
    .when("/pedidos", {
        templateUrl : "pedidos.html",
        controller : "pedidoController"
    })
    .when("/reservas", {
        templateUrl : "reservas.html",
        controller : "reservaController"
    })
    .when("/sugestoes", {
        templateUrl : "sugestoes.html",
        controller : "sugestaoController"
    });
});