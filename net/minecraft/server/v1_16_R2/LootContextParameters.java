/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class LootContextParameters
/*    */ {
/*  5 */   public static final LootContextParameter<Entity> THIS_ENTITY = a("this_entity");
/*  6 */   public static final LootContextParameter<EntityHuman> LAST_DAMAGE_PLAYER = a("last_damage_player");
/*  7 */   public static final LootContextParameter<DamageSource> DAMAGE_SOURCE = a("damage_source");
/*  8 */   public static final LootContextParameter<Entity> KILLER_ENTITY = a("killer_entity");
/*  9 */   public static final LootContextParameter<Entity> DIRECT_KILLER_ENTITY = a("direct_killer_entity");
/* 10 */   public static final LootContextParameter<Vec3D> ORIGIN = a("origin");
/* 11 */   public static final LootContextParameter<IBlockData> BLOCK_STATE = a("block_state");
/* 12 */   public static final LootContextParameter<TileEntity> BLOCK_ENTITY = a("block_entity");
/* 13 */   public static final LootContextParameter<ItemStack> TOOL = a("tool");
/* 14 */   public static final LootContextParameter<Float> EXPLOSION_RADIUS = a("explosion_radius");
/* 15 */   public static final LootContextParameter<Integer> LOOTING_MOD = new LootContextParameter<>(new MinecraftKey("bukkit:looting_mod"));
/*    */   
/*    */   private static <T> LootContextParameter<T> a(String s) {
/* 18 */     return new LootContextParameter<>(new MinecraftKey(s));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootContextParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */