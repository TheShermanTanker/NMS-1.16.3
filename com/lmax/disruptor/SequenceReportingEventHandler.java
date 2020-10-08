package com.lmax.disruptor;

public interface SequenceReportingEventHandler<T> extends EventHandler<T> {
  void setSequenceCallback(Sequence paramSequence);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SequenceReportingEventHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */