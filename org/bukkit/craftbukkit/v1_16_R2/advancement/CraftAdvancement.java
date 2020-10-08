/*    */ package org.bukkit.craftbukkit.v1_16_R2.advancement;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import net.minecraft.server.v1_16_R2.Advancement;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ 
/*    */ public class CraftAdvancement implements Advancement {
/*    */   private final Advancement handle;
/*    */   
/*    */   public CraftAdvancement(Advancement handle) {
/* 14 */     this.handle = handle;
/*    */   }
/*    */   
/*    */   public Advancement getHandle() {
/* 18 */     return this.handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 23 */     return CraftNamespacedKey.fromMinecraft(this.handle.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getCriteria() {
/* 28 */     return Collections.unmodifiableCollection(this.handle.getCriteria().keySet());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\advancement\CraftAdvancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */