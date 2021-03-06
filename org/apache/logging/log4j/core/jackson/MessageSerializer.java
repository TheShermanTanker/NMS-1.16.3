/*    */ package org.apache.logging.log4j.core.jackson;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonGenerationException;
/*    */ import com.fasterxml.jackson.core.JsonGenerator;
/*    */ import com.fasterxml.jackson.databind.SerializerProvider;
/*    */ import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
/*    */ import java.io.IOException;
/*    */ import org.apache.logging.log4j.message.Message;
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
/*    */ final class MessageSerializer
/*    */   extends StdScalarSerializer<Message>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   MessageSerializer() {
/* 38 */     super(Message.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serialize(Message value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
/* 44 */     jgen.writeString(value.getFormattedMessage());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\jackson\MessageSerializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */