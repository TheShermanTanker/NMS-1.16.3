/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.BiMap;
/*    */ import com.google.common.collect.ImmutableBiMap;
/*    */ import net.minecraft.server.v1_16_R2.IRegistry;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.Paintings;
/*    */ import org.bukkit.Art;
/*    */ 
/*    */ public class CraftArt {
/*    */   private static final BiMap<Paintings, Art> artwork;
/*    */   
/*    */   static {
/* 15 */     ImmutableBiMap.Builder<Paintings, Art> artworkBuilder = ImmutableBiMap.builder();
/* 16 */     for (MinecraftKey key : IRegistry.MOTIVE.keySet()) {
/* 17 */       artworkBuilder.put(IRegistry.MOTIVE.get(key), Art.getByName(key.getKey()));
/*    */     }
/*    */     
/* 20 */     artwork = (BiMap<Paintings, Art>)artworkBuilder.build();
/*    */   }
/*    */   
/*    */   public static Art NotchToBukkit(Paintings art) {
/* 24 */     Art bukkit = (Art)artwork.get(art);
/* 25 */     Preconditions.checkArgument((bukkit != null));
/* 26 */     return bukkit;
/*    */   }
/*    */   
/*    */   public static Paintings BukkitToNotch(Art art) {
/* 30 */     Paintings nms = (Paintings)artwork.inverse().get(art);
/* 31 */     Preconditions.checkArgument((nms != null));
/* 32 */     return nms;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftArt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */