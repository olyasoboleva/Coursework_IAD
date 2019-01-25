package model;

import lombok.Data;

@Data
public class Battle {
    private String attacking;
    private String defending;
    private int gameId;
}
