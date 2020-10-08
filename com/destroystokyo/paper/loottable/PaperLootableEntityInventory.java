/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public interface PaperLootableEntityInventory
/*    */   extends LootableEntityInventory, PaperLootableInventory {
/*    */   Entity getHandle();
/*    */   
/*    */   default LootableInventory getAPILootableInventory() {
/* 12 */     return this;
/*    */   }
/*    */   
/*    */   default Entity getEntity() {
/* 16 */     return (Entity)getHandle().getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   default World getNMSWorld() {
/* 21 */     return getHandle().getWorld();
/*    */   }
/*    */ 
/*    */   
/*    */   default PaperLootableInventoryData getLootableData() {
/* 26 */     return (getHandle()).lootableData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperLootableEntityInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */