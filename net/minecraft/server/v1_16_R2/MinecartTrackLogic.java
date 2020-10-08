/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MinecartTrackLogic {
/*     */   private final World a;
/*     */   private final BlockPosition b;
/*     */   
/*  10 */   public final World getWorld() { return this.a; } private final BlockMinecartTrackAbstract c; private IBlockData d; private final boolean e; public final BlockPosition getPos() {
/*  11 */     return this.b;
/*     */   } public final IBlockData getRailState() {
/*  13 */     return this.d;
/*     */   }
/*  15 */   private final List<BlockPosition> f = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  19 */     return (getWorld().getType(getPos()).getBlock() == getRailState().getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecartTrackLogic(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  24 */     this.a = world;
/*  25 */     this.b = blockposition;
/*  26 */     this.d = iblockdata;
/*  27 */     this.c = (BlockMinecartTrackAbstract)iblockdata.getBlock();
/*  28 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(this.c.d());
/*     */     
/*  30 */     this.e = this.c.c();
/*  31 */     a(blockpropertytrackposition);
/*     */   }
/*     */   
/*     */   public List<BlockPosition> a() {
/*  35 */     return this.f;
/*     */   }
/*     */   
/*     */   private void a(BlockPropertyTrackPosition blockpropertytrackposition) {
/*  39 */     this.f.clear();
/*  40 */     switch (blockpropertytrackposition) {
/*     */       case NORTH_SOUTH:
/*  42 */         this.f.add(this.b.north());
/*  43 */         this.f.add(this.b.south());
/*     */         break;
/*     */       case EAST_WEST:
/*  46 */         this.f.add(this.b.west());
/*  47 */         this.f.add(this.b.east());
/*     */         break;
/*     */       case ASCENDING_EAST:
/*  50 */         this.f.add(this.b.west());
/*  51 */         this.f.add(this.b.east().up());
/*     */         break;
/*     */       case ASCENDING_WEST:
/*  54 */         this.f.add(this.b.west().up());
/*  55 */         this.f.add(this.b.east());
/*     */         break;
/*     */       case ASCENDING_NORTH:
/*  58 */         this.f.add(this.b.north().up());
/*  59 */         this.f.add(this.b.south());
/*     */         break;
/*     */       case ASCENDING_SOUTH:
/*  62 */         this.f.add(this.b.north());
/*  63 */         this.f.add(this.b.south().up());
/*     */         break;
/*     */       case SOUTH_EAST:
/*  66 */         this.f.add(this.b.east());
/*  67 */         this.f.add(this.b.south());
/*     */         break;
/*     */       case SOUTH_WEST:
/*  70 */         this.f.add(this.b.west());
/*  71 */         this.f.add(this.b.south());
/*     */         break;
/*     */       case NORTH_WEST:
/*  74 */         this.f.add(this.b.west());
/*  75 */         this.f.add(this.b.north());
/*     */         break;
/*     */       case NORTH_EAST:
/*  78 */         this.f.add(this.b.east());
/*  79 */         this.f.add(this.b.north());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d() {
/*  85 */     for (int i = 0; i < this.f.size(); i++) {
/*  86 */       MinecartTrackLogic minecarttracklogic = b(this.f.get(i));
/*     */       
/*  88 */       if (minecarttracklogic != null && minecarttracklogic.a(this)) {
/*  89 */         this.f.set(i, minecarttracklogic.b);
/*     */       } else {
/*  91 */         this.f.remove(i--);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(BlockPosition blockposition) {
/*  98 */     return (BlockMinecartTrackAbstract.a(this.a, blockposition) || BlockMinecartTrackAbstract.a(this.a, blockposition.up()) || BlockMinecartTrackAbstract.a(this.a, blockposition.down()));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private MinecartTrackLogic b(BlockPosition blockposition) {
/* 103 */     IBlockData iblockdata = this.a.getType(blockposition);
/*     */     
/* 105 */     if (BlockMinecartTrackAbstract.g(iblockdata)) {
/* 106 */       return new MinecartTrackLogic(this.a, blockposition, iblockdata);
/*     */     }
/* 108 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 110 */     iblockdata = this.a.getType(blockposition1);
/* 111 */     if (BlockMinecartTrackAbstract.g(iblockdata)) {
/* 112 */       return new MinecartTrackLogic(this.a, blockposition1, iblockdata);
/*     */     }
/* 114 */     blockposition1 = blockposition.down();
/* 115 */     iblockdata = this.a.getType(blockposition1);
/* 116 */     return BlockMinecartTrackAbstract.g(iblockdata) ? new MinecartTrackLogic(this.a, blockposition1, iblockdata) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(MinecartTrackLogic minecarttracklogic) {
/* 122 */     return c(minecarttracklogic.b);
/*     */   }
/*     */   
/*     */   private boolean c(BlockPosition blockposition) {
/* 126 */     for (int i = 0; i < this.f.size(); i++) {
/* 127 */       BlockPosition blockposition1 = this.f.get(i);
/*     */       
/* 129 */       if (blockposition1.getX() == blockposition.getX() && blockposition1.getZ() == blockposition.getZ()) {
/* 130 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   protected int b() {
/* 138 */     int i = 0;
/* 139 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 141 */     while (iterator.hasNext()) {
/* 142 */       EnumDirection enumdirection = iterator.next();
/*     */       
/* 144 */       if (a(this.b.shift(enumdirection))) {
/* 145 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return i;
/*     */   }
/*     */   
/*     */   private boolean b(MinecartTrackLogic minecarttracklogic) {
/* 153 */     return (a(minecarttracklogic) || this.f.size() != 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(MinecartTrackLogic minecarttracklogic) {
/* 158 */     if (!isValid() || !minecarttracklogic.isValid()) {
/*     */       return;
/*     */     }
/*     */     
/* 162 */     this.f.add(minecarttracklogic.b);
/* 163 */     BlockPosition blockposition = this.b.north();
/* 164 */     BlockPosition blockposition1 = this.b.south();
/* 165 */     BlockPosition blockposition2 = this.b.west();
/* 166 */     BlockPosition blockposition3 = this.b.east();
/* 167 */     boolean flag = c(blockposition);
/* 168 */     boolean flag1 = c(blockposition1);
/* 169 */     boolean flag2 = c(blockposition2);
/* 170 */     boolean flag3 = c(blockposition3);
/* 171 */     BlockPropertyTrackPosition blockpropertytrackposition = null;
/*     */     
/* 173 */     if (flag || flag1) {
/* 174 */       blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */     }
/*     */     
/* 177 */     if (flag2 || flag3) {
/* 178 */       blockpropertytrackposition = BlockPropertyTrackPosition.EAST_WEST;
/*     */     }
/*     */     
/* 181 */     if (!this.e) {
/* 182 */       if (flag1 && flag3 && !flag && !flag2) {
/* 183 */         blockpropertytrackposition = BlockPropertyTrackPosition.SOUTH_EAST;
/*     */       }
/*     */       
/* 186 */       if (flag1 && flag2 && !flag && !flag3) {
/* 187 */         blockpropertytrackposition = BlockPropertyTrackPosition.SOUTH_WEST;
/*     */       }
/*     */       
/* 190 */       if (flag && flag2 && !flag1 && !flag3) {
/* 191 */         blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_WEST;
/*     */       }
/*     */       
/* 194 */       if (flag && flag3 && !flag1 && !flag2) {
/* 195 */         blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_EAST;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     if (blockpropertytrackposition == BlockPropertyTrackPosition.NORTH_SOUTH) {
/* 200 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition.up())) {
/* 201 */         blockpropertytrackposition = BlockPropertyTrackPosition.ASCENDING_NORTH;
/*     */       }
/*     */       
/* 204 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition1.up())) {
/* 205 */         blockpropertytrackposition = BlockPropertyTrackPosition.ASCENDING_SOUTH;
/*     */       }
/*     */     } 
/*     */     
/* 209 */     if (blockpropertytrackposition == BlockPropertyTrackPosition.EAST_WEST) {
/* 210 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition3.up())) {
/* 211 */         blockpropertytrackposition = BlockPropertyTrackPosition.ASCENDING_EAST;
/*     */       }
/*     */       
/* 214 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition2.up())) {
/* 215 */         blockpropertytrackposition = BlockPropertyTrackPosition.ASCENDING_WEST;
/*     */       }
/*     */     } 
/*     */     
/* 219 */     if (blockpropertytrackposition == null) {
/* 220 */       blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */     }
/*     */     
/* 223 */     this.d = this.d.set(this.c.d(), blockpropertytrackposition);
/* 224 */     this.a.setTypeAndData(this.b, this.d, 3);
/*     */   }
/*     */   
/*     */   private boolean d(BlockPosition blockposition) {
/* 228 */     MinecartTrackLogic minecarttracklogic = b(blockposition);
/*     */     
/* 230 */     if (minecarttracklogic == null) {
/* 231 */       return false;
/*     */     }
/* 233 */     minecarttracklogic.d();
/* 234 */     return minecarttracklogic.b(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecartTrackLogic a(boolean flag, boolean flag1, BlockPropertyTrackPosition blockpropertytrackposition) {
/* 239 */     BlockPosition blockposition = this.b.north();
/* 240 */     BlockPosition blockposition1 = this.b.south();
/* 241 */     BlockPosition blockposition2 = this.b.west();
/* 242 */     BlockPosition blockposition3 = this.b.east();
/* 243 */     boolean flag2 = d(blockposition);
/* 244 */     boolean flag3 = d(blockposition1);
/* 245 */     boolean flag4 = d(blockposition2);
/* 246 */     boolean flag5 = d(blockposition3);
/* 247 */     BlockPropertyTrackPosition blockpropertytrackposition1 = null;
/* 248 */     boolean flag6 = (flag2 || flag3);
/* 249 */     boolean flag7 = (flag4 || flag5);
/*     */     
/* 251 */     if (flag6 && !flag7) {
/* 252 */       blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */     }
/*     */     
/* 255 */     if (flag7 && !flag6) {
/* 256 */       blockpropertytrackposition1 = BlockPropertyTrackPosition.EAST_WEST;
/*     */     }
/*     */     
/* 259 */     boolean flag8 = (flag3 && flag5);
/* 260 */     boolean flag9 = (flag3 && flag4);
/* 261 */     boolean flag10 = (flag2 && flag5);
/* 262 */     boolean flag11 = (flag2 && flag4);
/*     */     
/* 264 */     if (!this.e) {
/* 265 */       if (flag8 && !flag2 && !flag4) {
/* 266 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_EAST;
/*     */       }
/*     */       
/* 269 */       if (flag9 && !flag2 && !flag5) {
/* 270 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_WEST;
/*     */       }
/*     */       
/* 273 */       if (flag11 && !flag3 && !flag5) {
/* 274 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_WEST;
/*     */       }
/*     */       
/* 277 */       if (flag10 && !flag3 && !flag4) {
/* 278 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_EAST;
/*     */       }
/*     */     } 
/*     */     
/* 282 */     if (blockpropertytrackposition1 == null) {
/* 283 */       if (flag6 && flag7) {
/* 284 */         blockpropertytrackposition1 = blockpropertytrackposition;
/* 285 */       } else if (flag6) {
/* 286 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_SOUTH;
/* 287 */       } else if (flag7) {
/* 288 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.EAST_WEST;
/*     */       } 
/*     */       
/* 291 */       if (!this.e) {
/* 292 */         if (flag) {
/* 293 */           if (flag8) {
/* 294 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_EAST;
/*     */           }
/*     */           
/* 297 */           if (flag9) {
/* 298 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_WEST;
/*     */           }
/*     */           
/* 301 */           if (flag10) {
/* 302 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_EAST;
/*     */           }
/*     */           
/* 305 */           if (flag11) {
/* 306 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_WEST;
/*     */           }
/*     */         } else {
/* 309 */           if (flag11) {
/* 310 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_WEST;
/*     */           }
/*     */           
/* 313 */           if (flag10) {
/* 314 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.NORTH_EAST;
/*     */           }
/*     */           
/* 317 */           if (flag9) {
/* 318 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_WEST;
/*     */           }
/*     */           
/* 321 */           if (flag8) {
/* 322 */             blockpropertytrackposition1 = BlockPropertyTrackPosition.SOUTH_EAST;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 328 */     if (blockpropertytrackposition1 == BlockPropertyTrackPosition.NORTH_SOUTH) {
/* 329 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition.up())) {
/* 330 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.ASCENDING_NORTH;
/*     */       }
/*     */       
/* 333 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition1.up())) {
/* 334 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.ASCENDING_SOUTH;
/*     */       }
/*     */     } 
/*     */     
/* 338 */     if (blockpropertytrackposition1 == BlockPropertyTrackPosition.EAST_WEST) {
/* 339 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition3.up())) {
/* 340 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.ASCENDING_EAST;
/*     */       }
/*     */       
/* 343 */       if (BlockMinecartTrackAbstract.a(this.a, blockposition2.up())) {
/* 344 */         blockpropertytrackposition1 = BlockPropertyTrackPosition.ASCENDING_WEST;
/*     */       }
/*     */     } 
/*     */     
/* 348 */     if (blockpropertytrackposition1 == null) {
/* 349 */       blockpropertytrackposition1 = blockpropertytrackposition;
/*     */     }
/*     */     
/* 352 */     a(blockpropertytrackposition1);
/* 353 */     this.d = this.d.set(this.c.d(), blockpropertytrackposition1);
/* 354 */     if (flag1 || this.a.getType(this.b) != this.d) {
/* 355 */       this.a.setTypeAndData(this.b, this.d, 3);
/*     */       
/* 357 */       if (!isValid()) {
/* 358 */         return this;
/*     */       }
/*     */ 
/*     */       
/* 362 */       for (int i = 0; i < this.f.size(); i++) {
/* 363 */         MinecartTrackLogic minecarttracklogic = b(this.f.get(i));
/*     */         
/* 365 */         if (minecarttracklogic != null && minecarttracklogic.isValid()) {
/* 366 */           minecarttracklogic.d();
/* 367 */           if (minecarttracklogic.b(this)) {
/* 368 */             minecarttracklogic.c(this);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 374 */     return this;
/*     */   }
/*     */   
/*     */   public IBlockData c() {
/* 378 */     return getWorld().getType(getPos());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecartTrackLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */