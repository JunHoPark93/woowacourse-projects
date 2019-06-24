const WHITE = "white"
const BLACK = "black"
let NOW_TURN
const SELECT = 0
const MOVE = 1
let MODE = SELECT
let SOURCE

demoInitBoard()
$("td").on("click",clickBoardHandler)

function demoInitBoard() {
    NOW_TURN = BLACK;
    $("#nowTurn").text(NOW_TURN)

    demoSetTeamAttr();
}

function demoSetTeamAttr() {
    let blackArea = ["a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"]
    let whiteArea = ["a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"]

    blackArea.forEach(function(area){
        $(`#${area}`).attr("team",BLACK)
    })

    whiteArea.forEach(function(area){
        $(`#${area}`).attr("team",WHITE)
    })
}

function clickBoardHandler() {
    let element = $(this)
    if (MODE === SELECT) {
        if (element.attr("team") === NOW_TURN) {
            getMovable(element);
        }
        return
    }

    if (MODE === MOVE) {
        if (element.hasClass("movable")) {
            demoMove(element)
        }
        if (element.hasClass("selected")) {
            changeModeToSelect()
        }
        return
    }

}

function getMovable(element) {
    console.log(MODE + ", " + element.attr("id"))
    SOURCE = element.attr("id")
    // ajax 통신
    let body = {src:SOURCE}
    let query = JSON.stringify(body)

    $.ajax({
        type: "POST",
        url: "/movableList",
        contentType: "application/json",
        data: query,
        success: function(data) {
            console.log(data);
            demoDrawMovable(data)
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}

function demoDrawMovable(data) {
    if (data.length == 0) {
        return
    }

    let movableArea = JSON.parse(data)

    MODE = MOVE
    $(`#${SOURCE}`).addClass("selected")
    movableArea.forEach(function(area){
        let x = area.square.x.xPosition;
        let y = area.square.y.yPosition;
        console.log("x : " + area.square.x.xPosition);
        console.log("y : " + area.square.y.yPosition);

        $(`#${x}${y}`).addClass("movable")
    })
}

function demoMove(element) {
    //ajax 통신
    console.log("Move")
    let body = {src:SOURCE, trg:element.attr("id")}
    let query = JSON.stringify(body)

    $.ajax({
        type: "POST",
        url: "/move",
        contentType: "application/json",
        data: query,
        success: function(data) {
            console.log(data);
            demoDrawMove(data)
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}

function changeModeToSelect() {
    console.log("Change")
    MODE = SELECT
    $("#board td").removeClass("movable")
    $("#board td").removeClass("selected")
}

function demoDrawMove(data) {
    console.log("dat:" + data);
    let target = JSON.parse(data)
    console.log(target)
    if (target.turn != null) {
        window.location.href = "/end?loser=" + target.turn
        console.log("end")
        return
    }
    let x = target.x.xPosition;
    let y = target.y.yPosition;
    let targetId = x+y
    let piece = $(`#${SOURCE}`).text()
    let diePiece = $(`#${targetId}`).text()
    let deadArea = $("#"+targetId).attr("team") + "_dead"
    $(`#${deadArea}`).text($(`#${deadArea}`).text() + diePiece)
    $(`#${SOURCE}`).text("")
    $(`#${SOURCE}`).removeAttr("team")
    $(`#${targetId}`).text(piece)
    $(`#${targetId}`).attr("team",NOW_TURN)

    changeNowTurn()
    changeModeToSelect()
}

function changeNowTurn() {
    if (NOW_TURN === WHITE) {
        NOW_TURN = BLACK
    } else {
        NOW_TURN = WHITE
    }

    $("#nowTurn").text(NOW_TURN)
}