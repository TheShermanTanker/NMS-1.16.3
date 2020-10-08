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
/*    */ 
/*    */ 
/*    */ public enum GameMode
/*    */ {
/* 17 */   CREATIVE(1),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   SURVIVAL(0),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   ADVENTURE(2),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   SPECTATOR(3);
/*    */   
/*    */   static {
/* 37 */     BY_ID = Maps.newHashMap();
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
/* 69 */     for (GameMode mode : values())
/* 70 */       BY_ID.put(Integer.valueOf(mode.getValue()), mode); 
/*    */   }
/*    */   
/*    */   private final int value;
/*    */   private static final Map<Integer, GameMode> BY_ID;
/*    */   
/*    */   GameMode(int value) {
/*    */     this.value = value;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public int getValue() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   @Nullable
/*    */   public static GameMode getByValue(int value) {
/*    */     return BY_ID.get(Integer.valueOf(value));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\GameMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */