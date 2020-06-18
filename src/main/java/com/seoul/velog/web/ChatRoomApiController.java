package com.seoul.velog.web;

import com.seoul.velog.domain.chat.ChatRoom;
import com.seoul.velog.domain.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatRoomApiController {
    private final ChatRoomRepository chatRoomRepository;
    // 채팅방 생성
    @PostMapping("/chat/api/v1/chat/room")
    public ChatRoom createRoom(String name){
        System.out.println("name-->"+name);
        log.info("name : ", name);
        name = "test";
        return chatRoomRepository.createChatRoom(name);
    }
}
