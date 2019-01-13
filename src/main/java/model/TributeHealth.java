package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TributeHealth {
    private String nick;
    private int health;
    private int hunger;
    private int thirst;

    public TributeHealth(String nick, int health, int hunger, int thirst) {
        this.nick = nick;
        this.health = health;
        this.hunger = hunger;
        this.thirst = thirst;
    }
}
