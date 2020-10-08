/*    */ package org.bukkit;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GrassSpecies
/*    */ {
/* 15 */   DEAD(0),
/*    */ 
/*    */ 
/*    */   
/* 19 */   NORMAL(1),
/*    */ 
/*    */ 
/*    */   
/* 23 */   FERN_LIKE(2);
/*    */   
/*    */   static {
/* 26 */     BY_DATA = Maps.newHashMap();
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
/* 58 */     for (GrassSpecies grassSpecies : values())
/* 59 */       BY_DATA.put(Byte.valueOf(grassSpecies.getData()), grassSpecies); 
/*    */   }
/*    */   
/*    */   private final byte data;
/*    */   private static final Map<Byte, GrassSpecies> BY_DATA;
/*    */   
/*    */   GrassSpecies(int data) {
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
/*    */   public static GrassSpecies getByData(byte data) {
/*    */     return BY_DATA.get(Byte.valueOf(data));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\GrassSpecies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */