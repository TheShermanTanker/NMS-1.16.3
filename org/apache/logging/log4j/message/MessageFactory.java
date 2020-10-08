package org.apache.logging.log4j.message;

public interface MessageFactory {
  Message newMessage(Object paramObject);
  
  Message newMessage(String paramString);
  
  Message newMessage(String paramString, Object... paramVarArgs);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\message\MessageFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */