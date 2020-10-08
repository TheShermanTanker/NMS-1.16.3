/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.BiFunction;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public interface ContainerAccess {
/*    */   default World getWorld() {
/* 11 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */   
/*    */   default BlockPosition getPosition() {
/* 15 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */   
/*    */   default Location getLocation() {
/* 19 */     return new Location((World)getWorld().getWorld(), getPosition().getX(), getPosition().getY(), getPosition().getZ());
/*    */   }
/*    */ 
/*    */   
/* 23 */   public static final ContainerAccess a = new ContainerAccess()
/*    */     {
/*    */       public <T> Optional<T> a(BiFunction<World, BlockPosition, T> bifunction) {
/* 26 */         return Optional.empty();
/*    */       }
/*    */     };
/*    */   
/*    */   static ContainerAccess at(final World world, final BlockPosition blockposition) {
/* 31 */     return new ContainerAccess()
/*    */       {
/*    */         public World getWorld()
/*    */         {
/* 35 */           return world;
/*    */         }
/*    */ 
/*    */         
/*    */         public BlockPosition getPosition() {
/* 40 */           return blockposition;
/*    */         }
/*    */ 
/*    */ 
/*    */         
/*    */         public <T> Optional<T> a(BiFunction<World, BlockPosition, T> bifunction) {
/* 46 */           return Optional.of(bifunction.apply(world, blockposition));
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   <T> Optional<T> a(BiFunction<World, BlockPosition, T> paramBiFunction);
/*    */   
/*    */   default <T> T a(BiFunction<World, BlockPosition, T> bifunction, T t0) {
/* 54 */     return a(bifunction).orElse(t0);
/*    */   }
/*    */   
/*    */   default void a(BiConsumer<World, BlockPosition> biconsumer) {
/* 58 */     a((world, blockposition) -> {
/*    */           biconsumer.accept(world, blockposition);
/*    */           return Optional.empty();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */