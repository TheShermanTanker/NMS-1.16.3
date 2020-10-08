/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySenses
/*    */ {
/*    */   private final EntityInsentient a;
/* 11 */   private final List<Entity> b = Lists.newArrayList();
/* 12 */   private final List<Entity> c = Lists.newArrayList();
/*    */   
/*    */   public EntitySenses(EntityInsentient var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public void a() {
/* 19 */     this.b.clear();
/* 20 */     this.c.clear();
/*    */   }
/*    */   
/*    */   public boolean a(Entity var0) {
/* 24 */     if (this.b.contains(var0)) {
/* 25 */       return true;
/*    */     }
/* 27 */     if (this.c.contains(var0)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     this.a.world.getMethodProfiler().enter("canSee");
/* 32 */     boolean var1 = this.a.hasLineOfSight(var0);
/* 33 */     this.a.world.getMethodProfiler().exit();
/* 34 */     if (var1) {
/* 35 */       this.b.add(var0);
/*    */     } else {
/* 37 */       this.c.add(var0);
/*    */     } 
/* 39 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySenses.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */