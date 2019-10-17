package com.cap.seattleemergencyhubs;

public class Hub {
    private String name;
    private String approxAdress;
    //private String fullAdress; // tentative
    private HubCaptain captain;
    private boolean hasHandRadioOperator;
    private boolean hasABox;
    private double Xcoord;
    private double Ycoord;

    // full constructor
    public Hub(String name, String approxAdress, HubCaptain captain,
               boolean hasHandRadioOperator, boolean hasABox, double Xcoord, double Ycoord){
        this.name = name;
        this.approxAdress = approxAdress;
        //private String fullAdress; // tentative
        this.captain = captain;
        this.hasHandRadioOperator = hasHandRadioOperator;
        this.hasABox = hasABox;
        this.Xcoord = Xcoord;
        this.Ycoord = Ycoord;
    }

    public Hub(String name, String approxAdress, double Xcoord, double Ycoord){
        this(name, approxAdress, null, false, false, Xcoord, Ycoord);

    }

    public String getHubName(){
        return this.name;
    }

    public String getApproxAdress(){
        return this.approxAdress;
    }

    public HubCaptain getCaptain(){
        return this.captain;
    }

    public boolean isHasHandRadioOperator(){
        return this.hasHandRadioOperator;
    }

    public boolean isHasABox(){
        return this.hasABox;
    }

    public double getXcoord(){
        return this.Xcoord;
    }

    public double getYcoord(){
        return this.Ycoord;
    }
}
