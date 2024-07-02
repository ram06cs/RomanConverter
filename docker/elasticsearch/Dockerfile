# elasticsearch version
FROM docker.elastic.co/elasticsearch/elasticsearch:7.17.5
COPY elasticsearch.yml /usr/share/elasticsearch/config/
RUN chown -R elasticsearch:elasticsearch /usr/share/elasticsearch/config/

# elasticsearch ports
EXPOSE 9200 9300

# Command to run Elasticsearch
CMD ["elasticsearch"]
