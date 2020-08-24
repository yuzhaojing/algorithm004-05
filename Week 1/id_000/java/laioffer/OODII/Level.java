package laioffer.OODII;

import java.util.ArrayList;
import java.util.List;

class Level {
    private List<ParkingSpot> spots;

    public Level(int capacity) {
        spots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity / 2; i++) {
            spots.add(new ParkingSpot(VehicleSize.Compact));
        }

        for (int i = 0; i < capacity - capacity / 2; i++) {
            spots.add(new ParkingSpot(VehicleSize.Large));
        }
    }

    public boolean park(Vehicle vehicle) {
        for (ParkingSpot parkingSpot : spots) {
            if (!parkingSpot.isPark() && parkingSpot.isFit(vehicle)) {
                parkingSpot.park(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle vehicle) {
        for (ParkingSpot parkingSpot : spots) {
            if (parkingSpot.isPark() && parkingSpot.getVehicle() == vehicle) {
                parkingSpot.leave();
                return true;
            }
        }
        return false;
    }

    public boolean hasSpot(Vehicle vehicle) {
        for (ParkingSpot parkingSpot : spots) {
            if (!parkingSpot.isPark() && parkingSpot.isFit(vehicle)) {
                return true;
            }
        }
        return false;
    }
}
