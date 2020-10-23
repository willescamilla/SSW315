package HWs;

public class Apartment {
    //Instance variables for Apartment
    private double quality;
    private int IDNum;
    private int yearsLeft;

    //Constructor for Apartment, to be completed by you
    public Apartment (double qual, int ID, int years) {  
    	quality = qual;
    	IDNum = ID;
    	yearsLeft = years;
    }

    //Returns quality of apartment
    public double getQuality () {
        return this.quality;
    }

    //Returns ID number of apartment
    public int getIDNum () {
        return this.IDNum;
    }

    //Returns years left until apartment is vacant
    public int getYearsLeft () {
        return this.yearsLeft;
    }

    //Sets number of years until apartment is vacant
    public void setYearsLeft (int years) {
        this.yearsLeft = years;
    }
}
