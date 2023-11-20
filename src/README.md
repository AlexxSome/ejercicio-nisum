# Postulacion Nisum
###  Senior Backend Developer en Nisum Latam:


### Tecnologias utilizadas:
* Java 11
* Spring boot 2.7.17
* Swagger 2
* DB H2
* Gradle

### Ejecución:

* Descargar respositorio : git clone https://github.com/AlexxSome/ejercicio-nisum.git
* Ejecutar via IDE o terminal: gradle bootRun
* No es necesario ejecutar un sql, creacion de BD y carga de datos al iniciar la aplicacion
* Probar endpoint via postman: localhost:8080/user/list

#### Curls:

* Creacion usuario:  
  curl --location 'localhost:8080/user/create' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hhunter222",
  "phones": [
  {
  "number": "1234567",
  "citycode": "1",
  "contrycode": "57"
  }
  ]
  }'

* Actualizar usuario:  
  curl --location --request PUT 'localhost:8080/user/update/juan@rodriguez.org' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "id": 2,
  "createdAt": "2023-11-20T00:34:46.167+00:00",
  "modified": "2023-11-20T00:34:46.167+00:00",
  "lastLogin": "2023-11-20T00:34:46.167+00:00",
  "name": "Juan Rodriguez 2",
  "email": "juan@rodriguez.org",
  "password": "Hhunter223",
  "token": "567c1ae2-29bf-4692-8ed3-eb4f0db35785",
  "phones": [
  {
  "number": "1234567",
  "citycode": "12",
  "contrycode": "59"
  }
  ],
  "active": true
  }'

* Eliminar usuario:  
  curl --location --request DELETE 'localhost:8080/user/delete/juan@rodriguez.org' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "id": 2,
  "createdAt": "2023-11-20T00:34:46.167+00:00",
  "modified": "2023-11-20T00:34:46.167+00:00",
  "lastLogin": "2023-11-20T00:34:46.167+00:00",
  "name": "Juan Rodriguez 2",
  "email": "juan@rodriguez.org",
  "password": "Hhunter223",
  "token": "567c1ae2-29bf-4692-8ed3-eb4f0db35785",
  "phones": [
  {
  "number": "1234567",
  "citycode": "12",
  "contrycode": "59"
  }
  ],
  "active": false
  }'


