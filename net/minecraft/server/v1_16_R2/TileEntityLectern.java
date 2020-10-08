/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.command.CraftBlockCommandSender;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class TileEntityLectern
/*     */   extends TileEntity
/*     */   implements Clearable, ITileInventory, ICommandListener
/*     */ {
/*  20 */   public final IInventory inventory = new LecternInventory();
/*     */   
/*     */   public class LecternInventory implements IInventory {
/*  23 */     public List<HumanEntity> transaction = new ArrayList<>();
/*  24 */     private int maxStack = 1;
/*     */ 
/*     */     
/*     */     public List<ItemStack> getContents() {
/*  28 */       return Arrays.asList(new ItemStack[] { TileEntityLectern.access$000(this.this$0) });
/*     */     }
/*     */ 
/*     */     
/*     */     public void onOpen(CraftHumanEntity who) {
/*  33 */       this.transaction.add(who);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onClose(CraftHumanEntity who) {
/*  38 */       this.transaction.remove(who);
/*     */     }
/*     */ 
/*     */     
/*     */     public List<HumanEntity> getViewers() {
/*  43 */       return this.transaction;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMaxStackSize(int i) {
/*  48 */       this.maxStack = i;
/*     */     }
/*     */ 
/*     */     
/*     */     public Location getLocation() {
/*  53 */       return new Location((World)TileEntityLectern.this.world.getWorld(), TileEntityLectern.this.position.getX(), TileEntityLectern.this.position.getY(), TileEntityLectern.this.position.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     public InventoryHolder getOwner() {
/*  58 */       return TileEntityLectern.this.getOwner();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSize() {
/*  64 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/*  69 */       return TileEntityLectern.this.book.isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getItem(int i) {
/*  74 */       return (i == 0) ? TileEntityLectern.this.book : ItemStack.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack splitStack(int i, int j) {
/*  79 */       if (i == 0) {
/*  80 */         ItemStack itemstack = TileEntityLectern.this.book.cloneAndSubtract(j);
/*     */         
/*  82 */         if (TileEntityLectern.this.book.isEmpty()) {
/*  83 */           TileEntityLectern.this.k();
/*     */         }
/*     */         
/*  86 */         return itemstack;
/*     */       } 
/*  88 */       return ItemStack.b;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack splitWithoutUpdate(int i) {
/*  94 */       if (i == 0) {
/*  95 */         ItemStack itemstack = TileEntityLectern.this.book;
/*     */         
/*  97 */         TileEntityLectern.this.book = ItemStack.b;
/*  98 */         TileEntityLectern.this.k();
/*  99 */         return itemstack;
/*     */       } 
/* 101 */       return ItemStack.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setItem(int i, ItemStack itemstack) {
/* 108 */       if (i == 0) {
/* 109 */         TileEntityLectern.this.setBook(itemstack);
/* 110 */         if (TileEntityLectern.this.getWorld() != null) {
/* 111 */           BlockLectern.setHasBook(TileEntityLectern.this.getWorld(), TileEntityLectern.this.getPosition(), TileEntityLectern.this.getBlock(), TileEntityLectern.this.hasBook());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 119 */       return this.maxStack;
/*     */     }
/*     */ 
/*     */     
/*     */     public void update() {
/* 124 */       TileEntityLectern.this.update();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(EntityHuman entityhuman) {
/* 129 */       return (TileEntityLectern.this.world.getTileEntity(TileEntityLectern.this.position) != TileEntityLectern.this) ? false : ((entityhuman.h(TileEntityLectern.this.position.getX() + 0.5D, TileEntityLectern.this.position.getY() + 0.5D, TileEntityLectern.this.position.getZ() + 0.5D) > 64.0D) ? false : TileEntityLectern.this.hasBook());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b(int i, ItemStack itemstack) {
/* 134 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {}
/*     */   }
/*     */   
/* 140 */   private final IContainerProperties containerProperties = new IContainerProperties()
/*     */     {
/*     */       public int getProperty(int i) {
/* 143 */         return (i == 0) ? TileEntityLectern.this.page : 0;
/*     */       }
/*     */ 
/*     */       
/*     */       public void setProperty(int i, int j) {
/* 148 */         if (i == 0) {
/* 149 */           TileEntityLectern.this.setPage(j);
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int a() {
/* 156 */         return 1;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private ItemStack book;
/*     */   
/*     */   public TileEntityLectern() {
/* 164 */     super(TileEntityTypes.LECTERN);
/* 165 */     this.book = ItemStack.b;
/*     */   }
/*     */   private int page; private int maxPage;
/*     */   public ItemStack getBook() {
/* 169 */     return this.book;
/*     */   }
/*     */   
/*     */   public boolean hasBook() {
/* 173 */     Item item = this.book.getItem();
/*     */     
/* 175 */     return (item == Items.WRITABLE_BOOK || item == Items.WRITTEN_BOOK);
/*     */   }
/*     */   
/*     */   public void setBook(ItemStack itemstack) {
/* 179 */     a(itemstack, (EntityHuman)null);
/*     */   }
/*     */   
/*     */   private void k() {
/* 183 */     this.page = 0;
/* 184 */     this.maxPage = 0;
/* 185 */     BlockLectern.setHasBook(getWorld(), getPosition(), getBlock(), false);
/*     */   }
/*     */   
/*     */   public void a(ItemStack itemstack, @Nullable EntityHuman entityhuman) {
/* 189 */     this.book = b(itemstack, entityhuman);
/* 190 */     this.page = 0;
/* 191 */     this.maxPage = ItemWrittenBook.g(this.book);
/* 192 */     update();
/*     */   }
/*     */   
/*     */   public void setPage(int i) {
/* 196 */     int j = MathHelper.clamp(i, 0, this.maxPage - 1);
/*     */     
/* 198 */     if (j != this.page) {
/* 199 */       this.page = j;
/* 200 */       update();
/* 201 */       if (this.world != null) BlockLectern.a(getWorld(), getPosition(), getBlock());
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getPage() {
/* 207 */     return this.page;
/*     */   }
/*     */   
/*     */   public int j() {
/* 211 */     float f = (this.maxPage > 1) ? (getPage() / (this.maxPage - 1.0F)) : 1.0F;
/*     */     
/* 213 */     return MathHelper.d(f * 14.0F) + (hasBook() ? 1 : 0);
/*     */   }
/*     */   
/*     */   private ItemStack b(ItemStack itemstack, @Nullable EntityHuman entityhuman) {
/* 217 */     if (this.world instanceof WorldServer && itemstack.getItem() == Items.WRITTEN_BOOK) {
/* 218 */       ItemWrittenBook.a(itemstack, a(entityhuman), entityhuman);
/*     */     }
/*     */     
/* 221 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 231 */     return (wrapper.getEntity() != null) ? wrapper.getEntity().getBukkitSender(wrapper) : (CommandSender)new CraftBlockCommandSender(wrapper, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSendSuccess() {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSendFailure() {
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldBroadcastCommands() {
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CommandListenerWrapper a(@Nullable EntityHuman entityhuman) {
/*     */     String s;
/*     */     Object object;
/* 254 */     if (entityhuman == null) {
/* 255 */       s = "Lectern";
/* 256 */       object = new ChatComponentText("Lectern");
/*     */     } else {
/* 258 */       s = entityhuman.getDisplayName().getString();
/* 259 */       object = entityhuman.getScoreboardDisplayName();
/*     */     } 
/*     */     
/* 262 */     Vec3D vec3d = Vec3D.a(this.position);
/*     */ 
/*     */     
/* 265 */     return new CommandListenerWrapper(this, vec3d, Vec2F.a, (WorldServer)this.world, 2, s, (IChatBaseComponent)object, this.world.getMinecraftServer(), entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFilteredNBT() {
/* 270 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 275 */     super.load(iblockdata, nbttagcompound);
/* 276 */     if (nbttagcompound.hasKeyOfType("Book", 10)) {
/* 277 */       this.book = b(ItemStack.a(nbttagcompound.getCompound("Book")), (EntityHuman)null);
/*     */     } else {
/* 279 */       this.book = ItemStack.b;
/*     */     } 
/*     */     
/* 282 */     this.maxPage = ItemWrittenBook.g(this.book);
/* 283 */     this.page = MathHelper.clamp(nbttagcompound.getInt("Page"), 0, this.maxPage - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 288 */     super.save(nbttagcompound);
/* 289 */     if (!getBook().isEmpty()) {
/* 290 */       nbttagcompound.set("Book", getBook().save(new NBTTagCompound()));
/* 291 */       nbttagcompound.setInt("Page", this.page);
/*     */     } 
/*     */     
/* 294 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 299 */     setBook(ItemStack.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 304 */     return new ContainerLectern(i, this.inventory, this.containerProperties, playerinventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 309 */     return new ChatMessage("container.lectern");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */