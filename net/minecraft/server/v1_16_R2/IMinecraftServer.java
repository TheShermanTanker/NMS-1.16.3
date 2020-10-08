package net.minecraft.server.v1_16_R2;

public interface IMinecraftServer {
  DedicatedServerProperties getDedicatedServerProperties();
  
  String h_();
  
  int p();
  
  String i_();
  
  String getVersion();
  
  int getPlayerCount();
  
  int getMaxPlayers();
  
  String[] getPlayers();
  
  String getWorld();
  
  String getPlugins();
  
  String executeRemoteCommand(String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IMinecraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */