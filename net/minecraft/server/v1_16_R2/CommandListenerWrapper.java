/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.mojang.brigadier.ResultConsumer;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BinaryOperator;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ 
/*     */ public class CommandListenerWrapper implements ICompletionProvider, BukkitBrigadierCommandSource {
/*  21 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("permissions.requires.player"));
/*  22 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("permissions.requires.entity"));
/*     */   public final ICommandListener base;
/*     */   private final Vec3D d;
/*     */   private final WorldServer e;
/*     */   private final int f;
/*     */   private final String g;
/*     */   private final IChatBaseComponent h;
/*     */   private final MinecraftServer i;
/*     */   private final boolean j;
/*     */   @Nullable
/*     */   private final Entity k;
/*     */   private final ResultConsumer<CommandListenerWrapper> l;
/*     */   private final ArgumentAnchor.Anchor m;
/*     */   private final Vec2F n;
/*  36 */   public ThreadLocal<CommandNode> currentCommand = new ThreadLocal<>();
/*     */   
/*     */   public CommandListenerWrapper(ICommandListener icommandlistener, Vec3D vec3d, Vec2F vec2f, WorldServer worldserver, int i, String s, IChatBaseComponent ichatbasecomponent, MinecraftServer minecraftserver, @Nullable Entity entity) {
/*  39 */     this(icommandlistener, vec3d, vec2f, worldserver, i, s, ichatbasecomponent, minecraftserver, entity, false, (commandcontext, flag, j) -> {  }ArgumentAnchor.Anchor.FEET);
/*     */   }
/*     */ 
/*     */   
/*     */   protected CommandListenerWrapper(ICommandListener icommandlistener, Vec3D vec3d, Vec2F vec2f, WorldServer worldserver, int i, String s, IChatBaseComponent ichatbasecomponent, MinecraftServer minecraftserver, @Nullable Entity entity, boolean flag, ResultConsumer<CommandListenerWrapper> resultconsumer, ArgumentAnchor.Anchor argumentanchor_anchor) {
/*  44 */     this.base = icommandlistener;
/*  45 */     this.d = vec3d;
/*  46 */     this.e = worldserver;
/*  47 */     this.j = flag;
/*  48 */     this.k = entity;
/*  49 */     this.f = i;
/*  50 */     this.g = s;
/*  51 */     this.h = ichatbasecomponent;
/*  52 */     this.i = minecraftserver;
/*  53 */     this.l = resultconsumer;
/*  54 */     this.m = argumentanchor_anchor;
/*  55 */     this.n = vec2f;
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(Entity entity) {
/*  59 */     return (this.k == entity) ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, this.f, entity.getDisplayName().getString(), entity.getScoreboardDisplayName(), this.i, entity, this.j, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(Vec3D vec3d) {
/*  63 */     return this.d.equals(vec3d) ? this : new CommandListenerWrapper(this.base, vec3d, this.n, this.e, this.f, this.g, this.h, this.i, this.k, this.j, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(Vec2F vec2f) {
/*  67 */     return this.n.c(vec2f) ? this : new CommandListenerWrapper(this.base, this.d, vec2f, this.e, this.f, this.g, this.h, this.i, this.k, this.j, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(ResultConsumer<CommandListenerWrapper> resultconsumer) {
/*  71 */     return this.l.equals(resultconsumer) ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, this.f, this.g, this.h, this.i, this.k, this.j, resultconsumer, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(ResultConsumer<CommandListenerWrapper> resultconsumer, BinaryOperator<ResultConsumer<CommandListenerWrapper>> binaryoperator) {
/*  75 */     ResultConsumer<CommandListenerWrapper> resultconsumer1 = binaryoperator.apply(this.l, resultconsumer);
/*     */     
/*  77 */     return a(resultconsumer1);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a() {
/*  81 */     return this.j ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, this.f, this.g, this.h, this.i, this.k, true, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(int i) {
/*  85 */     return (i == this.f) ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, i, this.g, this.h, this.i, this.k, this.j, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper b(int i) {
/*  89 */     return (i <= this.f) ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, i, this.g, this.h, this.i, this.k, this.j, this.l, this.m);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(ArgumentAnchor.Anchor argumentanchor_anchor) {
/*  93 */     return (argumentanchor_anchor == this.m) ? this : new CommandListenerWrapper(this.base, this.d, this.n, this.e, this.f, this.g, this.h, this.i, this.k, this.j, this.l, argumentanchor_anchor);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(WorldServer worldserver) {
/*  97 */     if (worldserver == this.e) {
/*  98 */       return this;
/*     */     }
/* 100 */     double d0 = DimensionManager.a(this.e.getDimensionManager(), worldserver.getDimensionManager());
/* 101 */     Vec3D vec3d = new Vec3D(this.d.x * d0, this.d.y, this.d.z * d0);
/*     */     
/* 103 */     return new CommandListenerWrapper(this.base, vec3d, this.n, worldserver, this.f, this.g, this.h, this.i, this.k, this.j, this.l, this.m);
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandListenerWrapper a(Entity entity, ArgumentAnchor.Anchor argumentanchor_anchor) throws CommandSyntaxException {
/* 108 */     return b(argumentanchor_anchor.a(entity));
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper b(Vec3D vec3d) throws CommandSyntaxException {
/* 112 */     Vec3D vec3d1 = this.m.a(this);
/* 113 */     double d0 = vec3d.x - vec3d1.x;
/* 114 */     double d1 = vec3d.y - vec3d1.y;
/* 115 */     double d2 = vec3d.z - vec3d1.z;
/* 116 */     double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/* 117 */     float f = MathHelper.g((float)-(MathHelper.d(d1, d3) * 57.2957763671875D));
/* 118 */     float f1 = MathHelper.g((float)(MathHelper.d(d2, d0) * 57.2957763671875D) - 90.0F);
/*     */     
/* 120 */     return a(new Vec2F(f, f1));
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 124 */     return this.h;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 128 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getBukkitEntity() {
/* 134 */     return (getEntity() != null) ? (Entity)getEntity().getBukkitEntity() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getBukkitWorld() {
/* 139 */     return (getWorld() != null) ? (World)getWorld().getWorld() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getBukkitLocation() {
/* 144 */     Vec3D pos = getPosition();
/* 145 */     World world = getBukkitWorld();
/* 146 */     return (world != null && pos != null) ? new Location(world, pos.x, pos.y, pos.z) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPermission(int i) {
/* 154 */     CommandNode currentCommand = this.currentCommand.get();
/* 155 */     if (currentCommand != null) {
/* 156 */       return hasPermission(i, VanillaCommandWrapper.getPermission(currentCommand));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 161 */     return (this.f >= i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPermission(int i, String bukkitPermission) {
/* 167 */     return (((getWorld() == null || !(getWorld().getServer()).ignoreVanillaPermissions) && this.f >= i) || getBukkitSender().hasPermission(bukkitPermission));
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D getPosition() {
/* 172 */     return this.d;
/*     */   }
/*     */   
/*     */   public WorldServer getWorld() {
/* 176 */     return this.e;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/* 181 */     return this.k;
/*     */   }
/*     */   
/*     */   public Entity g() throws CommandSyntaxException {
/* 185 */     if (this.k == null) {
/* 186 */       throw b.create();
/*     */     }
/* 188 */     return this.k;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer h() throws CommandSyntaxException {
/* 193 */     if (!(this.k instanceof EntityPlayer)) {
/* 194 */       throw a.create();
/*     */     }
/* 196 */     return (EntityPlayer)this.k;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2F i() {
/* 201 */     return this.n;
/*     */   }
/*     */   
/*     */   public MinecraftServer getServer() {
/* 205 */     return this.i;
/*     */   }
/*     */   
/*     */   public ArgumentAnchor.Anchor k() {
/* 209 */     return this.m;
/*     */   }
/*     */   
/*     */   public void sendMessage(IChatBaseComponent ichatbasecomponent, boolean flag) {
/* 213 */     if (this.base.shouldSendSuccess() && !this.j) {
/* 214 */       this.base.sendMessage(ichatbasecomponent, SystemUtils.b);
/*     */     }
/*     */     
/* 217 */     if (flag && this.base.shouldBroadcastCommands() && !this.j) {
/* 218 */       sendAdminMessage(ichatbasecomponent);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendAdminMessage(IChatBaseComponent ichatbasecomponent) {
/* 224 */     IChatMutableComponent ichatmutablecomponent = (new ChatMessage("chat.type.admin", new Object[] { getScoreboardDisplayName(), ichatbasecomponent })).a(new EnumChatFormat[] { EnumChatFormat.GRAY, EnumChatFormat.ITALIC });
/*     */     
/* 226 */     if (this.i.getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
/* 227 */       Iterator<EntityPlayer> iterator = this.i.getPlayerList().getPlayers().iterator();
/*     */       
/* 229 */       while (iterator.hasNext()) {
/* 230 */         EntityPlayer entityplayer = iterator.next();
/*     */         
/* 232 */         if (entityplayer != this.base && entityplayer.getBukkitEntity().hasPermission("minecraft.admin.command_feedback")) {
/* 233 */           entityplayer.sendMessage(ichatmutablecomponent, SystemUtils.b);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     if (this.base != this.i && this.i.getGameRules().getBoolean(GameRules.LOG_ADMIN_COMMANDS) && !SpigotConfig.silentCommandBlocks) {
/* 239 */       this.i.sendMessage(ichatmutablecomponent, SystemUtils.b);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendFailureMessage(IChatBaseComponent ichatbasecomponent) {
/* 245 */     if (this.base.shouldSendFailure() && !this.j) {
/* 246 */       this.base.sendMessage((new ChatComponentText("")).addSibling(ichatbasecomponent).a(EnumChatFormat.RED), SystemUtils.b);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(CommandContext<CommandListenerWrapper> commandcontext, boolean flag, int i) {
/* 252 */     if (this.l != null) {
/* 253 */       this.l.onCommandComplete(commandcontext, flag, i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> l() {
/* 260 */     return Lists.newArrayList((Object[])this.i.getPlayers());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> m() {
/* 265 */     return this.i.getScoreboard().f();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> n() {
/* 270 */     return IRegistry.SOUND_EVENT.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<MinecraftKey> o() {
/* 275 */     return this.i.getCraftingManager().d();
/*     */   }
/*     */ 
/*     */   
/*     */   public CompletableFuture<Suggestions> a(CommandContext<ICompletionProvider> commandcontext, SuggestionsBuilder suggestionsbuilder) {
/* 280 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ResourceKey<World>> p() {
/* 285 */     return this.i.F();
/*     */   }
/*     */ 
/*     */   
/*     */   public IRegistryCustom q() {
/* 290 */     return this.i.getCustomRegistry();
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandSender getBukkitSender() {
/* 295 */     return this.base.getBukkitSender(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandListenerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */