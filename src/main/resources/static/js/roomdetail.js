//alert(document.title);
// websocket & stomp initialize
var sock = new SockJS("/ws-stomp");
var ws = Stomp.over(sock);
var reconnect = 0;
// vue.js

var data = {
    roomId: '',
    room: {},
    sender: '',
    message: '',
    messages: []
};

function created() {
    this.roomId = localStorage.getItem('wschat.roomId');
    this.sender = localStorage.getItem('wschat.sender');
    this.findRoom();
};

function findRoom() {
    axios.get('/chat/room/' + this.roomId).then(response => { this.room = response.data; });
};
function sendMessage () {
    ws.send("/pub/chat/message", {}, JSON.stringify({ type: 'TALK', roomId: this.roomId, sender: this.sender, message: this.message }));
    this.message = '';
}
function recvMessage (recv) {
    this.messages.unshift({ "type": recv.type, "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender, "message": recv.message })
};


function connect() {
    // pub/sub event
    ws.connect({}, function (frame) {
        ws.subscribe("/sub/chat/room/" + vm.$data.roomId, function (message) {
            var recv = JSON.parse(message.body);
            vm.recvMessage(recv);
        });
        ws.send("/pub/chat/message", {}, JSON.stringify({ type: 'ENTER', roomId: vm.$data.roomId, sender: vm.$data.sender }));
    }, function (error) {
        if (reconnect++ <= 5) {
            setTimeout(function () {
                console.log("connection reconnect");
                sock = new SockJS("/ws-stomp");
                ws = Stomp.over(sock);
                connect();
            }, 10 * 1000);
        }
    });
}
//connect();