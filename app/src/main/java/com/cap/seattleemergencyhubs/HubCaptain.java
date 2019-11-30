package com.cap.seattleemergencyhubs;

class HubCaptain {
    private String name;
    private String contactEmail;
    private String contactPhone;

    public HubCaptain( String name, String contactEmail, String contactPhone){
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public HubCaptain(String name){
        this(name, "", "");
    }

    public HubCaptain(String name, String contactPhone){
        this(name, "", contactPhone);
    }


    public String getName(){
        return this.name;
    }

    public String getContactEmail(){
        return this.contactEmail;
    }

    public String getContactPhone(){
        return this.contactPhone;
    }
}
