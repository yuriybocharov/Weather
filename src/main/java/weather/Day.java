package weather;

public class Day {
    public String getStartDay() {
        return startDay;
    }

    public Double getTempDayMax() {
        return tempDayMax;
    }

    public Double getTempNightMin() {
        return tempNightMin;
    }

    public Double getTempDayMin() {return tempDayMin;}

    public Double getTempNightMax() {return tempNightMax;}

    public String startDay;
    public Double tempDayMax;
    public Double tempDayMin;
    public Double tempNightMax;



    public Double tempNightMin;

    public Day(String startDay,Double tempDayMin, Double tempDayMax,Double tempNightMin, Double tempNightMax) {
        this.startDay = startDay;
        this.tempDayMax = tempDayMax;
        this.tempDayMin = tempDayMin;
        this.tempNightMax = tempNightMax;
        this.tempNightMin = tempNightMin;
    }


    @Override
    public String toString() {
        String tempDayst = "Day: ";
        String tempNightst = "Night: ";
        String rezult = "";


        rezult = startDay + "\n" + tempDayst + " " + tempDayMin + "\u00b0" + "C" + " - "
                + tempDayMax + "\u00b0" + "C" + "\n" + tempNightst + " "
                + tempNightMin + "\u00b0" + "C" + " - " + tempNightMax + "\u00b0" + "C";

        return rezult;
    }
}
