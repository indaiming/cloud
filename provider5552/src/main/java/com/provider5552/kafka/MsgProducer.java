package com.provider5552.kafka;/**
 * @Author daim
 * @Description //TODO end
 * @Date date
 **/

import com.provider5552.entity.Person;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**消息提供者
 * @Classname Producer
 * @Description TODO
 * @Date 2019/8/1 
 * @Created by daim
 */
public class MsgProducer {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i <20 ; i++) {
            Person person = new Person(i+1,"FLSDFFSLDJF","kugou","jay"+i,"fjsldjl21fsdf2s2d");
            producer.send(new ProducerRecord<String, String>("test", "标题"+i, person.toString()));
            Thread.sleep(2000);
        }
        producer.close();
    }
}
