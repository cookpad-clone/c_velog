
function findAllRoom(){

}

function createRoom(){
                  var room_name = $("#room_name").val();
                    if("" === room_name) {
                        alert("방 제목을 입력해 주세요.");
                        return;
                    } else {
                        var params = new URLSearchParams();
                        params.append("name",room_name);
                        axios.post('/chat/room', params)
                        .then(
                            response => {
                                alert(response.data.name+"방 개설에 성공했습니다.")
                                room_name = '';
                                this.findAllRoom();
                            }
                        )
                        .catch( response => { alert("채팅방 개설에 실패했습니다."); } );
    }
}

function enterRoom(){
                    var roomId = $(this).attr('key');
                    var sender = prompt('대화명을 입력해 주세요.');
                    if(sender != "") {
                        localStorage.setItem('wschat.sender',sender);
                        localStorage.setItem('wschat.roomId',roomId);
                        location.href="/chat/room/enter/"+roomId;
                    }
}
