/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.command.CraftBlockCommandSender;
/*     */ 
/*     */ public class TileEntityCommand extends TileEntity {
/*     */   private boolean a;
/*     */   private boolean b;
/*     */   
/*  11 */   private final CommandBlockListenerAbstract h = new CommandBlockListenerAbstract()
/*     */     {
/*     */       public CommandSender getBukkitSender(CommandListenerWrapper wrapper)
/*     */       {
/*  15 */         return (CommandSender)new CraftBlockCommandSender(wrapper, TileEntityCommand.this);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void setCommand(String s) {
/*  21 */         super.setCommand(s);
/*  22 */         TileEntityCommand.this.update();
/*     */       }
/*     */ 
/*     */       
/*     */       public WorldServer d() {
/*  27 */         return (WorldServer)TileEntityCommand.this.world;
/*     */       }
/*     */ 
/*     */       
/*     */       public void e() {
/*  32 */         IBlockData iblockdata = TileEntityCommand.this.world.getType(TileEntityCommand.this.position);
/*     */         
/*  34 */         d().notify(TileEntityCommand.this.position, iblockdata, iblockdata, 3);
/*     */       }
/*     */ 
/*     */       
/*     */       public CommandListenerWrapper getWrapper() {
/*  39 */         return new CommandListenerWrapper(this, Vec3D.a(TileEntityCommand.this.position), Vec2F.a, d(), 2, getName().getString(), getName(), d().getMinecraftServer(), (Entity)null);
/*     */       }
/*     */     };
/*     */   private boolean c; private boolean g;
/*     */   public TileEntityCommand() {
/*  44 */     super(TileEntityTypes.COMMAND_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  49 */     super.save(nbttagcompound);
/*  50 */     this.h.a(nbttagcompound);
/*  51 */     nbttagcompound.setBoolean("powered", f());
/*  52 */     nbttagcompound.setBoolean("conditionMet", j());
/*  53 */     nbttagcompound.setBoolean("auto", g());
/*  54 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  59 */     super.load(iblockdata, nbttagcompound);
/*  60 */     this.h.b(nbttagcompound);
/*  61 */     this.a = nbttagcompound.getBoolean("powered");
/*  62 */     this.c = nbttagcompound.getBoolean("conditionMet");
/*  63 */     b(nbttagcompound.getBoolean("auto"));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/*  69 */     if (l()) {
/*  70 */       c(false);
/*  71 */       NBTTagCompound nbttagcompound = save(new NBTTagCompound());
/*     */       
/*  73 */       return new PacketPlayOutTileEntityData(this.position, 2, nbttagcompound);
/*     */     } 
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFilteredNBT() {
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   public CommandBlockListenerAbstract getCommandBlock() {
/*  85 */     return this.h;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/*  89 */     this.a = flag;
/*     */   }
/*     */   
/*     */   public boolean f() {
/*  93 */     return this.a;
/*     */   }
/*     */   
/*     */   public boolean g() {
/*  97 */     return this.b;
/*     */   }
/*     */   
/*     */   public void b(boolean flag) {
/* 101 */     boolean flag1 = this.b;
/*     */     
/* 103 */     this.b = flag;
/* 104 */     if (!flag1 && flag && !this.a && this.world != null && m() != Type.SEQUENCE) {
/* 105 */       y();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void h() {
/* 111 */     Type tileentitycommand_type = m();
/*     */     
/* 113 */     if (tileentitycommand_type == Type.AUTO && (this.a || this.b) && this.world != null) {
/* 114 */       y();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void y() {
/* 120 */     Block block = getBlock().getBlock();
/*     */     
/* 122 */     if (block instanceof BlockCommand) {
/* 123 */       k();
/* 124 */       this.world.getBlockTickList().a(this.position, block, 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 130 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 134 */     this.c = true;
/* 135 */     if (x()) {
/* 136 */       BlockPosition blockposition = this.position.shift(((EnumDirection)this.world.getType(this.position).get(BlockCommand.a)).opposite());
/*     */       
/* 138 */       if (this.world.getType(blockposition).getBlock() instanceof BlockCommand) {
/* 139 */         TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */         
/* 141 */         this.c = (tileentity instanceof TileEntityCommand && ((TileEntityCommand)tileentity).getCommandBlock().i() > 0);
/*     */       } else {
/* 143 */         this.c = false;
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean l() {
/* 151 */     return this.g;
/*     */   }
/*     */   
/*     */   public void c(boolean flag) {
/* 155 */     this.g = flag;
/*     */   }
/*     */   
/*     */   public Type m() {
/* 159 */     IBlockData iblockdata = getBlock();
/*     */     
/* 161 */     return iblockdata.a(Blocks.COMMAND_BLOCK) ? Type.REDSTONE : (iblockdata.a(Blocks.REPEATING_COMMAND_BLOCK) ? Type.AUTO : (iblockdata.a(Blocks.CHAIN_COMMAND_BLOCK) ? Type.SEQUENCE : Type.REDSTONE));
/*     */   }
/*     */   
/*     */   public boolean x() {
/* 165 */     IBlockData iblockdata = this.world.getType(getPosition());
/*     */     
/* 167 */     return (iblockdata.getBlock() instanceof BlockCommand) ? ((Boolean)iblockdata.get(BlockCommand.b)).booleanValue() : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void r() {
/* 172 */     invalidateBlockCache();
/* 173 */     super.r();
/*     */   }
/*     */   
/*     */   public enum Type
/*     */   {
/* 178 */     SEQUENCE, AUTO, REDSTONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */