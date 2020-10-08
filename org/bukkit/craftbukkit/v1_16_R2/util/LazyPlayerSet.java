/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class LazyPlayerSet extends LazyHashSet<Player> {
/*    */   private final MinecraftServer server;
/*    */   
/*    */   public LazyPlayerSet(MinecraftServer server) {
/* 14 */     this.server = server;
/*    */   }
/*    */ 
/*    */   
/*    */   HashSet<Player> makeReference() {
/* 19 */     if (this.reference != null) {
/* 20 */       throw new IllegalStateException("Reference already created!");
/*    */     }
/* 22 */     List<EntityPlayer> players = (this.server.getPlayerList()).players;
/* 23 */     HashSet<Player> reference = new HashSet<>(players.size());
/* 24 */     for (EntityPlayer player : players) {
/* 25 */       reference.add(player.getBukkitEntity());
/*    */     }
/* 27 */     return reference;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\LazyPlayerSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */