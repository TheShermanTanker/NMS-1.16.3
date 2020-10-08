package org.bukkit.conversations;

import java.util.EventListener;
import org.jetbrains.annotations.NotNull;

public interface ConversationAbandonedListener extends EventListener {
  void conversationAbandoned(@NotNull ConversationAbandonedEvent paramConversationAbandonedEvent);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ConversationAbandonedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */