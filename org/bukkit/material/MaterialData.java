/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class MaterialData
/*     */   implements Cloneable
/*     */ {
/*     */   private final Material type;
/*  15 */   private byte data = 0;
/*     */   
/*     */   public MaterialData(Material type) {
/*  18 */     this(type, (byte)0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MaterialData(Material type, byte data) {
/*  28 */     this.type = type;
/*  29 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getData() {
/*  40 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setData(byte data) {
/*  51 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getItemType() {
/*  60 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ItemStack toItemStack() {
/*  72 */     return new ItemStack(this.type, 0, (short)this.data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack toItemStack(int amount) {
/*  82 */     return new ItemStack(this.type, amount, (short)this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  87 */     return getItemType() + "(" + getData() + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  92 */     return getItemType().hashCode() << 8 ^ getData();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (obj != null && obj instanceof MaterialData) {
/*  98 */       MaterialData md = (MaterialData)obj;
/*     */       
/* 100 */       return (md.getItemType() == getItemType() && md.getData() == getData());
/*     */     } 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MaterialData clone() {
/*     */     try {
/* 109 */       return (MaterialData)super.clone();
/* 110 */     } catch (CloneNotSupportedException e) {
/* 111 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\MaterialData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */