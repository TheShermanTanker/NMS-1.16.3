/*    */ package com.destroystokyo.paper.proxy;
/*    */ 
/*    */ import com.destroystokyo.paper.PaperConfig;
/*    */ import com.google.common.net.InetAddresses;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.properties.Property;
/*    */ import java.net.InetAddress;
/*    */ import java.security.GeneralSecurityException;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.MessageDigest;
/*    */ import javax.crypto.Mac;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.PacketDataSerializer;
/*    */ 
/*    */ 
/*    */ public class VelocityProxy
/*    */ {
/*    */   private static final int SUPPORTED_FORWARDING_VERSION = 1;
/* 20 */   public static final MinecraftKey PLAYER_INFO_CHANNEL = new MinecraftKey("velocity", "player_info");
/*    */   
/*    */   public static boolean checkIntegrity(PacketDataSerializer buf) {
/* 23 */     byte[] signature = new byte[32];
/* 24 */     buf.readBytes(signature);
/*    */     
/* 26 */     byte[] data = new byte[buf.readableBytes()];
/* 27 */     buf.getBytes(buf.readerIndex(), data);
/*    */     
/*    */     try {
/* 30 */       Mac mac = Mac.getInstance("HmacSHA256");
/* 31 */       mac.init(new SecretKeySpec(PaperConfig.velocitySecretKey, "HmacSHA256"));
/* 32 */       byte[] mySignature = mac.doFinal(data);
/* 33 */       if (!MessageDigest.isEqual(signature, mySignature)) {
/* 34 */         return false;
/*    */       }
/* 36 */     } catch (InvalidKeyException|java.security.NoSuchAlgorithmException e) {
/* 37 */       throw new AssertionError(e);
/*    */     } 
/*    */     
/* 40 */     int version = buf.readVarInt();
/* 41 */     if (version != 1) {
/* 42 */       throw new IllegalStateException("Unsupported forwarding version " + version + ", wanted " + '\001');
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public static InetAddress readAddress(PacketDataSerializer buf) {
/* 49 */     return InetAddresses.forString(buf.readUTF(32767));
/*    */   }
/*    */   
/*    */   public static GameProfile createProfile(PacketDataSerializer buf) {
/* 53 */     GameProfile profile = new GameProfile(buf.readUUID(), buf.readUTF(16));
/* 54 */     readProperties(buf, profile);
/* 55 */     return profile;
/*    */   }
/*    */   
/*    */   private static void readProperties(PacketDataSerializer buf, GameProfile profile) {
/* 59 */     int properties = buf.readVarInt();
/* 60 */     for (int i1 = 0; i1 < properties; i1++) {
/* 61 */       String name = buf.readUTF(32767);
/* 62 */       String value = buf.readUTF(32767);
/* 63 */       String signature = buf.readBoolean() ? buf.readUTF(32767) : null;
/* 64 */       profile.getProperties().put(name, new Property(name, value, signature));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\proxy\VelocityProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */