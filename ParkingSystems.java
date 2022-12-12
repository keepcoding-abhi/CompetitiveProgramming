public class ParkingSystems {
    class ParkingSystem {

        int bigCarsParked, bigCarsCap, medCarsParked, medCarsCap, smallCarsParked, smallCarsCap;

        public ParkingSystem(int big, int medium, int small) {
            bigCarsParked = medCarsParked = smallCarsParked = 0;
            bigCarsCap = big;
            medCarsCap = medium;
            smallCarsCap = small;
        }

        public boolean addCar(int carType) {

            boolean parkingStatus = false;

            if(carType == 1) {
                if(bigCarsParked < bigCarsCap) {
                    bigCarsParked++;
                    parkingStatus = true;
                }
            }
            else if(carType == 2) {
                if(medCarsParked < medCarsCap) {
                    medCarsParked++;
                    parkingStatus = true;
                }
            }
            else if(carType == 3) {
                if(smallCarsParked < smallCarsCap) {
                    smallCarsParked++;
                    parkingStatus = true;
                }
            }

            return parkingStatus;
        }
    }

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
}
