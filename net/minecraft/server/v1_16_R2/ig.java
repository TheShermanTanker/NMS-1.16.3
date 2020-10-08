/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Consumer;
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
/*    */ 
/*    */ public class ig
/*    */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*    */ {
/*    */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/* 26 */     var0.accept(LootTables.ay, 
/* 27 */         LootTable.b()
/* 28 */         .a(LootSelector.a()
/* 29 */           .a(LootValueConstant.a(1))
/*    */ 
/*    */           
/* 32 */           .a(LootItem.a(Items.BOOK).a(5).b((new LootItemFunctionEnchant.a()).a(Enchantments.SOUL_SPEED)))
/*    */ 
/*    */           
/* 35 */           .a(LootItem.a(Items.IRON_BOOTS).a(8).b((new LootItemFunctionEnchant.a()).a(Enchantments.SOUL_SPEED)))
/* 36 */           .a(LootItem.a(Items.POTION).a(8).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:fire_resistance")))))
/* 37 */           .a(LootItem.a(Items.SPLASH_POTION).a(8).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:fire_resistance")))))
/*    */ 
/*    */           
/* 40 */           .a(LootItem.a(Items.POTION).a(10).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:water")))))
/* 41 */           .a(LootItem.a(Items.IRON_NUGGET).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(10.0F, 36.0F))))
/* 42 */           .a(LootItem.a(Items.ENDER_PEARL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/*    */ 
/*    */           
/* 45 */           .a(LootItem.a(Items.STRING).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 9.0F))))
/* 46 */           .a(LootItem.a(Items.QUARTZ).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 12.0F))))
/*    */ 
/*    */           
/* 49 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.co).a(40))
/* 50 */           .a(LootItem.a(Items.rA).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 51 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FIRE_CHARGE).a(40))
/* 52 */           .a(LootItem.a(Items.LEATHER).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 53 */           .a(LootItem.a(Items.dl).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 54 */           .a(LootItem.a(Items.NETHER_BRICK).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 55 */           .a(LootItem.a(Items.SPECTRAL_ARROW).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(6.0F, 12.0F))))
/* 56 */           .a(LootItem.a(Items.G).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 16.0F))))
/* 57 */           .a(LootItem.a(Items.rB).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 16.0F))))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */