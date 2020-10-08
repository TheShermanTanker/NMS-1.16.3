/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Fluid;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*    */ 
/*    */ public class BlockStateListPopulator extends DummyGeneratorAccess {
/*    */   private final World world;
/*    */   private final LinkedHashMap<BlockPosition, CraftBlockState> list;
/*    */   
/*    */   public BlockStateListPopulator(World world) {
/* 19 */     this(world, new LinkedHashMap<>());
/*    */   }
/*    */   
/*    */   public BlockStateListPopulator(World world, LinkedHashMap<BlockPosition, CraftBlockState> list) {
/* 23 */     this.world = world;
/* 24 */     this.list = list;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getType(BlockPosition bp) {
/* 29 */     CraftBlockState state = this.list.get(bp);
/* 30 */     return (state != null) ? state.getHandle() : this.world.getType(bp);
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid getFluid(BlockPosition bp) {
/* 35 */     CraftBlockState state = this.list.get(bp);
/* 36 */     return (state != null) ? state.getHandle().getFluid() : this.world.getFluid(bp);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean setTypeAndData(BlockPosition position, IBlockData data, int flag) {
/* 44 */     this.list.remove(position);
/*    */     
/* 46 */     CraftBlockState state = CraftBlockState.getBlockState(this.world, position, flag);
/* 47 */     state.setData(data);
/* 48 */     this.list.put(position, state);
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public void updateList() {
/* 53 */     for (BlockState state : this.list.values()) {
/* 54 */       state.update(true);
/*    */     }
/*    */   }
/*    */   
/*    */   public Set<BlockPosition> getBlocks() {
/* 59 */     return this.list.keySet();
/*    */   }
/*    */   
/*    */   public List<CraftBlockState> getList() {
/* 63 */     return new ArrayList<>(this.list.values());
/*    */   }
/*    */   
/*    */   public World getWorld() {
/* 67 */     return this.world;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\BlockStateListPopulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */