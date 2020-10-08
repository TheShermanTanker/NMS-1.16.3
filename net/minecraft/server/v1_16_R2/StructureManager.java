/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StructureManager {
/*    */   private final GeneratorAccess a;
/*    */   
/*    */   public GeneratorAccess getLevel() {
/* 10 */     return this.a;
/*    */   }
/*    */   private final GeneratorSettings b;
/*    */   public StructureManager(GeneratorAccess generatoraccess, GeneratorSettings generatorsettings) {
/* 14 */     this.a = generatoraccess;
/* 15 */     this.b = generatorsettings;
/*    */   }
/*    */   
/*    */   public StructureManager a(RegionLimitedWorldAccess regionlimitedworldaccess) {
/* 19 */     if (regionlimitedworldaccess.getMinecraftWorld() != this.a) {
/* 20 */       throw new IllegalStateException("Using invalid feature manager (source level: " + regionlimitedworldaccess.getMinecraftWorld() + ", region: " + regionlimitedworldaccess);
/*    */     }
/* 22 */     return new StructureManager(regionlimitedworldaccess, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<? extends StructureStart<?>> a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator) {
/* 27 */     return this.a.getChunkAt(sectionposition.a(), sectionposition.c(), ChunkStatus.STRUCTURE_REFERENCES).b(structuregenerator).stream().map(olong -> SectionPosition.a(new ChunkCoordIntPair(olong.longValue()), 0))
/*    */       
/* 29 */       .map(sectionposition1 -> a(sectionposition1, structuregenerator, this.a.getChunkAt(sectionposition1.a(), sectionposition1.c(), ChunkStatus.STRUCTURE_STARTS)))
/*    */       
/* 31 */       .filter(structurestart -> 
/* 32 */         (structurestart != null && structurestart.e()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<StructureStart<?>> getFeatureStarts(SectionPosition sectionPosition, StructureGenerator<?> structureGenerator) {
/* 39 */     return getFeatureStarts(sectionPosition, structureGenerator, null);
/*    */   }
/*    */   
/*    */   public List<StructureStart<?>> getFeatureStarts(SectionPosition sectionPosition, StructureGenerator<?> structureGenerator, IWorldReader world) {
/* 43 */     ObjectArrayList<StructureStart<?>> objectArrayList = new ObjectArrayList();
/* 44 */     for (LongIterator<Long> longIterator = ((world == null) ? getLevel() : world).getChunkAt(sectionPosition.a(), sectionPosition.c(), ChunkStatus.STRUCTURE_REFERENCES).b(structureGenerator).iterator(); longIterator.hasNext(); ) { Long curLong = longIterator.next();
/* 45 */       SectionPosition sectionPosition1 = SectionPosition.a(new ChunkCoordIntPair(curLong.longValue()), 0);
/* 46 */       StructureStart<?> structurestart = a(sectionPosition1, structureGenerator, getLevel().getChunkAt(sectionPosition1.a(), sectionPosition1.c(), ChunkStatus.STRUCTURE_STARTS));
/* 47 */       if (structurestart != null && structurestart.e()) {
/* 48 */         objectArrayList.add(structurestart);
/*    */       } }
/*    */     
/* 51 */     return (List<StructureStart<?>>)objectArrayList;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public StructureStart<?> a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator, IStructureAccess istructureaccess) {
/* 57 */     return istructureaccess.a(structuregenerator);
/*    */   }
/*    */   
/*    */   public void a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator, StructureStart<?> structurestart, IStructureAccess istructureaccess) {
/* 61 */     istructureaccess.a(structuregenerator, structurestart);
/*    */   }
/*    */   
/*    */   public void a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator, long i, IStructureAccess istructureaccess) {
/* 65 */     istructureaccess.a(structuregenerator, i);
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 69 */     return this.b.shouldGenerateMapFeatures();
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureStart<?> a(BlockPosition blockposition, boolean flag, StructureGenerator<?> structuregenerator) {
/* 74 */     return getStructureStarts(blockposition, flag, structuregenerator, null);
/*    */   }
/*    */   
/*    */   public StructureStart<?> getStructureStarts(BlockPosition blockposition, boolean flag, StructureGenerator<?> structuregenerator, IWorldReader world) {
/* 78 */     for (StructureStart<?> structurestart : getFeatureStarts(SectionPosition.a(blockposition), structuregenerator, world)) {
/* 79 */       if (structurestart.c().b(blockposition)) {
/* 80 */         if (!flag) {
/* 81 */           return structurestart;
/*    */         }
/* 83 */         for (StructurePiece structurepiece : structurestart.d()) {
/* 84 */           if (structurepiece.g().b(blockposition)) {
/* 85 */             return structurestart;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 90 */     return StructureStart.a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public World getWorld() {
/* 96 */     return this.a.getMinecraftWorld();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */