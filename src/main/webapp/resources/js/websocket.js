'use strict';

var stompClient = null;
var username = null;

var colors = [
	'#2196F3', '#32c787', '#00BCD4', '#ff5652',
	'#ffc107', '#ff85af', '#FF9800', '#39bbb0'
	];

function connect(chatusername,event) {

	if(chatusername) {

		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({'username' : chatusername}, (user) => {
			stompClient.subscribe('/topic/chat', (payload) => {
				var message = JSON.parse(payload.body);
				var messageArea = document.getElementById('messageArea');

				console.log(message);
				var messageElement = document.createElement('li');

				if(message.messageMode === 'JOIN') {
					refreshUsers();					
					messageElement.classList.add('event-message');
					message.text = message.fromUser.email + ' entrou na sala!';
				} else if (message.messageMode === 'LEAVE') {
					refreshUsers();
					messageElement.classList.add('event-message');
					message.text = message.fromUser.email + ' deixou a sala!';
				} else {
					messageElement.classList.add('chat-message');
					var avatarElement = document.createElement('i');
					var avatarText = document.createTextNode(message.fromUser.email[0]);
					avatarElement.appendChild(avatarText);
					var avatarCor = message.fromUser.email.substring(0,7);
					avatarElement.style['background-color'] = getAvatarColor(avatarCor);

					messageElement.appendChild(avatarElement);

					var usernameElement = document.createElement('span');
					var usernameText = document.createTextNode(message.fromUser.email);
					usernameElement.appendChild(usernameText);
					messageElement.appendChild(usernameElement);
				}

				var textElement = document.createElement('p');
				var messageText = document.createTextNode(message.text);
				textElement.appendChild(messageText);

				messageElement.appendChild(textElement);

				messageArea.appendChild(messageElement);
				messageArea.scrollTop = messageArea.scrollHeight;
			});  
			
			// inscreve na fila próppria
			
			stompClient.subscribe('/user/queue/chat', (payload) => {
				var message = JSON.parse(payload.body);
				var messageArea = document.getElementById('messageArea');

				console.log(message);
				var messageElement = document.createElement('li');

				
					messageElement.classList.add('chat-message');

					var avatarElement = document.createElement('i');
					var avatarText = document.createTextNode(message.fromUser.email[0]);
					avatarElement.appendChild(avatarText);
					var avatarCor = message.fromUser.email.substring(0,7);
					avatarElement.style['background-color'] = getAvatarColor(avatarCor);

					messageElement.appendChild(avatarElement);

					var usernameElement = document.createElement('span');
					var usernameText = document.createTextNode("Mensagem privada de: "+message.fromUser.email + " para: "+message.toUser.email);
					usernameElement.appendChild(usernameText);
										
					messageElement.appendChild(usernameElement);
					messageElement.style['background-color'] = '#c2d4dd';
				

				var textElement = document.createElement('p');
				var messageText = document.createTextNode(message.text);
				textElement.appendChild(messageText);

				messageElement.appendChild(textElement);
				messageArea.appendChild(messageElement);
				messageArea.scrollTop = messageArea.scrollHeight;
			});
		});
	}    
}

function refreshUsers(){
	console.log('hora de atualizar os usuários');
	
	 var jsfCommandLink = document.getElementById("menuform:hdnBtn");
     jsfCommandLink.click();
}

function getAvatarColor(messageSender) {
	var hash = 0;
	for (var i = 0; i < messageSender.length; i++) {
		hash = 31 * hash + messageSender.charCodeAt(i);
	}
	var index = Math.abs(hash % colors.length);
	return colors[index];
}
