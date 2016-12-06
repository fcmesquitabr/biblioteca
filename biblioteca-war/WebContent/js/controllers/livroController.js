moduleBiblioteca
	.controller('livroController', function($scope, $http) {
		$scope.sugestoes = [];
		$scope.reservas = [];
		
		$http.get('api/livros').
	        then(function(response) {
	            $scope.livros = response.data;
	            for(var i=0; i< $scope.livros.length; i++) {
	            	var livro = $scope.livros[i]; 
	            	livro.estaReservado=false;
	            	livro.estaSugerido=false;
	            }
	        });
		
		$scope.enviarReserva = function(){
			$http.post('api/reservas',$scope.getReservas()).
				then(function(response) {
					console.log("Sucesso");
				}, function(response) {
					console.log("Erro");
				});
		}
		
		$scope.getReservas = function(){
			var reservas=[];
			for(var i=0; i< $scope.reservas.length; i++) {
				var reservaLivro = {};
				var livro = $scope.reservas[i];
            	reservaLivro.livro = {};
            	reservaLivro.livro.id=livro.id;
            	reservaLivro.aluno = {};
            	reservaLivro.aluno.id=1;
            	reservas.push(reservaLivro);
	        }
			return reservas;
		}
		
		$scope.sugerirLivro = function(livro){
			livro.estaSugerido=true;
			if($scope.sugestoes.indexOf(livro) == -1){
				$scope.sugestoes.push(livro);				
			}
		}
		
		$scope.reservarLivro = function(livro){
			livro.estaReservado=true;
			if($scope.reservas.indexOf(livro) == -1){
				$scope.reservas.push(livro);				
			}
		} 

		$scope.removerReserva = function(livro){
			livro.estaReservado=false;
			var index = $scope.reservas.indexOf(livro);
			if(index >= 0){
				$scope.reservas.splice(index,1);				
			}
		}

		$scope.removerSugestao = function(livro){
			livro.estaSugerido=false;
			var index = $scope.sugestoes.indexOf(livro);
			if(index >= 0){
				$scope.sugestoes.splice(index,1);				
			}
		}
		
		$scope.cancelarSugestoes = function(){
			$scope.sugestoes = [];
            for(var i=0; i< $scope.livros.length; i++) {
            	var livro = $scope.livros[i]; 
            	livro.estaSugerido=false;
            }
		}
		
		$scope.cancelarReservas = function(){
			$scope.reservas = [];
            for(var i=0; i< $scope.livros.length; i++) {
            	var livro = $scope.livros[i]; 
            	livro.estaReservado=false;
            }
		}

	});