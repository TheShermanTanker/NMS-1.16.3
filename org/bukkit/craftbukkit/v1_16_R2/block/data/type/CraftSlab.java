/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Slab;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftSlab extends CraftBlockData implements Slab {
/*  8 */   private static final BlockStateEnum<?> TYPE = getEnum("type");
/*    */ 
/*    */   
/*    */   public Slab.Type getType() {
/* 12 */     return (Slab.Type)get(TYPE, Slab.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(Slab.Type type) {
/* 17 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */