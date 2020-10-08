/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ 
/*     */ public class TileEntitySign extends TileEntity implements ICommandListener {
/*     */   public final IChatBaseComponent[] lines;
/*     */   public boolean isEditable;
/*     */   private EntityHuman c;
/*     */   private final FormattedString[] g;
/*     */   private EnumColor color;
/*     */   public UUID signEditor;
/*  15 */   private static final boolean CONVERT_LEGACY_SIGNS = Boolean.getBoolean("convertLegacySigns");
/*     */   
/*     */   public TileEntitySign() {
/*  18 */     super(TileEntityTypes.SIGN);
/*  19 */     this.lines = new IChatBaseComponent[] { ChatComponentText.d, ChatComponentText.d, ChatComponentText.d, ChatComponentText.d };
/*  20 */     this.isEditable = true;
/*  21 */     this.g = new FormattedString[4];
/*  22 */     this.color = EnumColor.BLACK;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  27 */     super.save(nbttagcompound);
/*     */     
/*  29 */     for (int i = 0; i < 4; i++) {
/*  30 */       String s = IChatBaseComponent.ChatSerializer.a(this.lines[i]);
/*     */       
/*  32 */       nbttagcompound.setString("Text" + (i + 1), s);
/*     */     } 
/*     */ 
/*     */     
/*  36 */     if (CONVERT_LEGACY_SIGNS) {
/*  37 */       nbttagcompound.setBoolean("Bukkit.isConverted", true);
/*     */     }
/*     */ 
/*     */     
/*  41 */     nbttagcompound.setString("Color", this.color.c());
/*  42 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  47 */     this.isEditable = false;
/*  48 */     super.load(iblockdata, nbttagcompound);
/*  49 */     this.color = EnumColor.a(nbttagcompound.getString("Color"), EnumColor.BLACK);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     boolean oldSign = (Boolean.getBoolean("convertLegacySigns") && !nbttagcompound.getBoolean("Bukkit.isConverted"));
/*     */     
/*  57 */     for (int i = 0; i < 4; i++) {
/*  58 */       String s = nbttagcompound.getString("Text" + (i + 1));
/*  59 */       if (s != null && s.length() > 2048) {
/*  60 */         s = "\"\"";
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  66 */       try { if (oldSign && !this.isLoadingStructure)
/*  67 */         { this.lines[i] = CraftChatMessage.fromString(s)[0]; }
/*     */         
/*     */         else
/*     */         
/*  71 */         { IChatMutableComponent ichatmutablecomponent = IChatBaseComponent.ChatSerializer.a(s.isEmpty() ? "\"\"" : s);
/*     */           
/*  73 */           if (this.world instanceof WorldServer) {
/*     */             try {
/*  75 */               this.lines[i] = ChatComponentUtils.filterForDisplay(a((EntityPlayer)null), ichatmutablecomponent, (Entity)null, 0);
/*  76 */             } catch (CommandSyntaxException commandsyntaxexception) {
/*  77 */               this.lines[i] = ichatmutablecomponent;
/*     */             } 
/*     */           } else {
/*  80 */             this.lines[i] = ichatmutablecomponent;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  86 */           this.g[i] = null; }  } catch (JsonParseException jsonparseexception) { this.lines[i] = new ChatComponentText(s); this.g[i] = null; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(int i, IChatBaseComponent ichatbasecomponent) {
/*  92 */     this.lines[i] = ichatbasecomponent;
/*  93 */     this.g[i] = null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/*  99 */     return new PacketPlayOutTileEntityData(this.position, 9, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 104 */     return save(new NBTTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFilteredNBT() {
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 113 */     return this.isEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityHuman entityhuman) {
/* 119 */     this.signEditor = (entityhuman != null) ? entityhuman.getUniqueID() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHuman f() {
/* 124 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean b(EntityHuman entityhuman) {
/* 128 */     IChatBaseComponent[] aichatbasecomponent = this.lines;
/* 129 */     int i = aichatbasecomponent.length;
/*     */     
/* 131 */     for (int j = 0; j < i; j++) {
/* 132 */       IChatBaseComponent ichatbasecomponent = aichatbasecomponent[j];
/* 133 */       ChatModifier chatmodifier = (ichatbasecomponent == null) ? null : ichatbasecomponent.getChatModifier();
/*     */       
/* 135 */       if (chatmodifier != null && chatmodifier.getClickEvent() != null) {
/* 136 */         ChatClickable chatclickable = chatmodifier.getClickEvent();
/*     */         
/* 138 */         if (chatclickable.a() == ChatClickable.EnumClickAction.RUN_COMMAND) {
/* 139 */           entityhuman.getMinecraftServer().getCommandDispatcher().a(a((EntityPlayer)entityhuman), chatclickable.b());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {}
/*     */ 
/*     */   
/*     */   public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 153 */     return (wrapper.getEntity() != null) ? wrapper.getEntity().getBukkitSender(wrapper) : (CommandSender)new CraftBlockCommandSender(wrapper, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSendSuccess() {
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSendFailure() {
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldBroadcastCommands() {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandListenerWrapper a(@Nullable EntityPlayer entityplayer) {
/* 173 */     String s = (entityplayer == null) ? "Sign" : entityplayer.getDisplayName().getString();
/* 174 */     Object object = (entityplayer == null) ? new ChatComponentText("Sign") : entityplayer.getScoreboardDisplayName();
/*     */ 
/*     */     
/* 177 */     return new CommandListenerWrapper(this, Vec3D.a(this.position), Vec2F.a, (WorldServer)this.world, 2, s, (IChatBaseComponent)object, this.world.getMinecraftServer(), entityplayer);
/*     */   }
/*     */   
/*     */   public EnumColor getColor() {
/* 181 */     return this.color;
/*     */   }
/*     */   
/*     */   public boolean setColor(EnumColor enumcolor) {
/* 185 */     if (enumcolor != getColor()) {
/* 186 */       this.color = enumcolor;
/* 187 */       update();
/* 188 */       if (this.world != null) this.world.notify(getPosition(), getBlock(), getBlock(), 3); 
/* 189 */       return true;
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntitySign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */