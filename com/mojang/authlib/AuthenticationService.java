package com.mojang.authlib;

import com.mojang.authlib.minecraft.MinecraftSessionService;

public interface AuthenticationService {
  UserAuthentication createUserAuthentication(Agent paramAgent);
  
  MinecraftSessionService createMinecraftSessionService();
  
  GameProfileRepository createProfileRepository();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\authlib\AuthenticationService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */