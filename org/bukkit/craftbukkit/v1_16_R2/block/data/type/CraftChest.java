/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Chest;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftChest extends CraftBlockData implements Chest {
/*  8 */   private static final BlockStateEnum<?> TYPE = getEnum("type");
/*    */ 
/*    */   
/*    */   public Chest.Type getType() {
/* 12 */     return (Chest.Type)get(TYPE, Chest.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(Chest.Type type) {
/* 17 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */