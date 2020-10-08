/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityRabbit;
/*    */ import net.minecraft.server.v1_16_R2.PathfinderGoalSelector;
/*    */ import net.minecraft.server.v1_16_R2.WorldServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Rabbit;
/*    */ 
/*    */ public class CraftRabbit extends CraftAnimals implements Rabbit {
/*    */   public CraftRabbit(CraftServer server, EntityRabbit entity) {
/* 14 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityRabbit getHandle() {
/* 19 */     return (EntityRabbit)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 24 */     return "CraftRabbit{RabbitType=" + getRabbitType() + "}";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 29 */     return EntityType.RABBIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public Rabbit.Type getRabbitType() {
/* 34 */     int type = getHandle().getRabbitType();
/* 35 */     return CraftMagicMapping.fromMagic(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRabbitType(Rabbit.Type type) {
/* 40 */     EntityRabbit entity = getHandle();
/* 41 */     if (getRabbitType() == Rabbit.Type.THE_KILLER_BUNNY) {
/*    */       
/* 43 */       WorldServer worldServer = ((CraftWorld)getWorld()).getHandle();
/* 44 */       entity.goalSelector = new PathfinderGoalSelector(worldServer.getMethodProfilerSupplier());
/* 45 */       entity.targetSelector = new PathfinderGoalSelector(worldServer.getMethodProfilerSupplier());
/* 46 */       entity.initPathfinder();
/* 47 */       entity.initializePathFinderGoals();
/*    */     } 
/*    */     
/* 50 */     entity.setRabbitType(CraftMagicMapping.toMagic(type));
/*    */   }
/*    */   
/*    */   private static class CraftMagicMapping
/*    */   {
/* 55 */     private static final int[] types = new int[(Rabbit.Type.values()).length];
/* 56 */     private static final Rabbit.Type[] reverse = new Rabbit.Type[(Rabbit.Type.values()).length];
/*    */     
/*    */     static {
/* 59 */       set(Rabbit.Type.BROWN, 0);
/* 60 */       set(Rabbit.Type.WHITE, 1);
/* 61 */       set(Rabbit.Type.BLACK, 2);
/* 62 */       set(Rabbit.Type.BLACK_AND_WHITE, 3);
/* 63 */       set(Rabbit.Type.GOLD, 4);
/* 64 */       set(Rabbit.Type.SALT_AND_PEPPER, 5);
/* 65 */       set(Rabbit.Type.THE_KILLER_BUNNY, 99);
/*    */     }
/*    */     
/*    */     private static void set(Rabbit.Type type, int value) {
/* 69 */       types[type.ordinal()] = value;
/* 70 */       if (value < reverse.length) {
/* 71 */         reverse[value] = type;
/*    */       }
/*    */     }
/*    */     
/*    */     public static Rabbit.Type fromMagic(int magic) {
/* 76 */       if (magic >= 0 && magic < reverse.length)
/* 77 */         return reverse[magic]; 
/* 78 */       if (magic == 99) {
/* 79 */         return Rabbit.Type.THE_KILLER_BUNNY;
/*    */       }
/* 81 */       return null;
/*    */     }
/*    */ 
/*    */     
/*    */     public static int toMagic(Rabbit.Type type) {
/* 86 */       return types[type.ordinal()];
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */