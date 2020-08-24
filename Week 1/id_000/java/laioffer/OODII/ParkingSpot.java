package laioffer.OODII;

class ParkingSpot {
    private VehicleSize vehicleSize;
    private Vehicle vehicle;

    public ParkingSpot(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public boolean isPark() {
        return vehicle == null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isFit(Vehicle vehicle) {
        return vehicle.getVehicleSize().getSize() <= vehicleSize.getSize();
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void leave() {
        this.vehicle = null;
    }
}
