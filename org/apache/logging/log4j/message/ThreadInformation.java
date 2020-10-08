package org.apache.logging.log4j.message;

interface ThreadInformation {
  void printThreadInfo(StringBuilder paramStringBuilder);
  
  void printStack(StringBuilder paramStringBuilder, StackTraceElement[] paramArrayOfStackTraceElement);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\message\ThreadInformation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */