/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author matthewguerra
 */
public class Country {
    
    private int countryId;
    private String countryName;
    
    public Country(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }
    
    public int getCountryId(){
        return countryId;
    }
    
    public String getCountryName(){
        return countryName;
    }
    
    public void setcountryId(int countryId){
        this.countryId = countryId;
    }
    
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Country){
            Country comparedCountry = (Country)o;
            if(this.countryId == comparedCountry.getCountryId()){
                if(this.countryName.equals(comparedCountry.getCountryName())){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Country ID: " + this.countryId + " Country Name: " + this.countryName;
    }
}
