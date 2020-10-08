/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class CombatTracker
/*     */ {
/*  11 */   private final List<CombatEntry> a = Lists.newArrayList();
/*     */   private final EntityLiving b;
/*     */   private int c;
/*     */   private int d;
/*     */   private int e;
/*     */   private boolean f;
/*     */   private boolean g;
/*     */   private String h;
/*     */   
/*     */   public CombatTracker(EntityLiving entityliving) {
/*  21 */     this.b = entityliving;
/*     */   }
/*     */   
/*     */   public void a() {
/*  25 */     k();
/*  26 */     Optional<BlockPosition> optional = this.b.dq();
/*     */     
/*  28 */     if (optional.isPresent()) {
/*  29 */       IBlockData iblockdata = this.b.world.getType(optional.get());
/*     */       
/*  31 */       if (!iblockdata.a(Blocks.LADDER) && !iblockdata.a(TagsBlock.TRAPDOORS)) {
/*  32 */         if (iblockdata.a(Blocks.VINE)) {
/*  33 */           this.h = "vines";
/*  34 */         } else if (!iblockdata.a(Blocks.WEEPING_VINES) && !iblockdata.a(Blocks.WEEPING_VINES_PLANT)) {
/*  35 */           if (!iblockdata.a(Blocks.TWISTING_VINES) && !iblockdata.a(Blocks.TWISTING_VINES_PLANT)) {
/*  36 */             if (iblockdata.a(Blocks.SCAFFOLDING)) {
/*  37 */               this.h = "scaffolding";
/*     */             } else {
/*  39 */               this.h = "other_climbable";
/*     */             } 
/*     */           } else {
/*  42 */             this.h = "twisting_vines";
/*     */           } 
/*     */         } else {
/*  45 */           this.h = "weeping_vines";
/*     */         } 
/*     */       } else {
/*  48 */         this.h = "ladder";
/*     */       } 
/*  50 */     } else if (this.b.isInWater()) {
/*  51 */       this.h = "water";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackDamage(DamageSource damagesource, float f, float f1) {
/*  57 */     g();
/*  58 */     a();
/*  59 */     CombatEntry combatentry = new CombatEntry(damagesource, this.b.ticksLived, f, f1, this.h, this.b.fallDistance);
/*     */     
/*  61 */     this.a.add(combatentry);
/*  62 */     this.c = this.b.ticksLived;
/*  63 */     this.g = true;
/*  64 */     if (combatentry.f() && !this.f && this.b.isAlive()) {
/*  65 */       this.f = true;
/*  66 */       this.d = this.b.ticksLived;
/*  67 */       this.e = this.d;
/*  68 */       this.b.enterCombat();
/*     */     } 
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getDeathMessage() {
/*     */     Object object;
/*  74 */     if (this.a.isEmpty()) {
/*  75 */       return new ChatMessage("death.attack.generic", new Object[] { this.b.getScoreboardDisplayName() });
/*     */     }
/*  77 */     CombatEntry combatentry = j();
/*  78 */     CombatEntry combatentry1 = this.a.get(this.a.size() - 1);
/*  79 */     IChatBaseComponent ichatbasecomponent = combatentry1.h();
/*  80 */     Entity entity = combatentry1.a().getEntity();
/*     */ 
/*     */     
/*  83 */     if (combatentry != null && combatentry1.a() == DamageSource.FALL) {
/*  84 */       IChatBaseComponent ichatbasecomponent1 = combatentry.h();
/*     */       
/*  86 */       if (combatentry.a() != DamageSource.FALL && combatentry.a() != DamageSource.OUT_OF_WORLD) {
/*  87 */         if (ichatbasecomponent1 != null && (ichatbasecomponent == null || !ichatbasecomponent1.equals(ichatbasecomponent))) {
/*  88 */           Entity entity1 = combatentry.a().getEntity();
/*  89 */           ItemStack itemstack = (entity1 instanceof EntityLiving) ? ((EntityLiving)entity1).getItemInMainHand() : ItemStack.b;
/*     */           
/*  91 */           if (!itemstack.isEmpty() && itemstack.hasName()) {
/*  92 */             object = new ChatMessage("death.fell.assist.item", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent1, itemstack.C() });
/*     */           } else {
/*  94 */             object = new ChatMessage("death.fell.assist", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent1 });
/*     */           } 
/*  96 */         } else if (ichatbasecomponent != null) {
/*  97 */           ItemStack itemstack1 = (entity instanceof EntityLiving) ? ((EntityLiving)entity).getItemInMainHand() : ItemStack.b;
/*     */           
/*  99 */           if (!itemstack1.isEmpty() && itemstack1.hasName()) {
/* 100 */             object = new ChatMessage("death.fell.finish.item", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent, itemstack1.C() });
/*     */           } else {
/* 102 */             object = new ChatMessage("death.fell.finish", new Object[] { this.b.getScoreboardDisplayName(), ichatbasecomponent });
/*     */           } 
/*     */         } else {
/* 105 */           object = new ChatMessage("death.fell.killer", new Object[] { this.b.getScoreboardDisplayName() });
/*     */         } 
/*     */       } else {
/* 108 */         object = new ChatMessage("death.fell.accident." + a(combatentry), new Object[] { this.b.getScoreboardDisplayName() });
/*     */       } 
/*     */     } else {
/* 111 */       object = combatentry1.a().getLocalizedDeathMessage(this.b);
/*     */     } 
/*     */     
/* 114 */     return (IChatBaseComponent)object;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving c() {
/* 120 */     EntityLiving entityliving = null;
/* 121 */     EntityHuman entityhuman = null;
/* 122 */     float f = 0.0F;
/* 123 */     float f1 = 0.0F;
/* 124 */     Iterator<CombatEntry> iterator = this.a.iterator();
/*     */     
/* 126 */     while (iterator.hasNext()) {
/* 127 */       CombatEntry combatentry = iterator.next();
/*     */       
/* 129 */       if (combatentry.a().getEntity() instanceof EntityHuman && (entityhuman == null || combatentry.c() > f1)) {
/* 130 */         f1 = combatentry.c();
/* 131 */         entityhuman = (EntityHuman)combatentry.a().getEntity();
/*     */       } 
/*     */       
/* 134 */       if (combatentry.a().getEntity() instanceof EntityLiving && (entityliving == null || combatentry.c() > f)) {
/* 135 */         f = combatentry.c();
/* 136 */         entityliving = (EntityLiving)combatentry.a().getEntity();
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     if (entityhuman != null && f1 >= f / 3.0F) {
/* 141 */       return entityhuman;
/*     */     }
/* 143 */     return entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private CombatEntry j() {
/* 149 */     CombatEntry combatentry = null;
/* 150 */     CombatEntry combatentry1 = null;
/* 151 */     float f = 0.0F;
/* 152 */     float f1 = 0.0F;
/*     */     
/* 154 */     for (int i = 0; i < this.a.size(); i++) {
/* 155 */       CombatEntry combatentry2 = this.a.get(i);
/* 156 */       CombatEntry combatentry3 = (i > 0) ? this.a.get(i - 1) : null;
/*     */       
/* 158 */       if ((combatentry2.a() == DamageSource.FALL || combatentry2.a() == DamageSource.OUT_OF_WORLD) && combatentry2.j() > 0.0F && (combatentry == null || combatentry2.j() > f1)) {
/* 159 */         if (i > 0) {
/* 160 */           combatentry = combatentry3;
/*     */         } else {
/* 162 */           combatentry = combatentry2;
/*     */         } 
/*     */         
/* 165 */         f1 = combatentry2.j();
/*     */       } 
/*     */       
/* 168 */       if (combatentry2.g() != null && (combatentry1 == null || combatentry2.c() > f)) {
/* 169 */         combatentry1 = combatentry2;
/* 170 */         f = combatentry2.c();
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     if (f1 > 5.0F && combatentry != null)
/* 175 */       return combatentry; 
/* 176 */     if (f > 5.0F && combatentry1 != null) {
/* 177 */       return combatentry1;
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String a(CombatEntry combatentry) {
/* 184 */     return (combatentry.g() == null) ? "generic" : combatentry.g();
/*     */   }
/*     */   
/*     */   public int f() {
/* 188 */     return this.f ? (this.b.ticksLived - this.d) : (this.e - this.d);
/*     */   }
/*     */   
/*     */   private void k() {
/* 192 */     this.h = null;
/*     */   }
/*     */   public final void reset() {
/* 195 */     g();
/*     */   } public void g() {
/* 197 */     int i = this.f ? 300 : 100;
/*     */     
/* 199 */     if (this.g && (!this.b.isAlive() || this.b.ticksLived - this.c > i)) {
/* 200 */       boolean flag = this.f;
/*     */       
/* 202 */       this.g = false;
/* 203 */       this.f = false;
/* 204 */       this.e = this.b.ticksLived;
/* 205 */       if (flag) {
/* 206 */         this.b.exitCombat();
/*     */       }
/*     */       
/* 209 */       this.a.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLiving h() {
/* 215 */     return this.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CombatTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */