/*     */ package org.bukkit.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockIterator
/*     */   implements Iterator<Block>
/*     */ {
/*     */   private final World world;
/*     */   private final int maxDistance;
/*     */   private static final int gridSize = 16777216;
/*     */   private boolean end = false;
/*  25 */   private Block[] blockQueue = new Block[3];
/*  26 */   private int currentBlock = 0;
/*  27 */   private int currentDistance = 0;
/*     */ 
/*     */   
/*     */   private int maxDistanceInt;
/*     */ 
/*     */   
/*     */   private int secondError;
/*     */ 
/*     */   
/*     */   private int thirdError;
/*     */ 
/*     */   
/*     */   private int secondStep;
/*     */ 
/*     */   
/*     */   private int thirdStep;
/*     */ 
/*     */   
/*     */   private BlockFace mainFace;
/*     */ 
/*     */   
/*     */   private BlockFace secondFace;
/*     */ 
/*     */   
/*     */   private BlockFace thirdFace;
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockIterator(@NotNull World world, @NotNull Vector start, @NotNull Vector direction, double yOffset, int maxDistance) {
/*  56 */     this.world = world;
/*  57 */     this.maxDistance = maxDistance;
/*     */     
/*  59 */     Vector startClone = start.clone();
/*     */     
/*  61 */     startClone.setY(startClone.getY() + yOffset);
/*     */     
/*  63 */     this.currentDistance = 0;
/*     */     
/*  65 */     double mainDirection = 0.0D;
/*  66 */     double secondDirection = 0.0D;
/*  67 */     double thirdDirection = 0.0D;
/*     */     
/*  69 */     double mainPosition = 0.0D;
/*  70 */     double secondPosition = 0.0D;
/*  71 */     double thirdPosition = 0.0D;
/*     */     
/*  73 */     Block startBlock = this.world.getBlockAt(NumberConversions.floor(startClone.getX()), NumberConversions.floor(startClone.getY()), NumberConversions.floor(startClone.getZ()));
/*     */     
/*  75 */     if (getXLength(direction) > mainDirection) {
/*  76 */       this.mainFace = getXFace(direction);
/*  77 */       mainDirection = getXLength(direction);
/*  78 */       mainPosition = getXPosition(direction, startClone, startBlock);
/*     */       
/*  80 */       this.secondFace = getYFace(direction);
/*  81 */       secondDirection = getYLength(direction);
/*  82 */       secondPosition = getYPosition(direction, startClone, startBlock);
/*     */       
/*  84 */       this.thirdFace = getZFace(direction);
/*  85 */       thirdDirection = getZLength(direction);
/*  86 */       thirdPosition = getZPosition(direction, startClone, startBlock);
/*     */     } 
/*  88 */     if (getYLength(direction) > mainDirection) {
/*  89 */       this.mainFace = getYFace(direction);
/*  90 */       mainDirection = getYLength(direction);
/*  91 */       mainPosition = getYPosition(direction, startClone, startBlock);
/*     */       
/*  93 */       this.secondFace = getZFace(direction);
/*  94 */       secondDirection = getZLength(direction);
/*  95 */       secondPosition = getZPosition(direction, startClone, startBlock);
/*     */       
/*  97 */       this.thirdFace = getXFace(direction);
/*  98 */       thirdDirection = getXLength(direction);
/*  99 */       thirdPosition = getXPosition(direction, startClone, startBlock);
/*     */     } 
/* 101 */     if (getZLength(direction) > mainDirection) {
/* 102 */       this.mainFace = getZFace(direction);
/* 103 */       mainDirection = getZLength(direction);
/* 104 */       mainPosition = getZPosition(direction, startClone, startBlock);
/*     */       
/* 106 */       this.secondFace = getXFace(direction);
/* 107 */       secondDirection = getXLength(direction);
/* 108 */       secondPosition = getXPosition(direction, startClone, startBlock);
/*     */       
/* 110 */       this.thirdFace = getYFace(direction);
/* 111 */       thirdDirection = getYLength(direction);
/* 112 */       thirdPosition = getYPosition(direction, startClone, startBlock);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 117 */     double d = mainPosition / mainDirection;
/* 118 */     double secondd = secondPosition - secondDirection * d;
/* 119 */     double thirdd = thirdPosition - thirdDirection * d;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.secondError = NumberConversions.floor(secondd * 1.6777216E7D);
/* 125 */     this.secondStep = NumberConversions.round(secondDirection / mainDirection * 1.6777216E7D);
/* 126 */     this.thirdError = NumberConversions.floor(thirdd * 1.6777216E7D);
/* 127 */     this.thirdStep = NumberConversions.round(thirdDirection / mainDirection * 1.6777216E7D);
/*     */     
/* 129 */     if (this.secondError + this.secondStep <= 0) {
/* 130 */       this.secondError = -this.secondStep + 1;
/*     */     }
/*     */     
/* 133 */     if (this.thirdError + this.thirdStep <= 0) {
/* 134 */       this.thirdError = -this.thirdStep + 1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 139 */     Block lastBlock = startBlock.getRelative(this.mainFace.getOppositeFace());
/*     */     
/* 141 */     if (this.secondError < 0) {
/* 142 */       this.secondError += 16777216;
/* 143 */       lastBlock = lastBlock.getRelative(this.secondFace.getOppositeFace());
/*     */     } 
/*     */     
/* 146 */     if (this.thirdError < 0) {
/* 147 */       this.thirdError += 16777216;
/* 148 */       lastBlock = lastBlock.getRelative(this.thirdFace.getOppositeFace());
/*     */     } 
/*     */ 
/*     */     
/* 152 */     this.secondError -= 16777216;
/* 153 */     this.thirdError -= 16777216;
/*     */     
/* 155 */     this.blockQueue[0] = lastBlock;
/* 156 */     this.currentBlock = -1;
/*     */     
/* 158 */     scan();
/*     */     
/* 160 */     boolean startBlockFound = false;
/*     */     
/* 162 */     for (int cnt = this.currentBlock; cnt >= 0; cnt--) {
/* 163 */       if (blockEquals(this.blockQueue[cnt], startBlock)) {
/* 164 */         this.currentBlock = cnt;
/* 165 */         startBlockFound = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 170 */     if (!startBlockFound) {
/* 171 */       throw new IllegalStateException("Start block missed in BlockIterator");
/*     */     }
/*     */ 
/*     */     
/* 175 */     this.maxDistanceInt = NumberConversions.round(maxDistance / Math.sqrt(mainDirection * mainDirection + secondDirection * secondDirection + thirdDirection * thirdDirection) / mainDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean blockEquals(@NotNull Block a, @NotNull Block b) {
/* 180 */     return (a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ());
/*     */   }
/*     */   
/*     */   private BlockFace getXFace(@NotNull Vector direction) {
/* 184 */     return (direction.getX() > 0.0D) ? BlockFace.EAST : BlockFace.WEST;
/*     */   }
/*     */   
/*     */   private BlockFace getYFace(@NotNull Vector direction) {
/* 188 */     return (direction.getY() > 0.0D) ? BlockFace.UP : BlockFace.DOWN;
/*     */   }
/*     */   
/*     */   private BlockFace getZFace(@NotNull Vector direction) {
/* 192 */     return (direction.getZ() > 0.0D) ? BlockFace.SOUTH : BlockFace.NORTH;
/*     */   }
/*     */   
/*     */   private double getXLength(@NotNull Vector direction) {
/* 196 */     return Math.abs(direction.getX());
/*     */   }
/*     */   
/*     */   private double getYLength(@NotNull Vector direction) {
/* 200 */     return Math.abs(direction.getY());
/*     */   }
/*     */   
/*     */   private double getZLength(@NotNull Vector direction) {
/* 204 */     return Math.abs(direction.getZ());
/*     */   }
/*     */   
/*     */   private double getPosition(double direction, double position, int blockPosition) {
/* 208 */     return (direction > 0.0D) ? (position - blockPosition) : ((blockPosition + 1) - position);
/*     */   }
/*     */   
/*     */   private double getXPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
/* 212 */     return getPosition(direction.getX(), position.getX(), block.getX());
/*     */   }
/*     */   
/*     */   private double getYPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
/* 216 */     return getPosition(direction.getY(), position.getY(), block.getY());
/*     */   }
/*     */   
/*     */   private double getZPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
/* 220 */     return getPosition(direction.getZ(), position.getZ(), block.getZ());
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
/*     */   public BlockIterator(@NotNull Location loc, double yOffset, int maxDistance) {
/* 236 */     this(loc.getWorld(), loc.toVector(), loc.getDirection(), yOffset, maxDistance);
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
/*     */   public BlockIterator(@NotNull Location loc, double yOffset) {
/* 250 */     this(loc.getWorld(), loc.toVector(), loc.getDirection(), yOffset, 0);
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
/*     */   public BlockIterator(@NotNull Location loc) {
/* 262 */     this(loc, 0.0D);
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
/*     */   public BlockIterator(@NotNull LivingEntity entity, int maxDistance) {
/* 277 */     this(entity.getLocation(), entity.getEyeHeight(), maxDistance);
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
/*     */   public BlockIterator(@NotNull LivingEntity entity) {
/* 289 */     this(entity, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 298 */     scan();
/* 299 */     return (this.currentBlock != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block next() throws NoSuchElementException {
/* 310 */     scan();
/* 311 */     if (this.currentBlock <= -1) {
/* 312 */       throw new NoSuchElementException();
/*     */     }
/* 314 */     return this.blockQueue[this.currentBlock--];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 320 */     throw new UnsupportedOperationException("[BlockIterator] doesn't support block removal");
/*     */   }
/*     */   
/*     */   private void scan() {
/* 324 */     if (this.currentBlock >= 0) {
/*     */       return;
/*     */     }
/* 327 */     if (this.maxDistance != 0 && this.currentDistance > this.maxDistanceInt) {
/* 328 */       this.end = true;
/*     */       return;
/*     */     } 
/* 331 */     if (this.end) {
/*     */       return;
/*     */     }
/*     */     
/* 335 */     this.currentDistance++;
/*     */     
/* 337 */     this.secondError += this.secondStep;
/* 338 */     this.thirdError += this.thirdStep;
/*     */     
/* 340 */     if (this.secondError > 0 && this.thirdError > 0) {
/* 341 */       this.blockQueue[2] = this.blockQueue[0].getRelative(this.mainFace);
/* 342 */       if (this.secondStep * this.thirdError < this.thirdStep * this.secondError) {
/* 343 */         this.blockQueue[1] = this.blockQueue[2].getRelative(this.secondFace);
/* 344 */         this.blockQueue[0] = this.blockQueue[1].getRelative(this.thirdFace);
/*     */       } else {
/* 346 */         this.blockQueue[1] = this.blockQueue[2].getRelative(this.thirdFace);
/* 347 */         this.blockQueue[0] = this.blockQueue[1].getRelative(this.secondFace);
/*     */       } 
/* 349 */       this.thirdError -= 16777216;
/* 350 */       this.secondError -= 16777216;
/* 351 */       this.currentBlock = 2; return;
/*     */     } 
/* 353 */     if (this.secondError > 0) {
/* 354 */       this.blockQueue[1] = this.blockQueue[0].getRelative(this.mainFace);
/* 355 */       this.blockQueue[0] = this.blockQueue[1].getRelative(this.secondFace);
/* 356 */       this.secondError -= 16777216;
/* 357 */       this.currentBlock = 1; return;
/*     */     } 
/* 359 */     if (this.thirdError > 0) {
/* 360 */       this.blockQueue[1] = this.blockQueue[0].getRelative(this.mainFace);
/* 361 */       this.blockQueue[0] = this.blockQueue[1].getRelative(this.thirdFace);
/* 362 */       this.thirdError -= 16777216;
/* 363 */       this.currentBlock = 1;
/*     */       return;
/*     */     } 
/* 366 */     this.blockQueue[0] = this.blockQueue[0].getRelative(this.mainFace);
/* 367 */     this.currentBlock = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\BlockIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */