/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.entity.Sheep;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.SheepDyeWoolEvent;
/*    */ 
/*    */ public class ItemDye extends Item {
/* 10 */   private static final Map<EnumColor, ItemDye> a = Maps.newEnumMap(EnumColor.class);
/*    */   private final EnumColor b;
/*    */   
/*    */   public ItemDye(EnumColor enumcolor, Item.Info item_info) {
/* 14 */     super(item_info);
/* 15 */     this.b = enumcolor;
/* 16 */     a.put(enumcolor, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
/* 21 */     if (entityliving instanceof EntitySheep) {
/* 22 */       EntitySheep entitysheep = (EntitySheep)entityliving;
/*    */       
/* 24 */       if (entitysheep.isAlive() && !entitysheep.isSheared() && entitysheep.getColor() != this.b) {
/* 25 */         if (!entityhuman.world.isClientSide) {
/*    */           
/* 27 */           byte bColor = (byte)this.b.getColorIndex();
/* 28 */           SheepDyeWoolEvent event = new SheepDyeWoolEvent((Sheep)entitysheep.getBukkitEntity(), DyeColor.getByWoolData(bColor));
/* 29 */           entitysheep.world.getServer().getPluginManager().callEvent((Event)event);
/*    */           
/* 31 */           if (event.isCancelled()) {
/* 32 */             return EnumInteractionResult.PASS;
/*    */           }
/*    */           
/* 35 */           entitysheep.setColor(EnumColor.fromColorIndex(event.getColor().getWoolData()));
/*    */           
/* 37 */           itemstack.subtract(1);
/*    */         } 
/*    */         
/* 40 */         return EnumInteractionResult.a(entityhuman.world.isClientSide);
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */   
/*    */   public EnumColor d() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public static ItemDye a(EnumColor enumcolor) {
/* 52 */     return a.get(enumcolor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemDye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */