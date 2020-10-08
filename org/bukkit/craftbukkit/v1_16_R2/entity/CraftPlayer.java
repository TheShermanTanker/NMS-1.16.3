/*      */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*      */ import com.destroystokyo.paper.ClientOption;
/*      */ import com.destroystokyo.paper.Title;
/*      */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*      */ import com.destroystokyo.paper.profile.PlayerProfile;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.net.SocketAddress;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.WeakHashMap;
/*      */ import javax.annotation.Nullable;
/*      */ import net.md_5.bungee.api.ChatMessageType;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import net.md_5.bungee.chat.ComponentSerializer;
/*      */ import net.minecraft.server.v1_16_R2.AdvancementDataPlayer;
/*      */ import net.minecraft.server.v1_16_R2.AdvancementProgress;
/*      */ import net.minecraft.server.v1_16_R2.AttributeMapBase;
/*      */ import net.minecraft.server.v1_16_R2.AttributeModifiable;
/*      */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*      */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*      */ import net.minecraft.server.v1_16_R2.ChatMessageType;
/*      */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*      */ import net.minecraft.server.v1_16_R2.Container;
/*      */ import net.minecraft.server.v1_16_R2.EnchantmentManager;
/*      */ import net.minecraft.server.v1_16_R2.Entity;
/*      */ import net.minecraft.server.v1_16_R2.EntityExperienceOrb;
/*      */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*      */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*      */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*      */ import net.minecraft.server.v1_16_R2.GenericAttributes;
/*      */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.ItemStack;
/*      */ import net.minecraft.server.v1_16_R2.MapIcon;
/*      */ import net.minecraft.server.v1_16_R2.MathHelper;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*      */ import net.minecraft.server.v1_16_R2.NBTBase;
/*      */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*      */ import net.minecraft.server.v1_16_R2.Packet;
/*      */ import net.minecraft.server.v1_16_R2.PacketDataSerializer;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutBlockChange;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutChat;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutCustomPayload;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutCustomSoundEffect;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutExperience;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutMap;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutNamedSoundEffect;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerListHeaderFooter;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutUpdateHealth;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutWorldEvent;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutWorldParticles;
/*      */ import net.minecraft.server.v1_16_R2.PlayerChunkMap;
/*      */ import net.minecraft.server.v1_16_R2.PlayerConnection;
/*      */ import net.minecraft.server.v1_16_R2.SoundCategory;
/*      */ import net.minecraft.server.v1_16_R2.SystemUtils;
/*      */ import net.minecraft.server.v1_16_R2.TileEntitySign;
/*      */ import net.minecraft.server.v1_16_R2.Vec3D;
/*      */ import net.minecraft.server.v1_16_R2.WhiteListEntry;
/*      */ import net.minecraft.server.v1_16_R2.WorldServer;
/*      */ import org.apache.commons.lang.NotImplementedException;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.BanList;
/*      */ import org.bukkit.DyeColor;
/*      */ import org.bukkit.Effect;
/*      */ import org.bukkit.GameMode;
/*      */ import org.bukkit.Instrument;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.NamespacedKey;
/*      */ import org.bukkit.Note;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.Particle;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.SoundCategory;
/*      */ import org.bukkit.Statistic;
/*      */ import org.bukkit.WeatherType;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.advancement.Advancement;
/*      */ import org.bukkit.advancement.AdvancementProgress;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*      */ import org.bukkit.conversations.Conversation;
/*      */ import org.bukkit.conversations.ConversationAbandonedEvent;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftOfflinePlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftParticle;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftSound;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftStatistic;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.advancement.CraftAdvancement;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftSign;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.conversations.ConversationTracker;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.map.RenderData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.player.PlayerItemMendEvent;
/*      */ import org.bukkit.event.player.PlayerResourcePackStatusEvent;
/*      */ import org.bukkit.event.player.PlayerTeleportEvent;
/*      */ import org.bukkit.inventory.InventoryView;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.map.MapCursor;
/*      */ import org.bukkit.map.MapView;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.plugin.messaging.StandardMessenger;
/*      */ import org.bukkit.scoreboard.Scoreboard;
/*      */ 
/*      */ @DelegateDeserialization(CraftOfflinePlayer.class)
/*      */ public class CraftPlayer extends CraftHumanEntity implements Player {
/*  133 */   private long firstPlayed = 0L;
/*  134 */   private long lastPlayed = 0L;
/*      */   private boolean hasPlayedBefore = false;
/*  136 */   private final ConversationTracker conversationTracker = new ConversationTracker();
/*  137 */   private final Set<String> channels = new HashSet<>();
/*  138 */   private final Map<UUID, Set<WeakReference<Plugin>>> hiddenPlayers = new HashMap<>();
/*  139 */   private static final WeakHashMap<Plugin, WeakReference<Plugin>> pluginWeakReferences = new WeakHashMap<>();
/*  140 */   private int hash = 0;
/*  141 */   private double health = 20.0D;
/*      */   private boolean scaledHealth = false;
/*  143 */   private double healthScale = 20.0D;
/*      */   
/*      */   private PlayerResourcePackStatusEvent.Status resourcePackStatus;
/*      */   private String resourcePackHash;
/*  147 */   private static final boolean DISABLE_CHANNEL_LIMIT = (System.getProperty("paper.disableChannelLimit") != null); private long lastSaveTime;
/*      */   private IChatBaseComponent playerListHeader;
/*      */   private IChatBaseComponent playerListFooter;
/*      */   private final Player.Spigot spigot;
/*      */   
/*  152 */   public CraftPlayer(CraftServer server, EntityPlayer entity) { super(server, (EntityHuman)entity);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2069 */     this.spigot = new Player.Spigot()
/*      */       {
/*      */ 
/*      */         
/*      */         public InetSocketAddress getRawAddress()
/*      */         {
/* 2075 */           return (InetSocketAddress)(CraftPlayer.this.getHandle()).playerConnection.networkManager.getRawAddress();
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean getCollidesWithEntities() {
/* 2080 */           return CraftPlayer.this.isCollidable();
/*      */         }
/*      */ 
/*      */         
/*      */         public void setCollidesWithEntities(boolean collides) {
/* 2085 */           CraftPlayer.this.setCollidable(collides);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void respawn() {
/* 2091 */           if (CraftPlayer.this.getHealth() <= 0.0D && CraftPlayer.this.isOnline())
/*      */           {
/* 2093 */             CraftPlayer.this.server.getServer().getPlayerList().moveToWorld(CraftPlayer.this.getHandle(), false);
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Set<Player> getHiddenPlayers() {
/* 2100 */           Set<Player> ret = new HashSet<>();
/* 2101 */           for (UUID u : CraftPlayer.this.hiddenPlayers.keySet())
/*      */           {
/* 2103 */             ret.add(CraftPlayer.this.getServer().getPlayer(u));
/*      */           }
/*      */           
/* 2106 */           return Collections.unmodifiableSet(ret);
/*      */         }
/*      */ 
/*      */         
/*      */         public void sendMessage(BaseComponent component) {
/* 2111 */           sendMessage(new BaseComponent[] { component });
/*      */         }
/*      */ 
/*      */         
/*      */         public void sendMessage(BaseComponent... components) {
/* 2116 */           if ((CraftPlayer.this.getHandle()).playerConnection == null)
/*      */             return; 
/* 2118 */           PacketPlayOutChat packet = new PacketPlayOutChat(null, ChatMessageType.SYSTEM, SystemUtils.b);
/* 2119 */           packet.components = components;
/* 2120 */           (CraftPlayer.this.getHandle()).playerConnection.sendPacket((Packet)packet);
/*      */         }
/*      */ 
/*      */         
/*      */         public void sendMessage(ChatMessageType position, BaseComponent component) {
/* 2125 */           sendMessage(position, new BaseComponent[] { component });
/*      */         }
/*      */ 
/*      */         
/*      */         public void sendMessage(ChatMessageType position, BaseComponent... components) {
/* 2130 */           if ((CraftPlayer.this.getHandle()).playerConnection == null)
/*      */             return; 
/* 2132 */           PacketPlayOutChat packet = new PacketPlayOutChat(null, ChatMessageType.a((byte)position.ordinal()), SystemUtils.b);
/* 2133 */           packet.components = components;
/* 2134 */           (CraftPlayer.this.getHandle()).playerConnection.sendPacket((Packet)packet);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public int getPing() {
/* 2141 */           return (CraftPlayer.this.getHandle()).ping;
/*      */         }
/*      */       }; this.firstPlayed = System.currentTimeMillis(); }
/*      */   public GameProfile getProfile() { return getHandle().getProfile(); }
/*      */   public boolean isOp() { return this.server.getHandle().isOp(getProfile()); }
/*      */   public void setOp(boolean value) { if (value == isOp()) return;  if (value) { this.server.getHandle().addOp(getProfile()); } else { this.server.getHandle().removeOp(getProfile()); }  this.perm.recalculatePermissions(); }
/*      */   public boolean isOnline() { return (this.server.getPlayer(getUniqueId()) != null); }
/*      */   public InetSocketAddress getAddress() { if ((getHandle()).playerConnection == null) return null;  SocketAddress addr = (getHandle()).playerConnection.networkManager.getSocketAddress(); if (addr instanceof InetSocketAddress) return (InetSocketAddress)addr;  return null; }
/* 2149 */   public int getProtocolVersion() { if ((getHandle()).playerConnection == null) return -1;  return (getHandle()).playerConnection.networkManager.protocolVersion; } public InetSocketAddress getVirtualHost() { if ((getHandle()).playerConnection == null) return null;  return (getHandle()).playerConnection.networkManager.virtualHost; } public double getEyeHeight(boolean ignorePose) { if (ignorePose) return 1.62D;  return getEyeHeight(); } public void sendRawMessage(String message) { if ((getHandle()).playerConnection == null) return;  for (IChatBaseComponent component : CraftChatMessage.fromString(message)) (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutChat(component, ChatMessageType.CHAT, SystemUtils.b));  } public void sendMessage(String message) { if (!this.conversationTracker.isConversingModaly()) sendRawMessage(message);  } public void sendMessage(String[] messages) { for (String message : messages) sendMessage(message);  } public void sendActionBar(BaseComponent[] message) { if ((getHandle()).playerConnection == null) return;  (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, message, -1, -1, -1)); } public void sendActionBar(String message) { if ((getHandle()).playerConnection == null || message == null || message.isEmpty()) return;  (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, CraftChatMessage.fromStringOrNull(message))); } public void sendActionBar(char alternateChar, String message) { if (message == null || message.isEmpty()) return;  sendActionBar(ChatColor.translateAlternateColorCodes(alternateChar, message)); } public void setPlayerListHeaderFooter(BaseComponent[] header, BaseComponent[] footer) { if (header != null) { String headerJson = ComponentSerializer.toString(header); this.playerListHeader = IChatBaseComponent.ChatSerializer.jsonToComponent(headerJson); } else { this.playerListHeader = null; }  if (footer != null) { String footerJson = ComponentSerializer.toString(footer); this.playerListFooter = IChatBaseComponent.ChatSerializer.jsonToComponent(footerJson); } else { this.playerListFooter = null; }  updatePlayerListHeaderFooter(); } public void setPlayerListHeaderFooter(BaseComponent header, BaseComponent footer) { (new BaseComponent[1])[0] = header; (new BaseComponent[1])[0] = footer; setPlayerListHeaderFooter((header == null) ? null : new BaseComponent[1], (footer == null) ? null : new BaseComponent[1]); } public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks) { (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (BaseComponent[])null, fadeInTicks, stayTicks, fadeOutTicks)); } public void setSubtitle(BaseComponent[] subtitle) { (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitle, 0, 0, 0)); } public void setSubtitle(BaseComponent subtitle) { setSubtitle(new BaseComponent[] { subtitle }); } public void showTitle(BaseComponent[] title) { (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title, 0, 0, 0)); } public void showTitle(BaseComponent title) { showTitle(new BaseComponent[] { title }); } public void showTitle(BaseComponent[] title, BaseComponent[] subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) { setTitleTimes(fadeInTicks, stayTicks, fadeOutTicks); setSubtitle(subtitle); showTitle(title); } public void showTitle(BaseComponent title, BaseComponent subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) { setTitleTimes(fadeInTicks, stayTicks, fadeOutTicks); setSubtitle(subtitle); showTitle(title); } public void sendTitle(Title title) { Preconditions.checkNotNull(title, "Title is null"); setTitleTimes(title.getFadeIn(), title.getStay(), title.getFadeOut()); setSubtitle((title.getSubtitle() == null) ? new BaseComponent[0] : title.getSubtitle()); showTitle(title.getTitle()); } public void updateTitle(Title title) { Preconditions.checkNotNull(title, "Title is null"); setTitleTimes(title.getFadeIn(), title.getStay(), title.getFadeOut()); if (title.getSubtitle() != null) setSubtitle(title.getSubtitle());  showTitle(title.getTitle()); } public void hideTitle() { (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, (BaseComponent[])null, 0, 0, 0)); } public String getDisplayName() { return (getHandle()).displayName; } public void setDisplayName(String name) { (getHandle()).displayName = (name == null) ? getName() : name; } public String getPlayerListName() { return ((getHandle()).listName == null) ? getName() : CraftChatMessage.fromComponent((getHandle()).listName); } public void setPlayerListName(String name) { if (name == null) name = getName();  (getHandle()).listName = name.equals(getName()) ? null : CraftChatMessage.fromStringOrNull(name); for (EntityPlayer player : (this.server.getHandle()).players) { if (player.getBukkitEntity().canSee(this)) player.playerConnection.sendPacket((Packet)new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, new EntityPlayer[] { getHandle() }));  }  } public String getPlayerListHeader() { return (this.playerListHeader == null) ? null : CraftChatMessage.fromComponent(this.playerListHeader); } public String getPlayerListFooter() { return (this.playerListFooter == null) ? null : CraftChatMessage.fromComponent(this.playerListFooter); } public void setPlayerListHeader(String header) { this.playerListHeader = CraftChatMessage.fromStringOrNull(header, true); updatePlayerListHeaderFooter(); } public void setPlayerListFooter(String footer) { this.playerListFooter = CraftChatMessage.fromStringOrNull(footer, true); updatePlayerListHeaderFooter(); } public void setPlayerListHeaderFooter(String header, String footer) { this.playerListHeader = CraftChatMessage.fromStringOrNull(header, true); this.playerListFooter = CraftChatMessage.fromStringOrNull(footer, true); updatePlayerListHeaderFooter(); } private void updatePlayerListHeaderFooter() { if ((getHandle()).playerConnection == null) return;  PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(); packet.header = (this.playerListHeader == null) ? (IChatBaseComponent)new ChatComponentText("") : this.playerListHeader; packet.footer = (this.playerListFooter == null) ? (IChatBaseComponent)new ChatComponentText("") : this.playerListFooter; (getHandle()).playerConnection.sendPacket((Packet)packet); } public boolean equals(Object obj) { if (!(obj instanceof OfflinePlayer)) return false;  OfflinePlayer other = (OfflinePlayer)obj; if (getUniqueId() == null || other.getUniqueId() == null) return false;  boolean uuidEquals = getUniqueId().equals(other.getUniqueId()); boolean idEquals = true; if (other instanceof CraftPlayer) idEquals = (getEntityId() == ((CraftPlayer)other).getEntityId());  return (uuidEquals && idEquals); } public void kickPlayer(String message) { AsyncCatcher.catchOp("player kick"); if ((getHandle()).playerConnection == null) return;  (getHandle()).playerConnection.disconnect((message == null) ? "" : message); } public void setCompassTarget(Location loc) { if ((getHandle()).playerConnection == null) return;  (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutSpawnPosition(new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), loc.getYaw())); } public Location getCompassTarget() { return (getHandle()).compassTarget; } public void chat(String msg) { if ((getHandle()).playerConnection == null) return;  (getHandle()).playerConnection.chat(msg, false); } public boolean performCommand(String command) { return this.server.dispatchCommand((CommandSender)this, command); } public void playNote(Location loc, byte instrument, byte note) { if ((getHandle()).playerConnection == null) return;  String instrumentName = null; switch (instrument) { case 0: instrumentName = "harp"; break;case 1: instrumentName = "basedrum"; break;case 2: instrumentName = "snare"; break;case 3: instrumentName = "hat"; break;case 4: instrumentName = "bass"; break;case 5: instrumentName = "flute"; break;case 6: instrumentName = "bell"; break;case 7: instrumentName = "guitar"; break;case 8: instrumentName = "chime"; break;case 9: instrumentName = "xylophone"; break; }  float f = (float)Math.pow(2.0D, (note - 12.0D) / 12.0D); (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect("block.note_block." + instrumentName), SoundCategory.RECORDS, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 3.0F, f)); } public void playNote(Location loc, Instrument instrument, Note note) { if ((getHandle()).playerConnection == null) return;  String instrumentName = null; switch (instrument.ordinal()) { case 0: instrumentName = "harp"; break;case 1: instrumentName = "basedrum"; break;case 2: instrumentName = "snare"; break;case 3: instrumentName = "hat"; break;case 4: instrumentName = "bass"; break;case 5: instrumentName = "flute"; break;case 6: instrumentName = "bell"; break;case 7: instrumentName = "guitar"; break;case 8: instrumentName = "chime"; break;case 9: instrumentName = "xylophone"; break;case 10: instrumentName = "iron_xylophone"; break;case 11: instrumentName = "cow_bell"; break;case 12: instrumentName = "didgeridoo"; break;case 13: instrumentName = "bit"; break;case 14: instrumentName = "banjo"; break;case 15: instrumentName = "pling"; break;case 16: instrumentName = "xylophone"; break; }  float f = (float)Math.pow(2.0D, (note.getId() - 12.0D) / 12.0D); (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect("block.note_block." + instrumentName), SoundCategory.RECORDS, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 3.0F, f)); } public void playSound(Location loc, Sound sound, float volume, float pitch) { playSound(loc, sound, SoundCategory.MASTER, volume, pitch); } public void playSound(Location loc, String sound, float volume, float pitch) { playSound(loc, sound, SoundCategory.MASTER, volume, pitch); } public void playSound(Location loc, Sound sound, SoundCategory category, float volume, float pitch) { if (loc == null || sound == null || category == null || (getHandle()).playerConnection == null) return;  PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect(CraftSound.getSound(sound)), SoundCategory.valueOf(category.name()), loc.getX(), loc.getY(), loc.getZ(), volume, pitch); (getHandle()).playerConnection.sendPacket((Packet)packet); } public void playSound(Location loc, String sound, SoundCategory category, float volume, float pitch) { if (loc == null || sound == null || category == null || (getHandle()).playerConnection == null) return;  PacketPlayOutCustomSoundEffect packet = new PacketPlayOutCustomSoundEffect(new MinecraftKey(sound), SoundCategory.valueOf(category.name()), new Vec3D(loc.getX(), loc.getY(), loc.getZ()), volume, pitch); (getHandle()).playerConnection.sendPacket((Packet)packet); } public void stopSound(Sound sound) { stopSound(sound, (SoundCategory)null); } public void stopSound(String sound) { stopSound(sound, (SoundCategory)null); } public void stopSound(Sound sound, SoundCategory category) { stopSound(CraftSound.getSound(sound), category); } public void stopSound(String sound, SoundCategory category) { if ((getHandle()).playerConnection == null) return;  (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutStopSound(new MinecraftKey(sound), (category == null) ? SoundCategory.MASTER : SoundCategory.valueOf(category.name()))); } public void playEffect(Location loc, Effect effect, int data) { if ((getHandle()).playerConnection == null) return;  int packetData = effect.getId(); PacketPlayOutWorldEvent packet = new PacketPlayOutWorldEvent(packetData, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), data, false); (getHandle()).playerConnection.sendPacket((Packet)packet); } public <T> void playEffect(Location loc, Effect effect, T data) { if (data != null) { Validate.isTrue((effect.getData() != null && effect.getData().isAssignableFrom(data.getClass())), "Wrong kind of data for this effect!"); } else { Validate.isTrue((effect.getData() == null), "Wrong kind of data for this effect!"); }  int datavalue = (data == null) ? 0 : CraftEffect.getDataValue(effect, data); playEffect(loc, effect, datavalue); } public void sendBlockChange(Location loc, Material material, byte data) { if ((getHandle()).playerConnection == null) return;  PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), CraftMagicNumbers.getBlock(material, data)); (getHandle()).playerConnection.sendPacket((Packet)packet); } public void sendBlockChange(Location loc, BlockData block) { if ((getHandle()).playerConnection == null) return;  PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), ((CraftBlockData)block).getState()); (getHandle()).playerConnection.sendPacket((Packet)packet); } public void sendSignChange(Location loc, String[] lines) { sendSignChange(loc, lines, DyeColor.BLACK); } public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor) { if ((getHandle()).playerConnection == null) return;  if (lines == null) lines = new String[4];  Validate.notNull(loc, "Location can not be null"); Validate.notNull(dyeColor, "DyeColor can not be null"); if (lines.length < 4) throw new IllegalArgumentException("Must have at least 4 lines");  IChatBaseComponent[] components = CraftSign.sanitizeLines(lines); TileEntitySign sign = new TileEntitySign(); sign.setPosition(new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())); sign.setColor(EnumColor.fromColorIndex(dyeColor.getWoolData())); System.arraycopy(components, 0, sign.lines, 0, sign.lines.length); (getHandle()).playerConnection.sendPacket((Packet)sign.getUpdatePacket()); } public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) { if ((getHandle()).playerConnection == null) return false;  throw new NotImplementedException("Chunk changes do not yet work"); } public String getClientBrandName() { return ((getHandle()).playerConnection != null) ? (getHandle()).playerConnection.getClientBrandName() : null; }
/*      */   public void sendMap(MapView map) { if ((getHandle()).playerConnection == null) return;  RenderData data = ((CraftMapView)map).render(this); Collection<MapIcon> icons = new ArrayList<>(); for (MapCursor cursor : data.cursors) { if (cursor.isVisible()) icons.add(new MapIcon(MapIcon.Type.a(cursor.getRawType()), cursor.getX(), cursor.getY(), cursor.getDirection(), CraftChatMessage.fromStringOrNull(cursor.getCaption())));  }  PacketPlayOutMap packet = new PacketPlayOutMap(map.getId(), map.getScale().getValue(), true, map.isLocked(), icons, data.buffer, 0, 0, 128, 128); (getHandle()).playerConnection.sendPacket((Packet)packet); }
/*      */   public void setRotation(float yaw, float pitch) { throw new UnsupportedOperationException("Cannot set rotation of players. Consider teleporting instead."); }
/*      */   public CompletableFuture<Boolean> teleportAsync(Location loc, @Nonnull PlayerTeleportEvent.TeleportCause cause) { ((CraftWorld)loc.getWorld()).getHandle().getChunkProvider().markAreaHighPriority(new ChunkCoordIntPair(MathHelper.floor(loc.getX()) >> 4, MathHelper.floor(loc.getZ()) >> 4), 28, 3); return super.teleportAsync(loc, cause); }
/*      */   public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) { Preconditions.checkArgument((location != null), "location"); Preconditions.checkArgument((location.getWorld() != null), "location.world"); location.checkFinite(); EntityPlayer entity = getHandle(); if (getHealth() == 0.0D || entity.dead) return false;  if (entity.playerConnection == null) return false;  if (entity.isVehicle()) return false;  Location from = getLocation(); Location to = location; PlayerTeleportEvent event = new PlayerTeleportEvent(this, from, to, cause); this.server.getPluginManager().callEvent((Event)event); if (event.isCancelled()) return false;  entity.stopRiding(); if (isSleeping()) wakeup(false);  from = event.getFrom(); to = event.getTo(); WorldServer fromWorld = ((CraftWorld)from.getWorld()).getHandle(); WorldServer toWorld = ((CraftWorld)to.getWorld()).getHandle(); if ((getHandle()).activeContainer != (getHandle()).defaultContainer) getHandle().closeInventory(InventoryCloseEvent.Reason.TELEPORT);  if (fromWorld == toWorld) { entity.playerConnection.teleport(to); } else { this.server.getHandle().moveToWorld(entity, toWorld, true, to, !toWorld.paperConfig.disableTeleportationSuffocationCheck); }  return true; }
/*      */   public boolean setPassenger(Entity passenger) { boolean wasSet = super.setPassenger(passenger); if (wasSet) (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutMount((Entity)getHandle()));  return wasSet; }
/* 2155 */   public void setSneaking(boolean sneak) { getHandle().setSneaking(sneak); } public boolean isSneaking() { return getHandle().isSneaking(); } public boolean isSprinting() { return getHandle().isSprinting(); } public void setSprinting(boolean sprinting) { getHandle().setSprinting(sprinting); } public void loadData() { (this.server.getHandle()).playerFileData.load((EntityHuman)getHandle()); } public void saveData() { (this.server.getHandle()).playerFileData.save((EntityHuman)getHandle()); } @Deprecated public void updateInventory() { getHandle().updateInventory((getHandle()).activeContainer); } public void setSleepingIgnored(boolean isSleeping) { (getHandle()).fauxSleeping = isSleeping; ((CraftWorld)getWorld()).getHandle().everyoneSleeping(); } public boolean isSleepingIgnored() { return (getHandle()).fauxSleeping; } public Location getBedSpawnLocation() { WorldServer world = (getHandle()).server.getWorldServer(getHandle().getSpawnDimension()); BlockPosition bed = getHandle().getSpawn(); if (world != null && bed != null) { Optional<Vec3D> spawnLoc = EntityHuman.getBed(world, bed, getHandle().getSpawnAngle(), getHandle().isSpawnForced(), true); if (spawnLoc.isPresent()) { Vec3D vec = spawnLoc.get(); return new Location((World)world.getWorld(), vec.x, vec.y, vec.z); }  }  return null; } public void setBedSpawnLocation(Location location) { setBedSpawnLocation(location, false); } public void setBedSpawnLocation(Location location, boolean override) { if (location == null) { getHandle().setRespawnPosition(null, null, 0.0F, override, false); } else { getHandle().setRespawnPosition(((CraftWorld)location.getWorld()).getHandle().getDimensionKey(), new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), location.getYaw(), override, false); }  } public Location getBedLocation() { Preconditions.checkState(isSleeping(), "Not sleeping"); BlockPosition bed = getHandle().getSpawn(); return new Location(getWorld(), bed.getX(), bed.getY(), bed.getZ()); } public boolean hasDiscoveredRecipe(NamespacedKey recipe) { Preconditions.checkArgument((recipe != null), "recipe cannot be null"); return getHandle().getRecipeBook().hasDiscoveredRecipe(CraftNamespacedKey.toMinecraft(recipe)); } public Set<NamespacedKey> getDiscoveredRecipes() { ImmutableSet.Builder<NamespacedKey> bukkitRecipeKeys = ImmutableSet.builder(); (getHandle().getRecipeBook()).recipes.forEach(key -> bukkitRecipeKeys.add(CraftNamespacedKey.fromMinecraft(key))); return (Set<NamespacedKey>)bukkitRecipeKeys.build(); } public void incrementStatistic(Statistic statistic) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic); } public void decrementStatistic(Statistic statistic) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic); } public int getStatistic(Statistic statistic) { return CraftStatistic.getStatistic(getHandle().getStatisticManager(), statistic); } public void incrementStatistic(Statistic statistic, int amount) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic, amount); } public void decrementStatistic(Statistic statistic, int amount) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic, amount); } public void setStatistic(Statistic statistic, int newValue) { CraftStatistic.setStatistic(getHandle().getStatisticManager(), statistic, newValue); } public void incrementStatistic(Statistic statistic, Material material) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic, material); } public void decrementStatistic(Statistic statistic, Material material) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic, material); } public int getStatistic(Statistic statistic, Material material) { return CraftStatistic.getStatistic(getHandle().getStatisticManager(), statistic, material); } public void incrementStatistic(Statistic statistic, Material material, int amount) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic, material, amount); } public void decrementStatistic(Statistic statistic, Material material, int amount) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic, material, amount); } public void setStatistic(Statistic statistic, Material material, int newValue) { CraftStatistic.setStatistic(getHandle().getStatisticManager(), statistic, material, newValue); } public void incrementStatistic(Statistic statistic, EntityType entityType) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic, entityType); } public void decrementStatistic(Statistic statistic, EntityType entityType) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic, entityType); } public int getStatistic(Statistic statistic, EntityType entityType) { return CraftStatistic.getStatistic(getHandle().getStatisticManager(), statistic, entityType); } public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) { CraftStatistic.incrementStatistic(getHandle().getStatisticManager(), statistic, entityType, amount); } public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) { CraftStatistic.decrementStatistic(getHandle().getStatisticManager(), statistic, entityType, amount); } public void setStatistic(Statistic statistic, EntityType entityType, int newValue) { CraftStatistic.setStatistic(getHandle().getStatisticManager(), statistic, entityType, newValue); } public void setPlayerTime(long time, boolean relative) { (getHandle()).timeOffset = time; (getHandle()).relativeTime = relative; } public long getPlayerTimeOffset() { return (getHandle()).timeOffset; } public long getPlayerTime() { return getHandle().getPlayerTime(); } public boolean isPlayerTimeRelative() { return (getHandle()).relativeTime; } public void resetPlayerTime() { setPlayerTime(0L, true); } public void setPlayerWeather(WeatherType type) { getHandle().setPlayerWeather(type, true); } public WeatherType getPlayerWeather() { return getHandle().getPlayerWeather(); } public void resetPlayerWeather() { getHandle().resetPlayerWeather(); } public boolean isBanned() { return this.server.getBanList(BanList.Type.NAME).isBanned(getName()); } public boolean isWhitelisted() { return this.server.getHandle().getWhitelist().isWhitelisted(getProfile()); } public void setWhitelisted(boolean value) { if (value) { this.server.getHandle().getWhitelist().add((JsonListEntry)new WhiteListEntry(getProfile())); } else { this.server.getHandle().getWhitelist().remove(getProfile()); }  } public void setGameMode(GameMode mode) { if ((getHandle()).playerConnection == null) return;  if (mode == null) throw new IllegalArgumentException("Mode cannot be null");  getHandle().a(EnumGamemode.getById(mode.getValue())); } public GameMode getGameMode() { return GameMode.getByValue((getHandle()).playerInteractManager.getGameMode().getId()); } public int applyMending(int amount) { EntityPlayer handle = getHandle(); ItemStack itemstack = EnchantmentManager.getRandomEquippedItemWithEnchant(Enchantments.MENDING, (EntityLiving)handle); if (!itemstack.isEmpty() && itemstack.getItem().usesDurability()) { EntityExperienceOrb orb = (EntityExperienceOrb)EntityTypes.EXPERIENCE_ORB.create(handle.world); orb.value = amount; orb.spawnReason = ExperienceOrb.SpawnReason.CUSTOM; orb.setPositionRaw(handle.locX(), handle.locY(), handle.locZ()); int i = Math.min(orb.xpToDur(amount), itemstack.getDamage()); PlayerItemMendEvent event = CraftEventFactory.callPlayerItemMendEvent((EntityHuman)handle, orb, itemstack, i); i = event.getRepairAmount(); orb.dead = true; if (!event.isCancelled()) { amount -= orb.durToXp(i); itemstack.setDamage(itemstack.getDamage() - i); }  }  return amount; } public void giveExp(int exp, boolean applyMending) { if (applyMending) exp = applyMending(exp);  getHandle().giveExp(exp); } public void giveExpLevels(int levels) { getHandle().levelDown(levels); } public float getExp() { return (getHandle()).exp; } public void setExp(float exp) { Preconditions.checkArgument((exp >= 0.0D && exp <= 1.0D), "Experience progress must be between 0.0 and 1.0 (%s)", Float.valueOf(exp)); (getHandle()).exp = exp; (getHandle()).lastSentExp = -1; } public int getLevel() { return (getHandle()).expLevel; } public void setLevel(int level) { Preconditions.checkArgument((level >= 0), "Experience level must not be negative (%s)", level); (getHandle()).expLevel = level; (getHandle()).lastSentExp = -1; } public int getTotalExperience() { return (getHandle()).expTotal; } public void setTotalExperience(int exp) { Preconditions.checkArgument((exp >= 0), "Total experience points must not be negative (%s)", exp); (getHandle()).expTotal = exp; } public void sendExperienceChange(float progress) { sendExperienceChange(progress, getLevel()); } public void sendExperienceChange(float progress, int level) { Preconditions.checkArgument((progress >= 0.0D && progress <= 1.0D), "Experience progress must be between 0.0 and 1.0 (%s)", Float.valueOf(progress)); Preconditions.checkArgument((level >= 0), "Experience level must not be negative (%s)", level); if ((getHandle()).playerConnection == null) return;  PacketPlayOutExperience packet = new PacketPlayOutExperience(progress, getTotalExperience(), level); (getHandle()).playerConnection.sendPacket((Packet)packet); } public float getExhaustion() { return (getHandle().getFoodData()).exhaustionLevel; } public void setExhaustion(float value) { (getHandle().getFoodData()).exhaustionLevel = value; } public float getSaturation() { return (getHandle().getFoodData()).saturationLevel; } public void setSaturation(float value) { (getHandle().getFoodData()).saturationLevel = value; } public int getFoodLevel() { return (getHandle().getFoodData()).foodLevel; } public void setFoodLevel(int value) { (getHandle().getFoodData()).foodLevel = value; } @Nullable private static WeakReference<Plugin> getPluginWeakReference(@Nullable Plugin plugin) { return (plugin == null) ? null : pluginWeakReferences.computeIfAbsent(plugin, WeakReference::new); } @Deprecated public void hidePlayer(Player player) { hidePlayer0((Plugin)null, player); } public void hidePlayer(Plugin plugin, Player player) { Validate.notNull(plugin, "Plugin cannot be null"); Validate.isTrue(plugin.isEnabled(), "Plugin attempted to hide player while disabled"); hidePlayer0(plugin, player); } private void hidePlayer0(@Nullable Plugin plugin, Player player) { Validate.notNull(player, "hidden player cannot be null"); if ((getHandle()).playerConnection == null) return;  if (equals(player)) return;  Set<WeakReference<Plugin>> hidingPlugins = this.hiddenPlayers.get(player.getUniqueId()); if (hidingPlugins != null) { hidingPlugins.add(getPluginWeakReference(plugin)); return; }  hidingPlugins = new HashSet<>(); hidingPlugins.add(getPluginWeakReference(plugin)); this.hiddenPlayers.put(player.getUniqueId(), hidingPlugins); EntityPlayer other = ((CraftPlayer)player).getHandle(); unregisterPlayer(other); } private void unregisterPlayer(EntityPlayer other) { PlayerChunkMap tracker = (((WorldServer)this.entity.world).getChunkProvider()).playerChunkMap; PlayerChunkMap.EntityTracker entry = (PlayerChunkMap.EntityTracker)tracker.trackedEntities.get(other.getId()); if (entry != null) entry.clear(getHandle());  if (other.sentListPacket) (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { other }));  } @Deprecated public void showPlayer(Player player) { showPlayer0((Plugin)null, player); } public void showPlayer(Plugin plugin, Player player) { Validate.notNull(plugin, "Plugin cannot be null"); showPlayer0(plugin, player); } private void showPlayer0(@Nullable Plugin plugin, Player player) { Validate.notNull(player, "shown player cannot be null"); if ((getHandle()).playerConnection == null) return;  if (equals(player)) return;  Set<WeakReference<Plugin>> hidingPlugins = this.hiddenPlayers.get(player.getUniqueId()); if (hidingPlugins == null) return;  hidingPlugins.remove(getPluginWeakReference(plugin)); if (!hidingPlugins.isEmpty()) return;  this.hiddenPlayers.remove(player.getUniqueId()); EntityPlayer other = ((CraftPlayer)player).getHandle(); registerPlayer(other); } private void registerPlayer(EntityPlayer other) { PlayerChunkMap tracker = (((WorldServer)this.entity.world).getChunkProvider()).playerChunkMap; (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { other })); PlayerChunkMap.EntityTracker entry = (PlayerChunkMap.EntityTracker)tracker.trackedEntities.get(other.getId()); if (entry != null && !entry.trackedPlayers.contains(getHandle())) entry.updatePlayer(getHandle());  } private void reregisterPlayer(EntityPlayer player) { if (!this.hiddenPlayers.containsKey(player.getUniqueID())) { unregisterPlayer(player); registerPlayer(player); }  } public void setPlayerProfile(PlayerProfile profile) { EntityPlayer self = getHandle(); self.setProfile(CraftPlayerProfile.asAuthlibCopy(profile)); if (!self.sentListPacket) return;  List<EntityPlayer> players = (this.server.getServer().getPlayerList()).players; for (EntityPlayer player : players) player.getBukkitEntity().reregisterPlayer(self);  refreshPlayer(); } public PlayerProfile getPlayerProfile() { return (PlayerProfile)(new CraftPlayerProfile(this)).clone(); } private void refreshPlayer() { EntityPlayer handle = getHandle(); Location loc = getLocation(); PlayerConnection connection = handle.playerConnection; reregisterPlayer(handle); WorldServer worldserver = handle.getWorldServer(); connection.sendPacket((Packet)new PacketPlayOutRespawn(worldserver.getDimensionManager(), worldserver.getDimensionKey(), BiomeManager.a(worldserver.getSeed()), handle.playerInteractManager.getGameMode(), handle.playerInteractManager.c(), worldserver.isDebugWorld(), worldserver.isFlatWorld(), true)); handle.updateAbilities(); connection.sendPacket((Packet)new PacketPlayOutPosition(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch(), new HashSet(), 0)); MinecraftServer.getServer().getPlayerList().updateClient(handle); if (isOp()) { setOp(false); setOp(true); }  } public void removeDisconnectingPlayer(Player player) { this.hiddenPlayers.remove(player.getUniqueId()); } public boolean canSee(Player player) { return !this.hiddenPlayers.containsKey(player.getUniqueId()); } public Map<String, Object> serialize() { Map<String, Object> result = new LinkedHashMap<>(); result.put("name", getName()); return result; } public Player getPlayer() { return this; } public EntityPlayer getHandle() { return (EntityPlayer)this.entity; } public void setHandle(EntityPlayer entity) { setHandle((EntityHuman)entity); } public String toString() { return "CraftPlayer{name=" + getName() + '}'; } public int hashCode() { if (this.hash == 0 || this.hash == 485) this.hash = 485 + ((getUniqueId() != null) ? getUniqueId().hashCode() : 0);  return this.hash; } public long getFirstPlayed() { return this.firstPlayed; } public long getLastPlayed() { return this.lastPlayed; } public boolean hasPlayedBefore() { return this.hasPlayedBefore; } public void setFirstPlayed(long firstPlayed) { this.firstPlayed = firstPlayed; } public long getLastLogin() { return (getHandle()).loginTime; } public long getLastSeen() { return isOnline() ? System.currentTimeMillis() : this.lastSaveTime; } public void readExtraData(NBTTagCompound nbttagcompound) { this.hasPlayedBefore = true; if (nbttagcompound.hasKey("bukkit")) { NBTTagCompound data = nbttagcompound.getCompound("bukkit"); if (data.hasKey("firstPlayed")) { this.firstPlayed = data.getLong("firstPlayed"); this.lastPlayed = data.getLong("lastPlayed"); }  if (data.hasKey("newExp")) { EntityPlayer handle = getHandle(); handle.newExp = data.getInt("newExp"); handle.newTotalExp = data.getInt("newTotalExp"); handle.newLevel = data.getInt("newLevel"); handle.expToDrop = data.getInt("expToDrop"); handle.keepLevel = data.getBoolean("keepLevel"); }  }  } public void setExtraData(NBTTagCompound nbttagcompound) { this.lastSaveTime = System.currentTimeMillis(); if (!nbttagcompound.hasKey("bukkit")) nbttagcompound.set("bukkit", (NBTBase)new NBTTagCompound());  NBTTagCompound data = nbttagcompound.getCompound("bukkit"); EntityPlayer handle = getHandle(); data.setInt("newExp", handle.newExp); data.setInt("newTotalExp", handle.newTotalExp); data.setInt("newLevel", handle.newLevel); data.setInt("expToDrop", handle.expToDrop); data.setBoolean("keepLevel", handle.keepLevel); data.setLong("firstPlayed", getFirstPlayed()); data.setLong("lastPlayed", System.currentTimeMillis()); data.setString("lastKnownName", handle.getName()); if (!nbttagcompound.hasKey("Paper")) nbttagcompound.set("Paper", (NBTBase)new NBTTagCompound());  NBTTagCompound paper = nbttagcompound.getCompound("Paper"); paper.setLong("LastLogin", handle.loginTime); paper.setLong("LastSeen", System.currentTimeMillis()); } public boolean beginConversation(Conversation conversation) { return this.conversationTracker.beginConversation(conversation); } public void abandonConversation(Conversation conversation) { this.conversationTracker.abandonConversation(conversation, new ConversationAbandonedEvent(conversation, (ConversationCanceller)new ManuallyAbandonedConversationCanceller())); } public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) { this.conversationTracker.abandonConversation(conversation, details); } public void acceptConversationInput(String input) { this.conversationTracker.acceptConversationInput(input); } public boolean isConversing() { return this.conversationTracker.isConversing(); } public void sendPluginMessage(Plugin source, String channel, byte[] message) { StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message); if ((getHandle()).playerConnection == null) return;  if (this.channels.contains(channel)) { channel = StandardMessenger.validateAndCorrectChannel(channel); PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload(new MinecraftKey(channel), new PacketDataSerializer(Unpooled.wrappedBuffer(message))); (getHandle()).playerConnection.sendPacket((Packet)packet); }  } public void setTexturePack(String url) { setResourcePack(url); } public void setResourcePack(String url) { Validate.notNull(url, "Resource pack URL cannot be null"); getHandle().setResourcePack(url, "null"); } public void setResourcePack(String url, byte[] hash) { Validate.notNull(url, "Resource pack URL cannot be null"); Validate.notNull(hash, "Resource pack hash cannot be null"); Validate.isTrue((hash.length == 20), "Resource pack hash should be 20 bytes long but was " + hash.length); getHandle().setResourcePack(url, BaseEncoding.base16().lowerCase().encode(hash)); } public void addChannel(String channel) { Preconditions.checkState((DISABLE_CHANNEL_LIMIT || this.channels.size() < 128), "Cannot register channel '%s'. Too many channels registered!", channel); channel = StandardMessenger.validateAndCorrectChannel(channel); if (this.channels.add(channel)) this.server.getPluginManager().callEvent((Event)new PlayerRegisterChannelEvent(this, channel));  } public void removeChannel(String channel) { channel = StandardMessenger.validateAndCorrectChannel(channel); if (this.channels.remove(channel)) this.server.getPluginManager().callEvent((Event)new PlayerUnregisterChannelEvent(this, channel));  } public Set<String> getListeningPluginChannels() { return (Set<String>)ImmutableSet.copyOf(this.channels); } public void sendSupportedChannels() { if ((getHandle()).playerConnection == null) return;  Set<String> listening = this.server.getMessenger().getIncomingChannels(); if (!listening.isEmpty()) { ByteArrayOutputStream stream = new ByteArrayOutputStream(); for (String channel : listening) { try { stream.write(channel.getBytes("UTF8")); stream.write(0); } catch (IOException ex) { Logger.getLogger(CraftPlayer.class.getName()).log(Level.SEVERE, "Could not send Plugin Channel REGISTER to " + getName(), ex); }  }  (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutCustomPayload(new MinecraftKey("register"), new PacketDataSerializer(Unpooled.wrappedBuffer(stream.toByteArray())))); }  } public EntityType getType() { return EntityType.PLAYER; } public void setMetadata(String metadataKey, MetadataValue newMetadataValue) { this.server.getPlayerMetadata().setMetadata(this, metadataKey, newMetadataValue); } public List<MetadataValue> getMetadata(String metadataKey) { return this.server.getPlayerMetadata().getMetadata(this, metadataKey); } public boolean hasMetadata(String metadataKey) { return this.server.getPlayerMetadata().hasMetadata(this, metadataKey); } public void removeMetadata(String metadataKey, Plugin owningPlugin) { this.server.getPlayerMetadata().removeMetadata(this, metadataKey, owningPlugin); } public boolean setWindowProperty(InventoryView.Property prop, int value) { Container container = (getHandle()).activeContainer; if (container.getBukkitView().getType() != prop.getType()) return false;  getHandle().setContainerData(container, prop.getId(), value); return true; } public void disconnect(String reason) { this.conversationTracker.abandonAllConversations(); this.perm.clearPermissions(); } public boolean isFlying() { return (getHandle()).abilities.isFlying; } public void setFlying(boolean value) { boolean needsUpdate = ((getHandle()).abilities.isFlying != value); if (!getAllowFlight() && value) throw new IllegalArgumentException("Cannot make player fly if getAllowFlight() is false");  (getHandle()).abilities.isFlying = value; if (needsUpdate) getHandle().updateAbilities();  } public boolean getAllowFlight() { return (getHandle()).abilities.canFly; } public void setAllowFlight(boolean value) { if (isFlying() && !value) (getHandle()).abilities.isFlying = false;  (getHandle()).abilities.canFly = value; getHandle().updateAbilities(); } public int getNoDamageTicks() { if ((getHandle()).invulnerableTicks > 0) return Math.max((getHandle()).invulnerableTicks, (getHandle()).noDamageTicks);  return (getHandle()).noDamageTicks; } public void setNoDamageTicks(int ticks) { super.setNoDamageTicks(ticks); (getHandle()).invulnerableTicks = ticks; } public void setFlySpeed(float value) { validateSpeed(value); EntityPlayer player = getHandle(); player.abilities.flySpeed = value / 2.0F; player.updateAbilities(); } public void setWalkSpeed(float value) { validateSpeed(value); EntityPlayer player = getHandle(); player.abilities.walkSpeed = value / 2.0F; player.updateAbilities(); getHandle().getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(player.abilities.walkSpeed); } public float getFlySpeed() { return (getHandle()).abilities.flySpeed * 2.0F; } public float getWalkSpeed() { return (getHandle()).abilities.walkSpeed * 2.0F; } private void validateSpeed(float value) { if (value < 0.0F) { if (value < -1.0F) throw new IllegalArgumentException(value + " is too low");  } else if (value > 1.0F) { throw new IllegalArgumentException(value + " is too high"); }  } public void setMaxHealth(double amount) { super.setMaxHealth(amount); this.health = Math.min(this.health, this.health); getHandle().triggerHealthUpdate(); } public void resetMaxHealth() { super.resetMaxHealth(); getHandle().triggerHealthUpdate(); } public CraftScoreboard getScoreboard() { return this.server.getScoreboardManager().getPlayerBoard(this); } public void setScoreboard(Scoreboard scoreboard) { Validate.notNull(scoreboard, "Scoreboard cannot be null"); PlayerConnection playerConnection = (getHandle()).playerConnection; if (playerConnection == null) throw new IllegalStateException("Cannot set scoreboard yet");  if (playerConnection.isDisconnected()); this.server.getScoreboardManager().setPlayerBoard(this, scoreboard); } public void setHealthScale(double value) { Validate.isTrue(((float)value > 0.0F), "Must be greater than 0"); this.healthScale = value; this.scaledHealth = true; updateScaledHealth(); } public double getHealthScale() { return this.healthScale; } public void setHealthScaled(boolean scale) { if (this.scaledHealth != (this.scaledHealth = scale)) updateScaledHealth();  } public boolean isHealthScaled() { return this.scaledHealth; } public float getScaledHealth() { return (float)(isHealthScaled() ? (getHealth() * getHealthScale() / getMaxHealth()) : getHealth()); } public double getHealth() { return this.health; } public void setRealHealth(double health) { if (Double.isNaN(health)) return;  this.health = health; } public void updateScaledHealth() { updateScaledHealth(true); } public void updateScaledHealth(boolean sendHealth) { AttributeMapBase attributemapserver = getHandle().getAttributeMap(); Collection<AttributeModifiable> set = attributemapserver.b(); injectScaledMaxHealth(set, true); if ((getHandle()).playerConnection != null) { (getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutUpdateAttributes(getHandle().getId(), set)); if (sendHealth) sendHealthUpdate();  }  getHandle().getDataWatcher().set(EntityLiving.HEALTH, Float.valueOf(getScaledHealth())); (getHandle()).maxHealthCache = getMaxHealth(); } public void sendHealthUpdate() { PacketPlayOutUpdateHealth packet = new PacketPlayOutUpdateHealth(getScaledHealth(), getHandle().getFoodData().getFoodLevel(), getHandle().getFoodData().getSaturationLevel()); if ((getHandle()).queueHealthUpdatePacket) { (getHandle()).queuedHealthUpdatePacket = packet; } else { (getHandle()).playerConnection.sendPacket((Packet)packet); }  } public void injectScaledMaxHealth(Collection<AttributeModifiable> collection, boolean force) { if (!this.scaledHealth && !force) return;  for (AttributeModifiable genericInstance : collection) { if (genericInstance.getAttribute() == GenericAttributes.MAX_HEALTH) { collection.remove(genericInstance); break; }  }  AttributeModifiable dummy = new AttributeModifiable(GenericAttributes.MAX_HEALTH, attribute -> {  }); double healthMod = this.scaledHealth ? this.healthScale : getMaxHealth(); if (healthMod >= 3.4028234663852886E38D || healthMod <= 0.0D) { healthMod = 20.0D; getServer().getLogger().warning(getName() + " tried to crash the server with a large health attribute"); }  dummy.setValue(healthMod); collection.add(dummy); } public Entity getSpectatorTarget() { Entity followed = getHandle().getSpecatorTarget(); return (followed == getHandle()) ? null : followed.getBukkitEntity(); } public void setSpectatorTarget(Entity entity) { Preconditions.checkArgument((getGameMode() == GameMode.SPECTATOR), "Player must be in spectator mode"); getHandle().setSpectatorTarget((entity == null) ? null : ((CraftEntity)entity).getHandle()); } public void sendTitle(String title, String subtitle) { sendTitle(title, subtitle, 10, 70, 20); } public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) { PacketPlayOutTitle times = new PacketPlayOutTitle(fadeIn, stay, fadeOut); (getHandle()).playerConnection.sendPacket((Packet)times); if (title != null) { PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromStringOrNull(title)); (getHandle()).playerConnection.sendPacket((Packet)packetTitle); }  if (subtitle != null) { PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromStringOrNull(subtitle)); (getHandle()).playerConnection.sendPacket((Packet)packetSubtitle); }  } public void resetTitle() { PacketPlayOutTitle packetReset = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null); (getHandle()).playerConnection.sendPacket((Packet)packetReset); } public void spawnParticle(Particle particle, Location location, int count) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count); } public void spawnParticle(Particle particle, double x, double y, double z, int count) { spawnParticle(particle, x, y, z, count, (Object)null); } public <T> void spawnParticle(Particle particle, Location location, int count, T data) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data); } public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) { spawnParticle(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, data); } public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ); } public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) { spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, (Object)null); } public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data); } public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) { spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1.0D, data); } public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra); } public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) { spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, (Object)null); } public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) { spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data); } public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) { if (data != null && !particle.getDataType().isInstance(data)) throw new IllegalArgumentException("data should be " + particle.getDataType() + " got " + data.getClass());  PacketPlayOutWorldParticles packetplayoutworldparticles = new PacketPlayOutWorldParticles(CraftParticle.toNMS(particle, data), true, (float)x, (float)y, (float)z, (float)offsetX, (float)offsetY, (float)offsetZ, (float)extra, count); (getHandle()).playerConnection.sendPacket((Packet)packetplayoutworldparticles); } public AdvancementProgress getAdvancementProgress(Advancement advancement) { Preconditions.checkArgument((advancement != null), "advancement"); CraftAdvancement craft = (CraftAdvancement)advancement; AdvancementDataPlayer data = getHandle().getAdvancementData(); AdvancementProgress progress = data.getProgress(craft.getHandle()); return (AdvancementProgress)new CraftAdvancementProgress(craft, data, progress); } public int getClientViewDistance() { return ((getHandle()).clientViewDistance == null) ? Bukkit.getViewDistance() : (getHandle()).clientViewDistance.intValue(); } public String getLocale() { String locale = (getHandle()).locale; return (locale != null) ? locale : "en_us"; } public void setAffectsSpawning(boolean affects) { (getHandle()).affectsSpawning = affects; } public boolean getAffectsSpawning() { return (getHandle()).affectsSpawning; } public void setResourcePack(String url, String hash) { Validate.notNull(url, "Resource pack URL cannot be null"); Validate.notNull(hash, "Hash cannot be null"); getHandle().setResourcePack(url, hash); } public PlayerResourcePackStatusEvent.Status getResourcePackStatus() { return this.resourcePackStatus; } public String getResourcePackHash() { return this.resourcePackHash; } public boolean hasResourcePack() { return (this.resourcePackStatus == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED); } public void setResourcePackStatus(PlayerResourcePackStatusEvent.Status status) { this.resourcePackStatus = status; } public void updateCommands() { if ((getHandle()).playerConnection == null) return;  (getHandle()).server.getCommandDispatcher().a(getHandle()); } public void openBook(ItemStack book) { Validate.isTrue((book != null), "book == null"); Validate.isTrue((book.getType() == Material.WRITTEN_BOOK), "Book must be Material.WRITTEN_BOOK"); ItemStack hand = getInventory().getItemInMainHand(); getInventory().setItemInMainHand(book); getHandle().openBook(CraftItemStack.asNMSCopy(book), EnumHand.MAIN_HAND); getInventory().setItemInMainHand(hand); } public float getCooldownPeriod() { return getHandle().getCooldownPeriod(); } public float getCooledAttackStrength(float adjustTicks) { return getHandle().getAttackCooldown(adjustTicks); } public void resetCooldown() { getHandle().resetAttackCooldown(); } public void remove() { if (getHandle().getClass().equals(EntityPlayer.class)) throw new UnsupportedOperationException("Calling Entity#remove on players produces undefined (bad) behavior");  super.remove(); } public int getViewDistance() { throw new NotImplementedException("Per-Player View Distance APIs need further understanding to properly implement (There are per world view distances though!)"); } public void setViewDistance(int viewDistance) { throw new NotImplementedException("Per-Player View Distance APIs need further understanding to properly implement (There are per world view distances though!)"); } public <T> T getClientOption(ClientOption<T> type) { if (ClientOption.SKIN_PARTS.equals(type)) return type.getType().cast(new PaperSkinParts(((Byte)getHandle().getDataWatcher().get(EntityHuman.getSkinPartsWatcher())).byteValue()));  if (ClientOption.CHAT_COLORS_ENABLED.equals(type)) return type.getType().cast(Boolean.valueOf(getHandle().hasChatColorsEnabled()));  if (ClientOption.CHAT_VISIBILITY.equals(type)) return type.getType().cast((getHandle().getChatFlags() == null) ? ClientOption.ChatVisibility.UNKNOWN : ClientOption.ChatVisibility.valueOf(getHandle().getChatFlags().name()));  if (ClientOption.LOCALE.equals(type)) return type.getType().cast(getLocale());  if (ClientOption.MAIN_HAND.equals(type)) return type.getType().cast(getMainHand());  if (ClientOption.VIEW_DISTANCE.equals(type)) return type.getType().cast(Integer.valueOf(getClientViewDistance()));  throw new RuntimeException("Unknown settings type"); } public Player.Spigot spigot() { return this.spigot; }
/*      */ 
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */