/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityPanda;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Panda;
/*    */ 
/*    */ public class CraftPanda extends CraftAnimals implements Panda {
/*    */   public CraftPanda(CraftServer server, EntityPanda entity) {
/* 12 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPanda getHandle() {
/* 17 */     return (EntityPanda)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.PANDA;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "CraftPanda";
/*    */   }
/*    */ 
/*    */   
/*    */   public Panda.Gene getMainGene() {
/* 32 */     return fromNms(getHandle().getMainGene());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMainGene(Panda.Gene gene) {
/* 37 */     getHandle().setMainGene(toNms(gene));
/*    */   }
/*    */ 
/*    */   
/*    */   public Panda.Gene getHiddenGene() {
/* 42 */     return fromNms(getHandle().getHiddenGene());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHiddenGene(Panda.Gene gene) {
/* 47 */     getHandle().setHiddenGene(toNms(gene));
/*    */   }
/*    */   
/*    */   public static Panda.Gene fromNms(EntityPanda.Gene gene) {
/* 51 */     Preconditions.checkArgument((gene != null), "Gene may not be null");
/*    */     
/* 53 */     return Panda.Gene.values()[gene.ordinal()];
/*    */   }
/*    */   
/*    */   public static EntityPanda.Gene toNms(Panda.Gene gene) {
/* 57 */     Preconditions.checkArgument((gene != null), "Gene may not be null");
/*    */     
/* 59 */     return EntityPanda.Gene.values()[gene.ordinal()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */