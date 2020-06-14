package Models;

public class Outsourced extends Part{
    public String companyName;

    public Outsourced(int partId, String partName, double partPrice, int partStock, int partMin, int partMax, String partCompanyName) {
        super(partId, partName, partPrice, partStock, partMin, partMax);
        companyName = partCompanyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
