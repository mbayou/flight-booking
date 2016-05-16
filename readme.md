#Compiler le projet

```
mvn clean package
```

#lancer le projet

Nous supposons que vous êtes à la racine du projet

```
java -jar target/flight-booking-1.0-SNAPSHOT.jar 
```

#Info supplémentaire

## Logger

Vous pouvez modifier le niveau des log, actuellement il est fixé en "INFO". Ce changement est à effectuer dans le fichier logback.xml

## Le premier lancement

Lors du premier lancement le serveur est accessible depuis le port 9888 sur l'adresse 127.0.0.1 alias localhost. Vous devrez faire appel à la ressource suivante pour préparer les données, voici un code curl pour exécuter cette tâche : 

```
curl -X POST -H "Cache-Control: no-cache" -d '' "http://localhost:9888/admin/prepare"
```