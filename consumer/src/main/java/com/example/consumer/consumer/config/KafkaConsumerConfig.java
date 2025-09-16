package com.example.consumer.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.dto.MessageDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

//Activa el soporte de kafka en Spring, necesario para que @KafkaListener funciones
@EnableKafka
// Indica que esta clase proporciona beans de configuración par Spring
@Configuration
public class KafkaConsumerConfig {
// Define un bean que crea consumidores de kafka con la configuración personalizada.
    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {
        // Mapa con todas las propiedades necesarias para configurar el consumidor.
        Map<String, Object> props = new HashMap<>();
        // Direccion del broker kafka al que nos conectamos.
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Identificador del grupo de consumidores, permite que se repartan mensajes entre miembros del grupo.
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer");
        // Controla desde donde empezar a leer si no hay offsets previos, "earliest" lee desde el principio del topic.
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Permite que la deserialización de JSON confie en cualquier paquete de clases.
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Esto evita problemas de clase desconocida
/**
 * Creacion de la fabrica de consumidores
 * Configuracion(props), deserializador de claves(String), desearializador de valores(JSON->MessageDto, con manejo de errores.
 */
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(MessageDto.class))
        );
    }
// Bean que crea contenedores para los listener de Kafka(@KafkaListener)
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> kafkaListenerContainerFactory() {
        // Creamos un contenedor concurrente, capaz de manejar múltiples hilos.
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        // Indicmaos que use nuestra configuración de consumidor personalizada.
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}