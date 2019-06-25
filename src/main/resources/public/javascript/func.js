const WHITE = "WHITE"
const BLACK = "BLACK"
let NOW_TURN
const SELECT = 0
const MOVE = 1
let MODE = SELECT
let SOURCE

initBoard()
$("td").on("click",clickBoardHandler)

function initBoard() {
    $.ajax({
        type: "GET",
        url: "/init",
        contentType: "application/json",
        success: function(data) {
            drawPieces(data)
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}

function drawPieces(data) {
    let pieces = JSON.parse(data)
    let blackPlayer = pieces.blackPlayer.pieces
    let whitePlayer = pieces.whitePlayer.pieces
    let blackDead = pieces.blackDead
    let whiteDead = pieces.whiteDead
    let turn = pieces.turn

    jQuery.each(blackPlayer, function (key, value) {
        $(`#${key}`).text(BLACK_PIECE[value.type])
        $(`#${key}`).attr("team",BLACK)
    })

    jQuery.each(whitePlayer, function (key, value) {
        $(`#${key}`).text(WHITE_PIECE[value.type])
        $(`#${key}`).attr("team",WHITE)
    })

    blackDead.forEach(function(piece){
        let list = $("#BLACK_dead").text()
        $("#BLACK_dead").text(list + BLACK_PIECE[piece.type])
    })

    whiteDead.forEach(function(piece){
        let list = $("#WHITE_dead").text()
        $("#WHITE_dead").text(list + WHITE_PIECE[piece.type])
    })

    NOW_TURN = turn
    $("#nowTurn").text(NOW_TURN)
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
            move(element)
        }
        if (element.hasClass("selected")) {
            changeModeToSelect()
        }
        return
    }

}

function getMovable(element) {
    SOURCE = element.attr("id")
    let body = {src:SOURCE}
    let query = JSON.stringify(body)

    $.ajax({
        type: "POST",
        url: "/movableList",
        contentType: "application/json",
        data: query,
        success: function(data) {
            drawMovable(data)
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}

function drawMovable(data) {
    if (data.length == 0) {
        return
    }

    let movableArea = JSON.parse(data)

    MODE = MOVE
    $(`#${SOURCE}`).addClass("selected")
    movableArea.forEach(function(area){
        let x = area.square.x.xPosition;
        let y = area.square.y.yPosition;

        $(`#${x}${y}`).addClass("movable")
    })
}

function move(element) {
    let body = {src:SOURCE, trg:element.attr("id")}
    let query = JSON.stringify(body)

    $.ajax({
        type: "POST",
        url: "/move",
        contentType: "application/json",
        data: query,
        success: function(data) {
            drawMove(data)
        },
        error: function(e) {
            console.log(e.message);
        }
    });
}

function changeModeToSelect() {
    MODE = SELECT
    $("#board td").removeClass("movable")
    $("#board td").removeClass("selected")
}

function drawMove(data) {
    let target = JSON.parse(data)
    if (target.turn != null) {
        window.location.href = "/end?loser=" + target.turn
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