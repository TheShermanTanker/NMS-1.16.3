/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class id
/*     */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*     */ {
/*     */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/*  24 */     var0.accept(LootTables.ak, 
/*  25 */         LootTable.b()
/*  26 */         .a(LootSelector.a()
/*  27 */           .a(LootValueConstant.a(1))
/*  28 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.RABBIT_HIDE).a(10))
/*  29 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.RABBIT_FOOT).a(10))
/*  30 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.CHICKEN).a(10))
/*  31 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FEATHER).a(10))
/*  32 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ROTTEN_FLESH).a(10))
/*  33 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).a(10))
/*  34 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.PHANTOM_MEMBRANE).a(2))));
/*     */ 
/*     */     
/*  37 */     var0.accept(LootTables.al, 
/*  38 */         LootTable.b()
/*  39 */         .a(LootSelector.a()
/*  40 */           .a(LootValueConstant.a(1))
/*  41 */           .a(LootItem.a(Items.CHAINMAIL_HELMET))
/*  42 */           .a(LootItem.a(Items.CHAINMAIL_CHESTPLATE))
/*  43 */           .a(LootItem.a(Items.CHAINMAIL_LEGGINGS))
/*  44 */           .a(LootItem.a(Items.CHAINMAIL_BOOTS))));
/*     */ 
/*     */     
/*  47 */     var0.accept(LootTables.am, 
/*  48 */         LootTable.b()
/*  49 */         .a(LootSelector.a()
/*  50 */           .a(LootValueConstant.a(1))
/*  51 */           .a(LootItem.a(Items.COOKED_RABBIT))
/*  52 */           .a(LootItem.a(Items.COOKED_CHICKEN))
/*  53 */           .a(LootItem.a(Items.COOKED_PORKCHOP))
/*  54 */           .a(LootItem.a(Items.COOKED_BEEF))
/*  55 */           .a(LootItem.a(Items.COOKED_MUTTON))));
/*     */ 
/*     */     
/*  58 */     var0.accept(LootTables.an, 
/*  59 */         LootTable.b()
/*  60 */         .a(LootSelector.a()
/*  61 */           .a(LootValueConstant.a(1))
/*  62 */           .a(LootItem.a(Items.MAP))
/*  63 */           .a(LootItem.a(Items.PAPER))));
/*     */ 
/*     */     
/*  66 */     var0.accept(LootTables.ao, 
/*  67 */         LootTable.b()
/*  68 */         .a(LootSelector.a()
/*  69 */           .a(LootValueConstant.a(1))
/*  70 */           .a(LootItem.a(Items.REDSTONE))
/*  71 */           .a(LootItem.a(Items.LAPIS_LAZULI))));
/*     */ 
/*     */     
/*  74 */     var0.accept(LootTables.ap, 
/*  75 */         LootTable.b()
/*  76 */         .a(LootSelector.a()
/*  77 */           .a(LootValueConstant.a(1))
/*  78 */           .a(LootItem.a(Items.BREAD))
/*  79 */           .a(LootItem.a(Items.PUMPKIN_PIE))
/*  80 */           .a(LootItem.a(Items.COOKIE))));
/*     */ 
/*     */ 
/*     */     
/*  84 */     var0.accept(LootTables.aq, 
/*  85 */         LootTable.b()
/*  86 */         .a(LootSelector.a()
/*  87 */           .a(LootValueConstant.a(1))
/*  88 */           .a(LootItem.a(Items.COD))
/*  89 */           .a(LootItem.a(Items.SALMON))));
/*     */ 
/*     */     
/*  92 */     var0.accept(LootTables.ar, 
/*  93 */         LootTable.b()
/*  94 */         .a(LootSelector.a()
/*  95 */           .a(LootValueConstant.a(1))
/*  96 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ARROW).a(26))
/*  97 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:swiftness")))))
/*  98 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:slowness")))))
/*  99 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:strength")))))
/* 100 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:healing")))))
/* 101 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:harming")))))
/* 102 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:leaping")))))
/* 103 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:regeneration")))))
/* 104 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:fire_resistance")))))
/* 105 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:water_breathing")))))
/* 106 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:invisibility")))))
/* 107 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:night_vision")))))
/* 108 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:weakness")))))
/* 109 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:poison")))))));
/*     */ 
/*     */     
/* 112 */     var0.accept(LootTables.as, 
/* 113 */         LootTable.b()
/* 114 */         .a(LootSelector.a()
/* 115 */           .a(LootValueConstant.a(1))
/* 116 */           .a(LootItem.a(Items.LEATHER))));
/*     */ 
/*     */     
/* 119 */     var0.accept(LootTables.at, 
/* 120 */         LootTable.b()
/* 121 */         .a(LootSelector.a()
/* 122 */           .a(LootValueConstant.a(1))
/* 123 */           .a(LootItem.a(Items.BOOK))));
/*     */ 
/*     */     
/* 126 */     var0.accept(LootTables.au, 
/* 127 */         LootTable.b()
/* 128 */         .a(LootSelector.a()
/* 129 */           .a(LootValueConstant.a(1))
/* 130 */           .a(LootItem.a(Items.cY))));
/*     */ 
/*     */     
/* 133 */     var0.accept(LootTables.av, 
/* 134 */         LootTable.b()
/* 135 */         .a(LootSelector.a()
/* 136 */           .a(LootValueConstant.a(1))
/* 137 */           .a(LootItem.a(Items.aR))
/* 138 */           .a(LootItem.a(Items.aS))
/* 139 */           .a(LootItem.a(Items.aT))
/* 140 */           .a(LootItem.a(Items.aU))
/* 141 */           .a(LootItem.a(Items.aV))
/* 142 */           .a(LootItem.a(Items.aW))
/* 143 */           .a(LootItem.a(Items.aX))
/* 144 */           .a(LootItem.a(Items.aY))
/* 145 */           .a(LootItem.a(Items.aZ))
/* 146 */           .a(LootItem.a(Items.ba))
/* 147 */           .a(LootItem.a(Items.bb))
/* 148 */           .a(LootItem.a(Items.bc))
/* 149 */           .a(LootItem.a(Items.bd))
/* 150 */           .a(LootItem.a(Items.be))
/* 151 */           .a(LootItem.a(Items.bf))
/* 152 */           .a(LootItem.a(Items.bg))));
/*     */ 
/*     */     
/* 155 */     var0.accept(LootTables.aw, 
/* 156 */         LootTable.b()
/* 157 */         .a(LootSelector.a()
/* 158 */           .a(LootValueConstant.a(1))
/* 159 */           .a(LootItem.a(Items.STONE_PICKAXE))
/* 160 */           .a(LootItem.a(Items.STONE_AXE))
/* 161 */           .a(LootItem.a(Items.STONE_HOE))
/* 162 */           .a(LootItem.a(Items.STONE_SHOVEL))));
/*     */ 
/*     */     
/* 165 */     var0.accept(LootTables.ax, 
/* 166 */         LootTable.b()
/* 167 */         .a(LootSelector.a()
/* 168 */           .a(LootValueConstant.a(1))
/* 169 */           .a(LootItem.a(Items.STONE_AXE))
/* 170 */           .a(LootItem.a(Items.GOLDEN_AXE))
/* 171 */           .a(LootItem.a(Items.IRON_AXE))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\id.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */