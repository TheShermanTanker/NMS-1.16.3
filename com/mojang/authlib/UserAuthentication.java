package com.mojang.authlib;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.properties.PropertyMap;
import java.util.Map;

public interface UserAuthentication {
  boolean canLogIn();
  
  void logIn() throws AuthenticationException;
  
  void logOut();
  
  boolean isLoggedIn();
  
  boolean canPlayOnline();
  
  GameProfile[] getAvailableProfiles();
  
  GameProfile getSelectedProfile();
  
  void selectGameProfile(GameProfile paramGameProfile) throws AuthenticationException;
  
  void loadFromStorage(Map<String, Object> paramMap);
  
  Map<String, Object> saveForStorage();
  
  void setUsername(String paramString);
  
  void setPassword(String paramString);
  
  String getAuthenticatedToken();
  
  String getUserID();
  
  PropertyMap getUserProperties();
  
  UserType getUserType();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\authlib\UserAuthentication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */