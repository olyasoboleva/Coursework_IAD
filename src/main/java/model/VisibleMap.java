package model;

import entity.Map;
import entity.Weapon;
import entity.WeaponsInGame;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VisibleMap {
    List<Map> area;
    List<WeaponsInGame> weapons;
}
