/*    */ package org.bukkit.craftbukkit.v1_16_R2.tag;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.server.v1_16_R2.Block;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.Tags;
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ 
/*    */ public class CraftBlockTag extends CraftTag<Block, Material> {
/*    */   public CraftBlockTag(Tags<Block> registry, MinecraftKey tag) {
/* 15 */     super(registry, tag);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTagged(Material item) {
/* 20 */     return getHandle().isTagged(CraftMagicNumbers.getBlock(item));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Material> getValues() {
/* 25 */     return Collections.unmodifiableSet((Set<? extends Material>)getHandle().getTagged().stream().map(block -> CraftMagicNumbers.getMaterial(block)).collect(Collectors.toSet()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\tag\CraftBlockTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */