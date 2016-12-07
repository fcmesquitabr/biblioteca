


# Para o funcionamento do biblio-distributor é necessário
- Java JDK 1.8
- ActiveMQ
- Postgres

Executar as classes main como java program 
- org.fa7.biblio.producer.bin.Producer
- org.fa7.biblio.consumer.bin.Consumer

Os logs das duas aplicações serão criados nas respectivas pastas
* biblioteca/biblio-distributor/biblio-distributor-producer/logs
* biblioteca/biblio-distributor/biblio-distributor-consumer/logs

### Active MQ
Foi utilizado a versão 5.13.3
As aplicações estão configuradas para utilizar o endereço: tcp://localhost:61616
Nome da fila utilizada pela aplicação --> distributor-inbox
Se for necessário alguma ajuste no endereço, editar os arquivos application.properties de cada projeto (producer e consumer)

### Postgres
As tabelas serão criadas pelo JPA de maneira automática, mas observar se as configurações do banco estão ajustadas no arquivo application.properties.
spring.datasource.url=jdbc:postgresql://localhost:5432/biblio-distributor
spring.datasource.username=postgres
spring.datasource.password=postgres

### Sugestão para carga inicial de dados
INSERT INTO book (amount, description, isbn, name, "section") VALUES
(100, 'Livro 1', 123, 'Livro 1', 'Literatura'),
(500, 'Livro 2', 456, 'Livro 2', 'Aventura'),
(10, 'Livro 3', 789, 'Livro 3', 'Romance'),
(1, 'Livro 4', 12, 'Livro 4', 'Literatura'),
(40, 'Livro 5', 345, 'Livro 5', 'Aventura');

### Exemplo de uma requisição de pedido para o distribuidor
{
  "clientId": 12345,
  "callBackUrl": "http://localhost:8080/biblio/ordercallback",
  "clientOrderId": "123456",
  "items": [
    {
      "isbn": 123,
      "amount": 10
    },
    {
      "isbn": 345,
      "amount": 50
    },
    {
      "isbn": 456789,
      "amount": 10
    },
    {
      "isbn": 456,
      "amount": 10
    }
  ]
}
