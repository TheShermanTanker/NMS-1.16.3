/*     */ package com.destroystokyo.paper.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.BlockRedstoneWire;
/*     */ import net.minecraft.server.v1_16_R2.IBlockAccess;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import net.minecraft.server.v1_16_R2.IMaterial;
/*     */ import net.minecraft.server.v1_16_R2.IWorldReader;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.Items;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
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
/*     */ public class RedstoneWireTurbo
/*     */ {
/*     */   private final BlockRedstoneWire wire;
/* 118 */   private List<UpdateNode> updateQueue0 = Lists.newArrayList();
/* 119 */   private List<UpdateNode> updateQueue1 = Lists.newArrayList();
/* 120 */   private List<UpdateNode> updateQueue2 = Lists.newArrayList();
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
/*     */   public static BlockPosition[] computeAllNeighbors(BlockPosition pos) {
/* 138 */     int x = pos.getX();
/* 139 */     int y = pos.getY();
/* 140 */     int z = pos.getZ();
/* 141 */     BlockPosition[] n = new BlockPosition[24];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     n[0] = new BlockPosition(x - 1, y, z);
/* 147 */     n[1] = new BlockPosition(x + 1, y, z);
/* 148 */     n[2] = new BlockPosition(x, y - 1, z);
/* 149 */     n[3] = new BlockPosition(x, y + 1, z);
/* 150 */     n[4] = new BlockPosition(x, y, z - 1);
/* 151 */     n[5] = new BlockPosition(x, y, z + 1);
/*     */ 
/*     */ 
/*     */     
/* 155 */     n[6] = new BlockPosition(x - 2, y, z);
/* 156 */     n[7] = new BlockPosition(x - 1, y - 1, z);
/* 157 */     n[8] = new BlockPosition(x - 1, y + 1, z);
/* 158 */     n[9] = new BlockPosition(x - 1, y, z - 1);
/* 159 */     n[10] = new BlockPosition(x - 1, y, z + 1);
/* 160 */     n[11] = new BlockPosition(x + 2, y, z);
/* 161 */     n[12] = new BlockPosition(x + 1, y - 1, z);
/* 162 */     n[13] = new BlockPosition(x + 1, y + 1, z);
/* 163 */     n[14] = new BlockPosition(x + 1, y, z - 1);
/* 164 */     n[15] = new BlockPosition(x + 1, y, z + 1);
/* 165 */     n[16] = new BlockPosition(x, y - 2, z);
/* 166 */     n[17] = new BlockPosition(x, y - 1, z - 1);
/* 167 */     n[18] = new BlockPosition(x, y - 1, z + 1);
/* 168 */     n[19] = new BlockPosition(x, y + 2, z);
/* 169 */     n[20] = new BlockPosition(x, y + 1, z - 1);
/* 170 */     n[21] = new BlockPosition(x, y + 1, z + 1);
/* 171 */     n[22] = new BlockPosition(x, y, z - 2);
/* 172 */     n[23] = new BlockPosition(x, y, z + 2);
/* 173 */     return n;
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
/* 186 */   private static final boolean[] update_redstone = new boolean[] { 
/*     */       true, true, false, false, true, true, false, true, true, false, 
/*     */       false, false, true, true, false, false, false, true, true, false, 
/*     */       true, true, false, false };
/*     */ 
/*     */   
/*     */   private static final int North = 0;
/*     */ 
/*     */   
/*     */   private static final int East = 1;
/*     */ 
/*     */   
/*     */   private static final int South = 2;
/*     */ 
/*     */   
/*     */   private static final int West = 3;
/*     */ 
/*     */   
/* 204 */   private static final int[] forward_is_north = new int[] { 2, 3, 16, 19, 0, 4, 1, 5, 7, 8, 17, 20, 12, 13, 18, 21, 6, 9, 22, 14, 11, 10, 23, 15 };
/* 205 */   private static final int[] forward_is_east = new int[] { 2, 3, 16, 19, 4, 1, 5, 0, 17, 20, 12, 13, 18, 21, 7, 8, 22, 14, 11, 15, 23, 9, 6, 10 };
/* 206 */   private static final int[] forward_is_south = new int[] { 2, 3, 16, 19, 1, 5, 0, 4, 12, 13, 18, 21, 7, 8, 17, 20, 11, 15, 23, 10, 6, 14, 22, 9 };
/* 207 */   private static final int[] forward_is_west = new int[] { 2, 3, 16, 19, 5, 0, 4, 1, 18, 21, 7, 8, 17, 20, 12, 13, 23, 10, 6, 9, 22, 15, 11, 14 };
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
/*     */ 
/*     */ 
/*     */   
/* 253 */   private static final int[][] reordering = new int[][] { forward_is_north, forward_is_east, forward_is_south, forward_is_west };
/*     */   
/*     */   private final Map<BlockPosition, UpdateNode> nodeCache;
/*     */   
/*     */   private static final boolean old_current_change = false;
/*     */   
/*     */   private int currentWalkLayer;
/*     */   
/*     */   private static void orientNeighbors(UpdateNode[] src, UpdateNode[] dst, int heading) {
/* 262 */     int[] re = reordering[heading];
/* 263 */     for (int i = 0; i < 24; i++)
/* 264 */       dst[i] = src[re[i]]; 
/*     */   }
/*     */   
/*     */   private static class UpdateNode { IBlockData currentState;
/*     */     UpdateNode[] neighbor_nodes;
/*     */     BlockPosition self;
/*     */     BlockPosition parent;
/*     */     
/*     */     private UpdateNode() {}
/*     */     
/* 274 */     public enum Type { UNKNOWN, REDSTONE, OTHER; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     Type type = Type.UNKNOWN; int layer; boolean visited; int xbias; int zbias; } public enum Type {
/*     */     UNKNOWN, REDSTONE, OTHER;
/*     */   } private void identifyNode(World worldIn, UpdateNode upd1) { BlockPosition pos = upd1.self; IBlockData oldState = worldIn.getType(pos); upd1.currentState = oldState; Block block = oldState.getBlock(); if (block != this.wire) {
/*     */       upd1.type = UpdateNode.Type.OTHER; return;
/*     */     }  if (!this.wire.canPlace(null, (IWorldReader)worldIn, pos)) {
/*     */       Block.a(worldIn, pos, new ItemStack((IMaterial)Items.REDSTONE));
/*     */       worldIn.setAir(pos);
/*     */       upd1.type = UpdateNode.Type.OTHER;
/*     */       return;
/*     */     } 
/* 291 */     upd1.type = UpdateNode.Type.REDSTONE; } public RedstoneWireTurbo(BlockRedstoneWire wire) { this.nodeCache = Maps.newHashMap();
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
/* 605 */     this.currentWalkLayer = 0; this.wire = wire; }
/*     */   private static int computeHeading(int rx, int rz) { int j, code = rx + 1 + 3 * (rz + 1); switch (code) { case 0: j = ThreadLocalRandom.current().nextInt(0, 1); return (j == 0) ? 0 : 3;case 1: return 0;case 2: j = ThreadLocalRandom.current().nextInt(0, 1); return (j == 0) ? 0 : 1;case 3: return 3;case 4: return ThreadLocalRandom.current().nextInt(0, 4);case 5: return 1;case 6: j = ThreadLocalRandom.current().nextInt(0, 1); return (j == 0) ? 2 : 3;
/*     */       case 7: return 2;
/* 608 */       case 8: j = ThreadLocalRandom.current().nextInt(0, 1); return (j == 0) ? 2 : 1; }  return ThreadLocalRandom.current().nextInt(0, 4); } private void shiftQueue() { List<UpdateNode> t = this.updateQueue0;
/* 609 */     t.clear();
/* 610 */     this.updateQueue0 = this.updateQueue1;
/* 611 */     this.updateQueue1 = this.updateQueue2;
/* 612 */     this.updateQueue2 = t; } private void updateNode(World worldIn, UpdateNode upd1, int layer) {
/*     */     BlockPosition pos = upd1.self;
/*     */     upd1.visited = true;
/*     */     IBlockData oldState = upd1.currentState;
/*     */     IBlockData newState = calculateCurrentChanges(worldIn, upd1);
/*     */     if (newState != oldState) {
/*     */       upd1.currentState = newState;
/*     */       propagateChanges(worldIn, upd1, layer);
/*     */     } 
/*     */   }
/* 622 */   private void breadthFirstWalk(World worldIn) { shiftQueue();
/* 623 */     this.currentWalkLayer = 1;
/*     */ 
/*     */     
/* 626 */     while (this.updateQueue0.size() > 0 || this.updateQueue1.size() > 0) {
/*     */       
/* 628 */       List<UpdateNode> thisLayer = this.updateQueue0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 633 */       for (UpdateNode upd : thisLayer) {
/* 634 */         if (upd.type == UpdateNode.Type.REDSTONE) {
/*     */ 
/*     */ 
/*     */           
/* 638 */           updateNode(worldIn, upd, this.currentWalkLayer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 651 */         worldIn.neighborChanged(upd.self, (Block)this.wire, upd.parent);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 656 */       shiftQueue();
/* 657 */       this.currentWalkLayer++;
/*     */     } 
/*     */     
/* 660 */     this.currentWalkLayer = 0; } private void findNeighbors(World worldIn, UpdateNode upd1) { int heading; BlockPosition pos = upd1.self; BlockPosition[] neighbors = computeAllNeighbors(pos); UpdateNode[] neighbor_nodes = new UpdateNode[24]; upd1.neighbor_nodes = new UpdateNode[24]; for (int i = 0; i < 24; i++) {
/*     */       BlockPosition pos2 = neighbors[i]; UpdateNode upd2 = this.nodeCache.get(pos2); if (upd2 == null) {
/*     */         upd2 = new UpdateNode(); upd2.self = pos2; upd2.parent = pos; this.nodeCache.put(pos2, upd2); identifyNode(worldIn, upd2);
/*     */       }  if (update_redstone[i] || upd2.type != UpdateNode.Type.REDSTONE)
/*     */         neighbor_nodes[i] = upd2; 
/*     */     }  boolean fromWest = ((neighbor_nodes[0]).visited || (neighbor_nodes[7]).visited || (neighbor_nodes[8]).visited); boolean fromEast = ((neighbor_nodes[1]).visited || (neighbor_nodes[12]).visited || (neighbor_nodes[13]).visited); boolean fromNorth = ((neighbor_nodes[4]).visited || (neighbor_nodes[17]).visited || (neighbor_nodes[20]).visited); boolean fromSouth = ((neighbor_nodes[5]).visited || (neighbor_nodes[18]).visited || (neighbor_nodes[21]).visited); int cx = 0, cz = 0; if (fromWest)
/*     */       cx++;  if (fromEast)
/*     */       cx--;  if (fromNorth)
/*     */       cz++;  if (fromSouth)
/*     */       cz--;  if (cx == 0 && cz == 0) {
/*     */       heading = computeHeading(upd1.xbias, upd1.zbias); for (int j = 0; j < 24; j++) {
/*     */         UpdateNode nn = neighbor_nodes[j]; if (nn != null) {
/*     */           nn.xbias = upd1.xbias; nn.zbias = upd1.zbias;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       if (cx != 0 && cz != 0) {
/*     */         if (upd1.xbias != 0)
/*     */           cz = 0;  if (upd1.zbias != 0)
/*     */           cx = 0; 
/*     */       }  heading = computeHeading(cx, cz); for (int j = 0; j < 24; j++) {
/*     */         UpdateNode nn = neighbor_nodes[j];
/*     */         if (nn != null) {
/*     */           nn.xbias = cx;
/*     */           nn.zbias = cz;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     orientNeighbors(neighbor_nodes, upd1.neighbor_nodes, heading); }
/* 689 */   private IBlockData scheduleReentrantNeighborChanged(World worldIn, BlockPosition pos, IBlockData newState, BlockPosition source) { if (source != null) {
/*     */ 
/*     */       
/* 692 */       UpdateNode src = this.nodeCache.get(source);
/* 693 */       if (src == null) {
/* 694 */         src = new UpdateNode();
/* 695 */         src.self = source;
/* 696 */         src.parent = source;
/* 697 */         src.visited = true;
/* 698 */         identifyNode(worldIn, src);
/* 699 */         this.nodeCache.put(source, src);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 704 */     UpdateNode upd = this.nodeCache.get(pos);
/* 705 */     if (upd == null) {
/* 706 */       upd = new UpdateNode();
/* 707 */       upd.self = pos;
/* 708 */       upd.parent = pos;
/* 709 */       upd.visited = true;
/* 710 */       identifyNode(worldIn, upd);
/* 711 */       this.nodeCache.put(pos, upd);
/*     */     } 
/* 713 */     upd.currentState = newState;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 718 */     if (upd.neighbor_nodes != null) {
/* 719 */       for (int i = 0; i < 24; i++) {
/* 720 */         UpdateNode upd2 = upd.neighbor_nodes[i];
/* 721 */         if (upd2 != null) {
/* 722 */           upd2.type = UpdateNode.Type.UNKNOWN;
/* 723 */           upd2.currentState = null;
/* 724 */           identifyNode(worldIn, upd2);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 733 */     propagateChanges(worldIn, upd, this.currentWalkLayer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 739 */     return newState; } private void propagateChanges(World worldIn, UpdateNode upd1, int layer) { if (upd1.neighbor_nodes == null)
/*     */       findNeighbors(worldIn, upd1);  BlockPosition pos = upd1.self; int layer1 = layer + 1; for (int i = 0; i < 24; i++) {
/*     */       UpdateNode upd2 = upd1.neighbor_nodes[i]; if (upd2 != null && layer1 > upd2.layer) {
/*     */         upd2.layer = layer1; this.updateQueue1.add(upd2); upd2.parent = pos;
/*     */       } 
/*     */     }  int layer2 = layer + 2; for (int j = 0; j < 4; j++) {
/*     */       UpdateNode upd2 = upd1.neighbor_nodes[j];
/*     */       if (upd2 != null && layer2 > upd2.layer) {
/*     */         upd2.layer = layer2;
/*     */         this.updateQueue2.add(upd2);
/*     */         upd2.parent = pos;
/*     */       } 
/*     */     }  }
/* 752 */   public IBlockData updateSurroundingRedstone(World worldIn, BlockPosition pos, IBlockData state, BlockPosition source) { IBlockData newState = this.wire.calculateCurrentChanges(worldIn, pos, pos, state);
/*     */ 
/*     */     
/* 755 */     if (newState == state) {
/* 756 */       return state;
/*     */     }
/*     */ 
/*     */     
/* 760 */     if (this.currentWalkLayer > 0 || this.nodeCache.size() > 0)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 765 */       return scheduleReentrantNeighborChanged(worldIn, pos, newState, source);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 771 */     if (source != null) {
/* 772 */       UpdateNode src = new UpdateNode();
/* 773 */       src.self = source;
/* 774 */       src.parent = source;
/* 775 */       src.visited = true;
/* 776 */       this.nodeCache.put(source, src);
/* 777 */       identifyNode(worldIn, src);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 783 */     UpdateNode upd = new UpdateNode();
/* 784 */     upd.self = pos;
/* 785 */     upd.parent = (source != null) ? source : pos;
/* 786 */     upd.currentState = newState;
/* 787 */     upd.type = UpdateNode.Type.REDSTONE;
/* 788 */     upd.visited = true;
/* 789 */     this.nodeCache.put(pos, upd);
/* 790 */     propagateChanges(worldIn, upd, 0);
/*     */ 
/*     */ 
/*     */     
/* 794 */     breadthFirstWalk(worldIn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 800 */     this.nodeCache.clear();
/*     */     
/* 802 */     return newState; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 807 */   private static final int[] rs_neighbors = new int[] { 4, 5, 6, 7 };
/* 808 */   private static final int[] rs_neighbors_up = new int[] { 9, 11, 13, 15 };
/* 809 */   private static final int[] rs_neighbors_dn = new int[] { 8, 10, 12, 14 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IBlockData calculateCurrentChanges(World worldIn, UpdateNode upd) {
/* 817 */     IBlockData state = upd.currentState;
/* 818 */     int i = ((Integer)state.get((IBlockState)BlockRedstoneWire.POWER)).intValue();
/* 819 */     int j = 0;
/* 820 */     j = getMaxCurrentStrength(upd, j);
/* 821 */     int l = 0;
/*     */     
/* 823 */     this.wire.setCanProvidePower(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 829 */     int k = worldIn.isBlockIndirectlyGettingPowered(upd.self);
/* 830 */     this.wire.setCanProvidePower(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 837 */     if (k < 15) {
/* 838 */       if (upd.neighbor_nodes == null)
/*     */       {
/* 840 */         findNeighbors(worldIn, upd);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 847 */       UpdateNode center_up = upd.neighbor_nodes[1];
/* 848 */       boolean center_up_is_cube = center_up.currentState.isOccluding((IBlockAccess)worldIn, center_up.self);
/*     */       
/* 850 */       for (int m = 0; m < 4; m++) {
/*     */ 
/*     */         
/* 853 */         int n = rs_neighbors[m];
/*     */ 
/*     */ 
/*     */         
/* 857 */         UpdateNode neighbor = upd.neighbor_nodes[n];
/* 858 */         l = getMaxCurrentStrength(neighbor, l);
/*     */ 
/*     */ 
/*     */         
/* 862 */         boolean neighbor_is_cube = neighbor.currentState.isOccluding((IBlockAccess)worldIn, neighbor.self);
/* 863 */         if (!neighbor_is_cube) {
/* 864 */           UpdateNode neighbor_down = upd.neighbor_nodes[rs_neighbors_dn[m]];
/* 865 */           l = getMaxCurrentStrength(neighbor_down, l);
/*     */         }
/* 867 */         else if (!center_up_is_cube) {
/* 868 */           UpdateNode neighbor_up = upd.neighbor_nodes[rs_neighbors_up[m]];
/* 869 */           l = getMaxCurrentStrength(neighbor_up, l);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 877 */     j = l - 1;
/*     */ 
/*     */ 
/*     */     
/* 881 */     if (k > j) j = k;
/*     */ 
/*     */ 
/*     */     
/* 885 */     if (i != j) {
/* 886 */       BlockRedstoneEvent event = new BlockRedstoneEvent(worldIn.getWorld().getBlockAt(upd.self.getX(), upd.self.getY(), upd.self.getZ()), i, j);
/* 887 */       worldIn.getServer().getPluginManager().callEvent((Event)event);
/* 888 */       j = event.getNewCurrent();
/*     */     } 
/*     */     
/* 891 */     if (i != j) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 896 */       BlockPosition pos = new BlockPosition(upd.self.getX(), upd.self.getY(), upd.self.getZ());
/* 897 */       if (this.wire.canPlace(null, (IWorldReader)worldIn, pos)) {
/* 898 */         state = (IBlockData)state.set((IBlockState)BlockRedstoneWire.POWER, Integer.valueOf(j));
/* 899 */         worldIn.setTypeAndData(upd.self, state, 2);
/*     */       } 
/*     */     } 
/*     */     
/* 903 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getMaxCurrentStrength(UpdateNode upd, int strength) {
/* 911 */     if (upd.type != UpdateNode.Type.REDSTONE) return strength; 
/* 912 */     int i = ((Integer)upd.currentState.get((IBlockState)BlockRedstoneWire.POWER)).intValue();
/* 913 */     return (i > strength) ? i : strength;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\RedstoneWireTurbo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */