package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Supplier;

@FunctionalInterface
@GwtCompatible
public interface Supplier<T> extends Supplier<T> {
  @CanIgnoreReturnValue
  T get();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\common\base\Supplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */