moduleBiblioteca
	.controller('pedidoController', function($scope, $http) {
		
		$scope.pageHeader="Pedidos de Livros";
		$scope.pedidos = [];
		$scope.livroPedidos = [];
		
		$http.get('api/pedido').
			then(function(response) {
				$scope.pedidos = response.data;
			});				

		$scope.exibirLivros = function(pedido){
			$http.get('api/pedido/' + pedido.id + '/livros').
				then(function(response) {
					$scope.livroPedidos = response.data;
				});				
		}		
	})