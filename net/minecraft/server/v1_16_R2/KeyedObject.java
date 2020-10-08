/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ public interface KeyedObject
/*   */ {
/*   */   default String getMinecraftKeyString() {
/* 6 */     MinecraftKey key = getMinecraftKey();
/* 7 */     return (key != null) ? key.toString() : null;
/*   */   }
/*   */   
/*   */   MinecraftKey getMinecraftKey();
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\KeyedObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */