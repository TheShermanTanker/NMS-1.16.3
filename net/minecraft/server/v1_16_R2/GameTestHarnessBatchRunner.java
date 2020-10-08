/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameTestHarnessBatchRunner
/*     */ {
/*  19 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final BlockPosition b;
/*     */   private final WorldServer c;
/*     */   private final GameTestHarnessTicker d;
/*     */   private final int e;
/*  25 */   private final List<GameTestHarnessInfo> f = Lists.newArrayList();
/*  26 */   private final Map<GameTestHarnessInfo, BlockPosition> g = Maps.newHashMap();
/*  27 */   private final List<Pair<GameTestHarnessBatch, Collection<GameTestHarnessInfo>>> h = Lists.newArrayList();
/*     */   
/*     */   private GameTestHarnessCollector i;
/*  30 */   private int j = 0;
/*     */   private BlockPosition.MutableBlockPosition k;
/*     */   
/*     */   public GameTestHarnessBatchRunner(Collection<GameTestHarnessBatch> var0, BlockPosition var1, EnumBlockRotation var2, WorldServer var3, GameTestHarnessTicker var4, int var5) {
/*  34 */     this.k = var1.i();
/*  35 */     this.b = var1;
/*  36 */     this.c = var3;
/*  37 */     this.d = var4;
/*  38 */     this.e = var5;
/*     */     
/*  40 */     var0.forEach(var2 -> {
/*     */           Collection<GameTestHarnessInfo> var3 = Lists.newArrayList();
/*     */           Collection<GameTestHarnessTestFunction> var4 = var2.b();
/*     */           for (GameTestHarnessTestFunction var6 : var4) {
/*     */             GameTestHarnessInfo var7 = new GameTestHarnessInfo(var6, var0, var1);
/*     */             var3.add(var7);
/*     */             this.f.add(var7);
/*     */           } 
/*     */           this.h.add(Pair.of(var2, var3));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<GameTestHarnessInfo> a() {
/*  56 */     return this.f;
/*     */   }
/*     */   
/*     */   public void b() {
/*  60 */     a(0);
/*     */   }
/*     */   
/*     */   private void a(int var0) {
/*  64 */     this.j = var0;
/*  65 */     this.i = new GameTestHarnessCollector();
/*     */     
/*  67 */     if (var0 >= this.h.size()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  72 */     Pair<GameTestHarnessBatch, Collection<GameTestHarnessInfo>> var1 = this.h.get(this.j);
/*  73 */     GameTestHarnessBatch var2 = (GameTestHarnessBatch)var1.getFirst();
/*  74 */     Collection<GameTestHarnessInfo> var3 = (Collection<GameTestHarnessInfo>)var1.getSecond();
/*  75 */     a(var3);
/*  76 */     var2.a(this.c);
/*     */     
/*  78 */     String var4 = var2.a();
/*     */     
/*  80 */     LOGGER.info("Running test batch '" + var4 + "' (" + var3.size() + " tests)...");
/*  81 */     var3.forEach(var0 -> {
/*     */           this.i.a(var0);
/*     */           this.i.a(new GameTestHarnessListener(this)
/*     */               {
/*     */                 public void a(GameTestHarnessInfo var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void c(GameTestHarnessInfo var0) {
/*  95 */                   GameTestHarnessBatchRunner.a(this.a, var0);
/*     */                 }
/*     */               });
/*     */           BlockPosition var1 = this.g.get(var0);
/*     */           GameTestHarnessRunner.a(var0, var1, this.d);
/*     */         });
/*     */   }
/*     */   
/*     */   private void a(GameTestHarnessInfo var0) {
/* 104 */     if (this.i.i()) {
/* 105 */       a(this.j + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(Collection<GameTestHarnessInfo> var0) {
/* 110 */     int var1 = 0;
/* 111 */     AxisAlignedBB var2 = new AxisAlignedBB(this.k);
/*     */     
/* 113 */     for (GameTestHarnessInfo var4 : var0) {
/* 114 */       BlockPosition var5 = new BlockPosition(this.k);
/* 115 */       TileEntityStructure var6 = GameTestHarnessStructures.a(var4.s(), var5, var4.t(), 2, this.c, true);
/* 116 */       AxisAlignedBB var7 = GameTestHarnessStructures.a(var6);
/* 117 */       var4.a(var6.getPosition());
/* 118 */       this.g.put(var4, new BlockPosition(this.k));
/* 119 */       var2 = var2.b(var7);
/*     */       
/* 121 */       this.k.e((int)var7.b() + 5, 0, 0);
/*     */       
/* 123 */       if (var1++ % this.e == this.e - 1) {
/*     */         
/* 125 */         this.k.e(0, 0, (int)var2.d() + 6);
/* 126 */         this.k.o(this.b.getX());
/* 127 */         var2 = new AxisAlignedBB(this.k);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessBatchRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */