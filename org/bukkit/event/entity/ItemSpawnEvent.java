/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class ItemSpawnEvent
/*    */   extends EntitySpawnEvent
/*    */ {
/*    */   @Deprecated
/*    */   public ItemSpawnEvent(@NotNull Item spawnee, Location loc) {
/* 14 */     this(spawnee);
/*    */   }
/*    */   
/*    */   public ItemSpawnEvent(@NotNull Item spawnee) {
/* 18 */     super((Entity)spawnee);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getEntity() {
/* 24 */     return (Item)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ItemSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */