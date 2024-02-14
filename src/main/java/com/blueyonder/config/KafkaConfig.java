package com.blueyonder.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.KafkaOperations.ProducerCallback;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
public class KafkaConfig<TaskStatus>{
	
	   @Bean
	   public ConsumerFactory<String, TaskStatus> consumerFactory() {

	    Map<String, Object> configProps = new HashMap<>();
	    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	    configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "default-task-group");
	    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

	    return new DefaultKafkaConsumerFactory<>(configProps);
	  }
	
	   @Bean
		  public ProducerFactory<String, TaskStatus> producerFactory() {
	 
		    Map<String, Object> props = new HashMap<>();
		    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		    return new DefaultKafkaProducerFactory<String, TaskStatus>(props);
		  }
	   
	   @SuppressWarnings("unchecked")
		@Bean
		  public KafkaTemplate<String, TaskStatus> kafkaTemplate() {
		    KafkaTemplate<String, TaskStatus> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		    kafkaTemplate.setProducerListener(null);
		    return kafkaTemplate;
		  }

}
