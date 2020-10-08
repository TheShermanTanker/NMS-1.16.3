/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.BlockStateListPopulator;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.world.PortalCreateEvent;
/*     */ 
/*     */ public class PortalTravelAgent {
/*     */   public PortalTravelAgent(WorldServer worldserver) {
/*  12 */     this.world = worldserver;
/*     */   }
/*     */   private final WorldServer world;
/*     */   
/*     */   public Optional<BlockUtil.Rectangle> findPortal(BlockPosition blockposition, boolean flag) {
/*  17 */     return findPortal(blockposition, flag ? this.world.paperConfig.portalCreateRadius : this.world.paperConfig.portalSearchRadius);
/*     */   }
/*     */   
/*     */   public Optional<BlockUtil.Rectangle> findPortal(BlockPosition blockposition, int i) {
/*  21 */     VillagePlace villageplace = this.world.y();
/*     */ 
/*     */ 
/*     */     
/*  25 */     villageplace.a(this.world, blockposition, i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  34 */     Optional<VillagePlaceRecord> optional = villageplace.b(villageplacetype -> (villageplacetype == VillagePlaceType.v), blockposition, i, VillagePlace.Occupancy.ANY).sorted(Comparator.<VillagePlaceRecord>comparingDouble(villageplacerecord -> villageplacerecord.f().j(blockposition)).thenComparingInt(villageplacerecord -> villageplacerecord.f().getY())).filter(villageplacerecord -> this.world.getType(villageplacerecord.f()).b(BlockProperties.E)).findFirst();
/*     */     
/*  36 */     return optional.map(villageplacerecord -> {
/*     */           BlockPosition blockposition1 = villageplacerecord.f();
/*     */           this.world.getChunkProvider().addTicket(TicketType.PORTAL, new ChunkCoordIntPair(blockposition1), 3, blockposition1);
/*     */           IBlockData iblockdata = this.world.getType(blockposition1);
/*     */           return BlockUtil.a(blockposition1, (EnumDirection.EnumAxis)iblockdata.get(BlockProperties.E), 21, EnumDirection.EnumAxis.Y, 21, ());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<BlockUtil.Rectangle> createPortal(BlockPosition blockposition, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  50 */     return createPortal(blockposition, enumdirection_enumaxis, null, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<BlockUtil.Rectangle> createPortal(BlockPosition blockposition, EnumDirection.EnumAxis enumdirection_enumaxis, Entity entity, int createRadius) {
/*  55 */     EnumDirection enumdirection = EnumDirection.a(EnumDirection.EnumAxisDirection.POSITIVE, enumdirection_enumaxis);
/*  56 */     double d0 = -1.0D;
/*  57 */     BlockPosition blockposition1 = null;
/*  58 */     double d1 = -1.0D;
/*  59 */     BlockPosition blockposition2 = null;
/*  60 */     WorldBorder worldborder = this.world.getWorldBorder();
/*  61 */     int i = this.world.getHeight() - 1;
/*  62 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i();
/*  63 */     Iterator<BlockPosition.MutableBlockPosition> iterator = BlockPosition.a(blockposition, createRadius, EnumDirection.EAST, EnumDirection.SOUTH).iterator();
/*     */ 
/*     */ 
/*     */     
/*  67 */     while (iterator.hasNext()) {
/*  68 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = iterator.next();
/*     */       
/*  70 */       int j = Math.min(i, this.world.a(HeightMap.Type.MOTION_BLOCKING, blockposition_mutableblockposition1.getX(), blockposition_mutableblockposition1.getZ()));
/*  71 */       boolean flag = true;
/*     */       
/*  73 */       if (worldborder.a(blockposition_mutableblockposition1) && worldborder.a(blockposition_mutableblockposition1.c(enumdirection, 1))) {
/*  74 */         blockposition_mutableblockposition1.c(enumdirection.opposite(), 1);
/*     */         
/*  76 */         for (int k = j; k >= 0; k--) {
/*  77 */           blockposition_mutableblockposition1.p(k);
/*  78 */           if (this.world.isEmpty(blockposition_mutableblockposition1)) {
/*     */             int l;
/*     */             
/*  81 */             for (l = k; k > 0 && this.world.isEmpty(blockposition_mutableblockposition1.c(EnumDirection.DOWN)); k--);
/*     */ 
/*     */ 
/*     */             
/*  85 */             if (k + 4 <= i) {
/*  86 */               int i1 = l - k;
/*     */               
/*  88 */               if (i1 <= 0 || i1 >= 3) {
/*  89 */                 blockposition_mutableblockposition1.p(k);
/*  90 */                 if (a(blockposition_mutableblockposition1, blockposition_mutableblockposition, enumdirection, 0)) {
/*  91 */                   double d2 = blockposition.j(blockposition_mutableblockposition1);
/*     */                   
/*  93 */                   if (a(blockposition_mutableblockposition1, blockposition_mutableblockposition, enumdirection, -1) && a(blockposition_mutableblockposition1, blockposition_mutableblockposition, enumdirection, 1) && (d0 == -1.0D || d0 > d2)) {
/*  94 */                     d0 = d2;
/*  95 */                     blockposition1 = blockposition_mutableblockposition1.immutableCopy();
/*     */                   } 
/*     */                   
/*  98 */                   if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
/*  99 */                     d1 = d2;
/* 100 */                     blockposition2 = blockposition_mutableblockposition1.immutableCopy();
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     if (d0 == -1.0D && d1 != -1.0D) {
/* 111 */       blockposition1 = blockposition2;
/* 112 */       d0 = d1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 117 */     BlockStateListPopulator blockList = new BlockStateListPopulator(this.world);
/* 118 */     if (d0 == -1.0D) {
/* 119 */       blockposition1 = (new BlockPosition(blockposition.getX(), MathHelper.clamp(blockposition.getY(), 70, this.world.getHeight() - 10), blockposition.getZ())).immutableCopy();
/* 120 */       EnumDirection enumdirection1 = enumdirection.g();
/*     */       
/* 122 */       if (!worldborder.a(blockposition1)) {
/* 123 */         MinecraftServer.LOGGER.error("Unable to create a portal, likely target out of worldborder");
/* 124 */         return Optional.empty();
/*     */       } 
/*     */       
/* 127 */       for (int j = -1; j < 2; j++) {
/* 128 */         for (int k = 0; k < 2; k++) {
/* 129 */           for (int k1 = -1; k1 < 3; k1++) {
/* 130 */             IBlockData iblockdata = (k1 < 0) ? Blocks.OBSIDIAN.getBlockData() : Blocks.AIR.getBlockData();
/*     */             
/* 132 */             blockposition_mutableblockposition.a(blockposition1, k * enumdirection.getAdjacentX() + j * enumdirection1.getAdjacentX(), k1, k * enumdirection.getAdjacentZ() + j * enumdirection1.getAdjacentZ());
/* 133 */             blockList.setTypeAndData(blockposition_mutableblockposition, iblockdata, 3);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     for (int l1 = -1; l1 < 3; l1++) {
/* 140 */       for (int j = -1; j < 4; j++) {
/* 141 */         if (l1 == -1 || l1 == 2 || j == -1 || j == 3) {
/* 142 */           blockposition_mutableblockposition.a(blockposition1, l1 * enumdirection.getAdjacentX(), j, l1 * enumdirection.getAdjacentZ());
/* 143 */           blockList.setTypeAndData(blockposition_mutableblockposition, Blocks.OBSIDIAN.getBlockData(), 3);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     IBlockData iblockdata1 = Blocks.NETHER_PORTAL.getBlockData().set(BlockPortal.AXIS, enumdirection_enumaxis);
/*     */     
/* 150 */     for (int j1 = 0; j1 < 2; j1++) {
/* 151 */       for (int j = 0; j < 3; j++) {
/* 152 */         blockposition_mutableblockposition.a(blockposition1, j1 * enumdirection.getAdjacentX(), j, j1 * enumdirection.getAdjacentZ());
/* 153 */         blockList.setTypeAndData(blockposition_mutableblockposition, iblockdata1, 18);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 158 */     CraftWorld craftWorld = this.world.getWorld();
/* 159 */     PortalCreateEvent event = new PortalCreateEvent(blockList.getList(), (World)craftWorld, (entity == null) ? null : (Entity)entity.getBukkitEntity(), PortalCreateEvent.CreateReason.NETHER_PAIR);
/*     */     
/* 161 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/* 162 */     if (!event.isCancelled())
/* 163 */     { blockList.updateList(); }
/* 164 */     else { return Optional.empty(); }
/*     */     
/* 166 */     return Optional.of(new BlockUtil.Rectangle(blockposition1.immutableCopy(), 2, 3));
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition blockposition, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, EnumDirection enumdirection, int i) {
/* 170 */     EnumDirection enumdirection1 = enumdirection.g();
/*     */     
/* 172 */     for (int j = -1; j < 3; j++) {
/* 173 */       for (int k = -1; k < 4; k++) {
/* 174 */         blockposition_mutableblockposition.a(blockposition, enumdirection.getAdjacentX() * j + enumdirection1.getAdjacentX() * i, k, enumdirection.getAdjacentZ() * j + enumdirection1.getAdjacentZ() * i);
/* 175 */         if (k < 0 && !this.world.getType(blockposition_mutableblockposition).getMaterial().isBuildable()) {
/* 176 */           return false;
/*     */         }
/*     */         
/* 179 */         if (k >= 0 && !this.world.isEmpty(blockposition_mutableblockposition)) {
/* 180 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PortalTravelAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */