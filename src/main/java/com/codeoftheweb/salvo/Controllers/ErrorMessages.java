package com.codeoftheweb.salvo.Controllers;

public enum ErrorMessages {
    MAILEMPTY("Write your mail!"),PASSWORDEMPTY("Write your password"),MAILINUSE("Mail is already in use"),
    NOTLOGGED("You are not logged in"),PLAYERNULL("There is no current player"),
    GAMENULL("There is no game") ,GAMEFULL("The game is full"),SHIPSPLACED("Ships already placed"),
    CHEAT("The current user is not the game player the ID references"),GAMEPLAYERNULL("There is no game player"),
    NOTSALVOES("You haven't got salvoes"),OPPONENTNULL("There is no opponent");

    private String message;

    private ErrorMessages(String mensaje) {
        this.message = mensaje;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
