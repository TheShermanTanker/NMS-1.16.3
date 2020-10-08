/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class PersistentRaid extends PersistentBase {
/*  12 */   public final Map<Integer, Raid> raids = Maps.newHashMap();
/*     */   private final WorldServer b;
/*     */   private int c;
/*     */   private int d;
/*     */   
/*     */   public PersistentRaid(WorldServer worldserver) {
/*  18 */     super(a(worldserver.getDimensionManager()));
/*  19 */     this.b = worldserver;
/*  20 */     this.c = 1;
/*  21 */     b();
/*     */   }
/*     */   
/*     */   public Raid a(int i) {
/*  25 */     return this.raids.get(Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public void a() {
/*  29 */     this.d++;
/*  30 */     Iterator<Raid> iterator = this.raids.values().iterator();
/*     */     
/*  32 */     while (iterator.hasNext()) {
/*  33 */       Raid raid = iterator.next();
/*     */       
/*  35 */       if (this.b.getGameRules().getBoolean(GameRules.DISABLE_RAIDS)) {
/*  36 */         raid.stop();
/*     */       }
/*     */       
/*  39 */       if (raid.isStopped()) {
/*  40 */         iterator.remove();
/*  41 */         b(); continue;
/*     */       } 
/*  43 */       raid.o();
/*     */     } 
/*     */ 
/*     */     
/*  47 */     if (this.d % 200 == 0) {
/*  48 */       b();
/*     */     }
/*     */     
/*  51 */     PacketDebug.a(this.b, this.raids.values());
/*     */   }
/*     */   
/*     */   public static boolean a(EntityRaider entityraider, Raid raid) {
/*  55 */     return (entityraider != null && raid != null && raid.getWorld() != null) ? ((entityraider.isAlive() && entityraider.isCanJoinRaid() && entityraider.dc() <= 2400 && entityraider.world.getDimensionManager() == raid.getWorld().getDimensionManager())) : false;
/*     */   }
/*     */   @Nullable
/*     */   public Raid a(EntityPlayer entityplayer) {
/*     */     BlockPosition blockposition2;
/*  60 */     if (entityplayer.isSpectator())
/*  61 */       return null; 
/*  62 */     if (this.b.getGameRules().getBoolean(GameRules.DISABLE_RAIDS)) {
/*  63 */       return null;
/*     */     }
/*  65 */     DimensionManager dimensionmanager = entityplayer.world.getDimensionManager();
/*     */     
/*  67 */     if (!dimensionmanager.hasRaids()) {
/*  68 */       return null;
/*     */     }
/*  70 */     BlockPosition blockposition = entityplayer.getChunkCoordinates();
/*  71 */     List<VillagePlaceRecord> list = this.b.y().c(VillagePlaceType.b, blockposition, 64, VillagePlace.Occupancy.IS_OCCUPIED).collect((Collector)Collectors.toList());
/*  72 */     int i = 0;
/*  73 */     Vec3D vec3d = Vec3D.ORIGIN;
/*     */     
/*  75 */     for (Iterator<VillagePlaceRecord> iterator = list.iterator(); iterator.hasNext(); i++) {
/*  76 */       VillagePlaceRecord villageplacerecord = iterator.next();
/*  77 */       BlockPosition blockposition1 = villageplacerecord.f();
/*     */       
/*  79 */       vec3d = vec3d.add(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  84 */     if (i > 0) {
/*  85 */       vec3d = vec3d.a(1.0D / i);
/*  86 */       blockposition2 = new BlockPosition(vec3d);
/*     */     } else {
/*  88 */       blockposition2 = blockposition;
/*     */     } 
/*     */     
/*  91 */     Raid raid = a(entityplayer.getWorldServer(), blockposition2);
/*  92 */     boolean flag = false;
/*     */     
/*  94 */     if (!raid.isStarted()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 101 */       flag = true;
/*     */     }
/* 103 */     else if (raid.isInProgress() && raid.getBadOmenLevel() < raid.getMaxBadOmenLevel()) {
/* 104 */       flag = true;
/*     */     } else {
/*     */       
/* 107 */       entityplayer.removeEffect(MobEffects.BAD_OMEN);
/* 108 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)43));
/*     */     } 
/*     */     
/* 111 */     if (flag) {
/*     */       
/* 113 */       if (!CraftEventFactory.callRaidTriggerEvent(raid, entityplayer)) {
/* 114 */         entityplayer.removeEffect(MobEffects.BAD_OMEN);
/* 115 */         return null;
/*     */       } 
/*     */       
/* 118 */       if (!this.raids.containsKey(Integer.valueOf(raid.getId()))) {
/* 119 */         this.raids.put(Integer.valueOf(raid.getId()), raid);
/*     */       }
/*     */       
/* 122 */       raid.a(entityplayer);
/* 123 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)43));
/* 124 */       if (!raid.c()) {
/* 125 */         entityplayer.a(StatisticList.RAID_TRIGGER);
/* 126 */         CriterionTriggers.I.a(entityplayer);
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     b();
/* 131 */     return raid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Raid a(WorldServer worldserver, BlockPosition blockposition) {
/* 137 */     Raid raid = worldserver.b_(blockposition);
/*     */     
/* 139 */     return (raid != null) ? raid : new Raid(e(), worldserver, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 144 */     this.c = nbttagcompound.getInt("NextAvailableID");
/* 145 */     this.d = nbttagcompound.getInt("Tick");
/* 146 */     NBTTagList nbttaglist = nbttagcompound.getList("Raids", 10);
/*     */     
/* 148 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 149 */       NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/* 150 */       Raid raid = new Raid(this.b, nbttagcompound1);
/*     */       
/* 152 */       this.raids.put(Integer.valueOf(raid.getId()), raid);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 159 */     nbttagcompound.setInt("NextAvailableID", this.c);
/* 160 */     nbttagcompound.setInt("Tick", this.d);
/* 161 */     NBTTagList nbttaglist = new NBTTagList();
/* 162 */     Iterator<Raid> iterator = this.raids.values().iterator();
/*     */     
/* 164 */     while (iterator.hasNext()) {
/* 165 */       Raid raid = iterator.next();
/* 166 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */       
/* 168 */       raid.a(nbttagcompound1);
/* 169 */       nbttaglist.add(nbttagcompound1);
/*     */     } 
/*     */     
/* 172 */     nbttagcompound.set("Raids", nbttaglist);
/* 173 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static String a(DimensionManager dimensionmanager) {
/* 177 */     return "raids" + dimensionmanager.getSuffix();
/*     */   }
/*     */   
/*     */   private int e() {
/* 181 */     return ++this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Raid getNearbyRaid(BlockPosition blockposition, int i) {
/* 186 */     Raid raid = null;
/* 187 */     double d0 = i;
/* 188 */     Iterator<Raid> iterator = this.raids.values().iterator();
/*     */     
/* 190 */     while (iterator.hasNext()) {
/* 191 */       Raid raid1 = iterator.next();
/* 192 */       double d1 = raid1.getCenter().j(blockposition);
/*     */       
/* 194 */       if (raid1.v() && d1 < d0) {
/* 195 */         raid = raid1;
/* 196 */         d0 = d1;
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     return raid;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentRaid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */