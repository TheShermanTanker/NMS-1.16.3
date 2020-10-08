/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ 
/*     */ 
/*     */ public enum EnumProtocol
/*     */ {
/*  17 */   HANDSHAKING(-1, b().a(EnumProtocolDirection.SERVERBOUND, (new a<>()).a(PacketHandshakingInSetProtocol.class, PacketHandshakingInSetProtocol::new))), PLAY(0, b().<PacketListener>a(EnumProtocolDirection.CLIENTBOUND, (new a<>()).<PacketPlayOutSpawnEntity>a(PacketPlayOutSpawnEntity.class, PacketPlayOutSpawnEntity::new).<PacketPlayOutSpawnEntityExperienceOrb>a(PacketPlayOutSpawnEntityExperienceOrb.class, PacketPlayOutSpawnEntityExperienceOrb::new).<PacketPlayOutSpawnEntityLiving>a(PacketPlayOutSpawnEntityLiving.class, PacketPlayOutSpawnEntityLiving::new).<PacketPlayOutSpawnEntityPainting>a(PacketPlayOutSpawnEntityPainting.class, PacketPlayOutSpawnEntityPainting::new).<PacketPlayOutNamedEntitySpawn>a(PacketPlayOutNamedEntitySpawn.class, PacketPlayOutNamedEntitySpawn::new).<PacketPlayOutAnimation>a(PacketPlayOutAnimation.class, PacketPlayOutAnimation::new).<PacketPlayOutStatistic>a(PacketPlayOutStatistic.class, PacketPlayOutStatistic::new).<PacketPlayOutBlockBreak>a(PacketPlayOutBlockBreak.class, PacketPlayOutBlockBreak::new).<PacketPlayOutBlockBreakAnimation>a(PacketPlayOutBlockBreakAnimation.class, PacketPlayOutBlockBreakAnimation::new).<PacketPlayOutTileEntityData>a(PacketPlayOutTileEntityData.class, PacketPlayOutTileEntityData::new).<PacketPlayOutBlockAction>a(PacketPlayOutBlockAction.class, PacketPlayOutBlockAction::new).<PacketPlayOutBlockChange>a(PacketPlayOutBlockChange.class, PacketPlayOutBlockChange::new).<PacketPlayOutBoss>a(PacketPlayOutBoss.class, PacketPlayOutBoss::new).<PacketPlayOutServerDifficulty>a(PacketPlayOutServerDifficulty.class, PacketPlayOutServerDifficulty::new).<PacketPlayOutChat>a(PacketPlayOutChat.class, PacketPlayOutChat::new).<PacketPlayOutTabComplete>a(PacketPlayOutTabComplete.class, PacketPlayOutTabComplete::new).<PacketPlayOutCommands>a(PacketPlayOutCommands.class, PacketPlayOutCommands::new).<PacketPlayOutTransaction>a(PacketPlayOutTransaction.class, PacketPlayOutTransaction::new).<PacketPlayOutCloseWindow>a(PacketPlayOutCloseWindow.class, PacketPlayOutCloseWindow::new).<PacketPlayOutWindowItems>a(PacketPlayOutWindowItems.class, PacketPlayOutWindowItems::new).<PacketPlayOutWindowData>a(PacketPlayOutWindowData.class, PacketPlayOutWindowData::new).<PacketPlayOutSetSlot>a(PacketPlayOutSetSlot.class, PacketPlayOutSetSlot::new).<PacketPlayOutSetCooldown>a(PacketPlayOutSetCooldown.class, PacketPlayOutSetCooldown::new).<PacketPlayOutCustomPayload>a(PacketPlayOutCustomPayload.class, PacketPlayOutCustomPayload::new).<PacketPlayOutCustomSoundEffect>a(PacketPlayOutCustomSoundEffect.class, PacketPlayOutCustomSoundEffect::new).<PacketPlayOutKickDisconnect>a(PacketPlayOutKickDisconnect.class, PacketPlayOutKickDisconnect::new).<PacketPlayOutEntityStatus>a(PacketPlayOutEntityStatus.class, PacketPlayOutEntityStatus::new).<PacketPlayOutExplosion>a(PacketPlayOutExplosion.class, PacketPlayOutExplosion::new).<PacketPlayOutUnloadChunk>a(PacketPlayOutUnloadChunk.class, PacketPlayOutUnloadChunk::new).<PacketPlayOutGameStateChange>a(PacketPlayOutGameStateChange.class, PacketPlayOutGameStateChange::new).<PacketPlayOutOpenWindowHorse>a(PacketPlayOutOpenWindowHorse.class, PacketPlayOutOpenWindowHorse::new).<PacketPlayOutKeepAlive>a(PacketPlayOutKeepAlive.class, PacketPlayOutKeepAlive::new).<PacketPlayOutMapChunk>a(PacketPlayOutMapChunk.class, PacketPlayOutMapChunk::new).<PacketPlayOutWorldEvent>a(PacketPlayOutWorldEvent.class, PacketPlayOutWorldEvent::new).<PacketPlayOutWorldParticles>a(PacketPlayOutWorldParticles.class, PacketPlayOutWorldParticles::new).<PacketPlayOutLightUpdate>a(PacketPlayOutLightUpdate.class, PacketPlayOutLightUpdate::new).<PacketPlayOutLogin>a(PacketPlayOutLogin.class, PacketPlayOutLogin::new).<PacketPlayOutMap>a(PacketPlayOutMap.class, PacketPlayOutMap::new).<PacketPlayOutOpenWindowMerchant>a(PacketPlayOutOpenWindowMerchant.class, PacketPlayOutOpenWindowMerchant::new).<PacketPlayOutEntity.PacketPlayOutRelEntityMove>a(PacketPlayOutEntity.PacketPlayOutRelEntityMove.class, PacketPlayOutRelEntityMove::new).<PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook>a(PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class, PacketPlayOutRelEntityMoveLook::new).<PacketPlayOutEntity.PacketPlayOutEntityLook>a(PacketPlayOutEntity.PacketPlayOutEntityLook.class, PacketPlayOutEntityLook::new).<PacketPlayOutEntity>a(PacketPlayOutEntity.class, PacketPlayOutEntity::new).<PacketPlayOutVehicleMove>a(PacketPlayOutVehicleMove.class, PacketPlayOutVehicleMove::new).<PacketPlayOutOpenBook>a(PacketPlayOutOpenBook.class, PacketPlayOutOpenBook::new).<PacketPlayOutOpenWindow>a(PacketPlayOutOpenWindow.class, PacketPlayOutOpenWindow::new).<PacketPlayOutOpenSignEditor>a(PacketPlayOutOpenSignEditor.class, PacketPlayOutOpenSignEditor::new).<PacketPlayOutAutoRecipe>a(PacketPlayOutAutoRecipe.class, PacketPlayOutAutoRecipe::new).<PacketPlayOutAbilities>a(PacketPlayOutAbilities.class, PacketPlayOutAbilities::new).<PacketPlayOutCombatEvent>a(PacketPlayOutCombatEvent.class, PacketPlayOutCombatEvent::new).<PacketPlayOutPlayerInfo>a(PacketPlayOutPlayerInfo.class, PacketPlayOutPlayerInfo::new).<PacketPlayOutLookAt>a(PacketPlayOutLookAt.class, PacketPlayOutLookAt::new).<PacketPlayOutPosition>a(PacketPlayOutPosition.class, PacketPlayOutPosition::new).<PacketPlayOutRecipes>a(PacketPlayOutRecipes.class, PacketPlayOutRecipes::new).<PacketPlayOutEntityDestroy>a(PacketPlayOutEntityDestroy.class, PacketPlayOutEntityDestroy::new).<PacketPlayOutRemoveEntityEffect>a(PacketPlayOutRemoveEntityEffect.class, PacketPlayOutRemoveEntityEffect::new).<PacketPlayOutResourcePackSend>a(PacketPlayOutResourcePackSend.class, PacketPlayOutResourcePackSend::new).<PacketPlayOutRespawn>a(PacketPlayOutRespawn.class, PacketPlayOutRespawn::new).<PacketPlayOutEntityHeadRotation>a(PacketPlayOutEntityHeadRotation.class, PacketPlayOutEntityHeadRotation::new).<PacketPlayOutMultiBlockChange>a(PacketPlayOutMultiBlockChange.class, PacketPlayOutMultiBlockChange::new).<PacketPlayOutSelectAdvancementTab>a(PacketPlayOutSelectAdvancementTab.class, PacketPlayOutSelectAdvancementTab::new).<PacketPlayOutWorldBorder>a(PacketPlayOutWorldBorder.class, PacketPlayOutWorldBorder::new).<PacketPlayOutCamera>a(PacketPlayOutCamera.class, PacketPlayOutCamera::new).<PacketPlayOutHeldItemSlot>a(PacketPlayOutHeldItemSlot.class, PacketPlayOutHeldItemSlot::new).<PacketPlayOutViewCentre>a(PacketPlayOutViewCentre.class, PacketPlayOutViewCentre::new).<PacketPlayOutViewDistance>a(PacketPlayOutViewDistance.class, PacketPlayOutViewDistance::new).<PacketPlayOutSpawnPosition>a(PacketPlayOutSpawnPosition.class, PacketPlayOutSpawnPosition::new).<PacketPlayOutScoreboardDisplayObjective>a(PacketPlayOutScoreboardDisplayObjective.class, PacketPlayOutScoreboardDisplayObjective::new).<PacketPlayOutEntityMetadata>a(PacketPlayOutEntityMetadata.class, PacketPlayOutEntityMetadata::new).<PacketPlayOutAttachEntity>a(PacketPlayOutAttachEntity.class, PacketPlayOutAttachEntity::new).<PacketPlayOutEntityVelocity>a(PacketPlayOutEntityVelocity.class, PacketPlayOutEntityVelocity::new).<PacketPlayOutEntityEquipment>a(PacketPlayOutEntityEquipment.class, PacketPlayOutEntityEquipment::new).<PacketPlayOutExperience>a(PacketPlayOutExperience.class, PacketPlayOutExperience::new).<PacketPlayOutUpdateHealth>a(PacketPlayOutUpdateHealth.class, PacketPlayOutUpdateHealth::new).<PacketPlayOutScoreboardObjective>a(PacketPlayOutScoreboardObjective.class, PacketPlayOutScoreboardObjective::new).<PacketPlayOutMount>a(PacketPlayOutMount.class, PacketPlayOutMount::new).<PacketPlayOutScoreboardTeam>a(PacketPlayOutScoreboardTeam.class, PacketPlayOutScoreboardTeam::new).<PacketPlayOutScoreboardScore>a(PacketPlayOutScoreboardScore.class, PacketPlayOutScoreboardScore::new).<PacketPlayOutUpdateTime>a(PacketPlayOutUpdateTime.class, PacketPlayOutUpdateTime::new).<PacketPlayOutTitle>a(PacketPlayOutTitle.class, PacketPlayOutTitle::new).<PacketPlayOutEntitySound>a(PacketPlayOutEntitySound.class, PacketPlayOutEntitySound::new).<PacketPlayOutNamedSoundEffect>a(PacketPlayOutNamedSoundEffect.class, PacketPlayOutNamedSoundEffect::new).<PacketPlayOutStopSound>a(PacketPlayOutStopSound.class, PacketPlayOutStopSound::new).<PacketPlayOutPlayerListHeaderFooter>a(PacketPlayOutPlayerListHeaderFooter.class, PacketPlayOutPlayerListHeaderFooter::new).<PacketPlayOutNBTQuery>a(PacketPlayOutNBTQuery.class, PacketPlayOutNBTQuery::new).<PacketPlayOutCollect>a(PacketPlayOutCollect.class, PacketPlayOutCollect::new).<PacketPlayOutEntityTeleport>a(PacketPlayOutEntityTeleport.class, PacketPlayOutEntityTeleport::new).<PacketPlayOutAdvancements>a(PacketPlayOutAdvancements.class, PacketPlayOutAdvancements::new).<PacketPlayOutUpdateAttributes>a(PacketPlayOutUpdateAttributes.class, PacketPlayOutUpdateAttributes::new).<PacketPlayOutEntityEffect>a(PacketPlayOutEntityEffect.class, PacketPlayOutEntityEffect::new).<PacketPlayOutRecipeUpdate>a(PacketPlayOutRecipeUpdate.class, PacketPlayOutRecipeUpdate::new).a(PacketPlayOutTags.class, PacketPlayOutTags::new)).a(EnumProtocolDirection.SERVERBOUND, (new a<>()).<PacketPlayInTeleportAccept>a(PacketPlayInTeleportAccept.class, PacketPlayInTeleportAccept::new).<PacketPlayInTileNBTQuery>a(PacketPlayInTileNBTQuery.class, PacketPlayInTileNBTQuery::new).<PacketPlayInDifficultyChange>a(PacketPlayInDifficultyChange.class, PacketPlayInDifficultyChange::new).<PacketPlayInChat>a(PacketPlayInChat.class, PacketPlayInChat::new).<PacketPlayInClientCommand>a(PacketPlayInClientCommand.class, PacketPlayInClientCommand::new).<PacketPlayInSettings>a(PacketPlayInSettings.class, PacketPlayInSettings::new).<PacketPlayInTabComplete>a(PacketPlayInTabComplete.class, PacketPlayInTabComplete::new).<PacketPlayInTransaction>a(PacketPlayInTransaction.class, PacketPlayInTransaction::new).<PacketPlayInEnchantItem>a(PacketPlayInEnchantItem.class, PacketPlayInEnchantItem::new).<PacketPlayInWindowClick>a(PacketPlayInWindowClick.class, PacketPlayInWindowClick::new).<PacketPlayInCloseWindow>a(PacketPlayInCloseWindow.class, PacketPlayInCloseWindow::new).<PacketPlayInCustomPayload>a(PacketPlayInCustomPayload.class, PacketPlayInCustomPayload::new).<PacketPlayInBEdit>a(PacketPlayInBEdit.class, PacketPlayInBEdit::new).<PacketPlayInEntityNBTQuery>a(PacketPlayInEntityNBTQuery.class, PacketPlayInEntityNBTQuery::new).<PacketPlayInUseEntity>a(PacketPlayInUseEntity.class, PacketPlayInUseEntity::new).<PacketPlayInJigsawGenerate>a(PacketPlayInJigsawGenerate.class, PacketPlayInJigsawGenerate::new).<PacketPlayInKeepAlive>a(PacketPlayInKeepAlive.class, PacketPlayInKeepAlive::new).<PacketPlayInDifficultyLock>a(PacketPlayInDifficultyLock.class, PacketPlayInDifficultyLock::new).<PacketPlayInFlying.PacketPlayInPosition>a(PacketPlayInFlying.PacketPlayInPosition.class, PacketPlayInPosition::new).<PacketPlayInFlying.PacketPlayInPositionLook>a(PacketPlayInFlying.PacketPlayInPositionLook.class, PacketPlayInPositionLook::new).<PacketPlayInFlying.PacketPlayInLook>a(PacketPlayInFlying.PacketPlayInLook.class, PacketPlayInLook::new).<PacketPlayInFlying>a(PacketPlayInFlying.class, PacketPlayInFlying::new).<PacketPlayInVehicleMove>a(PacketPlayInVehicleMove.class, PacketPlayInVehicleMove::new).<PacketPlayInBoatMove>a(PacketPlayInBoatMove.class, PacketPlayInBoatMove::new).<PacketPlayInPickItem>a(PacketPlayInPickItem.class, PacketPlayInPickItem::new).<PacketPlayInAutoRecipe>a(PacketPlayInAutoRecipe.class, PacketPlayInAutoRecipe::new).<PacketPlayInAbilities>a(PacketPlayInAbilities.class, PacketPlayInAbilities::new).<PacketPlayInBlockDig>a(PacketPlayInBlockDig.class, PacketPlayInBlockDig::new).<PacketPlayInEntityAction>a(PacketPlayInEntityAction.class, PacketPlayInEntityAction::new).<PacketPlayInSteerVehicle>a(PacketPlayInSteerVehicle.class, PacketPlayInSteerVehicle::new).<PacketPlayInRecipeSettings>a(PacketPlayInRecipeSettings.class, PacketPlayInRecipeSettings::new).<PacketPlayInRecipeDisplayed>a(PacketPlayInRecipeDisplayed.class, PacketPlayInRecipeDisplayed::new).<PacketPlayInItemName>a(PacketPlayInItemName.class, PacketPlayInItemName::new).<PacketPlayInResourcePackStatus>a(PacketPlayInResourcePackStatus.class, PacketPlayInResourcePackStatus::new).<PacketPlayInAdvancements>a(PacketPlayInAdvancements.class, PacketPlayInAdvancements::new).<PacketPlayInTrSel>a(PacketPlayInTrSel.class, PacketPlayInTrSel::new).<PacketPlayInBeacon>a(PacketPlayInBeacon.class, PacketPlayInBeacon::new).<PacketPlayInHeldItemSlot>a(PacketPlayInHeldItemSlot.class, PacketPlayInHeldItemSlot::new).<PacketPlayInSetCommandBlock>a(PacketPlayInSetCommandBlock.class, PacketPlayInSetCommandBlock::new).<PacketPlayInSetCommandMinecart>a(PacketPlayInSetCommandMinecart.class, PacketPlayInSetCommandMinecart::new).<PacketPlayInSetCreativeSlot>a(PacketPlayInSetCreativeSlot.class, PacketPlayInSetCreativeSlot::new).<PacketPlayInSetJigsaw>a(PacketPlayInSetJigsaw.class, PacketPlayInSetJigsaw::new).<PacketPlayInStruct>a(PacketPlayInStruct.class, PacketPlayInStruct::new).<PacketPlayInUpdateSign>a(PacketPlayInUpdateSign.class, PacketPlayInUpdateSign::new).<PacketPlayInArmAnimation>a(PacketPlayInArmAnimation.class, PacketPlayInArmAnimation::new).<PacketPlayInSpectate>a(PacketPlayInSpectate.class, PacketPlayInSpectate::new).<PacketPlayInUseItem>a(PacketPlayInUseItem.class, PacketPlayInUseItem::new).a(PacketPlayInBlockPlace.class, PacketPlayInBlockPlace::new))), STATUS(1, b().<PacketListener>a(EnumProtocolDirection.SERVERBOUND, (new a<>()).<PacketStatusInStart>a(PacketStatusInStart.class, PacketStatusInStart::new).a(PacketStatusInPing.class, PacketStatusInPing::new)).a(EnumProtocolDirection.CLIENTBOUND, (new a<>()).<PacketStatusOutServerInfo>a(PacketStatusOutServerInfo.class, PacketStatusOutServerInfo::new).a(PacketStatusOutPong.class, PacketStatusOutPong::new))), LOGIN(2, b().<PacketListener>a(EnumProtocolDirection.CLIENTBOUND, (new a<>()).<PacketLoginOutDisconnect>a(PacketLoginOutDisconnect.class, PacketLoginOutDisconnect::new).<PacketLoginOutEncryptionBegin>a(PacketLoginOutEncryptionBegin.class, PacketLoginOutEncryptionBegin::new).<PacketLoginOutSuccess>a(PacketLoginOutSuccess.class, PacketLoginOutSuccess::new).<PacketLoginOutSetCompression>a(PacketLoginOutSetCompression.class, PacketLoginOutSetCompression::new).a(PacketLoginOutCustomPayload.class, PacketLoginOutCustomPayload::new)).a(EnumProtocolDirection.SERVERBOUND, (new a<>()).<PacketLoginInStart>a(PacketLoginInStart.class, PacketLoginInStart::new).<PacketLoginInEncryptionBegin>a(PacketLoginInEncryptionBegin.class, PacketLoginInEncryptionBegin::new).a(PacketLoginInCustomPayload.class, PacketLoginInCustomPayload::new)));
/*     */   private final Map<EnumProtocolDirection, ? extends a<?>> h;
/*  19 */   private final int g; private static final Map<Class<? extends Packet<?>>, EnumProtocol> f; private static final EnumProtocol[] e; private static b b() { return new b(); } EnumProtocol(int i, b enumprotocol_b) { this.g = i; this.h = enumprotocol_b.a; } @Nullable public Integer a(EnumProtocolDirection enumprotocoldirection, Packet<?> packet) { return ((a)this.h.get(enumprotocoldirection)).a(packet.getClass()); } @Nullable public Packet<?> a(EnumProtocolDirection enumprotocoldirection, int i) { return ((a)this.h.get(enumprotocoldirection)).a(i); } static { e = new EnumProtocol[4];
/*  20 */     f = Maps.newHashMap();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     EnumProtocol[] aenumprotocol = values();
/*  58 */     int i = aenumprotocol.length;
/*     */     
/*  60 */     for (int j = 0; j < i; j++) {
/*  61 */       EnumProtocol enumprotocol = aenumprotocol[j];
/*  62 */       int k = enumprotocol.a();
/*     */       
/*  64 */       if (k < -1 || k > 2) {
/*  65 */         throw new Error("Invalid protocol ID " + Integer.toString(k));
/*     */       }
/*     */       
/*  68 */       e[k - -1] = enumprotocol;
/*  69 */       enumprotocol.h.forEach((enumprotocoldirection, enumprotocol_a) -> enumprotocol_a.a().forEach(()));
/*     */     }  }
/*     */ 
/*     */   
/*     */   public int a() {
/*     */     return this.g;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumProtocol a(int i) {
/*     */     return (i >= -1 && i <= 2) ? e[i - -1] : null;
/*     */   }
/*     */   
/*     */   public static EnumProtocol a(Packet<?> packet) {
/*     */     return f.get(packet.getClass());
/*     */   }
/*     */   
/*     */   static class b {
/*  87 */     private final Map<EnumProtocolDirection, EnumProtocol.a<?>> a = Maps.newEnumMap(EnumProtocolDirection.class);
/*     */ 
/*     */     
/*     */     public <T extends PacketListener> b a(EnumProtocolDirection enumprotocoldirection, EnumProtocol.a<T> enumprotocol_a) {
/*  91 */       this.a.put(enumprotocoldirection, enumprotocol_a);
/*  92 */       return this;
/*     */     }
/*     */     
/*     */     private b() {} }
/*     */   
/*     */   static class a<T extends PacketListener> {
/*     */     private final Object2IntMap<Class<? extends Packet<T>>> a;
/*     */     private final List<Supplier<? extends Packet<T>>> b;
/*     */     
/*     */     private a() {
/* 102 */       this.a = (Object2IntMap<Class<? extends Packet<T>>>)SystemUtils.a(new Object2IntOpenHashMap(), object2intopenhashmap -> object2intopenhashmap.defaultReturnValue(-1));
/*     */ 
/*     */       
/* 105 */       this.b = Lists.newArrayList();
/*     */     }
/*     */     
/*     */     public <P extends Packet<T>> a<T> a(Class<P> oclass, Supplier<P> supplier) {
/* 109 */       int i = this.b.size();
/* 110 */       int j = this.a.put(oclass, i);
/*     */       
/* 112 */       if (j != -1) {
/* 113 */         String s = "Packet " + oclass + " is already registered to ID " + j;
/*     */         
/* 115 */         LogManager.getLogger().fatal(s);
/* 116 */         throw new IllegalArgumentException(s);
/*     */       } 
/* 118 */       this.b.add(supplier);
/* 119 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Integer a(Class<?> oclass) {
/* 125 */       int i = this.a.getInt(oclass);
/*     */       
/* 127 */       return (i == -1) ? null : Integer.valueOf(i);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public Packet<?> a(int i) {
/* 132 */       if (i < 0 || i >= this.b.size()) return null; 
/* 133 */       Supplier<? extends Packet<T>> supplier = this.b.get(i);
/*     */       
/* 135 */       return (supplier != null) ? supplier.get() : null;
/*     */     }
/*     */     
/*     */     public Iterable<Class<? extends Packet<?>>> a() {
/* 139 */       return Iterables.unmodifiableIterable((Iterable)this.a.keySet());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */