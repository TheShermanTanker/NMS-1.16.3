/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.BlockBannerAbstract;
/*     */ import net.minecraft.server.v1_16_R2.EnumColor;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBanner;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Banner;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.banner.Pattern;
/*     */ import org.bukkit.block.banner.PatternType;
/*     */ 
/*     */ public class CraftBanner extends CraftBlockEntityState<TileEntityBanner> implements Banner {
/*     */   private DyeColor base;
/*     */   private List<Pattern> patterns;
/*     */   
/*     */   public CraftBanner(Block block) {
/*  24 */     super(block, TileEntityBanner.class);
/*     */   }
/*     */   
/*     */   public CraftBanner(Material material, TileEntityBanner te) {
/*  28 */     super(material, te);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(TileEntityBanner banner) {
/*  33 */     super.load(banner);
/*     */     
/*  35 */     this.base = DyeColor.getByWoolData((byte)((BlockBannerAbstract)this.data.getBlock()).getColor().getColorIndex());
/*  36 */     this.patterns = new ArrayList<>();
/*     */     
/*  38 */     if (banner.patterns != null) {
/*  39 */       for (int i = 0; i < banner.patterns.size(); i++) {
/*  40 */         NBTTagCompound p = (NBTTagCompound)banner.patterns.get(i);
/*  41 */         this.patterns.add(new Pattern(DyeColor.getByWoolData((byte)p.getInt("Color")), PatternType.getByIdentifier(p.getString("Pattern"))));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getBaseColor() {
/*  48 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseColor(DyeColor color) {
/*  53 */     Preconditions.checkArgument((color != null), "color");
/*  54 */     this.base = color;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pattern> getPatterns() {
/*  59 */     return new ArrayList<>(this.patterns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPatterns(List<Pattern> patterns) {
/*  64 */     this.patterns = new ArrayList<>(patterns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPattern(Pattern pattern) {
/*  69 */     this.patterns.add(pattern);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern getPattern(int i) {
/*  74 */     return this.patterns.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern removePattern(int i) {
/*  79 */     return this.patterns.remove(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPattern(int i, Pattern pattern) {
/*  84 */     this.patterns.set(i, pattern);
/*     */   }
/*     */ 
/*     */   
/*     */   public int numberOfPatterns() {
/*  89 */     return this.patterns.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyTo(TileEntityBanner banner) {
/*  94 */     super.applyTo(banner);
/*     */     
/*  96 */     banner.color = EnumColor.fromColorIndex(this.base.getWoolData());
/*     */     
/*  98 */     NBTTagList newPatterns = new NBTTagList();
/*     */     
/* 100 */     for (Pattern p : this.patterns) {
/* 101 */       NBTTagCompound compound = new NBTTagCompound();
/* 102 */       compound.setInt("Color", p.getColor().getWoolData());
/* 103 */       compound.setString("Pattern", p.getPattern().getIdentifier());
/* 104 */       newPatterns.add(compound);
/*     */     } 
/* 106 */     banner.patterns = newPatterns;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */