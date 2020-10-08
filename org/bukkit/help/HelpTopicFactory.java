package org.bukkit.help;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HelpTopicFactory<TCommand extends org.bukkit.command.Command> {
  @Nullable
  HelpTopic createTopic(@NotNull TCommand paramTCommand);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\HelpTopicFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */