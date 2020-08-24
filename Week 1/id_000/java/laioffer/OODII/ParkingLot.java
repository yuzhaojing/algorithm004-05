package laioffer.OODII;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设计一个停车场
 * 1、首先确定需要设计的产品需要有什么功能和用途
 * 设计一个停车场，最主要的功能就是泊车以及取车
 * 所以需要两个核心API：parking and leave
 * 然后确定这些API的input/output
 * parking(vehicle) boolean
 * leave(vehicle) boolean
 *
 * 2、确定主要的class和作用
 * ParkingLot   停车场主类，用于对外提供API
 * ParkingSpot  停车场停泊位，用于存放停的车辆
 * Level        停车场分层，每层的全部停车位
 * Vihicle      车辆
 *
 * 3、确定每个class的数据结构
 * ParkingLot
 *  List<Level> levels
 *  Map<Vehicle, ParkingSpot>
 *
 * Level
 *  List<ParkingSpot>
 *
 * ParkingSpot
 *  size
 *  isParking
 *
 * Vehicle
 *  size
 *
 */

public class ParkingLot {
    private List<Level> levels;
    private Map<Vehicle, Level> map;

    public ParkingLot(int numLevels, int numSpotsOfLevel) {
        levels = new ArrayList<>();
        for (int i = 0; i < numLevels; i++) {
            levels.add(new Level(numSpotsOfLevel));
        }
    }

    public boolean park(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.park(vehicle)) {
                map.put(vehicle, level);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle vehicle) {
        Level level = map.get(vehicle);
        if (level != null) {
            return level.leave(vehicle);
        }
        return false;
    }

    public boolean hasSpot(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.hasSpot(vehicle)) {
                return true;
            }
        }

        return false;
    }
}
