$(document).ready(function() {
    const board = $("#board");
    const gameId = board.data("game-id");
    const boardSize = board.data("size");
    let currentPlayer = $("#currentPlayer").text();
    let gameStatus = "IN_PROGRESS";

    function initializeBoard() {
        const cells = $(".cell");

        if (boardSize > 3) {
            $(".board-table").attr("data-size", boardSize);
        }

        cells.on("click", function() {
            if (gameStatus !== "IN_PROGRESS") {
                showMessage("Game is over. Click Reset to play again.", "warning");
                return;
            }

            const cell = $(this);
            const row = cell.data("row");
            const col = cell.data("col");

            if (!cell.text()) {
                makeMove(row, col);
            } else {
                showMessage("Cell is already marked!", "warning");
            }
        });

        cells.hover(
            function() {
                if ($(this).text() === '' && gameStatus === "IN_PROGRESS") {
                    $(this).addClass("hover-" + currentPlayer.toLowerCase());
                    $(this).attr("data-hover", currentPlayer);
                }
            },
            function() {
                $(this).removeClass("hover-" + currentPlayer.toLowerCase());
                $(this).removeAttr("data-hover");
            }
        );
    }

    // Make a move
    function makeMove(row, col) {
        $.ajax({
            url: `/api/game/${gameId}/move`,
            method: "POST",
            data: {
                row: row,
                col: col
            },
            success: function(response) {
                updateBoard(response);
            },
            error: function(xhr) {
                const response = xhr.responseJSON;
                showMessage(response && response.error ? response.error : "Error making move", "danger");
            }
        });
    }

    // Update the board based on game state
    function updateBoard(game) {
        const cells = game.board.cells;
        for (let i = 0; i < cells.length; i++) {
            for (let j = 0; j < cells[i].length; j++) {
                const cellValue = cells[i][j].value;
                const cellElement = $(`.cell[data-row="${i}"][data-col="${j}"]`);

                if (cellValue) {
                    cellElement.text(cellValue);
                    cellElement.addClass(cellValue === "X" ? "x-symbol" : "o-symbol");
                } else {
                    cellElement.text("");
                    cellElement.removeClass("x-symbol o-symbol");
                }
            }
        }

        gameStatus = game.status;
        currentPlayer = game.currentPlayer ? game.currentPlayer.symbol : "";

        if (gameStatus === "IN_PROGRESS") {
            $("#currentPlayer").text(currentPlayer);
            $(".board-table").removeClass("game-over");
        } else {
            $(".board-table").addClass("game-over");

            if (gameStatus === "WINNER") {
                showMessage(`Player ${game.winner.symbol} won the game!`, "success");
                highlightWinningCells(game);
            } else if (gameStatus === "DRAW") {
                showMessage("Game ended in a draw!", "warning");
            }
        }
    }

    function highlightWinningCells(game) {
        const winnerSymbol = game.winner.symbol;
        $(`.cell:contains('${winnerSymbol}')`).addClass("winning-cell");
    }

    // Reset the game
    $("#resetButton").on("click", function(e) {
        e.preventDefault();

        $.ajax({
            url: `/api/game/${gameId}/reset`,
            method: "POST",
            success: function(response) {
                updateBoard(response);
                showMessage("Game has been reset!", "info");
                $(".cell").removeClass("winning-cell");
            },
            error: function(xhr) {
                const response = xhr.responseJSON;
                showMessage(response && response.error ? response.error : "Error resetting game", "danger");
            }
        });
    });

    // Show message
    function showMessage(message, type) {
        $(".alert").remove();

        const alert = $(`<div class="alert alert-${type} alert-dismissible fade show" role="alert">
                            ${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>`);

        $("#gameBoard").prepend(alert);

        setTimeout(function() {
            alert.alert('close');
        }, 5000);
    }

    if (board.length) {
        initializeBoard();
    }

    $("#boardSize").on("change", function() {
        const newSize = $(this).val();
    });
});