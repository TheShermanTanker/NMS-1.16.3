package org.bukkit.help;

import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HelpMap {
  @Nullable
  HelpTopic getHelpTopic(@NotNull String paramString);
  
  @NotNull
  Collection<HelpTopic> getHelpTopics();
  
  void addTopic(@NotNull HelpTopic paramHelpTopic);
  
  void clear();
  
  void registerHelpTopicFactory(@NotNull Class<?> paramClass, @NotNull HelpTopicFactory<?> paramHelpTopicFactory);
  
  @NotNull
  List<String> getIgnoredPlugins();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\HelpMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */