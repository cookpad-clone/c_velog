function findAllRoom() {

}

function createRoom() {
    var room_name = $("#room_name").val();
    if ("" === room_name) {
        alert("방 제목을 입력해 주세요.");
        return;
    } else {
        var data = {"name": room_name};
        console.log("name:"+room_name);
        $.ajax({
            type: 'POST',
            url: 'api/v1/chat/room',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: {"name": room_name}
        }).done(function(data) {
            alert(data + "방 개설에 성공했습니다.")
            room_name = '';
            $("#room_name").val("");
            findAllRoom();
        }).fail(function(error) {
            alert("채팅방 개설에 실패했습니다.");
            console.log(error);
        });

    }
}

function enterRoom(roomId) {
    //var roomId = $(this).attr('key');
    var sender = prompt('대화명을 입력해 주세요.');
    if (sender != "") {
        localStorage.setItem('wschat.sender', sender);
        localStorage.setItem('wschat.roomId', roomId);
        location.href = "/chat/room/enter/" + roomId;
    }
}