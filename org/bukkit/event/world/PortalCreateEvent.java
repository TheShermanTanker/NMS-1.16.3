/*     */ package org.bukkit.event.world;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PortalCreateEvent
/*     */   extends WorldEvent
/*     */   implements Cancellable
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel = false;
/*     */   private final List<BlockState> blocks;
/*     */   private final Entity entity;
/*     */   private final CreateReason reason;
/*     */   
/*     */   @Deprecated
/*     */   public PortalCreateEvent(@NotNull List<BlockState> blocks, @NotNull World world, @NotNull CreateReason reason) {
/*  24 */     this(blocks, world, null, reason);
/*     */   }
/*     */   
/*     */   public PortalCreateEvent(@NotNull List<BlockState> blocks, @NotNull World world, @Nullable Entity entity, @NotNull CreateReason reason) {
/*  28 */     super(world);
/*     */     
/*  30 */     this.blocks = blocks;
/*  31 */     this.entity = entity;
/*  32 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<BlockState> getBlocks() {
/*  42 */     return this.blocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/*  52 */     return this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  57 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  62 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CreateReason getReason() {
/*  72 */     return this.reason;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  78 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  83 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum CreateReason
/*     */   {
/*  94 */     FIRE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     NETHER_PAIR,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     END_PLATFORM;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\PortalCreateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */