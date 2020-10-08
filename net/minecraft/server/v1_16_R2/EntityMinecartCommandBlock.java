/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class EntityMinecartCommandBlock extends EntityMinecartAbstract {
/*   5 */   public static final DataWatcherObject<String> COMMAND = DataWatcher.a((Class)EntityMinecartCommandBlock.class, DataWatcherRegistry.d);
/*   6 */   private static final DataWatcherObject<IChatBaseComponent> c = DataWatcher.a((Class)EntityMinecartCommandBlock.class, DataWatcherRegistry.e);
/*   7 */   private final CommandBlockListenerAbstract d = new a();
/*     */   private int e;
/*     */   
/*     */   public EntityMinecartCommandBlock(EntityTypes<? extends EntityMinecartCommandBlock> entitytypes, World world) {
/*  11 */     super(entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityMinecartCommandBlock(World world, double d0, double d1, double d2) {
/*  15 */     super(EntityTypes.COMMAND_BLOCK_MINECART, world, d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  20 */     super.initDatawatcher();
/*  21 */     getDataWatcher().register(COMMAND, "");
/*  22 */     getDataWatcher().register(c, ChatComponentText.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/*  27 */     super.loadData(nbttagcompound);
/*  28 */     this.d.b(nbttagcompound);
/*  29 */     getDataWatcher().set(COMMAND, getCommandBlock().getCommand());
/*  30 */     getDataWatcher().set(c, getCommandBlock().j());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/*  35 */     super.saveData(nbttagcompound);
/*  36 */     this.d.a(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/*  41 */     return EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData q() {
/*  46 */     return Blocks.COMMAND_BLOCK.getBlockData();
/*     */   }
/*     */   
/*     */   public CommandBlockListenerAbstract getCommandBlock() {
/*  50 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, int j, int k, boolean flag) {
/*  55 */     if (flag && this.ticksLived - this.e >= 4) {
/*  56 */       getCommandBlock().a(this.world);
/*  57 */       this.e = this.ticksLived;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/*  64 */     return this.d.a(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  69 */     super.a(datawatcherobject);
/*  70 */     if (c.equals(datawatcherobject)) {
/*     */       try {
/*  72 */         this.d.b(getDataWatcher().<IChatBaseComponent>get(c));
/*  73 */       } catch (Throwable throwable) {}
/*     */     
/*     */     }
/*  76 */     else if (COMMAND.equals(datawatcherobject)) {
/*  77 */       this.d.setCommand(getDataWatcher().<String>get(COMMAND));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ci() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class a
/*     */     extends CommandBlockListenerAbstract
/*     */   {
/*     */     public WorldServer d() {
/*  93 */       return (WorldServer)EntityMinecartCommandBlock.this.world;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/*  98 */       EntityMinecartCommandBlock.this.getDataWatcher().set(EntityMinecartCommandBlock.COMMAND, getCommand());
/*  99 */       EntityMinecartCommandBlock.this.getDataWatcher().set(EntityMinecartCommandBlock.c, j());
/*     */     }
/*     */ 
/*     */     
/*     */     public CommandListenerWrapper getWrapper() {
/* 104 */       return new CommandListenerWrapper(this, EntityMinecartCommandBlock.this.getPositionVector(), EntityMinecartCommandBlock.this.bh(), d(), 2, getName().getString(), EntityMinecartCommandBlock.this.getScoreboardDisplayName(), d().getMinecraftServer(), EntityMinecartCommandBlock.this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 110 */       return (CommandSender)EntityMinecartCommandBlock.this.getBukkitEntity();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */