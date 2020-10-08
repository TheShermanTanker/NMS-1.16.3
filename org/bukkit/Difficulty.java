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
/*    */ public enum Difficulty
/*    */ {
/* 15 */   PEACEFUL(0),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   EASY(1),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   NORMAL(2),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   HARD(3);
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
/* 69 */     for (Difficulty diff : values())
/* 70 */       BY_ID.put(Integer.valueOf(diff.value), diff); 
/*    */   }
/*    */   
/*    */   private final int value;
/*    */   private static final Map<Integer, Difficulty> BY_ID;
/*    */   
/*    */   Difficulty(int value) {
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
/*    */   public static Difficulty getByValue(int value) {
/*    */     return BY_ID.get(Integer.valueOf(value));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Difficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */