/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*     */ import net.minecraft.server.v1_16_R2.EnumColor;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySign;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ 
/*     */ public class CraftSign extends CraftBlockEntityState<TileEntitySign> implements Sign {
/*     */   private String[] lines;
/*     */   private boolean editable;
/*     */   
/*     */   public CraftSign(Block block) {
/*  19 */     super(block, TileEntitySign.class);
/*  20 */     if (this.lines == null) this.lines = new String[] { "", "", "", "" }; 
/*     */   }
/*     */   
/*     */   public CraftSign(Material material, TileEntitySign te) {
/*  24 */     super(material, te);
/*  25 */     if (this.lines == null) this.lines = new String[] { "", "", "", "" };
/*     */   
/*     */   }
/*     */   
/*     */   public void load(TileEntitySign sign) {
/*  30 */     super.load(sign);
/*     */     
/*  32 */     this.lines = new String[sign.lines.length];
/*  33 */     System.arraycopy(revertComponents(sign.lines), 0, this.lines, 0, this.lines.length);
/*  34 */     this.editable = sign.isEditable;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getLines() {
/*  39 */     return this.lines;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLine(int index) throws IndexOutOfBoundsException {
/*  44 */     return this.lines[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLine(int index, String line) throws IndexOutOfBoundsException {
/*  49 */     this.lines[index] = line;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/*  54 */     return this.editable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/*  59 */     this.editable = editable;
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getColor() {
/*  64 */     return DyeColor.getByWoolData((byte)getSnapshot().getColor().getColorIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(DyeColor color) {
/*  69 */     getSnapshot().setColor(EnumColor.fromColorIndex(color.getWoolData()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyTo(TileEntitySign sign) {
/*  74 */     super.applyTo(sign);
/*     */     
/*  76 */     IChatBaseComponent[] newLines = sanitizeLines(this.lines);
/*  77 */     System.arraycopy(newLines, 0, sign.lines, 0, 4);
/*  78 */     sign.isEditable = this.editable;
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent[] sanitizeLines(String[] lines) {
/*  82 */     IChatBaseComponent[] components = new IChatBaseComponent[4];
/*     */     
/*  84 */     for (int i = 0; i < 4; i++) {
/*  85 */       if (i < lines.length && lines[i] != null) {
/*  86 */         components[i] = CraftChatMessage.fromString(lines[i])[0];
/*     */       } else {
/*  88 */         components[i] = (IChatBaseComponent)new ChatComponentText("");
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     return components;
/*     */   }
/*     */   
/*     */   public static String[] revertComponents(IChatBaseComponent[] components) {
/*  96 */     String[] lines = new String[components.length];
/*  97 */     for (int i = 0; i < lines.length; i++) {
/*  98 */       lines[i] = revertComponent(components[i]);
/*     */     }
/* 100 */     return lines;
/*     */   }
/*     */   
/*     */   private static String revertComponent(IChatBaseComponent component) {
/* 104 */     return CraftChatMessage.fromComponent(component);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */