/*    */ package org.bukkit.craftbukkit.v1_16_R2.potion;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.MobEffect;
/*    */ import net.minecraft.server.v1_16_R2.PotionRegistry;
/*    */ import org.bukkit.potion.PotionBrewer;
/*    */ import org.bukkit.potion.PotionData;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.potion.PotionType;
/*    */ 
/*    */ public class CraftPotionBrewer implements PotionBrewer {
/* 18 */   private static final Map<PotionType, Collection<PotionEffect>> cache = Maps.newHashMap();
/*    */ 
/*    */   
/*    */   public Collection<PotionEffect> getEffects(PotionType damage, boolean upgraded, boolean extended) {
/* 22 */     if (cache.containsKey(damage)) {
/* 23 */       return cache.get(damage);
/*    */     }
/* 25 */     List<MobEffect> mcEffects = PotionRegistry.a(CraftPotionUtil.fromBukkit(new PotionData(damage, extended, upgraded))).a();
/*    */     
/* 27 */     ImmutableList.Builder<PotionEffect> builder = new ImmutableList.Builder();
/* 28 */     for (MobEffect effect : mcEffects) {
/* 29 */       builder.add(CraftPotionUtil.toBukkit(effect));
/*    */     }
/*    */     
/* 32 */     cache.put(damage, builder.build());
/*    */     
/* 34 */     return cache.get(damage);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<PotionEffect> getEffectsFromDamage(int damage) {
/* 39 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public PotionEffect createEffect(PotionEffectType potion, int duration, int amplifier) {
/* 44 */     return new PotionEffect(potion, potion.isInstant() ? 1 : (int)(duration * potion.getDurationModifier()), amplifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\potion\CraftPotionBrewer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */