package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

public interface Conversable {
  boolean isConversing();
  
  void acceptConversationInput(@NotNull String paramString);
  
  boolean beginConversation(@NotNull Conversation paramConversation);
  
  void abandonConversation(@NotNull Conversation paramConversation);
  
  void abandonConversation(@NotNull Conversation paramConversation, @NotNull ConversationAbandonedEvent paramConversationAbandonedEvent);
  
  void sendRawMessage(@NotNull String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\Conversable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */