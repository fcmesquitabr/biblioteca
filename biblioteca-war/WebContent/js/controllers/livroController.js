moduleBiblioteca
	.controller('livroController', function($scope, $http) {
		
		$scope.aluno = {id:1};
		
		$http.get('api/livros').
	        then(function(response) {
	            $scope.livros = response.data;
	        });

		$http.get('api/reservas').
	        then(function(response) {
	            $scope.reservas = response.data;
	        });

		$http.get('api/sugestao').
	        then(function(response) {
	            $scope.sugestoes = response.data;
	        });

		$scope.isReservado = function(livro){
			for(var i=0; i< $scope.reservas.length; i++) {
				var reserva = $scope.reservas[i];
				if(reserva.livro.id == livro.id){
					return true;
				}
			}
			return false;
		}

		$scope.isSugerido = function(livro){
			for(var i=0; i< $scope.sugestoes.length; i++) {
				var sugestao = $scope.sugestoes[i];
				if(sugestao.livro.id == livro.id){
					return true;
				}
			}
			return false;
		}

		$scope.enviarReservas = function(){
			$http.post('api/reservas',$scope.getReservasPendentes()).
				then(function(response) {
					console.log("Sucesso");
					$http.get('api/reservas').
				        then(function(response) {
				            $scope.reservas = response.data;
				        });					
				}, function(response) {
					console.log("Erro");
				});
		}
		
		$scope.getReservasPendentes = function(){
			var reservas=[];
			for(var i=0; i< $scope.reservas.length; i++) {
				var reservaLivro = $scope.reservas[i];
				if(reservaLivro.id == null){
					reservas.push(reservaLivro);					
				}
	        }
			return reservas;
		}

		$scope.enviarSugestoes = function(){
			$http.post('api/sugestao',$scope.getSugestoesPendentes()).
				then(function(response) {
					console.log("Sucesso");
					$http.get('api/sugestao').
			        then(function(response) {
			            $scope.sugestoes = response.data;
			        });					
				}, function(response) {
					console.log("Erro");
				});
		}

		$scope.getSugestoesPendentes = function(){
			var sugestoes=[];
			for(var i=0; i< $scope.sugestoes.length; i++) {
				var sugestaoLivro = $scope.sugestoes[i];
				if(sugestaoLivro.id == null){
					sugestoes.push(sugestaoLivro);					
				}
	        }
			return sugestoes;
		}

		$scope.sugerirLivro = function(livro){
			var sugestaoLivro = {};
			sugestaoLivro.livro=livro;
			sugestaoLivro.aluno=$scope.aluno;

			if($scope.sugestoes.indexOf(sugestaoLivro) == -1){
				$scope.sugestoes.push(sugestaoLivro);				
			}
		}
		
		$scope.reservarLivro = function(livro){
			var reservaLivro = {};
			reservaLivro.livro=livro;
			reservaLivro.aluno=$scope.aluno;

			if($scope.reservas.indexOf(reservaLivro) == -1){
				$scope.reservas.push(reservaLivro);				
			}
		} 

		$scope.removerReserva = function(reserva){
			if(reserva.id!=null){
				$http.delete('api/reservas/' + reserva.id).
					then(function(response) {
						console.log("Sucesso");
					}, function(response) {
						console.log("Erro");
					});				
			}
			var index = $scope.reservas.indexOf(reserva);
			if(index >= 0){
				$scope.reservas.splice(index,1);				
			}
		}

		$scope.removerSugestao = function(sugestao){
			if(sugestao.id!=null){
				$http.delete('api/sugestao/' + sugestao.id).
					then(function(response) {
						console.log("Sucesso");
					}, function(response) {
						console.log("Erro");
					});				
			}
			var index = $scope.sugestoes.indexOf(sugestao);
			if(index >= 0){
				$scope.sugestoes.splice(index,1);				
			}
		}
		
		$scope.cancelarSugestoes = function(){
			var copiaSugestoes = $scope.sugestoes.slice();
            for(var i=0; i< copiaSugestoes.length; i++) {
            	var sugestao = copiaSugestoes[i]; 
            	$scope.removerSugestao(sugestao);
            }
		}
		
		$scope.cancelarReservas = function(){
			var copiaReservas = $scope.reservas.slice();
            for(var i=0; i< copiaReservas.length; i++) {
            	var reserva = copiaReservas[i]; 
            	$scope.removerReserva(reserva);
            }
		}

	});