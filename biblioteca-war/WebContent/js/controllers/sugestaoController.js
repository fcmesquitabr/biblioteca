moduleBiblioteca
	.controller('sugestaoController', function($scope, $rootScope, $http) {
		
		$scope.pageHeader="Sugest&otilde;es de Livros";
		$scope.sugestoes = [];
		$scope.distribuidoras = [];
		
		jQuery('#panelMensagemSucesso').hide();
		jQuery('#panelMensagemErro').hide();

		
		$http.get('api/sugestao').
			then(function(response) {
				$scope.sugestoes = response.data;
			}, function(response) {
	            $rootScope.mensagemErro = "Erro ao consultar as sugestões de livros!";
	            jQuery('#panelMensagemErro').show();
			});				

		$http.get('api/pedido/distribuidora').
			then(function(response) {
				$scope.distribuidoras = response.data;
			}, function(response) {
	            $rootScope.mensagemErro = "Erro ao consultar as distribuidoras!";
	            jQuery('#panelMensagemErro').show();
			});				

		$scope.novoPedido = function(){
			$scope.pedido = {};
			$scope.pedido.distribuidora = {};
			$scope.pedido.distribuidora.id = $scope.distribuidoras[0];
			$scope.pedidoLivros = [];			
		}

		$scope.novoPedido();

		$scope.incluirNoPedido = function(livro){
			var pedidoLivro = {};
			pedidoLivro.livro = livro;
			pedidoLivro.quantidadeSolicitada=0;
			$scope.pedidoLivros.push(pedidoLivro);
		}
			
		$scope.cancelarSugestao = function(sugestao){
			$http.put('api/sugestao/cancelamento/' + sugestao.id, sugestao).
				then(function(response){
					sugestao.situacaoSugestao = response.data.situacaoSugestao;
		            $rootScope.mensagemSucesso = "Sugest&atilde;o cancelada com sucesso!";
		            jQuery('#panelMensagemSucesso').show();	
				}, function(response){
					$rootScope.mensagemErro = "Erro ao cancelar a sugest&atilde;o!";
		            jQuery('#panelMensagemErro').show();
				});

		}

		$scope.removerPedidoLivro = function(pedidoLivro){
			var index = $scope.pedidoLivros.indexOf(pedidoLivro);
			if(index >= 0){
				$scope.pedidoLivros.splice(index,1);				
			}
		}

		$scope.enviarPedido = function(){
			$scope.pedido.pedidoLivros = $scope.pedidoLivros;
			$http.post('api/pedido/', $scope.pedido).
				then(function(response){
					$scope.pedido.situacaoPedido = response.data.situacaoPedido;
					$scope.novoPedido();
		            $rootScope.mensagemSucesso = "Pedido enviado!";
		            jQuery('#panelMensagemSucesso').show();						
				}, function(response){
					$rootScope.mensagemErro = "Erro ao enviar Pedido!";
		            jQuery('#panelMensagemErro').show();
				});			
		}
		
		$scope.cancelarPedido = function(pedido){
			var copiaPedidoLivros = $scope.pedidoLivros.slice();
            for(var i=0; i< copiaPedidoLivros.length; i++) {
            	var pedidoLivro = copiaPedidoLivros[i]; 
            	$scope.removerPedidoLivro(pedidoLivro);
            }
		}

		$scope.isBotaoEnvioDesabilitado = function(){
			return ($scope.pedidoLivros == null || $scope.pedidoLivros.length == 0 || $scope.totalLivrosPedido() == 0);
		}
		
		$scope.totalLivrosPedido = function(){
			var total = 0;
			for(var i=0; i< $scope.pedidoLivros.length; i++) {
				var pedidoLivro = $scope.pedidoLivros[i];
				total+=parseInt(pedidoLivro.quantidadeSolicitada);
			}
			return total;
		}
	})