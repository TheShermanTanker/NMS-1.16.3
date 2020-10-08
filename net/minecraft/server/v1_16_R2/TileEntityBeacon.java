/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.block.BeaconEffectEvent;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityBeacon
/*     */   extends TileEntity
/*     */   implements ITileInventory, ITickable
/*     */ {
/*  25 */   public static final MobEffectList[][] a = new MobEffectList[][] { { MobEffects.FASTER_MOVEMENT, MobEffects.FASTER_DIG }, { MobEffects.RESISTANCE, MobEffects.JUMP }, { MobEffects.INCREASE_DAMAGE }, { MobEffects.REGENERATION } };
/*  26 */   private static final Set<MobEffectList> b = (Set<MobEffectList>)Arrays.<MobEffectList[]>stream(a).flatMap(Arrays::stream).collect(Collectors.toSet());
/*  27 */   private List<BeaconColorTracker> c = Lists.newArrayList();
/*  28 */   private List<BeaconColorTracker> g = Lists.newArrayList();
/*     */   public int levels;
/*  30 */   private int i = -1;
/*     */   @Nullable
/*     */   public MobEffectList primaryEffect;
/*     */   @Nullable
/*     */   public MobEffectList secondaryEffect;
/*     */   @Nullable
/*     */   public IChatBaseComponent customName;
/*     */   public ChestLock chestLock;
/*     */   private final IContainerProperties containerProperties;
/*     */   
/*     */   public PotionEffect getPrimaryEffect() {
/*  41 */     return (this.primaryEffect != null) ? CraftPotionUtil.toBukkit(new MobEffect(this.primaryEffect, getLevel(), getAmplification(), true, true)) : null;
/*     */   }
/*     */   
/*     */   public PotionEffect getSecondaryEffect() {
/*  45 */     return hasSecondaryEffect() ? CraftPotionUtil.toBukkit(new MobEffect(this.secondaryEffect, getLevel(), getAmplification(), true, true)) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityBeacon() {
/*  50 */     super(TileEntityTypes.BEACON);
/*  51 */     this.chestLock = ChestLock.a;
/*  52 */     this.containerProperties = new IContainerProperties()
/*     */       {
/*     */         public int getProperty(int i) {
/*  55 */           switch (i) {
/*     */             case 0:
/*  57 */               return TileEntityBeacon.this.levels;
/*     */             case 1:
/*  59 */               return MobEffectList.getId(TileEntityBeacon.this.primaryEffect);
/*     */             case 2:
/*  61 */               return MobEffectList.getId(TileEntityBeacon.this.secondaryEffect);
/*     */           } 
/*  63 */           return 0;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setProperty(int i, int j) {
/*  69 */           switch (i) {
/*     */             case 0:
/*  71 */               TileEntityBeacon.this.levels = j;
/*     */               break;
/*     */             case 1:
/*  74 */               if (!TileEntityBeacon.this.world.isClientSide && !TileEntityBeacon.this.c.isEmpty()) {
/*  75 */                 TileEntityBeacon.this.a(SoundEffects.BLOCK_BEACON_POWER_SELECT);
/*     */               }
/*     */               
/*  78 */               TileEntityBeacon.this.primaryEffect = TileEntityBeacon.b(j);
/*     */               break;
/*     */             case 2:
/*  81 */               TileEntityBeacon.this.secondaryEffect = TileEntityBeacon.b(j);
/*     */               break;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public int a() {
/*  88 */           return 3;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void tick() {
/*     */     BlockPosition blockposition;
/*  95 */     int i = this.position.getX();
/*  96 */     int j = this.position.getY();
/*  97 */     int k = this.position.getZ();
/*     */ 
/*     */     
/* 100 */     if (this.i < j) {
/* 101 */       blockposition = this.position;
/* 102 */       this.g = Lists.newArrayList();
/* 103 */       this.i = blockposition.getY() - 1;
/*     */     } else {
/* 105 */       blockposition = new BlockPosition(i, this.i + 1, k);
/*     */     } 
/*     */     
/* 108 */     BeaconColorTracker tileentitybeacon_beaconcolortracker = this.g.isEmpty() ? null : this.g.get(this.g.size() - 1);
/* 109 */     int l = this.world.a(HeightMap.Type.WORLD_SURFACE, i, k);
/*     */     
/*     */     int i1;
/*     */     
/* 113 */     for (i1 = 0; i1 < 10 && blockposition.getY() <= l; i1++) {
/* 114 */       IBlockData iblockdata = this.world.getType(blockposition);
/* 115 */       Block block = iblockdata.getBlock();
/*     */       
/* 117 */       if (block instanceof IBeaconBeam) {
/* 118 */         float[] afloat = ((IBeaconBeam)block).a().getColor();
/*     */         
/* 120 */         if (this.g.size() <= 1) {
/* 121 */           tileentitybeacon_beaconcolortracker = new BeaconColorTracker(afloat);
/* 122 */           this.g.add(tileentitybeacon_beaconcolortracker);
/* 123 */         } else if (tileentitybeacon_beaconcolortracker != null) {
/* 124 */           if (Arrays.equals(afloat, tileentitybeacon_beaconcolortracker.a)) {
/* 125 */             tileentitybeacon_beaconcolortracker.a();
/*     */           } else {
/* 127 */             tileentitybeacon_beaconcolortracker = new BeaconColorTracker(new float[] { (BeaconColorTracker.access$200(tileentitybeacon_beaconcolortracker)[0] + afloat[0]) / 2.0F, (BeaconColorTracker.access$200(tileentitybeacon_beaconcolortracker)[1] + afloat[1]) / 2.0F, (BeaconColorTracker.access$200(tileentitybeacon_beaconcolortracker)[2] + afloat[2]) / 2.0F });
/* 128 */             this.g.add(tileentitybeacon_beaconcolortracker);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 132 */         if (tileentitybeacon_beaconcolortracker == null || (iblockdata.b(this.world, blockposition) >= 15 && block != Blocks.BEDROCK)) {
/* 133 */           this.g.clear();
/* 134 */           this.i = l;
/*     */           
/*     */           break;
/*     */         } 
/* 138 */         tileentitybeacon_beaconcolortracker.a();
/*     */       } 
/*     */       
/* 141 */       blockposition = blockposition.up();
/* 142 */       this.i++;
/*     */     } 
/*     */     
/* 145 */     i1 = this.levels;
/* 146 */     if (this.world.getTime() % 80L == 0L) {
/* 147 */       if (!this.c.isEmpty()) {
/* 148 */         a(i, j, k);
/*     */       }
/*     */       
/* 151 */       if (this.levels > 0 && !this.c.isEmpty()) {
/* 152 */         applyEffects();
/* 153 */         a(SoundEffects.BLOCK_BEACON_AMBIENT);
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     if (this.i >= l) {
/* 158 */       this.i = -1;
/* 159 */       boolean flag = (i1 > 0);
/*     */       
/* 161 */       this.c = this.g;
/* 162 */       if (!this.world.isClientSide) {
/* 163 */         boolean flag1 = (this.levels > 0);
/*     */         
/* 165 */         if (!flag && flag1) {
/* 166 */           a(SoundEffects.BLOCK_BEACON_ACTIVATE);
/* 167 */           Iterator<EntityPlayer> iterator = this.world.<EntityPlayer>a(EntityPlayer.class, (new AxisAlignedBB(i, j, k, i, (j - 4), k)).grow(10.0D, 5.0D, 10.0D)).iterator();
/*     */           
/* 169 */           while (iterator.hasNext()) {
/* 170 */             EntityPlayer entityplayer = iterator.next();
/*     */             
/* 172 */             CriterionTriggers.l.a(entityplayer, this);
/*     */           } 
/* 174 */         } else if (flag && !flag1) {
/* 175 */           a(SoundEffects.BLOCK_BEACON_DEACTIVATE);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int i, int j, int k) {
/* 183 */     this.levels = 0;
/*     */     
/* 185 */     for (int l = 1; l <= 4; this.levels = l++) {
/* 186 */       int i1 = j - l;
/*     */       
/* 188 */       if (i1 < 0) {
/*     */         break;
/*     */       }
/*     */       
/* 192 */       boolean flag = true;
/*     */       
/* 194 */       for (int j1 = i - l; j1 <= i + l && flag; j1++) {
/* 195 */         for (int k1 = k - l; k1 <= k + l; k1++) {
/* 196 */           if (!this.world.getType(new BlockPosition(j1, i1, k1)).a(TagsBlock.BEACON_BASE_BLOCKS)) {
/* 197 */             flag = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 203 */       if (!flag) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void al_() {
/* 212 */     a(SoundEffects.BLOCK_BEACON_DEACTIVATE);
/* 213 */     super.al_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private byte getAmplification() {
/* 219 */     byte b0 = 0;
/*     */     
/* 221 */     if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect) {
/* 222 */       b0 = 1;
/*     */     }
/*     */     
/* 225 */     return b0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getLevel() {
/* 231 */     int i = (9 + this.levels * 2) * 20;
/* 232 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getHumansInRange() {
/* 238 */     double d0 = (this.levels * 10 + 10);
/*     */     
/* 240 */     AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.position)).g(d0).b(0.0D, this.world.getBuildHeight(), 0.0D);
/* 241 */     List<EntityHuman> list = this.world.a(EntityHuman.class, axisalignedbb);
/*     */     
/* 243 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyEffect(List list, MobEffectList effects, int i, int b0) {
/* 249 */     applyEffect(list, effects, i, b0, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyEffect(List list, MobEffectList effects, int i, int b0, boolean isPrimary) {
/* 255 */     Iterator<EntityHuman> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     Block block = this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ());
/* 261 */     PotionEffect effect = CraftPotionUtil.toBukkit(new MobEffect(effects, i, b0, true, true));
/*     */ 
/*     */     
/* 264 */     while (iterator.hasNext()) {
/* 265 */       EntityHuman entityhuman = iterator.next();
/*     */ 
/*     */       
/* 268 */       BeaconEffectEvent event = new BeaconEffectEvent(block, effect, (Player)entityhuman.getBukkitEntity(), isPrimary);
/* 269 */       if (((BeaconEffectEvent)CraftEventFactory.callEvent((Event)event)).isCancelled())
/* 270 */         continue;  PotionEffect eventEffect = event.getEffect();
/* 271 */       entityhuman.addEffect(new MobEffect(MobEffectList.fromId(eventEffect.getType().getId()), eventEffect.getDuration(), eventEffect.getAmplifier(), true, true), EntityPotionEffectEvent.Cause.BEACON);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasSecondaryEffect() {
/* 279 */     if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect != null) {
/* 280 */       return true;
/*     */     }
/*     */     
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyEffects() {
/* 288 */     if (!this.world.isClientSide && this.primaryEffect != null) {
/* 289 */       double d0 = (this.levels * 10 + 10);
/* 290 */       byte b0 = getAmplification();
/*     */       
/* 292 */       int i = getLevel();
/* 293 */       List list = getHumansInRange();
/*     */       
/* 295 */       applyEffect(list, this.primaryEffect, i, b0, true);
/*     */       
/* 297 */       if (hasSecondaryEffect()) {
/* 298 */         applyEffect(list, this.secondaryEffect, i, 0, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(SoundEffect soundeffect) {
/* 306 */     this.world.playSound((EntityHuman)null, this.position, soundeffect, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public int h() {
/* 310 */     return this.levels;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 316 */     return new PacketPlayOutTileEntityData(this.position, 3, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 321 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static MobEffectList b(int i) {
/* 326 */     MobEffectList mobeffectlist = MobEffectList.fromId(i);
/*     */     
/* 328 */     return b.contains(mobeffectlist) ? mobeffectlist : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 333 */     super.load(iblockdata, nbttagcompound);
/*     */     
/* 335 */     this.primaryEffect = MobEffectList.fromId(nbttagcompound.getInt("Primary"));
/* 336 */     this.secondaryEffect = MobEffectList.fromId(nbttagcompound.getInt("Secondary"));
/* 337 */     this.levels = nbttagcompound.getInt("Levels");
/*     */     
/* 339 */     if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/* 340 */       this.customName = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("CustomName"));
/*     */     }
/*     */     
/* 343 */     this.chestLock = ChestLock.b(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 348 */     super.save(nbttagcompound);
/* 349 */     nbttagcompound.setInt("Primary", MobEffectList.getId(this.primaryEffect));
/* 350 */     nbttagcompound.setInt("Secondary", MobEffectList.getId(this.secondaryEffect));
/* 351 */     nbttagcompound.setInt("Levels", this.levels);
/* 352 */     if (this.customName != null) {
/* 353 */       nbttagcompound.setString("CustomName", IChatBaseComponent.ChatSerializer.a(this.customName));
/*     */     }
/*     */     
/* 356 */     this.chestLock.a(nbttagcompound);
/* 357 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void setCustomName(@Nullable IChatBaseComponent ichatbasecomponent) {
/* 361 */     this.customName = ichatbasecomponent;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 367 */     return TileEntityContainer.a(entityhuman, this.chestLock, getScoreboardDisplayName()) ? new ContainerBeacon(i, playerinventory, this.containerProperties, ContainerAccess.at(this.world, getPosition())) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 372 */     return (this.customName != null) ? this.customName : new ChatMessage("container.beacon");
/*     */   }
/*     */   
/*     */   public static class BeaconColorTracker
/*     */   {
/*     */     private final float[] a;
/*     */     private int b;
/*     */     
/*     */     public BeaconColorTracker(float[] afloat) {
/* 381 */       this.a = afloat;
/* 382 */       this.b = 1;
/*     */     }
/*     */     
/*     */     protected void a() {
/* 386 */       this.b++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */