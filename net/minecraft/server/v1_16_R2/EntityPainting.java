/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityPainting
/*     */   extends EntityHanging {
/*     */   public Paintings art;
/*     */   
/*     */   public EntityPainting(EntityTypes<? extends EntityPainting> entitytypes, World world) {
/*  13 */     super((EntityTypes)entitytypes, world);
/*     */     
/*  15 */     List<Paintings> list = Lists.newArrayList((Object[])new Paintings[] { Paintings.a });
/*  16 */     this.art = list.get(this.random.nextInt(list.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPainting(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  21 */     super((EntityTypes)EntityTypes.PAINTING, world, blockposition);
/*  22 */     List<Paintings> list = Lists.newArrayList();
/*  23 */     int i = 0;
/*  24 */     Iterator<Paintings> iterator = IRegistry.MOTIVE.iterator();
/*     */ 
/*     */ 
/*     */     
/*  28 */     while (iterator.hasNext()) {
/*  29 */       Paintings paintings = iterator.next();
/*  30 */       this.art = paintings;
/*  31 */       setDirection(enumdirection);
/*  32 */       if (survives()) {
/*  33 */         list.add(paintings);
/*  34 */         int j = paintings.getWidth() * paintings.getHeight();
/*     */         
/*  36 */         if (j > i) {
/*  37 */           i = j;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  42 */     if (!list.isEmpty()) {
/*  43 */       iterator = list.iterator();
/*     */       
/*  45 */       while (iterator.hasNext()) {
/*  46 */         Paintings paintings = iterator.next();
/*  47 */         if (paintings.getWidth() * paintings.getHeight() < i) {
/*  48 */           iterator.remove();
/*     */         }
/*     */       } 
/*     */       
/*  52 */       this.art = list.get(this.random.nextInt(list.size()));
/*     */     } 
/*     */     
/*  55 */     setDirection(enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  60 */     nbttagcompound.setString("Motive", IRegistry.MOTIVE.getKey(this.art).toString());
/*  61 */     nbttagcompound.setByte("Facing", (byte)this.direction.get2DRotationValue());
/*  62 */     super.saveData(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  67 */     this.art = IRegistry.MOTIVE.get(MinecraftKey.a(nbttagcompound.getString("Motive")));
/*  68 */     this.direction = EnumDirection.fromType2(nbttagcompound.getByte("Facing"));
/*  69 */     super.loadData(nbttagcompound);
/*  70 */     setDirection(this.direction);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHangingWidth() {
/*  75 */     return (this.art == null) ? 1 : this.art.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHangingHeight() {
/*  80 */     return (this.art == null) ? 1 : this.art.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/*  85 */     if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*  86 */       playSound(SoundEffects.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
/*  87 */       if (entity instanceof EntityHuman) {
/*  88 */         EntityHuman entityhuman = (EntityHuman)entity;
/*     */         
/*  90 */         if (entityhuman.abilities.canInstantlyBuild) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/*  95 */       a(Items.PAINTING);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playPlaceSound() {
/* 101 */     playSound(SoundEffects.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
/* 106 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 111 */     return new PacketPlayOutSpawnEntityPainting(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */