/*    */ package com.destroystokyo.paper.entity.ai;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface Goal<T extends org.bukkit.entity.Mob>
/*    */ {
/*    */   boolean shouldActivate();
/*    */   
/*    */   default boolean shouldStayActive() {
/* 27 */     return shouldActivate();
/*    */   }
/*    */   
/*    */   default void start() {}
/*    */   
/*    */   default void stop() {}
/*    */   
/*    */   default void tick() {}
/*    */   
/*    */   @NotNull
/*    */   GoalKey<T> getKey();
/*    */   
/*    */   @NotNull
/*    */   EnumSet<GoalType> getTypes();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\Goal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */