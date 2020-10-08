/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityJigsaw
/*     */   extends TileEntity
/*     */ {
/*     */   public enum JointType
/*     */     implements INamable
/*     */   {
/*  33 */     ROLLABLE("rollable"),
/*  34 */     ALIGNED("aligned");
/*     */     
/*     */     private final String c;
/*     */     
/*     */     JointType(String var2) {
/*  39 */       this.c = var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/*  44 */       return this.c;
/*     */     }
/*     */     
/*     */     public static Optional<JointType> a(String var0) {
/*  48 */       return Arrays.<JointType>stream(values()).filter(var1 -> var1.getName().equals(var0)).findFirst();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private MinecraftKey a = new MinecraftKey("empty");
/*  64 */   private MinecraftKey b = new MinecraftKey("empty");
/*  65 */   private MinecraftKey c = new MinecraftKey("empty");
/*  66 */   private JointType g = JointType.ROLLABLE;
/*  67 */   private String h = "minecraft:air";
/*     */   
/*     */   public TileEntityJigsaw(TileEntityTypes<?> var0) {
/*  70 */     super(var0);
/*     */   }
/*     */   
/*     */   public TileEntityJigsaw() {
/*  74 */     this(TileEntityTypes.JIGSAW);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(MinecraftKey var0) {
/*  98 */     this.a = var0;
/*     */   }
/*     */   
/*     */   public void b(MinecraftKey var0) {
/* 102 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public void c(MinecraftKey var0) {
/* 106 */     this.c = var0;
/*     */   }
/*     */   
/*     */   public void a(String var0) {
/* 110 */     this.h = var0;
/*     */   }
/*     */   
/*     */   public void a(JointType var0) {
/* 114 */     this.g = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound var0) {
/* 119 */     super.save(var0);
/* 120 */     var0.setString("name", this.a.toString());
/* 121 */     var0.setString("target", this.b.toString());
/* 122 */     var0.setString("pool", this.c.toString());
/* 123 */     var0.setString("final_state", this.h);
/* 124 */     var0.setString("joint", this.g.getName());
/* 125 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData var0, NBTTagCompound var1) {
/* 130 */     super.load(var0, var1);
/* 131 */     this.a = new MinecraftKey(var1.getString("name"));
/* 132 */     this.b = new MinecraftKey(var1.getString("target"));
/* 133 */     this.c = new MinecraftKey(var1.getString("pool"));
/* 134 */     this.h = var1.getString("final_state");
/* 135 */     this
/* 136 */       .g = JointType.a(var1.getString("joint")).orElseGet(() -> BlockJigsaw.h(var0).n().d() ? JointType.ALIGNED : JointType.ROLLABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 142 */     return new PacketPlayOutTileEntityData(this.position, 12, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 147 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public void a(WorldServer var0, int var1, boolean var2) {
/* 151 */     ChunkGenerator var3 = var0.getChunkProvider().getChunkGenerator();
/* 152 */     DefinedStructureManager var4 = var0.n();
/* 153 */     StructureManager var5 = var0.getStructureManager();
/* 154 */     Random var6 = var0.getRandom();
/* 155 */     BlockPosition var7 = getPosition();
/*     */     
/* 157 */     List<WorldGenFeaturePillagerOutpostPoolPiece> var8 = Lists.newArrayList();
/*     */     
/* 159 */     DefinedStructure var9 = new DefinedStructure();
/* 160 */     var9.a(var0, var7, new BlockPosition(1, 1, 1), false, (Block)null);
/*     */     
/* 162 */     WorldGenFeatureDefinedStructurePoolStructure var10 = new WorldGenFeatureDefinedStructurePoolSingle(var9);
/* 163 */     WorldGenFeaturePillagerOutpostPoolPiece var11 = new WorldGenFeaturePillagerOutpostPoolPiece(var4, var10, var7, 1, EnumBlockRotation.NONE, new StructureBoundingBox(var7, var7));
/*     */     
/* 165 */     WorldGenFeatureDefinedStructureJigsawPlacement.a(var0.r(), var11, var1, WorldGenFeaturePillagerOutpostPoolPiece::new, var3, var4, var8, var6);
/*     */     
/* 167 */     for (WorldGenFeaturePillagerOutpostPoolPiece var13 : var8)
/* 168 */       var13.a(var0, var5, var3, var6, StructureBoundingBox.b(), var7, var2); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */