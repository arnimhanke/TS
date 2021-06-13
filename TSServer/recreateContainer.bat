docker stop TSServer
docker container rm TSServer
docker build --tag TSServer:1.0 .
docker run --name TSServer -p 4567:4567 TSServer:1.0