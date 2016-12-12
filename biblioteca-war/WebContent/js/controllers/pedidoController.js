moduleBiblioteca
	.controller('pedidoController', function($scope, $rootScope, $http) {
		
		$scope.pageHeader="Pedidos de Livros";
		$scope.pedidos = [];
		$scope.livroPedidos = [];
		
		jQuery('#panelMensagemSucesso').hide();
		jQuery('#panelMensagemErro').hide();

		$http.get('api/pedido').
			then(function(response) {
				$scope.pedidos = response.data;
			},function(response) {
	            $rootScope.mensagemErro = "Erro ao consultar os pedidos!";
	            jQuery('#panelMensagemErro').show();
			});				

		$scope.exibirLivros = function(pedido){
			$http.get('api/pedido/' + pedido.id + '/livros').
				then(function(response) {
					$scope.livroPedidos = response.data;
				},function(response) {
		            $rootScope.mensagemErro = "Erro ao consultar os livros do pedido!";
		            jQuery('#panelMensagemErro').show();
				});				
		}		
	})