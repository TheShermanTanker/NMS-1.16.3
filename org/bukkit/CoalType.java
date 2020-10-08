/*    */ package org.bukkit;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CoalType
/*    */ {
/* 11 */   COAL(0),
/* 12 */   CHARCOAL(1);
/*    */   
/*    */   static {
/* 15 */     BY_DATA = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     for (CoalType type : values())
/* 48 */       BY_DATA.put(Byte.valueOf(type.data), type); 
/*    */   }
/*    */   
/*    */   private final byte data;
/*    */   private static final Map<Byte, CoalType> BY_DATA;
/*    */   
/*    */   CoalType(int data) {
/*    */     this.data = (byte)data;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public byte getData() {
/*    */     return this.data;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   @Nullable
/*    */   public static CoalType getByData(byte data) {
/*    */     return BY_DATA.get(Byte.valueOf(data));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\CoalType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */