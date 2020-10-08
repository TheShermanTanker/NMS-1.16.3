/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CraftNamespacedKey
/*    */ {
/*    */   public static NamespacedKey fromStringOrNull(String string) {
/* 12 */     if (string == null || string.isEmpty()) {
/* 13 */       return null;
/*    */     }
/* 15 */     MinecraftKey minecraft = MinecraftKey.a(string);
/* 16 */     return (minecraft == null) ? null : fromMinecraft(minecraft);
/*    */   }
/*    */   
/*    */   public static NamespacedKey fromString(String string) {
/* 20 */     return fromMinecraft(new MinecraftKey(string));
/*    */   }
/*    */   
/*    */   public static NamespacedKey fromMinecraft(MinecraftKey minecraft) {
/* 24 */     return new NamespacedKey(minecraft.getNamespace(), minecraft.getKey());
/*    */   }
/*    */   
/*    */   public static MinecraftKey toMinecraft(NamespacedKey key) {
/* 28 */     return new MinecraftKey(key.getNamespace(), key.getKey());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftNamespacedKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */