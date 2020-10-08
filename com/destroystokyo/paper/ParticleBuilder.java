/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.util.NumberConversions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleBuilder
/*     */ {
/*     */   private Particle particle;
/*     */   private List<Player> receivers;
/*     */   private Player source;
/*     */   private Location location;
/*  27 */   private int count = 1;
/*  28 */   private double offsetX = 0.0D, offsetY = 0.0D, offsetZ = 0.0D;
/*  29 */   private double extra = 1.0D;
/*     */   private Object data;
/*     */   private boolean force = true;
/*     */   
/*     */   public ParticleBuilder(@NotNull Particle particle) {
/*  34 */     this.particle = particle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder spawn() {
/*  45 */     if (this.location == null) {
/*  46 */       throw new IllegalStateException("Please specify location for this particle");
/*     */     }
/*  48 */     this.location.getWorld().spawnParticle(this.particle, this.receivers, this.source, this.location
/*  49 */         .getX(), this.location.getY(), this.location.getZ(), this.count, this.offsetX, this.offsetY, this.offsetZ, this.extra, this.data, this.force);
/*     */ 
/*     */     
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Particle particle() {
/*  60 */     return this.particle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder particle(@NotNull Particle particle) {
/*  71 */     this.particle = particle;
/*  72 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<Player> receivers() {
/*  80 */     return this.receivers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasReceivers() {
/*  91 */     return ((this.receivers == null && !this.location.getWorld().getPlayers().isEmpty()) || (this.receivers != null && 
/*  92 */       !this.receivers.isEmpty()));
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
/*     */   @NotNull
/*     */   public ParticleBuilder allPlayers() {
/* 105 */     this.receivers = null;
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(@Nullable List<Player> receivers) {
/* 118 */     this.receivers = (receivers != null) ? Lists.newArrayList(receivers) : null;
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(@Nullable Collection<Player> receivers) {
/* 129 */     this.receivers = (receivers != null) ? Lists.newArrayList(receivers) : null;
/* 130 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(@Nullable Player... receivers) {
/* 140 */     this.receivers = (receivers != null) ? Lists.newArrayList((Object[])receivers) : null;
/* 141 */     return this;
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
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(int radius) {
/* 154 */     return receivers(radius, radius);
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
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(int radius, boolean byDistance) {
/* 168 */     if (!byDistance) {
/* 169 */       return receivers(radius, radius, radius);
/*     */     }
/* 171 */     this.receivers = Lists.newArrayList();
/* 172 */     for (Player nearbyPlayer : this.location.getWorld()
/* 173 */       .getNearbyPlayers(this.location, radius, radius, radius)) {
/* 174 */       Location loc = nearbyPlayer.getLocation();
/* 175 */       double x = NumberConversions.square(this.location.getX() - loc.getX());
/* 176 */       double y = NumberConversions.square(this.location.getY() - loc.getY());
/* 177 */       double z = NumberConversions.square(this.location.getZ() - loc.getZ());
/* 178 */       if (Math.sqrt(x + y + z) > radius) {
/*     */         continue;
/*     */       }
/* 181 */       this.receivers.add(nearbyPlayer);
/*     */     } 
/* 183 */     return this;
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
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(int xzRadius, int yRadius) {
/* 199 */     return receivers(xzRadius, yRadius, xzRadius);
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
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(int xzRadius, int yRadius, boolean byDistance) {
/* 214 */     if (!byDistance) {
/* 215 */       return receivers(xzRadius, yRadius, xzRadius);
/*     */     }
/* 217 */     this.receivers = Lists.newArrayList();
/* 218 */     for (Player nearbyPlayer : this.location.getWorld()
/* 219 */       .getNearbyPlayers(this.location, xzRadius, yRadius, xzRadius)) {
/* 220 */       Location loc = nearbyPlayer.getLocation();
/* 221 */       if (Math.abs(loc.getY() - this.location.getY()) > yRadius) {
/*     */         continue;
/*     */       }
/* 224 */       double x = NumberConversions.square(this.location.getX() - loc.getX());
/* 225 */       double z = NumberConversions.square(this.location.getZ() - loc.getZ());
/* 226 */       if (x + z > NumberConversions.square(xzRadius)) {
/*     */         continue;
/*     */       }
/* 229 */       this.receivers.add(nearbyPlayer);
/*     */     } 
/* 231 */     return this;
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
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder receivers(int xRadius, int yRadius, int zRadius) {
/* 247 */     if (this.location == null) {
/* 248 */       throw new IllegalStateException("Please set location first");
/*     */     }
/* 250 */     return receivers(this.location.getWorld().getNearbyPlayers(this.location, xRadius, yRadius, zRadius));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player source() {
/* 258 */     return this.source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder source(@Nullable Player source) {
/* 269 */     this.source = source;
/* 270 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Location location() {
/* 278 */     return this.location;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder location(@NotNull Location location) {
/* 289 */     this.location = location.clone();
/* 290 */     return this;
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
/*     */   @NotNull
/*     */   public ParticleBuilder location(@NotNull World world, double x, double y, double z) {
/* 304 */     this.location = new Location(world, x, y, z);
/* 305 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int count() {
/* 312 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder count(int count) {
/* 323 */     this.count = count;
/* 324 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double offsetX() {
/* 333 */     return this.offsetX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double offsetY() {
/* 342 */     return this.offsetY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double offsetZ() {
/* 351 */     return this.offsetZ;
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
/*     */   @NotNull
/*     */   public ParticleBuilder offset(double offsetX, double offsetY, double offsetZ) {
/* 364 */     this.offsetX = offsetX;
/* 365 */     this.offsetY = offsetY;
/* 366 */     this.offsetZ = offsetZ;
/* 367 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double extra() {
/* 376 */     return this.extra;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder extra(double extra) {
/* 387 */     this.extra = extra;
/* 388 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T data() {
/* 400 */     return (T)this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public <T> ParticleBuilder data(@Nullable T data) {
/* 412 */     this.data = data;
/* 413 */     return this;
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
/*     */   @NotNull
/*     */   public ParticleBuilder force(boolean force) {
/* 426 */     this.force = force;
/* 427 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder color(@Nullable Color color) {
/* 438 */     return color(color, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder color(@Nullable Color color, float size) {
/* 450 */     if (this.particle != Particle.REDSTONE && color != null) {
/* 451 */       throw new IllegalStateException("Color may only be set on REDSTONE");
/*     */     }
/*     */ 
/*     */     
/* 455 */     if (color == null) {
/* 456 */       if (this.data instanceof Particle.DustOptions) {
/* 457 */         return data(null);
/*     */       }
/* 459 */       return this;
/*     */     } 
/*     */ 
/*     */     
/* 463 */     return data(new Particle.DustOptions(color, size));
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
/*     */   @NotNull
/*     */   public ParticleBuilder color(int r, int g, int b) {
/* 476 */     return color(Color.fromRGB(r, g, b));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\ParticleBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */