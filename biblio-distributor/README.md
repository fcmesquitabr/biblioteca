# biblio-distributor

Graduation project

- Spring boot
- ActiveMQ
- Postgres


#Request example
{
  "clientId": 12345,
  "callBackUrl": "http://localhost:8080/biblio/ordercallback",
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

#Initial DB query
INSERT INTO book (amount, description, isbn, name, "section") VALUES
(100, 'Livro 1', 123, 'Livro 1', 'Literatura'),
(500, 'Livro 2', 456, 'Livro 2', 'Aventura'),
(10, 'Livro 3', 789, 'Livro 3', 'Romance'),
(1, 'Livro 4', 12, 'Livro 4', 'Literatura'),
(40, 'Livro 5', 345, 'Livro 5', 'Aventura');

#Active MQ info 
broker -> tcp://localhost:61616
queue name --> distributor-inbox
more details in application.properties files (producer and consumer projects)

#Postgress info
database name --> biblio-distributor
port --> 5432
more details in application.properties file (consumer project)