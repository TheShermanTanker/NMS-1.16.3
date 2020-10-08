/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockCanBuildEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ 
/*     */ public class ItemBlock extends Item {
/*     */   public ItemBlock(Block block, Item.Info item_info) {
/*  18 */     super(item_info);
/*  19 */     this.a = block;
/*     */   }
/*     */   @Deprecated
/*     */   private final Block a;
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*  24 */     EnumInteractionResult enuminteractionresult = a(new BlockActionContext(itemactioncontext));
/*     */     
/*  26 */     return (!enuminteractionresult.a() && isFood()) ? a(itemactioncontext.getWorld(), itemactioncontext.getEntity(), itemactioncontext.getHand()).a() : enuminteractionresult;
/*     */   }
/*     */   public EnumInteractionResult a(BlockActionContext blockactioncontext) {
/*     */     CraftBlockState craftBlockState;
/*  30 */     if (!blockactioncontext.b()) {
/*  31 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  33 */     BlockActionContext blockactioncontext1 = b(blockactioncontext);
/*     */     
/*  35 */     if (blockactioncontext1 == null) {
/*  36 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  38 */     IBlockData iblockdata = c(blockactioncontext1);
/*     */     
/*  40 */     BlockState blockstate = null;
/*  41 */     if (this instanceof ItemWaterLily) {
/*  42 */       craftBlockState = CraftBlockState.getBlockState(blockactioncontext1.getWorld(), blockactioncontext1.getClickPosition());
/*     */     }
/*     */ 
/*     */     
/*  46 */     if (iblockdata == null)
/*  47 */       return EnumInteractionResult.FAIL; 
/*  48 */     if (!a(blockactioncontext1, iblockdata)) {
/*  49 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  51 */     BlockPosition blockposition = blockactioncontext1.getClickPosition();
/*  52 */     World world = blockactioncontext1.getWorld();
/*  53 */     EntityHuman entityhuman = blockactioncontext1.getEntity();
/*  54 */     ItemStack itemstack = blockactioncontext1.getItemStack();
/*  55 */     IBlockData iblockdata1 = world.getType(blockposition);
/*  56 */     Block block = iblockdata1.getBlock();
/*     */     
/*  58 */     if (block == iblockdata.getBlock()) {
/*  59 */       iblockdata1 = a(blockposition, world, itemstack, iblockdata1);
/*  60 */       a(blockposition, world, entityhuman, itemstack, iblockdata1);
/*  61 */       block.postPlace(world, blockposition, iblockdata1, entityhuman, itemstack);
/*     */       
/*  63 */       if (craftBlockState != null) {
/*  64 */         BlockPlaceEvent placeEvent = CraftEventFactory.callBlockPlaceEvent((WorldServer)world, entityhuman, blockactioncontext1.getHand(), (BlockState)craftBlockState, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  65 */         if (placeEvent != null && (placeEvent.isCancelled() || !placeEvent.canBuild())) {
/*  66 */           craftBlockState.update(true, false);
/*  67 */           return EnumInteractionResult.FAIL;
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       if (entityhuman instanceof EntityPlayer) {
/*  72 */         CriterionTriggers.y.a((EntityPlayer)entityhuman, blockposition, itemstack);
/*     */       }
/*     */     } 
/*     */     
/*  76 */     SoundEffectType soundeffecttype = iblockdata1.getStepSound();
/*     */ 
/*     */     
/*  79 */     if ((entityhuman == null || !entityhuman.abilities.canInstantlyBuild) && itemstack != ItemStack.b) {
/*  80 */       itemstack.subtract(1);
/*     */     }
/*     */     
/*  83 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect a(IBlockData iblockdata) {
/*  90 */     return iblockdata.getStepSound().e();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockActionContext b(BlockActionContext blockactioncontext) {
/*  95 */     return blockactioncontext;
/*     */   }
/*     */   
/*     */   protected boolean a(BlockPosition blockposition, World world, @Nullable EntityHuman entityhuman, ItemStack itemstack, IBlockData iblockdata) {
/*  99 */     return a(world, entityhuman, blockposition, itemstack);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected IBlockData c(BlockActionContext blockactioncontext) {
/* 104 */     IBlockData iblockdata = getBlock().getPlacedState(blockactioncontext);
/*     */     
/* 106 */     return (iblockdata != null && b(blockactioncontext, iblockdata)) ? iblockdata : null;
/*     */   }
/*     */   
/*     */   private IBlockData a(BlockPosition blockposition, World world, ItemStack itemstack, IBlockData iblockdata) {
/* 110 */     IBlockData iblockdata1 = iblockdata;
/* 111 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 113 */     if (nbttagcompound != null) {
/* 114 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("BlockStateTag");
/*     */       
/* 116 */       iblockdata1 = getBlockState(iblockdata1, nbttagcompound1);
/*     */     } 
/*     */     
/* 119 */     if (iblockdata1 != iblockdata) {
/* 120 */       world.setTypeAndData(blockposition, iblockdata1, 2);
/*     */     }
/*     */     
/* 123 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   public static IBlockData getBlockState(IBlockData iblockdata, NBTTagCompound nbttagcompound1) {
/* 127 */     IBlockData iblockdata1 = iblockdata;
/*     */ 
/*     */     
/* 130 */     BlockStateList<Block, IBlockData> blockstatelist = iblockdata.getBlock().getStates();
/* 131 */     Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */     
/* 133 */     while (iterator.hasNext()) {
/* 134 */       String s = iterator.next();
/* 135 */       IBlockState<?> iblockstate = blockstatelist.a(s);
/*     */       
/* 137 */       if (iblockstate != null) {
/* 138 */         String s1 = nbttagcompound1.get(s).asString();
/*     */         
/* 140 */         iblockdata1 = a(iblockdata1, iblockstate, s1);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   private static <T extends Comparable<T>> IBlockData a(IBlockData iblockdata, IBlockState<T> iblockstate, String s) {
/* 148 */     return iblockstate.b(s).map(comparable -> iblockdata.set(iblockstate, comparable))
/*     */       
/* 150 */       .orElse(iblockdata);
/*     */   }
/*     */   
/*     */   protected boolean b(BlockActionContext blockactioncontext, IBlockData iblockdata) {
/* 154 */     EntityHuman entityhuman = blockactioncontext.getEntity();
/* 155 */     VoxelShapeCollision voxelshapecollision = (entityhuman == null) ? VoxelShapeCollision.a() : VoxelShapeCollision.a(entityhuman);
/*     */     
/* 157 */     World world = blockactioncontext.getWorld();
/* 158 */     boolean defaultReturn = ((!isCheckCollisions() || iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) && world.checkEntityCollision(iblockdata, entityhuman, voxelshapecollision, blockactioncontext.getClickPosition(), true));
/* 159 */     Player player = (blockactioncontext.getEntity() instanceof EntityPlayer) ? (Player)blockactioncontext.getEntity().getBukkitEntity() : null;
/*     */     
/* 161 */     BlockCanBuildEvent event = new BlockCanBuildEvent((Block)CraftBlock.at(blockactioncontext.getWorld(), blockactioncontext.getClickPosition()), player, (BlockData)CraftBlockData.fromData(iblockdata), defaultReturn);
/* 162 */     blockactioncontext.getWorld().getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 164 */     return event.isBuildable();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCheckCollisions() {
/* 169 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean a(BlockActionContext blockactioncontext, IBlockData iblockdata) {
/* 173 */     return blockactioncontext.getWorld().setTypeAndData(blockactioncontext.getClickPosition(), iblockdata, 11);
/*     */   }
/*     */   
/*     */   public static boolean a(World world, @Nullable EntityHuman entityhuman, BlockPosition blockposition, ItemStack itemstack) {
/* 177 */     MinecraftServer minecraftserver = world.getMinecraftServer();
/*     */     
/* 179 */     if (minecraftserver == null) {
/* 180 */       return false;
/*     */     }
/* 182 */     NBTTagCompound nbttagcompound = itemstack.b("BlockEntityTag");
/*     */     
/* 184 */     if (nbttagcompound != null) {
/* 185 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 187 */       if (tileentity != null) {
/* 188 */         if (!world.isClientSide && tileentity.isFilteredNBT() && (entityhuman == null || (!entityhuman.isCreativeAndOp() && (!entityhuman.abilities.canInstantlyBuild || !entityhuman.getBukkitEntity().hasPermission("minecraft.nbt.place"))))) {
/* 189 */           return false;
/*     */         }
/*     */         
/* 192 */         NBTTagCompound nbttagcompound1 = tileentity.save(new NBTTagCompound());
/* 193 */         NBTTagCompound nbttagcompound2 = nbttagcompound1.clone();
/*     */         
/* 195 */         nbttagcompound1.a(nbttagcompound);
/* 196 */         nbttagcompound1.setInt("x", blockposition.getX());
/* 197 */         nbttagcompound1.setInt("y", blockposition.getY());
/* 198 */         nbttagcompound1.setInt("z", blockposition.getZ());
/* 199 */         if (!nbttagcompound1.equals(nbttagcompound2)) {
/* 200 */           tileentity.load(world.getType(blockposition), nbttagcompound1);
/* 201 */           tileentity.update();
/* 202 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 213 */     return getBlock().i();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
/* 218 */     if (a(creativemodetab)) {
/* 219 */       getBlock().a(creativemodetab, nonnulllist);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 225 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(Map<Block, Item> map, Item item) {
/* 229 */     map.put(getBlock(), item);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */