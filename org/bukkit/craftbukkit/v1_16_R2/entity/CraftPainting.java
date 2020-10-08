/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*    */ import net.minecraft.server.v1_16_R2.EntityPainting;
/*    */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*    */ import net.minecraft.server.v1_16_R2.Paintings;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import net.minecraft.server.v1_16_R2.WorldServer;
/*    */ import org.bukkit.Art;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftArt;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPainting extends CraftHanging implements Painting {
/*    */   public CraftPainting(CraftServer server, EntityPainting entity) {
/* 18 */     super(server, (EntityHanging)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Art getArt() {
/* 23 */     Paintings art = (getHandle()).art;
/* 24 */     return CraftArt.NotchToBukkit(art);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setArt(Art art) {
/* 29 */     return setArt(art, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setArt(Art art, boolean force) {
/* 34 */     EntityPainting painting = getHandle();
/* 35 */     Paintings oldArt = painting.art;
/* 36 */     painting.art = CraftArt.BukkitToNotch(art);
/* 37 */     painting.setDirection(painting.getDirection());
/* 38 */     if (!force && !painting.survives()) {
/*    */       
/* 40 */       painting.art = oldArt;
/* 41 */       painting.setDirection(painting.getDirection());
/* 42 */       return false;
/*    */     } 
/* 44 */     update();
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setFacingDirection(BlockFace face, boolean force) {
/* 50 */     if (super.setFacingDirection(face, force)) {
/* 51 */       update();
/* 52 */       return true;
/*    */     } 
/*    */     
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   private void update() {
/* 59 */     WorldServer world = ((CraftWorld)getWorld()).getHandle();
/* 60 */     EntityPainting painting = (EntityPainting)EntityTypes.PAINTING.a((World)world);
/* 61 */     painting.blockPosition = (getHandle()).blockPosition;
/* 62 */     painting.art = (getHandle()).art;
/* 63 */     painting.setDirection(getHandle().getDirection());
/* 64 */     getHandle().die();
/* 65 */     (getHandle()).velocityChanged = true;
/* 66 */     world.addEntity((Entity)painting);
/* 67 */     this.entity = (Entity)painting;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPainting getHandle() {
/* 72 */     return (EntityPainting)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "CraftPainting{art=" + getArt() + "}";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 82 */     return EntityType.PAINTING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */