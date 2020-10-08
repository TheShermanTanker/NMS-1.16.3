/*    */ package org.bukkit;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SandstoneType
/*    */ {
/* 11 */   CRACKED(0),
/* 12 */   GLYPHED(1),
/* 13 */   SMOOTH(2);
/*    */   
/*    */   static {
/* 16 */     BY_DATA = Maps.newHashMap();
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
/* 48 */     for (SandstoneType type : values())
/* 49 */       BY_DATA.put(Byte.valueOf(type.data), type); 
/*    */   }
/*    */   
/*    */   private final byte data;
/*    */   private static final Map<Byte, SandstoneType> BY_DATA;
/*    */   
/*    */   SandstoneType(int data) {
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
/*    */   public static SandstoneType getByData(byte data) {
/*    */     return BY_DATA.get(Byte.valueOf(data));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\SandstoneType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */