/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSetBlock
/*    */ {
/* 29 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.setblock.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 32 */     var0.register(
/* 33 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("setblock")
/* 34 */         .requires(var0 -> var0.hasPermission(2)))
/* 35 */         .then(
/* 36 */           CommandDispatcher.<T>a("pos", ArgumentPosition.a())
/* 37 */           .then((
/* 38 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("block", ArgumentTile.a())
/* 39 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentTile.a(var0, "block"), Mode.REPLACE, null)))
/* 40 */             .then(
/* 41 */               CommandDispatcher.a("destroy")
/* 42 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentTile.a(var0, "block"), Mode.DESTROY, null))))
/*    */             
/* 44 */             .then(
/* 45 */               CommandDispatcher.a("keep")
/* 46 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentTile.a(var0, "block"), Mode.REPLACE, ()))))
/*    */             
/* 48 */             .then(
/* 49 */               CommandDispatcher.a("replace")
/* 50 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentTile.a(var0, "block"), Mode.REPLACE, null))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, BlockPosition var1, ArgumentTileLocation var2, Mode var3, @Nullable Predicate<ShapeDetectorBlock> var4) throws CommandSyntaxException {
/*    */     boolean var6;
/* 58 */     WorldServer var5 = var0.getWorld();
/* 59 */     if (var4 != null && !var4.test(new ShapeDetectorBlock(var5, var1, true))) {
/* 60 */       throw a.create();
/*    */     }
/*    */ 
/*    */     
/* 64 */     if (var3 == Mode.DESTROY) {
/* 65 */       var5.b(var1, true);
/* 66 */       var6 = (!var2.a().isAir() || !var5.getType(var1).isAir());
/*    */     } else {
/* 68 */       TileEntity var7 = var5.getTileEntity(var1);
/* 69 */       Clearable.a(var7);
/* 70 */       var6 = true;
/*    */     } 
/* 72 */     if (var6 && !var2.a(var5, var1, 2)) {
/* 73 */       throw a.create();
/*    */     }
/*    */     
/* 76 */     var5.update(var1, var2.a().getBlock());
/* 77 */     var0.sendMessage(new ChatMessage("commands.setblock.success", new Object[] { Integer.valueOf(var1.getX()), Integer.valueOf(var1.getY()), Integer.valueOf(var1.getZ()) }), true);
/* 78 */     return 1;
/*    */   } public static interface Filter {
/*    */     @Nullable
/*    */     ArgumentTileLocation filter(StructureBoundingBox param1StructureBoundingBox, BlockPosition param1BlockPosition, ArgumentTileLocation param1ArgumentTileLocation, WorldServer param1WorldServer); }
/* 82 */   public enum Mode { REPLACE,
/* 83 */     DESTROY; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSetBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */