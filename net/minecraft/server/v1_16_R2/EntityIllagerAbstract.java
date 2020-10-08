/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityIllagerAbstract
/*    */   extends EntityRaider
/*    */ {
/*    */   protected EntityIllagerAbstract(EntityTypes<? extends EntityIllagerAbstract> var0, World var1) {
/* 23 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initPathfinder() {
/* 28 */     super.initPathfinder();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMonsterType getMonsterType() {
/* 33 */     return EnumMonsterType.ILLAGER;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public class b
/*    */     extends PathfinderGoalDoorOpen
/*    */   {
/*    */     public b(EntityIllagerAbstract var0, EntityRaider var1) {
/* 42 */       super(var1, false);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean a() {
/* 47 */       return (super.a() && this.a.fb());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityIllagerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */