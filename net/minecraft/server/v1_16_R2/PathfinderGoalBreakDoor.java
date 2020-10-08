/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class PathfinderGoalBreakDoor extends PathfinderGoalDoorInteract {
/*    */   private final Predicate<EnumDifficulty> g;
/*    */   protected int a;
/*    */   protected int b;
/*    */   protected int c;
/*    */   
/*    */   public PathfinderGoalBreakDoor(EntityInsentient entityinsentient, Predicate<EnumDifficulty> predicate) {
/* 13 */     super(entityinsentient);
/* 14 */     this.b = -1;
/* 15 */     this.c = -1;
/* 16 */     this.g = predicate;
/*    */   }
/*    */   
/*    */   public PathfinderGoalBreakDoor(EntityInsentient entityinsentient, int i, Predicate<EnumDifficulty> predicate) {
/* 20 */     this(entityinsentient, predicate);
/* 21 */     this.c = i;
/*    */   }
/*    */   
/*    */   protected int f() {
/* 25 */     return Math.max(240, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 30 */     return !super.a() ? false : (!this.entity.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? false : ((a(this.entity.world.getDifficulty()) && !g())));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 35 */     super.c();
/* 36 */     this.a = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 41 */     return (this.a <= f() && !g() && this.door.a(this.entity.getPositionVector(), 2.0D) && a(this.entity.world.getDifficulty()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 46 */     super.d();
/* 47 */     this.entity.world.a(this.entity.getId(), this.door, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 52 */     super.e();
/* 53 */     if (this.entity.getRandom().nextInt(20) == 0) {
/* 54 */       this.entity.world.triggerEffect(1019, this.door, 0);
/* 55 */       if (!this.entity.ai) {
/* 56 */         this.entity.swingHand(this.entity.getRaisedHand());
/*    */       }
/*    */     } 
/*    */     
/* 60 */     this.a++;
/* 61 */     int i = (int)(this.a / f() * 10.0F);
/*    */     
/* 63 */     if (i != this.b) {
/* 64 */       this.entity.world.a(this.entity.getId(), this.door, i);
/* 65 */       this.b = i;
/*    */     } 
/*    */     
/* 68 */     if (this.a == f() && a(this.entity.world.getDifficulty())) {
/*    */       
/* 70 */       if (CraftEventFactory.callEntityBreakDoorEvent(this.entity, this.door).isCancelled()) {
/* 71 */         c();
/*    */         
/*    */         return;
/*    */       } 
/* 75 */       this.entity.world.a(this.door, false);
/* 76 */       this.entity.world.triggerEffect(1021, this.door, 0);
/* 77 */       this.entity.world.triggerEffect(2001, this.door, Block.getCombinedId(this.entity.world.getType(this.door)));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean a(EnumDifficulty enumdifficulty) {
/* 83 */     return this.g.test(enumdifficulty);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalBreakDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */