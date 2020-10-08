/*    */ package org.bukkit;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public enum Fluid
/*    */   implements Keyed {
/*  8 */   WATER,
/*  9 */   FLOWING_WATER,
/* 10 */   LAVA,
/* 11 */   FLOWING_LAVA;
/*    */   
/*    */   private final NamespacedKey key;
/*    */   
/*    */   Fluid() {
/* 16 */     this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getKey() {
/* 22 */     return this.key;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Fluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */