moduleBiblioteca
	.controller('reservaController', function($scope, $http) {
		
		$scope.pageHeader="Reservas de Livros";		
		$scope.reservas = null;

		$http.get('api/reservas').
			then(function(response) {
				$scope.reservas = response.data;
			}, function(response){
				console.log("Erro ao consultar Reservas");
			});				

		$scope.emprestarLivro = function(reserva){
			console.log("Emprestou Livro");
			$http.put('api/reservas/emprestimo/' + reserva.id, reserva).
				then(function(response){
					reserva.situacaoReserva = response.data.situacaoReserva;
					//reserva = response.data;	
				}, function(response){
					console.log("Erro ao realizar empréstimo");
				});
		}
		
		$scope.cancelarReserva = function(reserva){
			console.log("Cancelou reserva");
			$http.put('api/reservas/cancelamento/' + reserva.id, reserva).
				then(function(response){
					reserva.situacaoReserva = response.data.situacaoReserva;
					//reserva = response.data;	
				}, function(response){
					console.log("Erro ao cancelar reserva");
				});
		}

	})