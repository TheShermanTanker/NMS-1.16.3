/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity.memory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IRegistry;
/*    */ import net.minecraft.server.v1_16_R2.MemoryModuleType;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.entity.memory.MemoryKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CraftMemoryKey
/*    */ {
/*    */   public static <T, U> MemoryModuleType<U> fromMemoryKey(MemoryKey<T> memoryKey) {
/* 13 */     return (MemoryModuleType<U>)IRegistry.MEMORY_MODULE_TYPE.get(CraftNamespacedKey.toMinecraft(memoryKey.getKey()));
/*    */   }
/*    */   
/*    */   public static <T, U> MemoryKey<U> toMemoryKey(MemoryModuleType<T> memoryModuleType) {
/* 17 */     return MemoryKey.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.MEMORY_MODULE_TYPE.getKey(memoryModuleType)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\memory\CraftMemoryKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */