/*    */ package org.bukkit.craftbukkit.v1_16_R2.tag;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.server.v1_16_R2.FluidType;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.Tags;
/*    */ import org.bukkit.Fluid;
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ 
/*    */ public class CraftFluidTag extends CraftTag<FluidType, Fluid> {
/*    */   public CraftFluidTag(Tags<FluidType> registry, MinecraftKey tag) {
/* 15 */     super(registry, tag);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTagged(Fluid fluid) {
/* 20 */     return getHandle().isTagged(CraftMagicNumbers.getFluid(fluid));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Fluid> getValues() {
/* 25 */     return Collections.unmodifiableSet((Set<? extends Fluid>)getHandle().getTagged().stream().map(CraftMagicNumbers::getFluid).collect(Collectors.toSet()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\tag\CraftFluidTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */