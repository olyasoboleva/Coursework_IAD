package model;

import lombok.Data;

@Data
public class Battle {
    private String attacking;
    private String defending;
    private String attWeaponName;
    private String  defWeaponName;
    private int gameId;
}
