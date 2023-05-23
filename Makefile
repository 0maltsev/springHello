startALL:
	startFirst
	runELK

startFirst:
	runZookeeper
	startServer
	runPrometheus
	#runGraphana

startServer:
	gradle build
	java -jar Producer/build/libs/Producer-0.0.1-SNAPSHOT.jar --spring.config.location=producerConfigs/producer2.properties


runZookeeper:
	apache-zookeeper-3.8.0-bin/bin/zkServer.sh start


###Makefile не умеет в переменные, поэтому необходимо руками из терминала запускать
runPrometheus:
	docker run -d -p 9093:9093 -v "$PWD"/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

runGraphana:
### Сначала надо скачать архив с https://grafana.com/grafana/download/9.5.2?platform=mac
	grafana-9.5.2/bin/grafana server


runELK:
	docker-compose up