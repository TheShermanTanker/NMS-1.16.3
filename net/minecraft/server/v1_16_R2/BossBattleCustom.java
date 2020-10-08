/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.boss.KeyedBossBar;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.boss.CraftKeyedBossbar;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossBattleCustom
/*     */   extends BossBattleServer
/*     */ {
/*     */   private final MinecraftKey h;
/*  17 */   private final Set<UUID> i = Sets.newHashSet();
/*     */   private int j;
/*  19 */   private int k = 100;
/*     */   
/*     */   private KeyedBossBar bossBar;
/*     */   
/*     */   public KeyedBossBar getBukkitEntity() {
/*  24 */     if (this.bossBar == null) {
/*  25 */       this.bossBar = (KeyedBossBar)new CraftKeyedBossbar(this);
/*     */     }
/*  27 */     return this.bossBar;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattleCustom(MinecraftKey minecraftkey, IChatBaseComponent ichatbasecomponent) {
/*  32 */     super(ichatbasecomponent, BossBattle.BarColor.WHITE, BossBattle.BarStyle.PROGRESS);
/*  33 */     this.h = minecraftkey;
/*  34 */     setProgress(0.0F);
/*     */   }
/*     */   
/*     */   public MinecraftKey getKey() {
/*  38 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(EntityPlayer entityplayer) {
/*  43 */     super.addPlayer(entityplayer);
/*  44 */     this.i.add(entityplayer.getUniqueID());
/*     */   }
/*     */   
/*     */   public void a(UUID uuid) {
/*  48 */     this.i.add(uuid);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayer(EntityPlayer entityplayer) {
/*  53 */     super.removePlayer(entityplayer);
/*  54 */     this.i.remove(entityplayer.getUniqueID());
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/*  59 */     super.b();
/*  60 */     this.i.clear();
/*     */   }
/*     */   
/*     */   public int c() {
/*  64 */     return this.j;
/*     */   }
/*     */   
/*     */   public int d() {
/*  68 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/*  72 */     this.j = i;
/*  73 */     setProgress(MathHelper.a(i / this.k, 0.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public void b(int i) {
/*  77 */     this.k = i;
/*  78 */     setProgress(MathHelper.a(this.j / i, 0.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public final IChatBaseComponent e() {
/*  82 */     return ChatComponentUtils.a(j()).format(chatmodifier -> chatmodifier.setColor(l().a()).setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatComponentText(getKey().toString()))).setInsertion(getKey().toString()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Collection<EntityPlayer> collection) {
/*  88 */     Set<UUID> set = Sets.newHashSet();
/*  89 */     Set<EntityPlayer> set1 = Sets.newHashSet();
/*  90 */     Iterator<UUID> iterator = this.i.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     while (iterator.hasNext()) {
/*  97 */       UUID uuid = iterator.next();
/*  98 */       boolean flag = false;
/*  99 */       Iterator<EntityPlayer> iterator1 = collection.iterator();
/*     */ 
/*     */       
/* 102 */       while (iterator1.hasNext()) {
/* 103 */         EntityPlayer entityplayer = iterator1.next();
/*     */         
/* 105 */         if (!entityplayer.getUniqueID().equals(uuid)) {
/*     */           continue;
/*     */         }
/*     */         
/* 109 */         flag = true;
/*     */       } 
/*     */       
/* 112 */       if (!flag) {
/* 113 */         set.add(uuid);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     iterator = (Iterator)collection.iterator();
/*     */ 
/*     */ 
/*     */     
/* 123 */     while (iterator.hasNext()) {
/* 124 */       EntityPlayer entityplayer1 = (EntityPlayer)iterator.next();
/* 125 */       boolean flag = false;
/* 126 */       Iterator<UUID> iterator1 = this.i.iterator();
/*     */ 
/*     */       
/* 129 */       while (iterator1.hasNext()) {
/* 130 */         UUID uuid1 = iterator1.next();
/*     */         
/* 132 */         if (!entityplayer1.getUniqueID().equals(uuid1)) {
/*     */           continue;
/*     */         }
/*     */         
/* 136 */         flag = true;
/*     */       } 
/*     */       
/* 139 */       if (!flag) {
/* 140 */         set1.add(entityplayer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 146 */     iterator = set.iterator();
/*     */     
/* 148 */     while (iterator.hasNext()) {
/* 149 */       UUID uuid = iterator.next();
/* 150 */       Iterator<EntityPlayer> iterator2 = getPlayers().iterator();
/*     */ 
/*     */       
/* 153 */       while (iterator2.hasNext()) {
/* 154 */         EntityPlayer entityplayer2 = iterator2.next();
/*     */         
/* 156 */         if (!entityplayer2.getUniqueID().equals(uuid)) {
/*     */           continue;
/*     */         }
/*     */         
/* 160 */         removePlayer(entityplayer2);
/*     */       } 
/*     */       
/* 163 */       this.i.remove(uuid);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 168 */     iterator = (Iterator)set1.iterator();
/*     */     
/* 170 */     while (iterator.hasNext()) {
/* 171 */       EntityPlayer entityplayer1 = (EntityPlayer)iterator.next();
/* 172 */       addPlayer(entityplayer1);
/*     */     } 
/*     */     
/* 175 */     return (!set.isEmpty() || !set1.isEmpty());
/*     */   }
/*     */   
/*     */   public NBTTagCompound f() {
/* 179 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 181 */     nbttagcompound.setString("Name", IChatBaseComponent.ChatSerializer.a(this.title));
/* 182 */     nbttagcompound.setBoolean("Visible", g());
/* 183 */     nbttagcompound.setInt("Value", this.j);
/* 184 */     nbttagcompound.setInt("Max", this.k);
/* 185 */     nbttagcompound.setString("Color", l().b());
/* 186 */     nbttagcompound.setString("Overlay", m().a());
/* 187 */     nbttagcompound.setBoolean("DarkenScreen", isDarkenSky());
/* 188 */     nbttagcompound.setBoolean("PlayBossMusic", isPlayMusic());
/* 189 */     nbttagcompound.setBoolean("CreateWorldFog", isCreateFog());
/* 190 */     NBTTagList nbttaglist = new NBTTagList();
/* 191 */     Iterator<UUID> iterator = this.i.iterator();
/*     */     
/* 193 */     while (iterator.hasNext()) {
/* 194 */       UUID uuid = iterator.next();
/*     */       
/* 196 */       nbttaglist.add(GameProfileSerializer.a(uuid));
/*     */     } 
/*     */     
/* 199 */     nbttagcompound.set("Players", nbttaglist);
/* 200 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static BossBattleCustom a(NBTTagCompound nbttagcompound, MinecraftKey minecraftkey) {
/* 204 */     BossBattleCustom bossbattlecustom = new BossBattleCustom(minecraftkey, IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("Name")));
/*     */     
/* 206 */     bossbattlecustom.setVisible(nbttagcompound.getBoolean("Visible"));
/* 207 */     bossbattlecustom.a(nbttagcompound.getInt("Value"));
/* 208 */     bossbattlecustom.b(nbttagcompound.getInt("Max"));
/* 209 */     bossbattlecustom.a(BossBattle.BarColor.a(nbttagcompound.getString("Color")));
/* 210 */     bossbattlecustom.a(BossBattle.BarStyle.a(nbttagcompound.getString("Overlay")));
/* 211 */     bossbattlecustom.setDarkenSky(nbttagcompound.getBoolean("DarkenScreen"));
/* 212 */     bossbattlecustom.setPlayMusic(nbttagcompound.getBoolean("PlayBossMusic"));
/* 213 */     bossbattlecustom.setCreateFog(nbttagcompound.getBoolean("CreateWorldFog"));
/* 214 */     NBTTagList nbttaglist = nbttagcompound.getList("Players", 11);
/*     */     
/* 216 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 217 */       bossbattlecustom.a(GameProfileSerializer.a(nbttaglist.get(i)));
/*     */     }
/*     */     
/* 220 */     return bossbattlecustom;
/*     */   }
/*     */   
/*     */   public void c(EntityPlayer entityplayer) {
/* 224 */     if (this.i.contains(entityplayer.getUniqueID())) {
/* 225 */       addPlayer(entityplayer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(EntityPlayer entityplayer) {
/* 231 */     super.removePlayer(entityplayer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BossBattleCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */