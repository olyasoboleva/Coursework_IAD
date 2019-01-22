package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String content;
    private String nick;
    private Type type;

    public Message(String content, String nick, Type type) {
        this.content = content;
        this.nick = nick;
        this.type = type;
    }

    public enum Type{
        SELECTION, GAMESTART, HOOK, GAMEOVER, PRESENT, DEADTRIBUTE, ATTACK
    }
}
