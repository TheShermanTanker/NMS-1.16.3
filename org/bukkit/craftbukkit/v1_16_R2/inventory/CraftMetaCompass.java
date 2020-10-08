/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.destroystokyo.paper.Namespaced;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.ResourceKey;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaCompass extends CraftMetaItem implements CompassMeta {
/*  25 */   static final CraftMetaItem.ItemMetaKey LODESTONE_DIMENSION = new CraftMetaItem.ItemMetaKey("LodestoneDimension");
/*  26 */   static final CraftMetaItem.ItemMetaKey LODESTONE_POS = new CraftMetaItem.ItemMetaKey("LodestonePos", "lodestone");
/*  27 */   static final CraftMetaItem.ItemMetaKey LODESTONE_POS_WORLD = new CraftMetaItem.ItemMetaKey("LodestonePosWorld");
/*  28 */   static final CraftMetaItem.ItemMetaKey LODESTONE_POS_X = new CraftMetaItem.ItemMetaKey("LodestonePosX");
/*  29 */   static final CraftMetaItem.ItemMetaKey LODESTONE_POS_Y = new CraftMetaItem.ItemMetaKey("LodestonePosY");
/*  30 */   static final CraftMetaItem.ItemMetaKey LODESTONE_POS_Z = new CraftMetaItem.ItemMetaKey("LodestonePosZ");
/*  31 */   static final CraftMetaItem.ItemMetaKey LODESTONE_TRACKED = new CraftMetaItem.ItemMetaKey("LodestoneTracked");
/*     */   
/*     */   private NBTTagString lodestoneWorld;
/*     */   private int lodestoneX;
/*     */   private int lodestoneY;
/*     */   private int lodestoneZ;
/*     */   private Boolean tracked;
/*     */   
/*     */   CraftMetaCompass(CraftMetaItem meta) {
/*  40 */     super(meta);
/*  41 */     if (!(meta instanceof CraftMetaCompass)) {
/*     */       return;
/*     */     }
/*  44 */     CraftMetaCompass compassMeta = (CraftMetaCompass)meta;
/*  45 */     this.lodestoneWorld = compassMeta.lodestoneWorld;
/*  46 */     this.lodestoneX = compassMeta.lodestoneX;
/*  47 */     this.lodestoneY = compassMeta.lodestoneY;
/*  48 */     this.lodestoneZ = compassMeta.lodestoneZ;
/*  49 */     this.tracked = compassMeta.tracked;
/*     */   }
/*     */   
/*     */   CraftMetaCompass(NBTTagCompound tag) {
/*  53 */     super(tag);
/*  54 */     if (tag.hasKey(LODESTONE_DIMENSION.NBT) && tag.hasKey(LODESTONE_POS.NBT)) {
/*  55 */       this.lodestoneWorld = (NBTTagString)tag.get(LODESTONE_DIMENSION.NBT);
/*  56 */       NBTTagCompound pos = tag.getCompound(LODESTONE_POS.NBT);
/*  57 */       this.lodestoneX = pos.getInt("X");
/*  58 */       this.lodestoneY = pos.getInt("Y");
/*  59 */       this.lodestoneZ = pos.getInt("Z");
/*     */     } 
/*  61 */     if (tag.hasKey(LODESTONE_TRACKED.NBT)) {
/*  62 */       this.tracked = Boolean.valueOf(tag.getBoolean(LODESTONE_TRACKED.NBT));
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaCompass(Map<String, Object> map) {
/*  67 */     super(map);
/*  68 */     String lodestoneWorldString = CraftMetaItem.SerializableMeta.getString(map, LODESTONE_POS_WORLD.BUKKIT, true);
/*  69 */     if (lodestoneWorldString != null) {
/*  70 */       this.lodestoneWorld = NBTTagString.a(lodestoneWorldString);
/*  71 */       this.lodestoneX = ((Integer)map.get(LODESTONE_POS_X.BUKKIT)).intValue();
/*  72 */       this.lodestoneY = ((Integer)map.get(LODESTONE_POS_Y.BUKKIT)).intValue();
/*  73 */       this.lodestoneZ = ((Integer)map.get(LODESTONE_POS_Z.BUKKIT)).intValue();
/*     */     } else {
/*     */       
/*  76 */       Location lodestone = CraftMetaItem.SerializableMeta.<Location>getObject(Location.class, map, LODESTONE_POS.BUKKIT, true);
/*  77 */       if (lodestone != null && lodestone.getWorld() != null) {
/*  78 */         setLodestone(lodestone);
/*     */       }
/*     */     } 
/*  81 */     this.tracked = Boolean.valueOf(CraftMetaItem.SerializableMeta.getBoolean(map, LODESTONE_TRACKED.BUKKIT));
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  86 */     super.applyToItem(tag);
/*     */     
/*  88 */     if (this.lodestoneWorld != null) {
/*  89 */       NBTTagCompound pos = new NBTTagCompound();
/*  90 */       pos.setInt("X", this.lodestoneX);
/*  91 */       pos.setInt("Y", this.lodestoneY);
/*  92 */       pos.setInt("Z", this.lodestoneZ);
/*  93 */       tag.set(LODESTONE_POS.NBT, (NBTBase)pos);
/*  94 */       tag.set(LODESTONE_DIMENSION.NBT, (NBTBase)this.lodestoneWorld);
/*     */     } 
/*     */     
/*  97 */     if (this.tracked != null) {
/*  98 */       tag.setBoolean(LODESTONE_TRACKED.NBT, this.tracked.booleanValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 104 */     return (super.isEmpty() && isCompassEmpty());
/*     */   }
/*     */   
/*     */   boolean isCompassEmpty() {
/* 108 */     return (!hasLodestone() && !hasLodestoneTracked());
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 113 */     return (type == Material.COMPASS);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaCompass clone() {
/* 118 */     CraftMetaCompass clone = (CraftMetaCompass)super.clone();
/* 119 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLodestone() {
/* 124 */     return (this.lodestoneWorld != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLodestone() {
/* 129 */     if (this.lodestoneWorld == null) {
/* 130 */       return null;
/*     */     }
/* 132 */     Optional<ResourceKey<World>> key = World.f.parse((DynamicOps)DynamicOpsNBT.a, this.lodestoneWorld).result();
/* 133 */     WorldServer worldServer = key.isPresent() ? MinecraftServer.getServer().getWorldServer(key.get()) : null;
/* 134 */     CraftWorld craftWorld = (worldServer != null) ? worldServer.getWorld() : null;
/* 135 */     return new Location((World)craftWorld, this.lodestoneX, this.lodestoneY, this.lodestoneZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLodestone(Location lodestone) {
/* 140 */     Preconditions.checkArgument((lodestone == null || lodestone.getWorld() != null), "world is null");
/* 141 */     if (lodestone == null) {
/* 142 */       this.lodestoneWorld = null;
/*     */     } else {
/* 144 */       ResourceKey<World> key = ((CraftWorld)lodestone.getWorld()).getHandle().getDimensionKey();
/* 145 */       DataResult<NBTBase> dataresult = World.f.encodeStart((DynamicOps)DynamicOpsNBT.a, key);
/* 146 */       this.lodestoneWorld = (NBTTagString)dataresult.get().orThrow();
/* 147 */       this.lodestoneX = lodestone.getBlockX();
/* 148 */       this.lodestoneY = lodestone.getBlockY();
/* 149 */       this.lodestoneZ = lodestone.getBlockZ();
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean hasLodestoneTracked() {
/* 154 */     return (this.tracked != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLodestoneTracked() {
/* 159 */     return (hasLodestoneTracked() && this.tracked.booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLodestoneTracked(boolean tracked) {
/* 164 */     this.tracked = Boolean.valueOf(tracked);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 170 */     int original = super.applyHash(), hash = original;
/* 171 */     if (hasLodestone()) {
/* 172 */       hash = 73 * hash + this.lodestoneWorld.hashCode();
/* 173 */       hash = 73 * hash + this.lodestoneX;
/* 174 */       hash = 73 * hash + this.lodestoneY;
/* 175 */       hash = 73 * hash + this.lodestoneZ;
/*     */     } 
/* 177 */     if (hasLodestoneTracked()) {
/* 178 */       hash = 73 * hash + (isLodestoneTracked() ? 1231 : 1237);
/*     */     }
/*     */     
/* 181 */     return (original != hash) ? (CraftMetaCompass.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 186 */     if (!super.equalsCommon(meta)) {
/* 187 */       return false;
/*     */     }
/* 189 */     if (meta instanceof CraftMetaCompass) {
/* 190 */       CraftMetaCompass that = (CraftMetaCompass)meta;
/*     */       
/* 192 */       if (hasLodestone() ? (that.hasLodestone() && this.lodestoneWorld.asString().equals(that.lodestoneWorld.asString()) && this.lodestoneX == that.lodestoneX && this.lodestoneY == that.lodestoneY && this.lodestoneZ == that.lodestoneZ) : 
/*     */         
/* 194 */         !that.hasLodestone()) if (this.tracked == that.tracked); 
/*     */       return false;
/*     */     } 
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 202 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaCompass || isCompassEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 207 */     super.serialize(builder);
/*     */     
/* 209 */     if (hasLodestone()) {
/* 210 */       builder.put(LODESTONE_POS_WORLD.BUKKIT, this.lodestoneWorld.asString());
/* 211 */       builder.put(LODESTONE_POS_X.BUKKIT, Integer.valueOf(this.lodestoneX));
/* 212 */       builder.put(LODESTONE_POS_Y.BUKKIT, Integer.valueOf(this.lodestoneY));
/* 213 */       builder.put(LODESTONE_POS_Z.BUKKIT, Integer.valueOf(this.lodestoneZ));
/*     */     } 
/* 215 */     if (hasLodestoneTracked()) {
/* 216 */       builder.put(LODESTONE_TRACKED.BUKKIT, this.tracked);
/*     */     }
/*     */     
/* 219 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaCompass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */