/*    */ package org.bukkit.craftbukkit.v1_16_R2.tag;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.Tag;
/*    */ import net.minecraft.server.v1_16_R2.Tags;
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.Tag;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ 
/*    */ public abstract class CraftTag<N, B extends Keyed>
/*    */   implements Tag<B> {
/*    */   private final Tags<N> registry;
/*    */   private final MinecraftKey tag;
/*    */   private Tag<N> handle;
/*    */   
/*    */   public CraftTag(Tags<N> registry, MinecraftKey tag) {
/* 18 */     this.registry = registry;
/* 19 */     this.tag = tag;
/*    */   }
/*    */   
/*    */   protected Tag<N> getHandle() {
/* 23 */     if (this.handle == null) {
/* 24 */       this.handle = this.registry.b(this.tag);
/*    */     }
/*    */     
/* 27 */     return this.handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 32 */     return CraftNamespacedKey.fromMinecraft(this.tag);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\tag\CraftTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */