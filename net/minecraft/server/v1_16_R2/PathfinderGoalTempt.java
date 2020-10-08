/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalTempt
/*     */   extends PathfinderGoal
/*     */ {
/*  13 */   private static final PathfinderTargetCondition c = (new PathfinderTargetCondition()).a(10.0D).a().b().d().c();
/*     */   protected final EntityCreature a;
/*     */   private final double d;
/*     */   private double e;
/*     */   private double f;
/*     */   private double g;
/*     */   private double h;
/*     */   private double i;
/*     */   protected EntityLiving target;
/*     */   private int j;
/*     */   private boolean k;
/*     */   private final RecipeItemStack l;
/*     */   private final boolean m;
/*     */   
/*     */   public PathfinderGoalTempt(EntityCreature entitycreature, double d0, RecipeItemStack recipeitemstack, boolean flag) {
/*  28 */     this(entitycreature, d0, flag, recipeitemstack);
/*     */   }
/*     */   
/*     */   public PathfinderGoalTempt(EntityCreature entitycreature, double d0, boolean flag, RecipeItemStack recipeitemstack) {
/*  32 */     this.a = entitycreature;
/*  33 */     this.d = d0;
/*  34 */     this.l = recipeitemstack;
/*  35 */     this.m = flag;
/*  36 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*  37 */     if (!(entitycreature.getNavigation() instanceof Navigation) && !(entitycreature.getNavigation() instanceof NavigationFlying)) {
/*  38 */       throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  44 */     if (this.j > 0) {
/*  45 */       this.j--;
/*  46 */       return false;
/*     */     } 
/*  48 */     this.target = this.a.world.a(c, this.a);
/*     */     
/*  50 */     boolean tempt = (this.target == null) ? false : ((a(this.target.getItemInMainHand()) || a(this.target.getItemInOffHand())));
/*  51 */     if (tempt) {
/*  52 */       EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent(this.a, this.target, EntityTargetEvent.TargetReason.TEMPT);
/*  53 */       if (event.isCancelled()) {
/*  54 */         return false;
/*     */       }
/*  56 */       this.target = (event.getTarget() == null) ? null : ((CraftLivingEntity)event.getTarget()).getHandle();
/*     */     } 
/*  58 */     return (tempt && this.target != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(ItemStack itemstack) {
/*  64 */     return this.l.test(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  69 */     if (g()) {
/*  70 */       if (this.a.h(this.target) < 36.0D) {
/*  71 */         if (this.target.h(this.e, this.f, this.g) > 0.010000000000000002D) {
/*  72 */           return false;
/*     */         }
/*     */         
/*  75 */         if (Math.abs(this.target.pitch - this.h) > 5.0D || Math.abs(this.target.yaw - this.i) > 5.0D) {
/*  76 */           return false;
/*     */         }
/*     */       } else {
/*  79 */         this.e = this.target.locX();
/*  80 */         this.f = this.target.locY();
/*  81 */         this.g = this.target.locZ();
/*     */       } 
/*     */       
/*  84 */       this.h = this.target.pitch;
/*  85 */       this.i = this.target.yaw;
/*     */     } 
/*     */     
/*  88 */     return a();
/*     */   }
/*     */   
/*     */   protected boolean g() {
/*  92 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  97 */     this.e = this.target.locX();
/*  98 */     this.f = this.target.locY();
/*  99 */     this.g = this.target.locZ();
/* 100 */     this.k = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/* 105 */     this.target = null;
/* 106 */     this.a.getNavigation().o();
/* 107 */     this.j = 100;
/* 108 */     this.k = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/* 113 */     this.a.getControllerLook().a(this.target, (this.a.eo() + 20), this.a.O());
/* 114 */     if (this.a.h(this.target) < 6.25D) {
/* 115 */       this.a.getNavigation().o();
/*     */     } else {
/* 117 */       this.a.getNavigation().a(this.target, this.d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean h() {
/* 123 */     return this.k;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalTempt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */