/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public abstract class CommandBlockListenerAbstract implements ICommandListener {
/*  11 */   private static final SimpleDateFormat b = new SimpleDateFormat("HH:mm:ss");
/*  12 */   private static final IChatBaseComponent c = new ChatComponentText("@");
/*  13 */   private long lastExecution = -1L;
/*     */   private boolean updateLastExecution = true;
/*     */   private int successCount;
/*     */   private boolean trackOutput = true;
/*     */   @Nullable
/*     */   private IChatBaseComponent lastOutput;
/*  19 */   private String command = "";
/*     */ 
/*     */   
/*     */   private IChatBaseComponent customName;
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandBlockListenerAbstract() {
/*  27 */     this.customName = c;
/*     */   }
/*     */   
/*     */   public int i() {
/*  31 */     return this.successCount;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/*  35 */     this.successCount = i;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent j() {
/*  39 */     return (this.lastOutput == null) ? ChatComponentText.d : this.lastOutput;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/*  43 */     nbttagcompound.setString("Command", this.command);
/*  44 */     nbttagcompound.setInt("SuccessCount", this.successCount);
/*  45 */     nbttagcompound.setString("CustomName", IChatBaseComponent.ChatSerializer.a(this.customName));
/*  46 */     nbttagcompound.setBoolean("TrackOutput", this.trackOutput);
/*  47 */     if (this.lastOutput != null && this.trackOutput) {
/*  48 */       nbttagcompound.setString("LastOutput", IChatBaseComponent.ChatSerializer.a(this.lastOutput));
/*     */     }
/*     */     
/*  51 */     nbttagcompound.setBoolean("UpdateLastExecution", this.updateLastExecution);
/*  52 */     if (this.updateLastExecution && this.lastExecution > 0L) {
/*  53 */       nbttagcompound.setLong("LastExecution", this.lastExecution);
/*     */     }
/*     */     
/*  56 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  60 */     this.command = nbttagcompound.getString("Command");
/*  61 */     this.successCount = nbttagcompound.getInt("SuccessCount");
/*  62 */     if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/*  63 */       setName(MCUtil.getBaseComponentFromNbt("CustomName", nbttagcompound));
/*     */     }
/*     */     
/*  66 */     if (nbttagcompound.hasKeyOfType("TrackOutput", 1)) {
/*  67 */       this.trackOutput = nbttagcompound.getBoolean("TrackOutput");
/*     */     }
/*     */     
/*  70 */     if (nbttagcompound.hasKeyOfType("LastOutput", 8) && this.trackOutput) {
/*     */       try {
/*  72 */         this.lastOutput = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("LastOutput"));
/*  73 */       } catch (Throwable throwable) {
/*  74 */         this.lastOutput = new ChatComponentText(throwable.getMessage());
/*     */       } 
/*     */     } else {
/*  77 */       this.lastOutput = null;
/*     */     } 
/*     */     
/*  80 */     if (nbttagcompound.hasKey("UpdateLastExecution")) {
/*  81 */       this.updateLastExecution = nbttagcompound.getBoolean("UpdateLastExecution");
/*     */     }
/*     */     
/*  84 */     if (this.updateLastExecution && nbttagcompound.hasKey("LastExecution")) {
/*  85 */       this.lastExecution = nbttagcompound.getLong("LastExecution");
/*     */     } else {
/*  87 */       this.lastExecution = -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCommand(String s) {
/*  93 */     this.command = s;
/*  94 */     this.successCount = 0;
/*     */   }
/*     */   
/*     */   public String getCommand() {
/*  98 */     return this.command;
/*     */   }
/*     */   
/*     */   public boolean a(World world) {
/* 102 */     if (!world.isClientSide && world.getTime() != this.lastExecution) {
/* 103 */       if ("Searge".equalsIgnoreCase(this.command)) {
/* 104 */         this.lastOutput = new ChatComponentText("#itzlipofutzli");
/* 105 */         this.successCount = 1;
/* 106 */         return true;
/*     */       } 
/* 108 */       this.successCount = 0;
/* 109 */       MinecraftServer minecraftserver = d().getMinecraftServer();
/*     */       
/* 111 */       if (minecraftserver.getEnableCommandBlock() && !UtilColor.b(this.command)) {
/*     */         try {
/* 113 */           this.lastOutput = null;
/* 114 */           CommandListenerWrapper commandlistenerwrapper = getWrapper().a((commandcontext, flag, i) -> {
/*     */                 if (flag) {
/*     */                   this.successCount++;
/*     */                 }
/*     */               });
/*     */ 
/*     */           
/* 121 */           minecraftserver.getCommandDispatcher().dispatchServerCommand(commandlistenerwrapper, this.command);
/* 122 */         } catch (Throwable throwable) {
/* 123 */           CrashReport crashreport = CrashReport.a(throwable, "Executing command block");
/* 124 */           CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Command to be executed");
/*     */           
/* 126 */           crashreportsystemdetails.a("Command", this::getCommand);
/* 127 */           crashreportsystemdetails.a("Name", () -> getName().getString());
/*     */ 
/*     */           
/* 130 */           throw new ReportedException(crashreport);
/*     */         } 
/*     */       }
/*     */       
/* 134 */       if (this.updateLastExecution) {
/* 135 */         this.lastExecution = world.getTime();
/*     */       } else {
/* 137 */         this.lastExecution = -1L;
/*     */       } 
/*     */       
/* 140 */       return true;
/*     */     } 
/*     */     
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getName() {
/* 148 */     return this.customName;
/*     */   }
/*     */   
/*     */   public void setName(@Nullable IChatBaseComponent ichatbasecomponent) {
/* 152 */     if (ichatbasecomponent != null) {
/* 153 */       this.customName = ichatbasecomponent;
/*     */     } else {
/* 155 */       this.customName = c;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {
/* 162 */     if (this.trackOutput) {
/* 163 */       this.lastOutput = (new ChatComponentText("[" + b.format(new Date()) + "] ")).addSibling(ichatbasecomponent);
/* 164 */       e();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(@Nullable IChatBaseComponent ichatbasecomponent) {
/* 174 */     this.lastOutput = ichatbasecomponent;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 178 */     this.trackOutput = flag;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman) {
/* 182 */     if (!entityhuman.isCreativeAndOp() && !entityhuman.isCreative() && !entityhuman.getBukkitEntity().hasPermission("minecraft.commandblock")) {
/* 183 */       return EnumInteractionResult.PASS;
/*     */     }
/* 185 */     if ((entityhuman.getWorld()).isClientSide) {
/* 186 */       entityhuman.a(this);
/*     */     }
/*     */     
/* 189 */     return EnumInteractionResult.a(entityhuman.world.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSendSuccess() {
/* 197 */     return (d().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK) && this.trackOutput);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSendFailure() {
/* 202 */     return this.trackOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldBroadcastCommands() {
/* 207 */     return d().getGameRules().getBoolean(GameRules.COMMAND_BLOCK_OUTPUT);
/*     */   }
/*     */   
/*     */   public abstract CommandSender getBukkitSender(CommandListenerWrapper paramCommandListenerWrapper);
/*     */   
/*     */   public abstract WorldServer d();
/*     */   
/*     */   public abstract void e();
/*     */   
/*     */   public abstract CommandListenerWrapper getWrapper();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandBlockListenerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */