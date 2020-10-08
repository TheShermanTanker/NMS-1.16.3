/*    */ package org.apache.logging.log4j.core.appender.mom.kafka;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.kafka.clients.producer.KafkaProducer;
/*    */ import org.apache.kafka.clients.producer.Producer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultKafkaProducerFactory
/*    */   implements KafkaProducerFactory
/*    */ {
/*    */   public Producer<byte[], byte[]> newKafkaProducer(Properties config) {
/* 40 */     return (Producer<byte[], byte[]>)new KafkaProducer(config);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\appender\mom\kafka\DefaultKafkaProducerFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */