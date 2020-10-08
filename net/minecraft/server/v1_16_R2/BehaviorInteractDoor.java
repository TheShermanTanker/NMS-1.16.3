/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ 
/*     */ public class BehaviorInteractDoor extends Behavior<EntityLiving> {
/*     */   @Nullable
/*     */   private PathPoint b;
/*     */   
/*     */   public BehaviorInteractDoor() {
/*  18 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.PATH, MemoryStatus.VALUE_PRESENT, MemoryModuleType.DOORS_TO_CLOSE, MemoryStatus.REGISTERED));
/*     */   }
/*     */   private int c;
/*     */   
/*     */   protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
/*  23 */     PathEntity pathentity = entityliving.getBehaviorController().<PathEntity>getMemory(MemoryModuleType.PATH).get();
/*     */     
/*  25 */     if (!pathentity.b() && !pathentity.c()) {
/*  26 */       if (!Objects.equals(this.b, pathentity.h())) {
/*  27 */         this.c = 20;
/*  28 */         return true;
/*     */       } 
/*  30 */       if (this.c > 0) {
/*  31 */         this.c--;
/*     */       }
/*     */       
/*  34 */       return (this.c == 0);
/*     */     } 
/*     */     
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
/*  43 */     PathEntity pathentity = entityliving.getBehaviorController().<PathEntity>getMemory(MemoryModuleType.PATH).get();
/*     */     
/*  45 */     this.b = pathentity.h();
/*  46 */     PathPoint pathpoint = pathentity.i();
/*  47 */     PathPoint pathpoint1 = pathentity.h();
/*  48 */     BlockPosition blockposition = pathpoint.a();
/*  49 */     IBlockData iblockdata = worldserver.getType(blockposition);
/*     */     
/*  51 */     if (iblockdata.a(TagsBlock.WOODEN_DOORS)) {
/*  52 */       BlockDoor blockdoor = (BlockDoor)iblockdata.getBlock();
/*     */       
/*  54 */       if (!blockdoor.h(iblockdata)) {
/*     */         
/*  56 */         EntityInteractEvent event = new EntityInteractEvent((Entity)entityliving.getBukkitEntity(), (Block)CraftBlock.at(entityliving.world, blockposition));
/*  57 */         entityliving.world.getServer().getPluginManager().callEvent((Event)event);
/*  58 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  62 */         blockdoor.setDoor(worldserver, iblockdata, blockposition, true);
/*     */       } 
/*     */       
/*  65 */       c(worldserver, entityliving, blockposition);
/*     */     } 
/*     */     
/*  68 */     BlockPosition blockposition1 = pathpoint1.a();
/*  69 */     IBlockData iblockdata1 = worldserver.getType(blockposition1);
/*     */     
/*  71 */     if (iblockdata1.a(TagsBlock.WOODEN_DOORS)) {
/*  72 */       BlockDoor blockdoor1 = (BlockDoor)iblockdata1.getBlock();
/*     */       
/*  74 */       if (!blockdoor1.h(iblockdata1)) {
/*     */         
/*  76 */         EntityInteractEvent event = new EntityInteractEvent((Entity)entityliving.getBukkitEntity(), (Block)CraftBlock.at(entityliving.world, blockposition));
/*  77 */         entityliving.world.getServer().getPluginManager().callEvent((Event)event);
/*  78 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  82 */         blockdoor1.setDoor(worldserver, iblockdata1, blockposition1, true);
/*  83 */         c(worldserver, entityliving, blockposition1);
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     a(worldserver, entityliving, pathpoint, pathpoint1);
/*     */   }
/*     */   
/*     */   public static void a(WorldServer worldserver, EntityLiving entityliving, @Nullable PathPoint pathpoint, @Nullable PathPoint pathpoint1) {
/*  91 */     BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
/*     */     
/*  93 */     if (behaviorcontroller.hasMemory(MemoryModuleType.DOORS_TO_CLOSE)) {
/*  94 */       Iterator<GlobalPos> iterator = ((Set)behaviorcontroller.<Set>getMemory((MemoryModuleType)MemoryModuleType.DOORS_TO_CLOSE).get()).iterator();
/*     */       
/*  96 */       while (iterator.hasNext()) {
/*  97 */         GlobalPos globalpos = iterator.next();
/*  98 */         BlockPosition blockposition = globalpos.getBlockPosition();
/*     */         
/* 100 */         if ((pathpoint == null || !pathpoint.a().equals(blockposition)) && (pathpoint1 == null || !pathpoint1.a().equals(blockposition))) {
/* 101 */           if (a(worldserver, entityliving, globalpos)) {
/* 102 */             iterator.remove(); continue;
/*     */           } 
/* 104 */           IBlockData iblockdata = worldserver.getType(blockposition);
/*     */           
/* 106 */           if (!iblockdata.a(TagsBlock.WOODEN_DOORS)) {
/* 107 */             iterator.remove(); continue;
/*     */           } 
/* 109 */           BlockDoor blockdoor = (BlockDoor)iblockdata.getBlock();
/*     */           
/* 111 */           if (!blockdoor.h(iblockdata)) {
/* 112 */             iterator.remove(); continue;
/* 113 */           }  if (a(worldserver, entityliving, blockposition)) {
/* 114 */             iterator.remove(); continue;
/*     */           } 
/* 116 */           blockdoor.setDoor(worldserver, iblockdata, blockposition, false);
/* 117 */           iterator.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(WorldServer worldserver, EntityLiving entityliving, BlockPosition blockposition) {
/* 128 */     BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
/*     */     
/* 130 */     return !behaviorcontroller.hasMemory(MemoryModuleType.MOBS) ? false : (
/*     */ 
/*     */ 
/*     */       
/* 134 */       (List)behaviorcontroller.<List>getMemory((MemoryModuleType)MemoryModuleType.MOBS).get()).stream().filter(entityliving1 -> (entityliving1.getEntityType() == entityliving.getEntityType())).filter(entityliving1 -> blockposition.a(entityliving1.getPositionVector(), 2.0D)).anyMatch(entityliving1 -> b(worldserver, entityliving1, blockposition));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean b(WorldServer worldserver, EntityLiving entityliving, BlockPosition blockposition) {
/* 140 */     if (!entityliving.getBehaviorController().hasMemory(MemoryModuleType.PATH)) {
/* 141 */       return false;
/*     */     }
/* 143 */     PathEntity pathentity = entityliving.getBehaviorController().<PathEntity>getMemory(MemoryModuleType.PATH).get();
/*     */     
/* 145 */     if (pathentity.c()) {
/* 146 */       return false;
/*     */     }
/* 148 */     PathPoint pathpoint = pathentity.i();
/*     */     
/* 150 */     if (pathpoint == null) {
/* 151 */       return false;
/*     */     }
/* 153 */     PathPoint pathpoint1 = pathentity.h();
/*     */     
/* 155 */     return (blockposition.equals(pathpoint.a()) || blockposition.equals(pathpoint1.a()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(WorldServer worldserver, EntityLiving entityliving, GlobalPos globalpos) {
/* 162 */     return (globalpos.getDimensionManager() != worldserver.getDimensionKey() || !globalpos.getBlockPosition().a(entityliving.getPositionVector(), 2.0D));
/*     */   }
/*     */   
/*     */   private void c(WorldServer worldserver, EntityLiving entityliving, BlockPosition blockposition) {
/* 166 */     BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
/* 167 */     GlobalPos globalpos = GlobalPos.create(worldserver.getDimensionKey(), blockposition);
/*     */     
/* 169 */     if (behaviorcontroller.<Set<GlobalPos>>getMemory(MemoryModuleType.DOORS_TO_CLOSE).isPresent()) {
/* 170 */       ((Set<GlobalPos>)behaviorcontroller.<Set<GlobalPos>>getMemory(MemoryModuleType.DOORS_TO_CLOSE).get()).add(globalpos);
/*     */     } else {
/* 172 */       behaviorcontroller.setMemory(MemoryModuleType.DOORS_TO_CLOSE, Sets.newHashSet((Object[])new GlobalPos[] { globalpos }));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorInteractDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */