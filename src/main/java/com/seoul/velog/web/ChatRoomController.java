package com.seoul.velog.web;

import com.seoul.velog.domain.chat.ChatRoom;
import com.seoul.velog.domain.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model){
        model.addAttribute("rooms", chatRoomRepository.findAllRoom());

        return "/chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody // 자바 객체를 HTTP 응답 본문의 객체로 변환
    public List<ChatRoom> room(){
        return chatRoomRepository.findAllRoom();
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomInfo(Model model,@PathVariable String roomId){
        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
        model.addAttribute("room", chatRoom);
        log.info("채팅방 입장 화면... name="+chatRoom.getName());
        return  "/chat/roomdetail";
    }

}
