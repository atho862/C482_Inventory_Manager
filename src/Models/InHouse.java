package Models;

public class InHouse extends Part{
    public int machineId;

    public InHouse(int partId, String partName, double partPrice, int partStock, int partMin, int partMax, int partMachineId) {
        super(partId, partName, partPrice, partStock, partMin, partMax);
        machineId = partMachineId;
    }

    public void setMachineId(int newMachineId){
        machineId = newMachineId;
    }

    public int getMachineId(){
        return machineId;
    }
}
