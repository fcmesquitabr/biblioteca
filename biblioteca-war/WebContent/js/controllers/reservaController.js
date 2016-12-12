moduleBiblioteca
	.controller('reservaController', function($scope, $rootScope, $http) {
		
		$scope.pageHeader="Reservas de Livros";		
		$scope.reservas = null;

		jQuery('#panelMensagemSucesso').hide();
		jQuery('#panelMensagemErro').hide();

		$http.get('api/reservas').
			then(function(response) {
				$scope.reservas = response.data;
			}, function(response){
	            $rootScope.mensagemErro = "Erro ao consultar as Reservas!";
	            jQuery('#panelMensagemErro').show();
			});				

		$scope.emprestarLivro = function(reserva){
			console.log("Emprestou Livro");
			$http.put('api/reservas/emprestimo/' + reserva.id, reserva).
				then(function(response){
					reserva.situacaoReserva = response.data.situacaoReserva;
					reserva.dataEmprestimo = response.data.dataEmprestimo; 
		            $rootScope.mensagemSucesso = "Livro emprestado com sucesso!";
		            jQuery('#panelMensagemSucesso').show();	
				}, function(response){
		            $rootScope.mensagemErro = "Erro ao realizar empréstimo!";
		            jQuery('#panelMensagemErro').show();

				});
		}
		
		$scope.cancelarReserva = function(reserva){
			console.log("Cancelou reserva");
			$http.put('api/reservas/cancelamento/' + reserva.id, reserva).
				then(function(response){
					reserva.situacaoReserva = response.data.situacaoReserva;
		            $rootScope.mensagemSucesso = "Reserva cancelada com sucesso!";
		            jQuery('#panelMensagemSucesso').show();
				}, function(response){
		            $rootScope.mensagemErro = "Erro ao cancelar a reserva!";
		            jQuery('#panelMensagemErro').show();

				});
		}

	})