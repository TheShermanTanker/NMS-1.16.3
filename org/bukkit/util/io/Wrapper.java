/*    */ package org.bukkit.util.io;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ final class Wrapper<T extends Map<String, ?> & Serializable>
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = -986209235411767547L;
/*    */   final T map;
/*    */   
/*    */   static Wrapper<ImmutableMap<String, ?>> newWrapper(@NotNull ConfigurationSerializable obj) {
/* 16 */     return new Wrapper<>(ImmutableMap.builder().put("==", ConfigurationSerialization.getAlias(obj.getClass())).putAll(obj.serialize()).build());
/*    */   }
/*    */   
/*    */   private Wrapper(@NotNull T map) {
/* 20 */     this.map = map;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\io\Wrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */