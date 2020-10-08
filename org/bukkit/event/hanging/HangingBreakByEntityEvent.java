/*    */ package org.bukkit.event.hanging;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class HangingBreakByEntityEvent
/*    */   extends HangingBreakEvent
/*    */ {
/*    */   private final Entity remover;
/*    */   
/*    */   public HangingBreakByEntityEvent(@NotNull Hanging hanging, @Nullable Entity remover) {
/* 15 */     this(hanging, remover, HangingBreakEvent.RemoveCause.ENTITY);
/*    */   }
/*    */   
/*    */   public HangingBreakByEntityEvent(@NotNull Hanging hanging, @Nullable Entity remover, @NotNull HangingBreakEvent.RemoveCause cause) {
/* 19 */     super(hanging, cause);
/* 20 */     this.remover = remover;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getRemover() {
/* 31 */     return this.remover;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\hanging\HangingBreakByEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */