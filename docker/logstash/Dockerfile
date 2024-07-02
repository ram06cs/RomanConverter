FROM docker.elastic.co/logstash/logstash:7.17.5
RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY pipeline /usr/share/logstash/pipeline/
