package com.mojang.authlib.minecraft;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import java.net.InetAddress;
import java.util.Map;

public interface MinecraftSessionService {
  void joinServer(GameProfile paramGameProfile, String paramString1, String paramString2) throws AuthenticationException;
  
  GameProfile hasJoinedServer(GameProfile paramGameProfile, String paramString, InetAddress paramInetAddress) throws AuthenticationUnavailableException;
  
  Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(GameProfile paramGameProfile, boolean paramBoolean);
  
  GameProfile fillProfileProperties(GameProfile paramGameProfile, boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\authlib\minecraft\MinecraftSessionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */