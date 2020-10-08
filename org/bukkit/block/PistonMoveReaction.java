/*    */ package org.bukkit.block;
/*    */ 
/*    */ import java.util.HashMap;
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
/*    */ public enum PistonMoveReaction
/*    */ {
/* 16 */   MOVE(0),
/*    */ 
/*    */ 
/*    */   
/* 20 */   BREAK(1),
/*    */ 
/*    */ 
/*    */   
/* 24 */   BLOCK(2),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   IGNORE(3),
/*    */ 
/*    */ 
/*    */   
/* 35 */   PUSH_ONLY(4); private int id;
/*    */   
/*    */   static {
/* 38 */     byId = new HashMap<>();
/*    */     
/* 40 */     for (PistonMoveReaction reaction : values())
/* 41 */       byId.put(Integer.valueOf(reaction.id), reaction); 
/*    */   }
/*    */   private static Map<Integer, PistonMoveReaction> byId;
/*    */   
/*    */   PistonMoveReaction(int id) {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public int getId() {
/* 55 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @Nullable
/*    */   public static PistonMoveReaction getById(int id) {
/* 66 */     return byId.get(Integer.valueOf(id));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\PistonMoveReaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */