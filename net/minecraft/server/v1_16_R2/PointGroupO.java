/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
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
/*     */ public enum PointGroupO
/*     */   implements INamable
/*     */ {
/*  23 */   IDENTITY("identity", PointGroupS.P123, false, false, false),
/*     */ 
/*     */   
/*  26 */   ROT_180_FACE_XY("rot_180_face_xy", PointGroupS.P123, true, true, false),
/*  27 */   ROT_180_FACE_XZ("rot_180_face_xz", PointGroupS.P123, true, false, true),
/*  28 */   ROT_180_FACE_YZ("rot_180_face_yz", PointGroupS.P123, false, true, true),
/*     */ 
/*     */   
/*  31 */   ROT_120_NNN("rot_120_nnn", PointGroupS.P231, false, false, false),
/*  32 */   ROT_120_NNP("rot_120_nnp", PointGroupS.P312, true, false, true),
/*  33 */   ROT_120_NPN("rot_120_npn", PointGroupS.P312, false, true, true),
/*  34 */   ROT_120_NPP("rot_120_npp", PointGroupS.P231, true, false, true),
/*  35 */   ROT_120_PNN("rot_120_pnn", PointGroupS.P312, true, true, false),
/*  36 */   ROT_120_PNP("rot_120_pnp", PointGroupS.P231, true, true, false),
/*  37 */   ROT_120_PPN("rot_120_ppn", PointGroupS.P231, false, true, true),
/*  38 */   ROT_120_PPP("rot_120_ppp", PointGroupS.P312, false, false, false),
/*     */ 
/*     */   
/*  41 */   ROT_180_EDGE_XY_NEG("rot_180_edge_xy_neg", PointGroupS.P213, true, true, true),
/*  42 */   ROT_180_EDGE_XY_POS("rot_180_edge_xy_pos", PointGroupS.P213, false, false, true),
/*  43 */   ROT_180_EDGE_XZ_NEG("rot_180_edge_xz_neg", PointGroupS.P321, true, true, true),
/*  44 */   ROT_180_EDGE_XZ_POS("rot_180_edge_xz_pos", PointGroupS.P321, false, true, false),
/*  45 */   ROT_180_EDGE_YZ_NEG("rot_180_edge_yz_neg", PointGroupS.P132, true, true, true),
/*  46 */   ROT_180_EDGE_YZ_POS("rot_180_edge_yz_pos", PointGroupS.P132, true, false, false),
/*     */ 
/*     */   
/*  49 */   ROT_90_X_NEG("rot_90_x_neg", PointGroupS.P132, false, false, true),
/*  50 */   ROT_90_X_POS("rot_90_x_pos", PointGroupS.P132, false, true, false),
/*  51 */   ROT_90_Y_NEG("rot_90_y_neg", PointGroupS.P321, true, false, false),
/*  52 */   ROT_90_Y_POS("rot_90_y_pos", PointGroupS.P321, false, false, true),
/*  53 */   ROT_90_Z_NEG("rot_90_z_neg", PointGroupS.P213, false, true, false),
/*  54 */   ROT_90_Z_POS("rot_90_z_pos", PointGroupS.P213, true, false, false),
/*     */ 
/*     */   
/*  57 */   INVERSION("inversion", PointGroupS.P123, true, true, true),
/*     */ 
/*     */   
/*  60 */   INVERT_X("invert_x", PointGroupS.P123, true, false, false),
/*  61 */   INVERT_Y("invert_y", PointGroupS.P123, false, true, false),
/*  62 */   INVERT_Z("invert_z", PointGroupS.P123, false, false, true),
/*     */ 
/*     */   
/*  65 */   ROT_60_REF_NNN("rot_60_ref_nnn", PointGroupS.P312, true, true, true),
/*  66 */   ROT_60_REF_NNP("rot_60_ref_nnp", PointGroupS.P231, true, false, false),
/*  67 */   ROT_60_REF_NPN("rot_60_ref_npn", PointGroupS.P231, false, false, true),
/*  68 */   ROT_60_REF_NPP("rot_60_ref_npp", PointGroupS.P312, false, false, true),
/*  69 */   ROT_60_REF_PNN("rot_60_ref_pnn", PointGroupS.P231, false, true, false),
/*  70 */   ROT_60_REF_PNP("rot_60_ref_pnp", PointGroupS.P312, true, false, false),
/*  71 */   ROT_60_REF_PPN("rot_60_ref_ppn", PointGroupS.P312, false, true, false),
/*  72 */   ROT_60_REF_PPP("rot_60_ref_ppp", PointGroupS.P231, true, true, true),
/*     */ 
/*     */   
/*  75 */   SWAP_XY("swap_xy", PointGroupS.P213, false, false, false),
/*  76 */   SWAP_YZ("swap_yz", PointGroupS.P132, false, false, false),
/*  77 */   SWAP_XZ("swap_xz", PointGroupS.P321, false, false, false),
/*     */ 
/*     */   
/*  80 */   SWAP_NEG_XY("swap_neg_xy", PointGroupS.P213, true, true, false),
/*  81 */   SWAP_NEG_YZ("swap_neg_yz", PointGroupS.P132, false, true, true),
/*  82 */   SWAP_NEG_XZ("swap_neg_xz", PointGroupS.P321, true, false, true),
/*     */ 
/*     */   
/*  85 */   ROT_90_REF_X_NEG("rot_90_ref_x_neg", PointGroupS.P132, true, false, true),
/*  86 */   ROT_90_REF_X_POS("rot_90_ref_x_pos", PointGroupS.P132, true, true, false),
/*  87 */   ROT_90_REF_Y_NEG("rot_90_ref_y_neg", PointGroupS.P321, true, true, false),
/*  88 */   ROT_90_REF_Y_POS("rot_90_ref_y_pos", PointGroupS.P321, false, true, true),
/*  89 */   ROT_90_REF_Z_NEG("rot_90_ref_z_neg", PointGroupS.P213, false, true, true),
/*  90 */   ROT_90_REF_Z_POS("rot_90_ref_z_pos", PointGroupS.P213, true, false, true);
/*     */   
/*     */   private final Matrix3f W;
/*     */   
/*     */   private final String X;
/*     */   
/*     */   @Nullable
/*     */   private Map<EnumDirection, EnumDirection> Y;
/*     */   private final boolean Z;
/*     */   private final boolean aa;
/*     */   private final boolean ab;
/*     */   private final PointGroupS ac;
/*     */   private static final PointGroupO[][] ad;
/*     */   private static final PointGroupO[] ae;
/*     */   
/*     */   PointGroupO(String var2, PointGroupS var3, boolean var4, boolean var5, boolean var6) {
/* 106 */     this.X = var2;
/* 107 */     this.Z = var4;
/* 108 */     this.aa = var5;
/* 109 */     this.ab = var6;
/* 110 */     this.ac = var3;
/*     */     
/* 112 */     this.W = new Matrix3f();
/* 113 */     this.W.a = var4 ? -1.0F : 1.0F;
/* 114 */     this.W.e = var5 ? -1.0F : 1.0F;
/* 115 */     this.W.i = var6 ? -1.0F : 1.0F;
/*     */     
/* 117 */     this.W.b(var3.a());
/*     */   }
/*     */   
/*     */   private BooleanList b() {
/* 121 */     return (BooleanList)new BooleanArrayList(new boolean[] { this.Z, this.aa, this.ab });
/*     */   }
/*     */   static {
/* 124 */     ad = SystemUtils.<PointGroupO[][]>a(new PointGroupO[(values()).length][(values()).length], var0 -> {
/*     */           Map<Pair<PointGroupS, BooleanList>, PointGroupO> var1 = (Map<Pair<PointGroupS, BooleanList>, PointGroupO>)Arrays.<PointGroupO>stream(values()).collect(Collectors.toMap((), ()));
/*     */           
/*     */           for (PointGroupO var5 : values()) {
/*     */             for (PointGroupO var9 : values()) {
/*     */               BooleanList var10 = var5.b();
/*     */               
/*     */               BooleanList var11 = var9.b();
/*     */               
/*     */               PointGroupS var12 = var9.ac.a(var5.ac);
/*     */               
/*     */               BooleanArrayList var13 = new BooleanArrayList(3);
/*     */               
/*     */               for (int var14 = 0; var14 < 3; var14++) {
/*     */                 var13.add(var10.getBoolean(var14) ^ var11.getBoolean(var5.ac.a(var14)));
/*     */               }
/*     */               
/*     */               var0[var5.ordinal()][var9.ordinal()] = var1.get(Pair.of(var12, var13));
/*     */             } 
/*     */           } 
/*     */         });
/* 145 */     ae = (PointGroupO[])Arrays.<PointGroupO>stream(values()).map(var0 -> (PointGroupO)Arrays.<PointGroupO>stream(values()).filter(()).findAny().get()).toArray(var0 -> new PointGroupO[var0]);
/*     */   }
/*     */   public PointGroupO a(PointGroupO var0) {
/* 148 */     return ad[ordinal()][var0.ordinal()];
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
/*     */   public String toString() {
/* 161 */     return this.X;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 166 */     return this.X;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection a(EnumDirection var0) {
/* 171 */     if (this.Y == null) {
/* 172 */       this.Y = Maps.newEnumMap(EnumDirection.class);
/*     */       
/* 174 */       for (EnumDirection var4 : EnumDirection.values()) {
/* 175 */         EnumDirection.EnumAxis var5 = var4.n();
/* 176 */         EnumDirection.EnumAxisDirection var6 = var4.e();
/*     */         
/* 178 */         EnumDirection.EnumAxis var7 = EnumDirection.EnumAxis.values()[this.ac.a(var5.ordinal())];
/*     */         
/* 180 */         EnumDirection.EnumAxisDirection var8 = a(var7) ? var6.c() : var6;
/*     */         
/* 182 */         EnumDirection var9 = EnumDirection.a(var7, var8);
/*     */         
/* 184 */         this.Y.put(var4, var9);
/*     */       } 
/*     */     } 
/* 187 */     return this.Y.get(var0);
/*     */   }
/*     */   
/*     */   public boolean a(EnumDirection.EnumAxis var0) {
/* 191 */     switch (null.a[var0.ordinal()]) {
/*     */       case 1:
/* 193 */         return this.Z;
/*     */       case 2:
/* 195 */         return this.aa;
/*     */     } 
/*     */     
/* 198 */     return this.ab;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPropertyJigsawOrientation a(BlockPropertyJigsawOrientation var0) {
/* 203 */     return BlockPropertyJigsawOrientation.a(a(var0.b()), a(var0.c()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PointGroupO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */