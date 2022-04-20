cd $1
mvn clean package
cd ..
docker-compose build