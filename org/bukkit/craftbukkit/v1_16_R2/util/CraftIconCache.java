/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ import org.bukkit.util.CachedServerIcon;
/*    */ 
/*    */ public class CraftIconCache implements CachedServerIcon {
/*    */   public final String value;
/*    */   
/*    */   public String getData() {
/*  8 */     return this.value;
/*    */   } public CraftIconCache(String value) {
/* 10 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftIconCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */