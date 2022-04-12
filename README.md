# WADA Athlete Locator
An application designed for 'World Anti-Doping Agency' to locate and test athletes for anti-doping tests.

## Getting Started (Docker)
Build using docker:
```
docker-compose build
```

Start API Server and MongoDB:
```
docker-compose up -d
```

## Access MongoDB (via Docker)
```
$ docker exec -it wada-athlete-locator_mongos_1 mongo --authenticationDatabase admin --username root --password 123456
$ use wada
```
