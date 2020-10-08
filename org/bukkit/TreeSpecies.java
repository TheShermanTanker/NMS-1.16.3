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
/*    */ public enum TreeSpecies
/*    */ {
/* 15 */   GENERIC(0),
/*    */ 
/*    */ 
/*    */   
/* 19 */   REDWOOD(1),
/*    */ 
/*    */ 
/*    */   
/* 23 */   BIRCH(2),
/*    */ 
/*    */ 
/*    */   
/* 27 */   JUNGLE(3),
/*    */ 
/*    */ 
/*    */   
/* 31 */   ACACIA(4),
/*    */ 
/*    */ 
/*    */   
/* 35 */   DARK_OAK(5);
/*    */   private final byte data;
/*    */   
/*    */   static {
/* 39 */     BY_DATA = Maps.newHashMap();
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
/* 71 */     for (TreeSpecies species : values())
/* 72 */       BY_DATA.put(Byte.valueOf(species.data), species); 
/*    */   }
/*    */   
/*    */   private static final Map<Byte, TreeSpecies> BY_DATA;
/*    */   
/*    */   TreeSpecies(int data) {
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
/*    */   public static TreeSpecies getByData(byte data) {
/*    */     return BY_DATA.get(Byte.valueOf(data));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\TreeSpecies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */