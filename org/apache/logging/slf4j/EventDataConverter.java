/*    */ package org.apache.logging.slf4j;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.message.Message;
/*    */ import org.apache.logging.log4j.message.ParameterizedMessage;
/*    */ import org.apache.logging.log4j.message.StructuredDataMessage;
/*    */ import org.slf4j.ext.EventData;
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
/*    */ public class EventDataConverter
/*    */ {
/*    */   public Message convertEvent(String message, Object[] objects, Throwable throwable) {
/*    */     try {
/* 33 */       EventData data = (objects != null && objects[0] instanceof EventData) ? (EventData)objects[0] : new EventData(message);
/*    */       
/* 35 */       StructuredDataMessage msg = new StructuredDataMessage(data.getEventId(), data.getMessage(), data.getEventType());
/*    */       
/* 37 */       for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)data.getEventMap().entrySet()) {
/* 38 */         String key = entry.getKey();
/* 39 */         if ("EventType".equals(key) || "EventId".equals(key) || "EventMessage".equals(key)) {
/*    */           continue;
/*    */         }
/*    */         
/* 43 */         msg.put(key, String.valueOf(entry.getValue()));
/*    */       } 
/* 45 */       return (Message)msg;
/* 46 */     } catch (Exception ex) {
/* 47 */       return (Message)new ParameterizedMessage(message, objects, throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\EventDataConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */