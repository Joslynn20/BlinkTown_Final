package web.mvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import web.mvc.dto.ChatRoom;
import web.mvc.service.ChatService;

@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	@PostMapping
	public ChatRoom createRoom(@RequestBody String name) {
		return chatService.createRoom(name);
	}
	
	@GetMapping
	public List<ChatRoom> findAllRoom(){
		return chatService.findAllRoom();
	}
}
