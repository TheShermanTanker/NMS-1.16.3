/*     */ package org.bukkit.craftbukkit.v1_16_R2.map;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.ResourceKey;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldMap;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.map.MapRenderer;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public final class CraftMapView implements MapView {
/*  22 */   private final Map<CraftPlayer, RenderData> renderCache = new HashMap<>();
/*  23 */   private final List<MapRenderer> renderers = new ArrayList<>();
/*  24 */   private final Map<MapRenderer, Map<CraftPlayer, CraftMapCanvas>> canvases = new HashMap<>();
/*     */   protected final WorldMap worldMap;
/*     */   
/*     */   public CraftMapView(WorldMap worldMap) {
/*  28 */     this.worldMap = worldMap;
/*  29 */     addRenderer(new CraftMapRenderer(this, worldMap));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  34 */     String text = this.worldMap.getId();
/*  35 */     if (text.startsWith("map_")) {
/*     */       try {
/*  37 */         return Integer.parseInt(text.substring("map_".length()));
/*  38 */       } catch (NumberFormatException ex) {
/*  39 */         throw new IllegalStateException("Map has non-numeric ID");
/*     */       } 
/*     */     }
/*  42 */     throw new IllegalStateException("Map has invalid ID");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVirtual() {
/*  48 */     return (this.renderers.size() > 0 && !(this.renderers.get(0) instanceof CraftMapRenderer));
/*     */   }
/*     */ 
/*     */   
/*     */   public MapView.Scale getScale() {
/*  53 */     return MapView.Scale.valueOf(this.worldMap.scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(MapView.Scale scale) {
/*  58 */     this.worldMap.scale = scale.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  63 */     ResourceKey<World> dimension = this.worldMap.map;
/*  64 */     WorldServer world = MinecraftServer.getServer().getWorldServer(dimension);
/*     */     
/*  66 */     return (world == null) ? null : (World)world.getWorld();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorld(World world) {
/*  71 */     this.worldMap.map = ((CraftWorld)world).getHandle().getDimensionKey();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCenterX() {
/*  76 */     return this.worldMap.centerX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCenterZ() {
/*  81 */     return this.worldMap.centerZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCenterX(int x) {
/*  86 */     this.worldMap.centerX = x;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCenterZ(int z) {
/*  91 */     this.worldMap.centerZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MapRenderer> getRenderers() {
/*  96 */     return new ArrayList<>(this.renderers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRenderer(MapRenderer renderer) {
/* 101 */     if (!this.renderers.contains(renderer)) {
/* 102 */       this.renderers.add(renderer);
/* 103 */       this.canvases.put(renderer, new HashMap<>());
/* 104 */       renderer.initialize(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeRenderer(MapRenderer renderer) {
/* 110 */     if (this.renderers.contains(renderer)) {
/* 111 */       this.renderers.remove(renderer);
/* 112 */       for (Map.Entry<CraftPlayer, CraftMapCanvas> entry : (Iterable<Map.Entry<CraftPlayer, CraftMapCanvas>>)((Map)this.canvases.get(renderer)).entrySet()) {
/* 113 */         for (int x = 0; x < 128; x++) {
/* 114 */           for (int y = 0; y < 128; y++) {
/* 115 */             ((CraftMapCanvas)entry.getValue()).setPixel(x, y, (byte)-1);
/*     */           }
/*     */         } 
/*     */       } 
/* 119 */       this.canvases.remove(renderer);
/* 120 */       return true;
/*     */     } 
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isContextual() {
/* 127 */     for (MapRenderer renderer : this.renderers) {
/* 128 */       if (renderer.isContextual()) return true; 
/*     */     } 
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public RenderData render(CraftPlayer player) {
/* 134 */     boolean context = isContextual();
/* 135 */     RenderData render = this.renderCache.get(context ? player : null);
/*     */     
/* 137 */     if (render == null) {
/* 138 */       render = new RenderData();
/* 139 */       this.renderCache.put(context ? player : null, render);
/*     */     } 
/*     */     
/* 142 */     if (context && this.renderCache.containsKey(null)) {
/* 143 */       this.renderCache.remove(null);
/*     */     }
/*     */     
/* 146 */     Arrays.fill(render.buffer, (byte)0);
/* 147 */     render.cursors.clear();
/*     */     
/* 149 */     for (MapRenderer renderer : this.renderers) {
/* 150 */       CraftMapCanvas canvas = (CraftMapCanvas)((Map)this.canvases.get(renderer)).get(renderer.isContextual() ? player : null);
/* 151 */       if (canvas == null) {
/* 152 */         canvas = new CraftMapCanvas(this);
/* 153 */         ((Map<CraftPlayer, CraftMapCanvas>)this.canvases.get(renderer)).put(renderer.isContextual() ? player : null, canvas);
/*     */       } 
/*     */       
/* 156 */       canvas.setBase(render.buffer);
/*     */       try {
/* 158 */         renderer.render(this, canvas, (Player)player);
/* 159 */       } catch (Throwable ex) {
/* 160 */         Bukkit.getLogger().log(Level.SEVERE, "Could not render map using renderer " + renderer.getClass().getName(), ex);
/*     */       } 
/*     */       
/* 163 */       byte[] buf = canvas.getBuffer(); int i;
/* 164 */       for (i = 0; i < buf.length; i++) {
/* 165 */         byte color = buf[i];
/*     */         
/* 167 */         if (color >= 0 || color <= -21) render.buffer[i] = color;
/*     */       
/*     */       } 
/* 170 */       for (i = 0; i < canvas.getCursors().size(); i++) {
/* 171 */         render.cursors.add(canvas.getCursors().getCursor(i));
/*     */       }
/*     */     } 
/*     */     
/* 175 */     return render;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTrackingPosition() {
/* 180 */     return this.worldMap.track;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrackingPosition(boolean trackingPosition) {
/* 185 */     this.worldMap.track = trackingPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnlimitedTracking() {
/* 190 */     return this.worldMap.unlimitedTracking;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnlimitedTracking(boolean unlimited) {
/* 195 */     this.worldMap.unlimitedTracking = unlimited;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 200 */     return this.worldMap.locked;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocked(boolean locked) {
/* 205 */     this.worldMap.locked = locked;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\map\CraftMapView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */