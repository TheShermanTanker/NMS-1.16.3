package io.netty.util;

public interface AttributeMap {
  <T> Attribute<T> attr(AttributeKey<T> paramAttributeKey);
  
  <T> boolean hasAttr(AttributeKey<T> paramAttributeKey);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\AttributeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */