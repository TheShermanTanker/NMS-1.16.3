package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.io.Serializable;

@Beta
public interface Funnel<T> extends Serializable {
  void funnel(T paramT, PrimitiveSink paramPrimitiveSink);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\common\hash\Funnel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */