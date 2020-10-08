/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.FaceAttachable;
/*    */ 
/*    */ public abstract class CraftFaceAttachable extends CraftBlockData implements FaceAttachable {
/*  7 */   private static final BlockStateEnum<?> ATTACH_FACE = getEnum("face");
/*    */ 
/*    */   
/*    */   public FaceAttachable.AttachedFace getAttachedFace() {
/* 11 */     return get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttachedFace(FaceAttachable.AttachedFace face) {
/* 16 */     set(ATTACH_FACE, (Enum<Enum>)face);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftFaceAttachable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */