/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class Containers<T extends Container>
/*    */ {
/*  5 */   public static final Containers<ContainerChest> GENERIC_9X1 = a("generic_9x1", ContainerChest::a);
/*  6 */   public static final Containers<ContainerChest> GENERIC_9X2 = a("generic_9x2", ContainerChest::b);
/*  7 */   public static final Containers<ContainerChest> GENERIC_9X3 = a("generic_9x3", ContainerChest::c);
/*  8 */   public static final Containers<ContainerChest> GENERIC_9X4 = a("generic_9x4", ContainerChest::d);
/*  9 */   public static final Containers<ContainerChest> GENERIC_9X5 = a("generic_9x5", ContainerChest::e);
/* 10 */   public static final Containers<ContainerChest> GENERIC_9X6 = a("generic_9x6", ContainerChest::f);
/* 11 */   public static final Containers<ContainerDispenser> GENERIC_3X3 = a("generic_3x3", ContainerDispenser::new);
/* 12 */   public static final Containers<ContainerAnvil> ANVIL = a("anvil", ContainerAnvil::new);
/* 13 */   public static final Containers<ContainerBeacon> BEACON = a("beacon", ContainerBeacon::new);
/* 14 */   public static final Containers<ContainerBlastFurnace> BLAST_FURNACE = a("blast_furnace", ContainerBlastFurnace::new);
/* 15 */   public static final Containers<ContainerBrewingStand> BREWING_STAND = a("brewing_stand", ContainerBrewingStand::new);
/* 16 */   public static final Containers<ContainerWorkbench> CRAFTING = a("crafting", ContainerWorkbench::new);
/* 17 */   public static final Containers<ContainerEnchantTable> ENCHANTMENT = a("enchantment", ContainerEnchantTable::new);
/* 18 */   public static final Containers<ContainerFurnaceFurnace> FURNACE = a("furnace", ContainerFurnaceFurnace::new);
/* 19 */   public static final Containers<ContainerGrindstone> GRINDSTONE = a("grindstone", ContainerGrindstone::new);
/* 20 */   public static final Containers<ContainerHopper> HOPPER = a("hopper", ContainerHopper::new); static {
/* 21 */     LECTERN = a("lectern", (i, playerinventory) -> new ContainerLectern(i, playerinventory));
/*    */   }
/*    */   public static final Containers<ContainerLectern> LECTERN;
/* 24 */   public static final Containers<ContainerLoom> LOOM = a("loom", ContainerLoom::new);
/* 25 */   public static final Containers<ContainerMerchant> MERCHANT = a("merchant", ContainerMerchant::new);
/* 26 */   public static final Containers<ContainerShulkerBox> SHULKER_BOX = a("shulker_box", ContainerShulkerBox::new);
/* 27 */   public static final Containers<ContainerSmithing> SMITHING = a("smithing", ContainerSmithing::new);
/* 28 */   public static final Containers<ContainerSmoker> SMOKER = a("smoker", ContainerSmoker::new);
/* 29 */   public static final Containers<ContainerCartography> CARTOGRAPHY_TABLE = a("cartography_table", ContainerCartography::new);
/* 30 */   public static final Containers<ContainerStonecutter> STONECUTTER = a("stonecutter", ContainerStonecutter::new);
/*    */   private final Supplier<T> y;
/*    */   
/*    */   private static <T extends Container> Containers<T> a(String s, Supplier<T> containers_supplier) {
/* 34 */     return (Containers<T>)IRegistry.<Containers<?>>a(IRegistry.MENU, s, new Containers(containers_supplier));
/*    */   }
/*    */   
/*    */   private Containers(Supplier<T> containers_supplier) {
/* 38 */     this.y = containers_supplier;
/*    */   }
/*    */   
/*    */   static interface Supplier<T extends Container> {
/*    */     T supply(int param1Int, PlayerInventory param1PlayerInventory);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Containers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */