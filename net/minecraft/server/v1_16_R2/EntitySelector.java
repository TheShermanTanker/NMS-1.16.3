/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class EntitySelector
/*     */ {
/*     */   private final int a;
/*     */   private final boolean b;
/*     */   private final boolean c;
/*     */   private final Predicate<Entity> d;
/*     */   private final CriterionConditionValue.FloatRange e;
/*     */   private final Function<Vec3D, Vec3D> f;
/*     */   
/*     */   public EntitySelector(int i, boolean flag, boolean flag1, Predicate<Entity> predicate, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange, Function<Vec3D, Vec3D> function, @Nullable AxisAlignedBB axisalignedbb, BiConsumer<Vec3D, List<? extends Entity>> biconsumer, boolean flag2, @Nullable String s, @Nullable UUID uuid, @Nullable EntityTypes<?> entitytypes, boolean flag3) {
/*  35 */     this.a = i;
/*  36 */     this.b = flag;
/*  37 */     this.c = flag1;
/*  38 */     this.d = predicate;
/*  39 */     this.e = criterionconditionvalue_floatrange;
/*  40 */     this.f = function;
/*  41 */     this.g = axisalignedbb;
/*  42 */     this.h = biconsumer;
/*  43 */     this.i = flag2;
/*  44 */     this.j = s;
/*  45 */     this.k = uuid;
/*  46 */     this.l = entitytypes;
/*  47 */     this.checkPermissions = flag3; } @Nullable
/*     */   private final AxisAlignedBB g; private final BiConsumer<Vec3D, List<? extends Entity>> h; private final boolean i; @Nullable
/*     */   private final String j; @Nullable
/*     */   private final UUID k; @Nullable
/*  51 */   private final EntityTypes<?> l; private final boolean checkPermissions; public int a() { return this.a; }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  55 */     return this.b;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  59 */     return this.i;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  63 */     return this.c;
/*     */   }
/*     */   
/*     */   private void e(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
/*  67 */     if (this.checkPermissions && !commandlistenerwrapper.hasPermission(2, "minecraft.command.selector")) {
/*  68 */       throw ArgumentEntity.f.create();
/*     */     }
/*     */   }
/*     */   
/*     */   public Entity a(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
/*  73 */     e(commandlistenerwrapper);
/*  74 */     List<? extends Entity> list = getEntities(commandlistenerwrapper);
/*     */     
/*  76 */     if (list.isEmpty())
/*  77 */       throw ArgumentEntity.d.create(); 
/*  78 */     if (list.size() > 1) {
/*  79 */       throw ArgumentEntity.a.create();
/*     */     }
/*  81 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<? extends Entity> getEntities(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
/*  86 */     e(commandlistenerwrapper);
/*  87 */     if (!this.b)
/*  88 */       return (List)d(commandlistenerwrapper); 
/*  89 */     if (this.j != null) {
/*  90 */       EntityPlayer entityplayer = commandlistenerwrapper.getServer().getPlayerList().getPlayer(this.j);
/*     */       
/*  92 */       return (entityplayer == null) ? Collections.<Entity>emptyList() : Lists.newArrayList((Object[])new EntityPlayer[] { entityplayer });
/*  93 */     }  if (this.k != null) {
/*  94 */       Entity entity; Iterator<WorldServer> iterator = commandlistenerwrapper.getServer().getWorlds().iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/*  99 */         if (!iterator.hasNext()) {
/* 100 */           return Collections.emptyList();
/*     */         }
/*     */         
/* 103 */         WorldServer worldserver = iterator.next();
/*     */         
/* 105 */         entity = worldserver.getEntity(this.k);
/* 106 */       } while (entity == null);
/*     */       
/* 108 */       return Lists.newArrayList((Object[])new Entity[] { entity });
/*     */     } 
/* 110 */     Vec3D vec3d = this.f.apply(commandlistenerwrapper.getPosition());
/* 111 */     Predicate<Entity> predicate = a(vec3d);
/*     */     
/* 113 */     if (this.i) {
/* 114 */       return (commandlistenerwrapper.getEntity() != null && predicate.test(commandlistenerwrapper.getEntity())) ? Lists.newArrayList((Object[])new Entity[] { commandlistenerwrapper.getEntity() }) : Collections.<Entity>emptyList();
/*     */     }
/* 116 */     List<Entity> list = Lists.newArrayList();
/*     */     
/* 118 */     if (d()) {
/* 119 */       a(list, commandlistenerwrapper.getWorld(), vec3d, predicate);
/*     */     } else {
/* 121 */       Iterator<WorldServer> iterator1 = commandlistenerwrapper.getServer().getWorlds().iterator();
/*     */       
/* 123 */       while (iterator1.hasNext()) {
/* 124 */         WorldServer worldserver1 = iterator1.next();
/*     */         
/* 126 */         a(list, worldserver1, vec3d, predicate);
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     return a(vec3d, list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(List<Entity> list, WorldServer worldserver, Vec3D vec3d, Predicate<Entity> predicate) {
/* 136 */     if (this.g != null) {
/* 137 */       list.addAll((Collection)worldserver.a(this.l, this.g.c(vec3d), predicate));
/*     */     } else {
/* 139 */       list.addAll(worldserver.a(this.l, predicate));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer c(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
/* 145 */     e(commandlistenerwrapper);
/* 146 */     List<EntityPlayer> list = d(commandlistenerwrapper);
/*     */     
/* 148 */     if (list.size() != 1) {
/* 149 */       throw ArgumentEntity.e.create();
/*     */     }
/* 151 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public List<EntityPlayer> d(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
/*     */     Object object;
/* 156 */     e(commandlistenerwrapper);
/*     */ 
/*     */     
/* 159 */     if (this.j != null) {
/* 160 */       EntityPlayer entityplayer = commandlistenerwrapper.getServer().getPlayerList().getPlayer(this.j);
/* 161 */       return (entityplayer == null) ? Collections.<EntityPlayer>emptyList() : Lists.newArrayList((Object[])new EntityPlayer[] { entityplayer });
/* 162 */     }  if (this.k != null) {
/* 163 */       EntityPlayer entityplayer = commandlistenerwrapper.getServer().getPlayerList().getPlayer(this.k);
/* 164 */       return (entityplayer == null) ? Collections.<EntityPlayer>emptyList() : Lists.newArrayList((Object[])new EntityPlayer[] { entityplayer });
/*     */     } 
/* 166 */     Vec3D vec3d = this.f.apply(commandlistenerwrapper.getPosition());
/* 167 */     Predicate<Entity> predicate = a(vec3d);
/*     */     
/* 169 */     if (this.i) {
/* 170 */       if (commandlistenerwrapper.getEntity() instanceof EntityPlayer) {
/* 171 */         EntityPlayer entityplayer1 = (EntityPlayer)commandlistenerwrapper.getEntity();
/*     */         
/* 173 */         if (predicate.test(entityplayer1)) {
/* 174 */           return Lists.newArrayList((Object[])new EntityPlayer[] { entityplayer1 });
/*     */         }
/*     */       } 
/*     */       
/* 178 */       return Collections.emptyList();
/*     */     } 
/*     */ 
/*     */     
/* 182 */     if (d()) {
/* 183 */       WorldServer worldserver = commandlistenerwrapper.getWorld();
/*     */       
/* 185 */       predicate.getClass();
/* 186 */       Objects.requireNonNull(predicate); object = worldserver.a(predicate::test);
/*     */     } else {
/* 188 */       object = Lists.newArrayList();
/* 189 */       Iterator<EntityPlayer> iterator = commandlistenerwrapper.getServer().getPlayerList().getPlayers().iterator();
/*     */       
/* 191 */       while (iterator.hasNext()) {
/* 192 */         EntityPlayer entityplayer2 = iterator.next();
/*     */         
/* 194 */         if (predicate.test(entityplayer2)) {
/* 195 */           ((List<EntityPlayer>)object).add(entityplayer2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     return a(vec3d, (List<EntityPlayer>)object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Predicate<Entity> a(Vec3D vec3d) {
/* 206 */     Predicate<Entity> predicate = this.d;
/*     */     
/* 208 */     if (this.g != null) {
/* 209 */       AxisAlignedBB axisalignedbb = this.g.c(vec3d);
/*     */       
/* 211 */       predicate = predicate.and(entity -> axisalignedbb.c(entity.getBoundingBox()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (!this.e.c()) {
/* 217 */       predicate = predicate.and(entity -> this.e.a(entity.e(vec3d)));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 222 */     return predicate;
/*     */   }
/*     */   
/*     */   private <T extends Entity> List<T> a(Vec3D vec3d, List<T> list) {
/* 226 */     if (list.size() > 1) {
/* 227 */       this.h.accept(vec3d, list);
/*     */     }
/*     */     
/* 230 */     return list.subList(0, Math.min(this.a, list.size()));
/*     */   }
/*     */   
/*     */   public static IChatMutableComponent a(List<? extends Entity> list) {
/* 234 */     return ChatComponentUtils.b(list, Entity::getScoreboardDisplayName);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */