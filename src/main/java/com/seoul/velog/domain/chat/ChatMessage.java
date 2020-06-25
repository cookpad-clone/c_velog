package com.seoul.velog.domain.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class ChatMessage {
    public enum MessageType{
        ENTER, TALK, OUT
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message){
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
