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
    NOW_TURN = WHITE;
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
            demoGetMovable(element);
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

function demoGetMovable(element) {
    console.log(MODE + ", " + element.attr("id"))
    SOURCE = element.attr("id")
    // ajax 통신
    let movableArea = ["a3","a4"]
    // let movableArea = []
    demoDrawMovable(movableArea)
}

function demoDrawMovable(movableArea) {
    if (movableArea.length == 0) {
        return
    }

    MODE = MOVE
    $(`#${SOURCE}`).addClass("selected")
    movableArea.forEach(function(area){
        $(`#${area}`).addClass("movable")
    })
}

function demoMove(element) {
    //ajax 통신
    console.log("Move")
    demoDrawMove(element)
}

function changeModeToSelect() {
    console.log("Change")
    MODE = SELECT
    $("#board td").removeClass("movable")
    $("#board td").removeClass("selected")
}

function demoDrawMove(element) {
    let target = element.attr("id")
    let piece = $(`#${SOURCE}`).text()
    let diePiece = $(`#${target}`).text()
    $(`#${element.attr("team")}_dead`).text($(`#${element.attr("team")}_dead`).text() + diePiece)
    $(`#${SOURCE}`).text("")
    $(`#${SOURCE}`).removeAttr("team")
    $(`#${target}`).text(piece)
    $(`#${target}`).attr("team",NOW_TURN)

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