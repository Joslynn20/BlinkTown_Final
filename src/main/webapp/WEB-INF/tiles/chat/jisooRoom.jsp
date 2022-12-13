<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
				<div class="chat-input">
					<form name="message-send" id="message-send">
						<input type="hidden" value="typeName" id="type"> <input
							type="hidden" value="senderName" id="sender"> <input
							type="hidden" value="roomIdName" id="roomId"> <input
							type="text" id="message" placeholder="Send a message..." />
						<button type="submit" class="chat-submit" id="chat-submit">
							<i class="material-icons">send</i>
						</button>
					</form>
				</div>
				
				<script type="text/javascript">

		var webSocket = new WebSocket("ws://localhost:8000/ws/chat");

		function sendText() {
			  // Construct a msg object containing the data the server needs to process the message from the chat client.
			  var msg = {
			    type: "message",
			    text: document.getElementById("text").value,
			    id:   clientID,
			    date: Date.now()
			  };

			  // Send the msg object as a JSON-formatted string.
			  exampleSocket.send(JSON.stringify(msg));

			  // Blank the text input element, ready to receive the next line of text from the user.
			  document.getElementById("text").value = "";
			}
	</script>
</body>
</html>