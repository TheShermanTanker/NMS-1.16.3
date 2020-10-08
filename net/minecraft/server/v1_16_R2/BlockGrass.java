/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BlockGrass
/*    */   extends BlockDirtSnowSpreadable
/*    */   implements IBlockFragilePlantElement {
/*    */   public BlockGrass(BlockBase.Info blockbase_info) {
/*  9 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 14 */     return iblockaccess.getType(blockposition.up()).isAir();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 19 */     return true;
/*    */   }
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/*    */     // Byte code:
/*    */     //   0: aload_3
/*    */     //   1: invokevirtual up : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   4: astore #5
/*    */     //   6: getstatic net/minecraft/server/v1_16_R2/Blocks.GRASS : Lnet/minecraft/server/v1_16_R2/Block;
/*    */     //   9: invokevirtual getBlockData : ()Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   12: astore #6
/*    */     //   14: iconst_0
/*    */     //   15: istore #7
/*    */     //   17: iload #7
/*    */     //   19: sipush #128
/*    */     //   22: if_icmpge -> 283
/*    */     //   25: aload #5
/*    */     //   27: astore #8
/*    */     //   29: iconst_0
/*    */     //   30: istore #9
/*    */     //   32: iload #9
/*    */     //   34: iload #7
/*    */     //   36: bipush #16
/*    */     //   38: idiv
/*    */     //   39: if_icmpge -> 115
/*    */     //   42: aload #8
/*    */     //   44: aload_2
/*    */     //   45: iconst_3
/*    */     //   46: invokevirtual nextInt : (I)I
/*    */     //   49: iconst_1
/*    */     //   50: isub
/*    */     //   51: aload_2
/*    */     //   52: iconst_3
/*    */     //   53: invokevirtual nextInt : (I)I
/*    */     //   56: iconst_1
/*    */     //   57: isub
/*    */     //   58: aload_2
/*    */     //   59: iconst_3
/*    */     //   60: invokevirtual nextInt : (I)I
/*    */     //   63: imul
/*    */     //   64: iconst_2
/*    */     //   65: idiv
/*    */     //   66: aload_2
/*    */     //   67: iconst_3
/*    */     //   68: invokevirtual nextInt : (I)I
/*    */     //   71: iconst_1
/*    */     //   72: isub
/*    */     //   73: invokevirtual b : (III)Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   76: astore #8
/*    */     //   78: aload_1
/*    */     //   79: aload #8
/*    */     //   81: invokevirtual down : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   84: invokevirtual getType : (Lnet/minecraft/server/v1_16_R2/BlockPosition;)Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   87: aload_0
/*    */     //   88: invokevirtual a : (Lnet/minecraft/server/v1_16_R2/Block;)Z
/*    */     //   91: ifeq -> 274
/*    */     //   94: aload_1
/*    */     //   95: aload #8
/*    */     //   97: invokevirtual getType : (Lnet/minecraft/server/v1_16_R2/BlockPosition;)Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   100: aload_1
/*    */     //   101: aload #8
/*    */     //   103: invokevirtual r : (Lnet/minecraft/server/v1_16_R2/IBlockAccess;Lnet/minecraft/server/v1_16_R2/BlockPosition;)Z
/*    */     //   106: ifne -> 274
/*    */     //   109: iinc #9, 1
/*    */     //   112: goto -> 32
/*    */     //   115: aload_1
/*    */     //   116: aload #8
/*    */     //   118: invokevirtual getType : (Lnet/minecraft/server/v1_16_R2/BlockPosition;)Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   121: astore #10
/*    */     //   123: aload #10
/*    */     //   125: aload #6
/*    */     //   127: invokevirtual getBlock : ()Lnet/minecraft/server/v1_16_R2/Block;
/*    */     //   130: invokevirtual a : (Lnet/minecraft/server/v1_16_R2/Block;)Z
/*    */     //   133: ifeq -> 164
/*    */     //   136: aload_2
/*    */     //   137: bipush #10
/*    */     //   139: invokevirtual nextInt : (I)I
/*    */     //   142: ifne -> 164
/*    */     //   145: aload #6
/*    */     //   147: invokevirtual getBlock : ()Lnet/minecraft/server/v1_16_R2/Block;
/*    */     //   150: checkcast net/minecraft/server/v1_16_R2/IBlockFragilePlantElement
/*    */     //   153: aload_1
/*    */     //   154: aload_2
/*    */     //   155: aload #8
/*    */     //   157: aload #10
/*    */     //   159: invokeinterface a : (Lnet/minecraft/server/v1_16_R2/WorldServer;Ljava/util/Random;Lnet/minecraft/server/v1_16_R2/BlockPosition;Lnet/minecraft/server/v1_16_R2/IBlockData;)V
/*    */     //   164: aload #10
/*    */     //   166: invokevirtual isAir : ()Z
/*    */     //   169: ifeq -> 274
/*    */     //   172: aload_2
/*    */     //   173: bipush #8
/*    */     //   175: invokevirtual nextInt : (I)I
/*    */     //   178: ifne -> 249
/*    */     //   181: aload_1
/*    */     //   182: aload #8
/*    */     //   184: invokevirtual getBiome : (Lnet/minecraft/server/v1_16_R2/BlockPosition;)Lnet/minecraft/server/v1_16_R2/BiomeBase;
/*    */     //   187: invokevirtual e : ()Lnet/minecraft/server/v1_16_R2/BiomeSettingsGeneration;
/*    */     //   190: invokevirtual b : ()Ljava/util/List;
/*    */     //   193: astore #12
/*    */     //   195: aload #12
/*    */     //   197: invokeinterface isEmpty : ()Z
/*    */     //   202: ifeq -> 208
/*    */     //   205: goto -> 274
/*    */     //   208: aload #12
/*    */     //   210: iconst_0
/*    */     //   211: invokeinterface get : (I)Ljava/lang/Object;
/*    */     //   216: checkcast net/minecraft/server/v1_16_R2/WorldGenFeatureConfigured
/*    */     //   219: astore #13
/*    */     //   221: aload #13
/*    */     //   223: getfield e : Lnet/minecraft/server/v1_16_R2/WorldGenerator;
/*    */     //   226: checkcast net/minecraft/server/v1_16_R2/WorldGenFlowers
/*    */     //   229: astore #14
/*    */     //   231: aload #14
/*    */     //   233: aload_2
/*    */     //   234: aload #8
/*    */     //   236: aload #13
/*    */     //   238: invokevirtual c : ()Lnet/minecraft/server/v1_16_R2/WorldGenFeatureConfiguration;
/*    */     //   241: invokevirtual b : (Ljava/util/Random;Lnet/minecraft/server/v1_16_R2/BlockPosition;Lnet/minecraft/server/v1_16_R2/WorldGenFeatureConfiguration;)Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   244: astore #11
/*    */     //   246: goto -> 253
/*    */     //   249: aload #6
/*    */     //   251: astore #11
/*    */     //   253: aload #11
/*    */     //   255: aload_1
/*    */     //   256: aload #8
/*    */     //   258: invokevirtual canPlace : (Lnet/minecraft/server/v1_16_R2/IWorldReader;Lnet/minecraft/server/v1_16_R2/BlockPosition;)Z
/*    */     //   261: ifeq -> 274
/*    */     //   264: aload_1
/*    */     //   265: aload #8
/*    */     //   267: aload #11
/*    */     //   269: iconst_3
/*    */     //   270: invokestatic handleBlockGrowEvent : (Lnet/minecraft/server/v1_16_R2/World;Lnet/minecraft/server/v1_16_R2/BlockPosition;Lnet/minecraft/server/v1_16_R2/IBlockData;I)Z
/*    */     //   273: pop
/*    */     //   274: iinc #7, 1
/*    */     //   277: goto -> 280
/*    */     //   280: goto -> 17
/*    */     //   283: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     //   #25	-> 6
/*    */     //   #26	-> 14
/*    */     //   #28	-> 17
/*    */     //   #29	-> 25
/*    */     //   #30	-> 29
/*    */     //   #33	-> 32
/*    */     //   #34	-> 42
/*    */     //   #35	-> 78
/*    */     //   #36	-> 109
/*    */     //   #37	-> 112
/*    */     //   #40	-> 115
/*    */     //   #42	-> 123
/*    */     //   #43	-> 145
/*    */     //   #46	-> 164
/*    */     //   #51	-> 172
/*    */     //   #52	-> 181
/*    */     //   #54	-> 195
/*    */     //   #55	-> 205
/*    */     //   #58	-> 208
/*    */     //   #59	-> 221
/*    */     //   #61	-> 231
/*    */     //   #62	-> 246
/*    */     //   #63	-> 249
/*    */     //   #66	-> 253
/*    */     //   #67	-> 264
/*    */     //   #73	-> 274
/*    */     //   #74	-> 277
/*    */     //   #76	-> 280
/*    */     //   #78	-> 283
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   195	51	12	list	Ljava/util/List;
/*    */     //   221	25	13	worldgenfeatureconfigured	Lnet/minecraft/server/v1_16_R2/WorldGenFeatureConfigured;
/*    */     //   231	15	14	worldgenflowers	Lnet/minecraft/server/v1_16_R2/WorldGenFlowers;
/*    */     //   246	3	11	iblockdata3	Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   253	21	11	iblockdata3	Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   123	151	10	iblockdata2	Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   29	251	8	blockposition2	Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   32	248	9	j	I
/*    */     //   0	284	0	this	Lnet/minecraft/server/v1_16_R2/BlockGrass;
/*    */     //   0	284	1	worldserver	Lnet/minecraft/server/v1_16_R2/WorldServer;
/*    */     //   0	284	2	random	Ljava/util/Random;
/*    */     //   0	284	3	blockposition	Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   0	284	4	iblockdata	Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   6	278	5	blockposition1	Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*    */     //   14	270	6	iblockdata1	Lnet/minecraft/server/v1_16_R2/IBlockData;
/*    */     //   17	267	7	i	I
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   195	51	12	list	Ljava/util/List<Lnet/minecraft/server/v1_16_R2/WorldGenFeatureConfigured<**>;>;
/*    */     //   221	25	13	worldgenfeatureconfigured	Lnet/minecraft/server/v1_16_R2/WorldGenFeatureConfigured<**>;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */