package com.mojang.authlib;

public interface ProfileLookupCallback {
  void onProfileLookupSucceeded(GameProfile paramGameProfile);
  
  void onProfileLookupFailed(GameProfile paramGameProfile, Exception paramException);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\authlib\ProfileLookupCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */