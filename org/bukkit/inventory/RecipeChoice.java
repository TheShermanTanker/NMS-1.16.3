/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Tag;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface RecipeChoice
/*     */   extends Predicate<ItemStack>, Cloneable
/*     */ {
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getItemStack();
/*     */   
/*     */   @NotNull
/*     */   RecipeChoice clone();
/*     */   
/*     */   boolean test(@NotNull ItemStack paramItemStack);
/*     */   
/*     */   public static class MaterialChoice
/*     */     implements RecipeChoice
/*     */   {
/*     */     private List<Material> choices;
/*     */     
/*     */     public MaterialChoice(@NotNull Material choice) {
/*  46 */       this(Arrays.asList(new Material[] { choice }));
/*     */     }
/*     */     
/*     */     public MaterialChoice(@NotNull Material... choices) {
/*  50 */       this(Arrays.asList(choices));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MaterialChoice(@NotNull Tag<Material> choices) {
/*  60 */       Preconditions.checkArgument((choices != null), "choices");
/*  61 */       this.choices = new ArrayList<>(choices.getValues());
/*     */     }
/*     */     
/*     */     public MaterialChoice(@NotNull List<Material> choices) {
/*  65 */       Preconditions.checkArgument((choices != null), "choices");
/*  66 */       Preconditions.checkArgument(!choices.isEmpty(), "Must have at least one choice");
/*  67 */       for (Material choice : choices) {
/*  68 */         Preconditions.checkArgument((choice != null), "Cannot have null choice");
/*     */       }
/*     */       
/*  71 */       this.choices = new ArrayList<>(choices);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(@NotNull ItemStack t) {
/*  76 */       for (Material match : this.choices) {
/*  77 */         if (t.getType() == match) {
/*  78 */           return true;
/*     */         }
/*     */       } 
/*     */       
/*  82 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public ItemStack getItemStack() {
/*  88 */       ItemStack stack = new ItemStack(this.choices.get(0));
/*     */ 
/*     */       
/*  91 */       if (this.choices.size() > 1) {
/*  92 */         stack.setDurability('翿');
/*     */       }
/*     */       
/*  95 */       return stack;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public List<Material> getChoices() {
/* 100 */       return Collections.unmodifiableList(this.choices);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public MaterialChoice clone() {
/*     */       try {
/* 107 */         MaterialChoice clone = (MaterialChoice)super.clone();
/* 108 */         clone.choices = new ArrayList<>(this.choices);
/* 109 */         return clone;
/* 110 */       } catch (CloneNotSupportedException ex) {
/* 111 */         throw new AssertionError(ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 117 */       int hash = 3;
/* 118 */       hash = 37 * hash + Objects.hashCode(this.choices);
/* 119 */       return hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 124 */       if (this == obj) {
/* 125 */         return true;
/*     */       }
/* 127 */       if (obj == null) {
/* 128 */         return false;
/*     */       }
/* 130 */       if (getClass() != obj.getClass()) {
/* 131 */         return false;
/*     */       }
/* 133 */       MaterialChoice other = (MaterialChoice)obj;
/* 134 */       if (!Objects.equals(this.choices, other.choices)) {
/* 135 */         return false;
/*     */       }
/* 137 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 142 */       return "MaterialChoice{choices=" + this.choices + '}';
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static class ExactChoice
/*     */     implements RecipeChoice
/*     */   {
/*     */     private List<ItemStack> choices;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ExactChoice(@NotNull ItemStack stack) {
/* 160 */       this(Arrays.asList(new ItemStack[] { stack }));
/*     */     }
/*     */     
/*     */     public ExactChoice(@NotNull ItemStack... stacks) {
/* 164 */       this(Arrays.asList(stacks));
/*     */     }
/*     */     
/*     */     public ExactChoice(@NotNull List<ItemStack> choices) {
/* 168 */       Preconditions.checkArgument((choices != null), "choices");
/* 169 */       Preconditions.checkArgument(!choices.isEmpty(), "Must have at least one choice");
/* 170 */       for (ItemStack choice : choices) {
/* 171 */         Preconditions.checkArgument((choice != null), "Cannot have null choice");
/*     */       }
/*     */       
/* 174 */       this.choices = new ArrayList<>(choices);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public ItemStack getItemStack() {
/* 180 */       return ((ItemStack)this.choices.get(0)).clone();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public List<ItemStack> getChoices() {
/* 185 */       return Collections.unmodifiableList(this.choices);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public ExactChoice clone() {
/*     */       try {
/* 192 */         ExactChoice clone = (ExactChoice)super.clone();
/* 193 */         clone.choices = new ArrayList<>(this.choices);
/* 194 */         return clone;
/* 195 */       } catch (CloneNotSupportedException ex) {
/* 196 */         throw new AssertionError(ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(@NotNull ItemStack t) {
/* 202 */       for (ItemStack match : this.choices) {
/* 203 */         if (t.isSimilar(match)) {
/* 204 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 208 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 213 */       int hash = 7;
/* 214 */       hash = 41 * hash + Objects.hashCode(this.choices);
/* 215 */       return hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 220 */       if (this == obj) {
/* 221 */         return true;
/*     */       }
/* 223 */       if (obj == null) {
/* 224 */         return false;
/*     */       }
/* 226 */       if (getClass() != obj.getClass()) {
/* 227 */         return false;
/*     */       }
/* 229 */       ExactChoice other = (ExactChoice)obj;
/* 230 */       if (!Objects.equals(this.choices, other.choices)) {
/* 231 */         return false;
/*     */       }
/* 233 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 238 */       return "ExactChoice{choices=" + this.choices + '}';
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\RecipeChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */