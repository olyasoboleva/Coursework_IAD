package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coordinates {
    private String nick;
    private int x;
    private int y;

    public Coordinates(String nick, int x, int y){
        setNick(nick);
        setX(x);
        setY(y);
    }
}