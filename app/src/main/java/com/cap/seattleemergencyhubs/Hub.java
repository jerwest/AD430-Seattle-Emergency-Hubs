package com.cap.seattleemergencyhubs;

public class Hub implements  java.io.Serializable {
    //private HubCaptain captain;
    private String city;
    private String captain;
    private String description;
    private String email;
    private String hub_location;
    private String hub_type;
    private String name;
    private String neighborhood;
    private String phone;
    private String state;
    private double x_coordinate;
    private double y_coordinate;
    private long zip_code;


   public Hub(){

   }



    public String getCaptain(){
        return this.captain;
    }

    public String getName(){
       return this.name;
    }
    public String getCity(){
       return this.city;
    }
    public String getDescription(){
      return this.description;
    }

    public String getEmail(){
       return this.email;
    }
    public String getHub_type(){
       return this.hub_type;
    }

    public String getHub_location(){
       return this.hub_location;
    }

    public String getNeighborhood(){
       return this.neighborhood;
    }

    public String getPhone(){
       return this.phone;
    }
    public String getState(){
       return this.state;
    }
    public long getZip_code(){
       return this.zip_code;
    }

    public double getX_coordinate(){
       return x_coordinate;
    }

    public double getY_coordinate(){
       return y_coordinate;
    }
    /* --------------SETTERS----------------*/

    public void setCity(String city){
       this.city = city;
    }

    public void setCaptain(String captain){
       this.captain = captain;
    }

    public void setDescription(String description){
       this.description = description;
    }
    public void setEmail(String email){
       this.email = email;
    }
    public void setHub_location(String location){
       this.hub_location = location;
    }

    public void setHub_type(String type){
       this.hub_type = type;
    }


    public void setName(String name){
       this.name = name;
    }

    public void setNeighborhood(String neighborhood){
       this.neighborhood = neighborhood;
    }

    public void setPhone(String phone){
       this.phone = phone;
    }

    public void setState(String state){
       this.state = state;
    }
    public void setX_coordinate(double x_coordinate){
       this.x_coordinate = x_coordinate;
    }

    public void setY_coordinate(double yCoordinate){
       this.y_coordinate = yCoordinate;
    }

    public void setZip_code(long zip_code){
       this.zip_code = zip_code;
    }


    // full constructor
    /*
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
*/
    /*
    public Hub(String name, String approxAdress, double Xcoord, double Ycoord){
        this(name, approxAdress, null, false, false, Xcoord, Ycoord);

    }
*/


/*
    public String getApproxAdress(){
        return this.approxAdress;
    }

    public HubCaptain getCaptain(){
        return this.captain;
    }
   */

/*
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
*/

}
