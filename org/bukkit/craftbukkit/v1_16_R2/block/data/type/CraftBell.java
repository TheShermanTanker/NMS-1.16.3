/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Bell;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftBell extends CraftBlockData implements Bell {
/*  8 */   private static final BlockStateEnum<?> ATTACHMENT = getEnum("attachment");
/*    */ 
/*    */   
/*    */   public Bell.Attachment getAttachment() {
/* 12 */     return (Bell.Attachment)get(ATTACHMENT, Bell.Attachment.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttachment(Bell.Attachment leaves) {
/* 17 */     set(ATTACHMENT, (Enum)leaves);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */