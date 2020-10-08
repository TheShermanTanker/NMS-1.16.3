/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
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
/*     */ public class BlockFlowerPot
/*     */   extends Block
/*     */ {
/*  25 */   private static final Map<Block, Block> b = Maps.newHashMap();
/*     */ 
/*     */   
/*  28 */   protected static final VoxelShape a = Block.a(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
/*     */   
/*     */   private final Block c;
/*     */   
/*     */   public BlockFlowerPot(Block var0, BlockBase.Info var1) {
/*  33 */     super(var1);
/*  34 */     this.c = var0;
/*     */     
/*  36 */     b.put(var0, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  41 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  46 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  51 */     ItemStack var6 = var3.b(var4);
/*     */     
/*  53 */     Item var7 = var6.getItem();
/*  54 */     Block var8 = (var7 instanceof ItemBlock) ? b.getOrDefault(((ItemBlock)var7).getBlock(), Blocks.AIR) : Blocks.AIR;
/*  55 */     boolean var9 = (var8 == Blocks.AIR);
/*  56 */     boolean var10 = (this.c == Blocks.AIR);
/*     */     
/*  58 */     if (var9 != var10) {
/*  59 */       if (var10) {
/*  60 */         var1.setTypeAndData(var2, var8.getBlockData(), 3);
/*  61 */         var3.a(StatisticList.POT_FLOWER);
/*     */         
/*  63 */         if (!var3.abilities.canInstantlyBuild) {
/*  64 */           var6.subtract(1);
/*     */         }
/*     */       } else {
/*  67 */         ItemStack var11 = new ItemStack(this.c);
/*  68 */         if (var6.isEmpty()) {
/*  69 */           var3.a(var4, var11);
/*  70 */         } else if (!var3.g(var11)) {
/*  71 */           var3.drop(var11, false);
/*     */         } 
/*  73 */         var1.setTypeAndData(var2, Blocks.FLOWER_POT.getBlockData(), 3);
/*     */       } 
/*  75 */       return EnumInteractionResult.a(var1.isClientSide);
/*     */     } 
/*     */     
/*  78 */     return EnumInteractionResult.CONSUME;
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
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  91 */     if (var1 == EnumDirection.DOWN && !var0.canPlace(var3, var4)) {
/*  92 */       return Blocks.AIR.getBlockData();
/*     */     }
/*     */     
/*  95 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */   
/*     */   public Block c() {
/*  99 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 104 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */