package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.Nullable;

@GwtCompatible
public interface FutureCallback<V> {
  void onSuccess(@Nullable V paramV);
  
  void onFailure(Throwable paramThrowable);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\commo\\util\concurrent\FutureCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */