/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.DamageSource;
/*    */ 
/*    */ public final class CraftDamageSource
/*    */   extends DamageSource {
/*    */   public static DamageSource copyOf(DamageSource original) {
/*  8 */     CraftDamageSource newSource = new CraftDamageSource(original.translationIndex);
/*    */ 
/*    */     
/* 11 */     if (original.ignoresArmor()) {
/* 12 */       newSource.setIgnoreArmor();
/*    */     }
/*    */ 
/*    */     
/* 16 */     if (original.isMagic()) {
/* 17 */       newSource.setMagic();
/*    */     }
/*    */ 
/*    */     
/* 21 */     if (original.isExplosion()) {
/* 22 */       newSource.setExplosion();
/*    */     }
/*    */     
/* 25 */     return newSource;
/*    */   }
/*    */   
/*    */   private CraftDamageSource(String identifier) {
/* 29 */     super(identifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */