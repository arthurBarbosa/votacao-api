package com.arthurbarbosa.votacao.services;

public interface KafkaDispatcherService {

    public void send(String topic, String value, String otherValue);
}
