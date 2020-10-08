package org.bukkit.craftbukkit.libs.org.apache.commons.lang3.concurrent;

public interface ConcurrentInitializer<T> {
  T get() throws ConcurrentException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\lang3\concurrent\ConcurrentInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */