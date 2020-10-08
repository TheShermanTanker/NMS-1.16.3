package net.minecraft.server.v1_16_R2;

public interface PacketListenerPlayOut extends PacketListener {
  void a(PacketPlayOutSpawnEntity paramPacketPlayOutSpawnEntity);
  
  void a(PacketPlayOutSpawnEntityExperienceOrb paramPacketPlayOutSpawnEntityExperienceOrb);
  
  void a(PacketPlayOutSpawnEntityLiving paramPacketPlayOutSpawnEntityLiving);
  
  void a(PacketPlayOutScoreboardObjective paramPacketPlayOutScoreboardObjective);
  
  void a(PacketPlayOutSpawnEntityPainting paramPacketPlayOutSpawnEntityPainting);
  
  void a(PacketPlayOutNamedEntitySpawn paramPacketPlayOutNamedEntitySpawn);
  
  void a(PacketPlayOutAnimation paramPacketPlayOutAnimation);
  
  void a(PacketPlayOutStatistic paramPacketPlayOutStatistic);
  
  void a(PacketPlayOutRecipes paramPacketPlayOutRecipes);
  
  void a(PacketPlayOutBlockBreakAnimation paramPacketPlayOutBlockBreakAnimation);
  
  void a(PacketPlayOutOpenSignEditor paramPacketPlayOutOpenSignEditor);
  
  void a(PacketPlayOutTileEntityData paramPacketPlayOutTileEntityData);
  
  void a(PacketPlayOutBlockAction paramPacketPlayOutBlockAction);
  
  void a(PacketPlayOutBlockChange paramPacketPlayOutBlockChange);
  
  void a(PacketPlayOutChat paramPacketPlayOutChat);
  
  void a(PacketPlayOutMultiBlockChange paramPacketPlayOutMultiBlockChange);
  
  void a(PacketPlayOutMap paramPacketPlayOutMap);
  
  void a(PacketPlayOutTransaction paramPacketPlayOutTransaction);
  
  void a(PacketPlayOutCloseWindow paramPacketPlayOutCloseWindow);
  
  void a(PacketPlayOutWindowItems paramPacketPlayOutWindowItems);
  
  void a(PacketPlayOutOpenWindowHorse paramPacketPlayOutOpenWindowHorse);
  
  void a(PacketPlayOutWindowData paramPacketPlayOutWindowData);
  
  void a(PacketPlayOutSetSlot paramPacketPlayOutSetSlot);
  
  void a(PacketPlayOutCustomPayload paramPacketPlayOutCustomPayload);
  
  void a(PacketPlayOutKickDisconnect paramPacketPlayOutKickDisconnect);
  
  void a(PacketPlayOutEntityStatus paramPacketPlayOutEntityStatus);
  
  void a(PacketPlayOutAttachEntity paramPacketPlayOutAttachEntity);
  
  void a(PacketPlayOutMount paramPacketPlayOutMount);
  
  void a(PacketPlayOutExplosion paramPacketPlayOutExplosion);
  
  void a(PacketPlayOutGameStateChange paramPacketPlayOutGameStateChange);
  
  void a(PacketPlayOutKeepAlive paramPacketPlayOutKeepAlive);
  
  void a(PacketPlayOutMapChunk paramPacketPlayOutMapChunk);
  
  void a(PacketPlayOutUnloadChunk paramPacketPlayOutUnloadChunk);
  
  void a(PacketPlayOutWorldEvent paramPacketPlayOutWorldEvent);
  
  void a(PacketPlayOutLogin paramPacketPlayOutLogin);
  
  void a(PacketPlayOutEntity paramPacketPlayOutEntity);
  
  void a(PacketPlayOutPosition paramPacketPlayOutPosition);
  
  void a(PacketPlayOutWorldParticles paramPacketPlayOutWorldParticles);
  
  void a(PacketPlayOutAbilities paramPacketPlayOutAbilities);
  
  void a(PacketPlayOutPlayerInfo paramPacketPlayOutPlayerInfo);
  
  void a(PacketPlayOutEntityDestroy paramPacketPlayOutEntityDestroy);
  
  void a(PacketPlayOutRemoveEntityEffect paramPacketPlayOutRemoveEntityEffect);
  
  void a(PacketPlayOutRespawn paramPacketPlayOutRespawn);
  
  void a(PacketPlayOutEntityHeadRotation paramPacketPlayOutEntityHeadRotation);
  
  void a(PacketPlayOutHeldItemSlot paramPacketPlayOutHeldItemSlot);
  
  void a(PacketPlayOutScoreboardDisplayObjective paramPacketPlayOutScoreboardDisplayObjective);
  
  void a(PacketPlayOutEntityMetadata paramPacketPlayOutEntityMetadata);
  
  void a(PacketPlayOutEntityVelocity paramPacketPlayOutEntityVelocity);
  
  void a(PacketPlayOutEntityEquipment paramPacketPlayOutEntityEquipment);
  
  void a(PacketPlayOutExperience paramPacketPlayOutExperience);
  
  void a(PacketPlayOutUpdateHealth paramPacketPlayOutUpdateHealth);
  
  void a(PacketPlayOutScoreboardTeam paramPacketPlayOutScoreboardTeam);
  
  void a(PacketPlayOutScoreboardScore paramPacketPlayOutScoreboardScore);
  
  void a(PacketPlayOutSpawnPosition paramPacketPlayOutSpawnPosition);
  
  void a(PacketPlayOutUpdateTime paramPacketPlayOutUpdateTime);
  
  void a(PacketPlayOutNamedSoundEffect paramPacketPlayOutNamedSoundEffect);
  
  void a(PacketPlayOutEntitySound paramPacketPlayOutEntitySound);
  
  void a(PacketPlayOutCustomSoundEffect paramPacketPlayOutCustomSoundEffect);
  
  void a(PacketPlayOutCollect paramPacketPlayOutCollect);
  
  void a(PacketPlayOutEntityTeleport paramPacketPlayOutEntityTeleport);
  
  void a(PacketPlayOutUpdateAttributes paramPacketPlayOutUpdateAttributes);
  
  void a(PacketPlayOutEntityEffect paramPacketPlayOutEntityEffect);
  
  void a(PacketPlayOutTags paramPacketPlayOutTags);
  
  void a(PacketPlayOutCombatEvent paramPacketPlayOutCombatEvent);
  
  void a(PacketPlayOutServerDifficulty paramPacketPlayOutServerDifficulty);
  
  void a(PacketPlayOutCamera paramPacketPlayOutCamera);
  
  void a(PacketPlayOutWorldBorder paramPacketPlayOutWorldBorder);
  
  void a(PacketPlayOutTitle paramPacketPlayOutTitle);
  
  void a(PacketPlayOutPlayerListHeaderFooter paramPacketPlayOutPlayerListHeaderFooter);
  
  void a(PacketPlayOutResourcePackSend paramPacketPlayOutResourcePackSend);
  
  void a(PacketPlayOutBoss paramPacketPlayOutBoss);
  
  void a(PacketPlayOutSetCooldown paramPacketPlayOutSetCooldown);
  
  void a(PacketPlayOutVehicleMove paramPacketPlayOutVehicleMove);
  
  void a(PacketPlayOutAdvancements paramPacketPlayOutAdvancements);
  
  void a(PacketPlayOutSelectAdvancementTab paramPacketPlayOutSelectAdvancementTab);
  
  void a(PacketPlayOutAutoRecipe paramPacketPlayOutAutoRecipe);
  
  void a(PacketPlayOutCommands paramPacketPlayOutCommands);
  
  void a(PacketPlayOutStopSound paramPacketPlayOutStopSound);
  
  void a(PacketPlayOutTabComplete paramPacketPlayOutTabComplete);
  
  void a(PacketPlayOutRecipeUpdate paramPacketPlayOutRecipeUpdate);
  
  void a(PacketPlayOutLookAt paramPacketPlayOutLookAt);
  
  void a(PacketPlayOutNBTQuery paramPacketPlayOutNBTQuery);
  
  void a(PacketPlayOutLightUpdate paramPacketPlayOutLightUpdate);
  
  void a(PacketPlayOutOpenBook paramPacketPlayOutOpenBook);
  
  void a(PacketPlayOutOpenWindow paramPacketPlayOutOpenWindow);
  
  void a(PacketPlayOutOpenWindowMerchant paramPacketPlayOutOpenWindowMerchant);
  
  void a(PacketPlayOutViewDistance paramPacketPlayOutViewDistance);
  
  void a(PacketPlayOutViewCentre paramPacketPlayOutViewCentre);
  
  void a(PacketPlayOutBlockBreak paramPacketPlayOutBlockBreak);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketListenerPlayOut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */