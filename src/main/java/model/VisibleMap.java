package model;

import entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VisibleMap {
    List<Map> area;
    List<Location> location;
    List<Coordinates> tributes;
    List<Coordinates> weapons;
}
