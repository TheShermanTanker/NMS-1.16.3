/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class EntityIllagerWizard extends EntityIllagerAbstract {
/*   8 */   private static final DataWatcherObject<Byte> bo = DataWatcher.a((Class)EntityIllagerWizard.class, DataWatcherRegistry.a);
/*     */   protected int b;
/*     */   private Spell bp;
/*     */   
/*     */   protected EntityIllagerWizard(EntityTypes<? extends EntityIllagerWizard> entitytypes, World world) {
/*  13 */     super((EntityTypes)entitytypes, world);
/*  14 */     this.bp = Spell.NONE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  19 */     super.initDatawatcher();
/*  20 */     this.datawatcher.register(bo, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  25 */     super.loadData(nbttagcompound);
/*  26 */     this.b = nbttagcompound.getInt("SpellTicks");
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  31 */     super.saveData(nbttagcompound);
/*  32 */     nbttagcompound.setInt("SpellTicks", this.b);
/*     */   }
/*     */   
/*     */   public boolean eW() {
/*  36 */     return this.world.isClientSide ? ((((Byte)this.datawatcher.<Byte>get(bo)).byteValue() > 0)) : ((this.b > 0));
/*     */   }
/*     */   
/*     */   public void setSpell(Spell entityillagerwizard_spell) {
/*  40 */     this.bp = entityillagerwizard_spell;
/*  41 */     this.datawatcher.set(bo, Byte.valueOf((byte)entityillagerwizard_spell.g));
/*     */   }
/*     */   
/*     */   public Spell getSpell() {
/*  45 */     return !this.world.isClientSide ? this.bp : Spell.a(((Byte)this.datawatcher.<Byte>get(bo)).byteValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  50 */     super.mobTick();
/*  51 */     if (this.b > 0) {
/*  52 */       this.b--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  59 */     super.tick();
/*  60 */     if (this.world.isClientSide && eW()) {
/*  61 */       Spell entityillagerwizard_spell = getSpell();
/*  62 */       double d0 = entityillagerwizard_spell.h[0];
/*  63 */       double d1 = entityillagerwizard_spell.h[1];
/*  64 */       double d2 = entityillagerwizard_spell.h[2];
/*  65 */       float f = this.aA * 0.017453292F + MathHelper.cos(this.ticksLived * 0.6662F) * 0.25F;
/*  66 */       float f1 = MathHelper.cos(f);
/*  67 */       float f2 = MathHelper.sin(f);
/*     */       
/*  69 */       this.world.addParticle(Particles.ENTITY_EFFECT, locX() + f1 * 0.6D, locY() + 1.8D, locZ() + f2 * 0.6D, d0, d1, d2);
/*  70 */       this.world.addParticle(Particles.ENTITY_EFFECT, locX() - f1 * 0.6D, locY() + 1.8D, locZ() - f2 * 0.6D, d0, d1, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int eY() {
/*  76 */     return this.b;
/*     */   }
/*     */   
/*     */   protected abstract SoundEffect getSoundCastSpell();
/*     */   
/*     */   public enum Spell
/*     */   {
/*  83 */     NONE(0, 0.0D, 0.0D, 0.0D), SUMMON_VEX(1, 0.7D, 0.7D, 0.8D), FANGS(2, 0.4D, 0.3D, 0.35D), WOLOLO(3, 0.7D, 0.5D, 0.2D), DISAPPEAR(4, 0.3D, 0.3D, 0.8D), BLINDNESS(5, 0.1D, 0.1D, 0.2D);
/*     */     
/*     */     private final double[] h;
/*     */     private final int g;
/*     */     
/*     */     Spell(int i, double d0, double d1, double d2) {
/*  89 */       this.g = i;
/*  90 */       this.h = new double[] { d0, d1, d2 };
/*     */     }
/*     */     
/*     */     public static Spell a(int i) {
/*  94 */       Spell[] aentityillagerwizard_spell = values();
/*  95 */       int j = aentityillagerwizard_spell.length;
/*     */       
/*  97 */       for (int k = 0; k < j; k++) {
/*  98 */         Spell entityillagerwizard_spell = aentityillagerwizard_spell[k];
/*     */         
/* 100 */         if (i == entityillagerwizard_spell.g) {
/* 101 */           return entityillagerwizard_spell;
/*     */         }
/*     */       } 
/*     */       
/* 105 */       return NONE;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract class PathfinderGoalCastSpell
/*     */     extends PathfinderGoal
/*     */   {
/*     */     protected int b;
/*     */     
/*     */     protected int c;
/*     */     
/*     */     public boolean a() {
/* 118 */       EntityLiving entityliving = EntityIllagerWizard.this.getGoalTarget();
/*     */       
/* 120 */       return (entityliving != null && entityliving.isAlive()) ? (EntityIllagerWizard.this.eW() ? false : ((EntityIllagerWizard.this.ticksLived >= this.c))) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 125 */       EntityLiving entityliving = EntityIllagerWizard.this.getGoalTarget();
/*     */       
/* 127 */       return (entityliving != null && entityliving.isAlive() && this.b > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 132 */       this.b = m();
/* 133 */       EntityIllagerWizard.this.b = g();
/* 134 */       this.c = EntityIllagerWizard.this.ticksLived + h();
/* 135 */       SoundEffect soundeffect = k();
/*     */       
/* 137 */       if (soundeffect != null) {
/* 138 */         EntityIllagerWizard.this.playSound(soundeffect, 1.0F, 1.0F);
/*     */       }
/*     */       
/* 141 */       EntityIllagerWizard.this.setSpell(getCastSpell());
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 146 */       this.b--;
/* 147 */       if (this.b == 0) {
/*     */         
/* 149 */         if (!CraftEventFactory.handleEntitySpellCastEvent(EntityIllagerWizard.this, getCastSpell())) {
/*     */           return;
/*     */         }
/*     */         
/* 153 */         j();
/* 154 */         EntityIllagerWizard.this.playSound(EntityIllagerWizard.this.getSoundCastSpell(), 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected abstract void j();
/*     */     
/*     */     protected int m() {
/* 162 */       return 20;
/*     */     }
/*     */     
/*     */     protected abstract int g();
/*     */     
/*     */     protected abstract int h();
/*     */     
/*     */     @Nullable
/*     */     protected abstract SoundEffect k();
/*     */     
/*     */     protected abstract EntityIllagerWizard.Spell getCastSpell();
/*     */   }
/*     */   
/*     */   public class b
/*     */     extends PathfinderGoal {
/*     */     public b() {
/* 178 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 183 */       return (EntityIllagerWizard.this.eY() > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 188 */       super.c();
/* 189 */       EntityIllagerWizard.this.navigation.o();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 194 */       super.d();
/* 195 */       EntityIllagerWizard.this.setSpell(EntityIllagerWizard.Spell.NONE);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 200 */       if (EntityIllagerWizard.this.getGoalTarget() != null)
/* 201 */         EntityIllagerWizard.this.getControllerLook().a(EntityIllagerWizard.this.getGoalTarget(), EntityIllagerWizard.this.eo(), EntityIllagerWizard.this.O()); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityIllagerWizard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */