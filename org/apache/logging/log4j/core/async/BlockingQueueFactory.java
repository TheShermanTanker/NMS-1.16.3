package org.apache.logging.log4j.core.async;

import java.util.concurrent.BlockingQueue;

public interface BlockingQueueFactory<E> {
  public static final String ELEMENT_TYPE = "BlockingQueueFactory";
  
  BlockingQueue<E> create(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\async\BlockingQueueFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */