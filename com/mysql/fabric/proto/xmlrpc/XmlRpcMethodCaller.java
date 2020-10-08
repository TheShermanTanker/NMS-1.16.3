package com.mysql.fabric.proto.xmlrpc;

import com.mysql.fabric.FabricCommunicationException;
import java.util.List;

public interface XmlRpcMethodCaller {
  List<?> call(String paramString, Object[] paramArrayOfObject) throws FabricCommunicationException;
  
  void setHeader(String paramString1, String paramString2);
  
  void clearHeader(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\proto\xmlrpc\XmlRpcMethodCaller.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */