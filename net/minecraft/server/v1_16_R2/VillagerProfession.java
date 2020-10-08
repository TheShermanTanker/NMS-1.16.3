/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
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
/*    */ public class VillagerProfession
/*    */ {
/* 17 */   public static final VillagerProfession NONE = a("none", VillagePlaceType.c, null);
/* 18 */   public static final VillagerProfession ARMORER = a("armorer", VillagePlaceType.d, SoundEffects.ENTITY_VILLAGER_WORK_ARMORER);
/* 19 */   public static final VillagerProfession BUTCHER = a("butcher", VillagePlaceType.e, SoundEffects.ENTITY_VILLAGER_WORK_BUTCHER);
/* 20 */   public static final VillagerProfession CARTOGRAPHER = a("cartographer", VillagePlaceType.f, SoundEffects.ENTITY_VILLAGER_WORK_CARTOGRAPHER);
/* 21 */   public static final VillagerProfession CLERIC = a("cleric", VillagePlaceType.g, SoundEffects.ENTITY_VILLAGER_WORK_CLERIC);
/* 22 */   public static final VillagerProfession FARMER = a("farmer", VillagePlaceType.h, ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.BONE_MEAL), ImmutableSet.of(Blocks.FARMLAND), SoundEffects.ENTITY_VILLAGER_WORK_FARMER);
/* 23 */   public static final VillagerProfession FISHERMAN = a("fisherman", VillagePlaceType.i, SoundEffects.ENTITY_VILLAGER_WORK_FISHERMAN);
/* 24 */   public static final VillagerProfession FLETCHER = a("fletcher", VillagePlaceType.j, SoundEffects.ENTITY_VILLAGER_WORK_FLETCHER);
/* 25 */   public static final VillagerProfession LEATHERWORKER = a("leatherworker", VillagePlaceType.k, SoundEffects.ENTITY_VILLAGER_WORK_LEATHERWORKER);
/* 26 */   public static final VillagerProfession LIBRARIAN = a("librarian", VillagePlaceType.l, SoundEffects.ENTITY_VILLAGER_WORK_LIBRARIAN);
/* 27 */   public static final VillagerProfession MASON = a("mason", VillagePlaceType.m, SoundEffects.ENTITY_VILLAGER_WORK_MASON);
/* 28 */   public static final VillagerProfession NITWIT = a("nitwit", VillagePlaceType.n, null);
/* 29 */   public static final VillagerProfession SHEPHERD = a("shepherd", VillagePlaceType.o, SoundEffects.ENTITY_VILLAGER_WORK_SHEPHERD);
/* 30 */   public static final VillagerProfession TOOLSMITH = a("toolsmith", VillagePlaceType.p, SoundEffects.ENTITY_VILLAGER_WORK_TOOLSMITH);
/* 31 */   public static final VillagerProfession WEAPONSMITH = a("weaponsmith", VillagePlaceType.q, SoundEffects.ENTITY_VILLAGER_WORK_WEAPONSMITH);
/*    */   
/*    */   private final String p;
/*    */   private final VillagePlaceType q;
/*    */   private final ImmutableSet<Item> r;
/*    */   private final ImmutableSet<Block> s;
/*    */   @Nullable
/*    */   private final SoundEffect t;
/*    */   
/*    */   private VillagerProfession(String var0, VillagePlaceType var1, ImmutableSet<Item> var2, ImmutableSet<Block> var3, @Nullable SoundEffect var4) {
/* 41 */     this.p = var0;
/* 42 */     this.q = var1;
/* 43 */     this.r = var2;
/* 44 */     this.s = var3;
/* 45 */     this.t = var4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public VillagePlaceType b() {
/* 53 */     return this.q;
/*    */   }
/*    */   
/*    */   public ImmutableSet<Item> c() {
/* 57 */     return this.r;
/*    */   }
/*    */   
/*    */   public ImmutableSet<Block> d() {
/* 61 */     return this.s;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public SoundEffect e() {
/* 66 */     return this.t;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return this.p;
/*    */   }
/*    */   
/*    */   static VillagerProfession a(String var0, VillagePlaceType var1, @Nullable SoundEffect var2) {
/* 75 */     return a(var0, var1, ImmutableSet.of(), ImmutableSet.of(), var2);
/*    */   }
/*    */   
/*    */   static VillagerProfession a(String var0, VillagePlaceType var1, ImmutableSet<Item> var2, ImmutableSet<Block> var3, @Nullable SoundEffect var4) {
/* 79 */     return IRegistry.<VillagerProfession, VillagerProfession>a(IRegistry.VILLAGER_PROFESSION, new MinecraftKey(var0), new VillagerProfession(var0, var1, var2, var3, var4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagerProfession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */