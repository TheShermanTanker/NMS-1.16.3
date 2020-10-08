/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PotionUtil
/*     */ {
/*  11 */   private static final IChatMutableComponent a = (new ChatMessage("effect.none")).a(EnumChatFormat.GRAY);
/*     */   
/*     */   public static List<MobEffect> getEffects(ItemStack itemstack) {
/*  14 */     return a(itemstack.getTag());
/*     */   }
/*     */   
/*     */   public static List<MobEffect> a(PotionRegistry potionregistry, Collection<MobEffect> collection) {
/*  18 */     List<MobEffect> list = Lists.newArrayList();
/*     */     
/*  20 */     list.addAll(potionregistry.a());
/*  21 */     list.addAll(collection);
/*  22 */     return list;
/*     */   }
/*     */   
/*     */   public static List<MobEffect> a(@Nullable NBTTagCompound nbttagcompound) {
/*  26 */     List<MobEffect> list = Lists.newArrayList();
/*     */     
/*  28 */     list.addAll(c(nbttagcompound).a());
/*  29 */     a(nbttagcompound, list);
/*  30 */     return list;
/*     */   }
/*     */   
/*     */   public static List<MobEffect> b(ItemStack itemstack) {
/*  34 */     return b(itemstack.getTag());
/*     */   }
/*     */   
/*     */   public static List<MobEffect> b(@Nullable NBTTagCompound nbttagcompound) {
/*  38 */     List<MobEffect> list = Lists.newArrayList();
/*     */     
/*  40 */     a(nbttagcompound, list);
/*  41 */     return list;
/*     */   }
/*     */   
/*     */   public static void a(@Nullable NBTTagCompound nbttagcompound, List<MobEffect> list) {
/*  45 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionEffects", 9)) {
/*  46 */       NBTTagList nbttaglist = nbttagcompound.getList("CustomPotionEffects", 10);
/*     */       
/*  48 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  49 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/*  50 */         MobEffect mobeffect = MobEffect.b(nbttagcompound1);
/*     */         
/*  52 */         if (mobeffect != null) {
/*  53 */           list.add(mobeffect);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int c(ItemStack itemstack) {
/*  61 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/*  63 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionColor", 99)) ? nbttagcompound.getInt("CustomPotionColor") : ((d(itemstack) == Potions.EMPTY) ? 16253176 : a(getEffects(itemstack)));
/*     */   }
/*     */   
/*     */   public static int a(PotionRegistry potionregistry) {
/*  67 */     return (potionregistry == Potions.EMPTY) ? 16253176 : a(potionregistry.a());
/*     */   }
/*     */   
/*     */   public static int a(Collection<MobEffect> collection) {
/*  71 */     int i = 3694022;
/*     */     
/*  73 */     if (collection.isEmpty()) {
/*  74 */       return 3694022;
/*     */     }
/*  76 */     float f = 0.0F;
/*  77 */     float f1 = 0.0F;
/*  78 */     float f2 = 0.0F;
/*  79 */     int j = 0;
/*  80 */     Iterator<MobEffect> iterator = collection.iterator();
/*     */     
/*  82 */     while (iterator.hasNext()) {
/*  83 */       MobEffect mobeffect = iterator.next();
/*     */       
/*  85 */       if (mobeffect.isShowParticles()) {
/*  86 */         int k = mobeffect.getMobEffect().getColor();
/*  87 */         int l = mobeffect.getAmplifier() + 1;
/*     */         
/*  89 */         f += (l * (k >> 16 & 0xFF)) / 255.0F;
/*  90 */         f1 += (l * (k >> 8 & 0xFF)) / 255.0F;
/*  91 */         f2 += (l * (k >> 0 & 0xFF)) / 255.0F;
/*  92 */         j += l;
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     if (j == 0) {
/*  97 */       return 0;
/*     */     }
/*  99 */     f = f / j * 255.0F;
/* 100 */     f1 = f1 / j * 255.0F;
/* 101 */     f2 = f2 / j * 255.0F;
/* 102 */     return (int)f << 16 | (int)f1 << 8 | (int)f2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PotionRegistry d(ItemStack itemstack) {
/* 108 */     return c(itemstack.getTag());
/*     */   }
/*     */   
/*     */   public static PotionRegistry c(@Nullable NBTTagCompound nbttagcompound) {
/* 112 */     return (nbttagcompound == null) ? Potions.EMPTY : PotionRegistry.a(nbttagcompound.getString("Potion"));
/*     */   }
/*     */   public static ItemStack addPotionToItemStack(ItemStack itemstack, PotionRegistry potionregistry) {
/* 115 */     return a(itemstack, potionregistry);
/*     */   } public static ItemStack a(ItemStack itemstack, PotionRegistry potionregistry) {
/* 117 */     MinecraftKey minecraftkey = IRegistry.POTION.getKey(potionregistry);
/*     */     
/* 119 */     if (potionregistry == Potions.EMPTY) {
/* 120 */       itemstack.removeTag("Potion");
/*     */     } else {
/* 122 */       itemstack.getOrCreateTag().setString("Potion", minecraftkey.toString());
/*     */     } 
/*     */     
/* 125 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static ItemStack a(ItemStack itemstack, Collection<MobEffect> collection) {
/* 129 */     if (collection.isEmpty()) {
/* 130 */       return itemstack;
/*     */     }
/* 132 */     NBTTagCompound nbttagcompound = itemstack.getOrCreateTag();
/* 133 */     NBTTagList nbttaglist = nbttagcompound.getList("CustomPotionEffects", 9);
/* 134 */     Iterator<MobEffect> iterator = collection.iterator();
/*     */     
/* 136 */     while (iterator.hasNext()) {
/* 137 */       MobEffect mobeffect = iterator.next();
/*     */       
/* 139 */       nbttaglist.add(mobeffect.a(new NBTTagCompound()));
/*     */     } 
/*     */     
/* 142 */     nbttagcompound.set("CustomPotionEffects", nbttaglist);
/* 143 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PotionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */