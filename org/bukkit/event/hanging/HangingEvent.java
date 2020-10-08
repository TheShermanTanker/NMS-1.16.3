/*    */ package org.bukkit.event.hanging;
/*    */ 
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.event.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class HangingEvent
/*    */   extends Event
/*    */ {
/*    */   protected Hanging hanging;
/*    */   
/*    */   protected HangingEvent(@NotNull Hanging painting) {
/* 14 */     this.hanging = painting;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Hanging getEntity() {
/* 24 */     return this.hanging;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\hanging\HangingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */