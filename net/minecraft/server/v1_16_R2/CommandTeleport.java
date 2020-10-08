/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityTeleportEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ 
/*     */ public class CommandTeleport {
/*  23 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.teleport.invalidPosition"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/*  26 */     LiteralCommandNode<CommandListenerWrapper> literalcommandnode = com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("teleport").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*     */         
/*  28 */         .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("location", ArgumentVec3.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), (IVectorPosition)null, (a)null)))
/*     */             
/*  30 */             .then(CommandDispatcher.<T>a("rotation", ArgumentRotation.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), ArgumentRotation.a(commandcontext, "rotation"), (a)null))))
/*     */             
/*  32 */             .then(((LiteralArgumentBuilder)CommandDispatcher.a("facing").then(CommandDispatcher.a("entity").then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("facingEntity", ArgumentEntity.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), (IVectorPosition)null, new a(ArgumentEntity.a(commandcontext, "facingEntity"), ArgumentAnchor.Anchor.FEET))))
/*     */                   
/*  34 */                   .then(CommandDispatcher.<T>a("facingAnchor", ArgumentAnchor.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), (IVectorPosition)null, new a(ArgumentEntity.a(commandcontext, "facingEntity"), ArgumentAnchor.a(commandcontext, "facingAnchor"))))))))
/*     */               
/*  36 */               .then(CommandDispatcher.<T>a("facingLocation", ArgumentVec3.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), (IVectorPosition)null, new a(ArgumentVec3.a(commandcontext, "facingLocation"))))))))
/*     */           
/*  38 */           .then(CommandDispatcher.<T>a("destination", ArgumentEntity.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentEntity.a(commandcontext, "destination"))))))
/*     */         
/*  40 */         .then(CommandDispatcher.<T>a("location", ArgumentVec3.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), Collections.singleton(((CommandListenerWrapper)commandcontext.getSource()).g()), ((CommandListenerWrapper)commandcontext.getSource()).getWorld(), ArgumentVec3.b(commandcontext, "location"), VectorPosition.d(), (a)null))))
/*     */         
/*  42 */         .then(CommandDispatcher.<T>a("destination", ArgumentEntity.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), Collections.singleton(((CommandListenerWrapper)commandcontext.getSource()).g()), ArgumentEntity.a(commandcontext, "destination")))));
/*     */ 
/*     */ 
/*     */     
/*  46 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("tp").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*     */         
/*  48 */         .redirect((CommandNode)literalcommandnode));
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, Entity entity) throws CommandSyntaxException {
/*  52 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/*  54 */     while (iterator.hasNext()) {
/*  55 */       Entity entity1 = iterator.next();
/*     */       
/*  57 */       a(commandlistenerwrapper, entity1, (WorldServer)entity.world, entity.locX(), entity.locY(), entity.locZ(), EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class), entity.yaw, entity.pitch, (a)null);
/*     */     } 
/*     */     
/*  60 */     if (collection.size() == 1) {
/*  61 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.teleport.success.entity.single", new Object[] { ((Entity)collection.iterator().next()).getScoreboardDisplayName(), entity.getScoreboardDisplayName() }), true);
/*     */     } else {
/*  63 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.teleport.success.entity.multiple", new Object[] { Integer.valueOf(collection.size()), entity.getScoreboardDisplayName() }), true);
/*     */     } 
/*     */     
/*  66 */     return collection.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, WorldServer worldserver, IVectorPosition ivectorposition, @Nullable IVectorPosition ivectorposition1, @Nullable a commandteleport_a) throws CommandSyntaxException {
/*  70 */     Vec3D vec3d = ivectorposition.a(commandlistenerwrapper);
/*  71 */     Vec2F vec2f = (ivectorposition1 == null) ? null : ivectorposition1.b(commandlistenerwrapper);
/*  72 */     Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
/*     */     
/*  74 */     if (ivectorposition.a()) {
/*  75 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X);
/*     */     }
/*     */     
/*  78 */     if (ivectorposition.b()) {
/*  79 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/*     */     }
/*     */     
/*  82 */     if (ivectorposition.c()) {
/*  83 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Z);
/*     */     }
/*     */     
/*  86 */     if (ivectorposition1 == null) {
/*  87 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*  88 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */     } else {
/*  90 */       if (ivectorposition1.a()) {
/*  91 */         set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*     */       }
/*     */       
/*  94 */       if (ivectorposition1.b()) {
/*  95 */         set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */       }
/*     */     } 
/*     */     
/*  99 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/* 101 */     while (iterator.hasNext()) {
/* 102 */       Entity entity = iterator.next();
/*     */       
/* 104 */       if (ivectorposition1 == null) {
/* 105 */         a(commandlistenerwrapper, entity, worldserver, vec3d.x, vec3d.y, vec3d.z, set, entity.yaw, entity.pitch, commandteleport_a); continue;
/*     */       } 
/* 107 */       a(commandlistenerwrapper, entity, worldserver, vec3d.x, vec3d.y, vec3d.z, set, vec2f.j, vec2f.i, commandteleport_a);
/*     */     } 
/*     */ 
/*     */     
/* 111 */     if (collection.size() == 1) {
/* 112 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.teleport.success.location.single", new Object[] { ((Entity)collection.iterator().next()).getScoreboardDisplayName(), Double.valueOf(vec3d.x), Double.valueOf(vec3d.y), Double.valueOf(vec3d.z) }), true);
/*     */     } else {
/* 114 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.teleport.success.location.multiple", new Object[] { Integer.valueOf(collection.size()), Double.valueOf(vec3d.x), Double.valueOf(vec3d.y), Double.valueOf(vec3d.z) }), true);
/*     */     } 
/*     */     
/* 117 */     return collection.size();
/*     */   }
/*     */   
/*     */   private static void a(CommandListenerWrapper commandlistenerwrapper, Entity entity, WorldServer worldserver, double d0, double d1, double d2, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set, float f, float f1, @Nullable a commandteleport_a) throws CommandSyntaxException {
/* 121 */     BlockPosition blockposition = new BlockPosition(d0, d1, d2);
/*     */     
/* 123 */     if (d0 <= -3.0E7D || d2 <= -3.0E7D || d0 > 3.0E7D || d2 > 3.0E7D || d1 > 3.0E7D || d1 <= -3.0E7D) {
/* 124 */       Bukkit.getLogger().warning("Refused to teleport " + entity.getName() + " to " + d0 + ", " + d1 + ", " + d2);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 129 */     if (!World.l(blockposition)) {
/* 130 */       throw a.create();
/*     */     }
/* 132 */     if (entity instanceof EntityPlayer) {
/* 133 */       ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(new BlockPosition(d0, d1, d2));
/*     */       
/* 135 */       worldserver.getChunkProvider().addTicket(TicketType.POST_TELEPORT, chunkcoordintpair, 1, Integer.valueOf(entity.getId()));
/* 136 */       entity.stopRiding();
/* 137 */       if (((EntityPlayer)entity).isSleeping()) {
/* 138 */         ((EntityPlayer)entity).wakeup(true, true);
/*     */       }
/*     */       
/* 141 */       if (worldserver == entity.world) {
/* 142 */         ((EntityPlayer)entity).playerConnection.a(d0, d1, d2, f, f1, set, PlayerTeleportEvent.TeleportCause.COMMAND);
/*     */       } else {
/* 144 */         ((EntityPlayer)entity).a(worldserver, d0, d1, d2, f, f1, PlayerTeleportEvent.TeleportCause.COMMAND);
/*     */       } 
/*     */       
/* 147 */       entity.setHeadRotation(f);
/*     */     } else {
/* 149 */       float f2 = MathHelper.g(f);
/* 150 */       float f3 = MathHelper.g(f1);
/*     */       
/* 152 */       f3 = MathHelper.a(f3, -90.0F, 90.0F);
/*     */       
/* 154 */       Location to = new Location((World)worldserver.getWorld(), d0, d1, d2, f2, f3);
/* 155 */       EntityTeleportEvent event = new EntityTeleportEvent((Entity)entity.getBukkitEntity(), entity.getBukkitEntity().getLocation(), to);
/* 156 */       worldserver.getServer().getPluginManager().callEvent((Event)event);
/* 157 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 161 */       d0 = to.getX();
/* 162 */       d1 = to.getY();
/* 163 */       d2 = to.getZ();
/* 164 */       f2 = to.getYaw();
/* 165 */       f3 = to.getPitch();
/* 166 */       worldserver = ((CraftWorld)to.getWorld()).getHandle();
/*     */       
/* 168 */       if (worldserver == entity.world) {
/* 169 */         entity.setPositionRotation(d0, d1, d2, f2, f3);
/* 170 */         entity.setHeadRotation(f2);
/*     */       } else {
/* 172 */         entity.decouple();
/* 173 */         Entity entity1 = entity;
/*     */         
/* 175 */         entity = (Entity)entity.getEntityType().a(worldserver);
/* 176 */         if (entity == null) {
/*     */           return;
/*     */         }
/*     */         
/* 180 */         entity.v(entity1);
/* 181 */         entity.setPositionRotation(d0, d1, d2, f2, f3);
/* 182 */         entity.setHeadRotation(f2);
/* 183 */         worldserver.addEntityTeleport(entity);
/* 184 */         entity1.dead = true;
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     if (commandteleport_a != null) {
/* 189 */       commandteleport_a.a(commandlistenerwrapper, entity);
/*     */     }
/*     */     
/* 192 */     if (!(entity instanceof EntityLiving) || !((EntityLiving)entity).isGliding()) {
/* 193 */       entity.setMot(entity.getMot().d(1.0D, 0.0D, 1.0D));
/* 194 */       entity.setOnGround(true);
/*     */     } 
/*     */     
/* 197 */     if (entity instanceof EntityCreature) {
/* 198 */       ((EntityCreature)entity).getNavigation().o();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     private final Vec3D a;
/*     */     
/*     */     private final Entity b;
/*     */     private final ArgumentAnchor.Anchor c;
/*     */     
/*     */     public a(Entity entity, ArgumentAnchor.Anchor argumentanchor_anchor) {
/* 211 */       this.b = entity;
/* 212 */       this.c = argumentanchor_anchor;
/* 213 */       this.a = argumentanchor_anchor.a(entity);
/*     */     }
/*     */     
/*     */     public a(Vec3D vec3d) {
/* 217 */       this.b = null;
/* 218 */       this.a = vec3d;
/* 219 */       this.c = null;
/*     */     }
/*     */     
/*     */     public void a(CommandListenerWrapper commandlistenerwrapper, Entity entity) {
/* 223 */       if (this.b != null) {
/* 224 */         if (entity instanceof EntityPlayer) {
/* 225 */           ((EntityPlayer)entity).a(commandlistenerwrapper.k(), this.b, this.c);
/*     */         } else {
/* 227 */           entity.a(commandlistenerwrapper.k(), this.a);
/*     */         } 
/*     */       } else {
/* 230 */         entity.a(commandlistenerwrapper.k(), this.a);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */