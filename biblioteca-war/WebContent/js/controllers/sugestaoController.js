moduleBiblioteca
	.controller('sugestaoController', function($scope, $http) {
		
		$scope.pageHeader="Sugest&otilde;es de Livros";
		$scope.sugestoes = null;
		$scope.distribuidoras = null;
		$scope.pedido = {};
		$scope.pedido.distribuidora = {};
		$scope.pedidoLivros = [];

		$http.get('api/sugestao').
			then(function(response) {
				$scope.sugestoes = response.data;
			});				

		$http.get('api/pedido/distribuidora').
			then(function(response) {
				$scope.distribuidoras = response.data;
			});				

		$scope.incluirNoPedido = function(livro){
			var pedidoLivro = {};
			pedidoLivro.livro = livro;
			pedidoLivro.quantidadeSolicitada=0;
			$scope.pedidoLivros.push(pedidoLivro);
			console.log("Incluiu no Pedido");
		}
			
		$scope.cancelarSugestao = function(sugestao){
			console.log("Cancelar sugestão");
			$http.put('api/sugestao/cancelamento/' + sugestao.id, sugestao).
			then(function(response){
				sugestao.situacaoSugestao = response.data.situacaoReserva;	
			}, function(response){
				console.log("Erro ao cancelar reserva");
			});

		}

		$scope.removerPedidoLivro = function(pedidoLivro){
			console.log("Remover pedido do Livro");
			var index = $scope.pedidoLivros.indexOf(pedidoLivro);
			if(index >= 0){
				$scope.pedidoLivros.splice(index,1);				
			}

		}

		$scope.enviarPedido = function(){
			console.log("Enviar Pedido");
		}
		
		$scope.cancelarPedido = function(pedido){
			console.log("Cancelar Pedido");
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