package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.services.KafkaDispatcherService;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDispatcherServiceImpl implements Closeable, KafkaDispatcherService {

    private final KafkaProducer<String, String> producer;

    KafkaDispatcherServiceImpl(){
        this.producer = new KafkaProducer<>(properties());
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.201.182:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    @Override
    public void send(String topic, String value, String otherValue) {
        var record = new ProducerRecord<>(topic, value, otherValue);
        try {
            Callback callback = (data, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                System.out.println("Sessão encerrada: " + data.topic() + "::partition: " + data.partition() + " offset: " + data.offset());
            };
            producer.send(record, callback).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}
