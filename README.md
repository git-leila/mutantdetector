# mutantdetector
 api rest sts for exam ml
 
Estructura del proyecto -> ver wiki

Para ejecutar la aplicacion:
1. Generar la base de datos y la tabla con su data de forma local en mySql corriendo el script script_db.sql que se encuentra en la ruta: mutantdetector\src\main\resources\db
2. Correr el programa con sh o con ide de preferencia (Springboot) con profile local:
Posicionar en el directorio \mutantdetector\target y correr el comando: java -jar -Dspring.profiles.active=local mutant-0.0.1-SNAPSHOT.jar
3. Nivel 2: enviar la peticion POST -> http://localhost:8080/mutant/ 
            json ejemplo:              {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
   Nivek 3: enviar la pticion GET -> http://localhost:8080/stats/

URL API: 
     http://23.20.50.251:8080
 Nivel 2:
    POST http://23.20.50.251:8080/mutant/
    body raw JSON (aplication/json) for example: {"dna":["TCAG","TGCC","AAAC","TCTG"]}
 Nivel 3:
    http://23.20.50.251:8080/stats/
