
# 下载server

##官方提供了一键脚本

curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar

##如果用 Docker 

docker run -d -p 9411:9411 openzipkin/zipkin

#访问

任一方式启动后，访问 http://localhost:9411/zipkin/

