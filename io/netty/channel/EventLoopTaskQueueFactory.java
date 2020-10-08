package io.netty.channel;

import java.util.Queue;

public interface EventLoopTaskQueueFactory {
  Queue<Runnable> newTaskQueue(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\EventLoopTaskQueueFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */