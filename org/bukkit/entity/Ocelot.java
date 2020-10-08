/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Ocelot
/*    */   extends Animals
/*    */ {
/*    */   @NotNull
/*    */   Type getCatType();
/*    */   
/*    */   void setCatType(@NotNull Type paramType);
/*    */   
/*    */   @Deprecated
/*    */   public enum Type
/*    */   {
/* 34 */     WILD_OCELOT(0),
/* 35 */     BLACK_CAT(1),
/* 36 */     RED_CAT(2),
/* 37 */     SIAMESE_CAT(3);
/*    */     
/* 39 */     private static final Type[] types = new Type[(values()).length];
/*    */     private final int id;
/*    */     
/*    */     static {
/* 43 */       for (Type type : values()) {
/* 44 */         types[type.getId()] = type;
/*    */       }
/*    */     }
/*    */     
/*    */     Type(int id) {
/* 49 */       this.id = id;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @Deprecated
/*    */     public int getId() {
/* 60 */       return this.id;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @Deprecated
/*    */     @Nullable
/*    */     public static Type getType(int id) {
/* 73 */       return (id >= types.length) ? null : types[id];
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Ocelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */