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
public class City {
    
    private int cityId;
    private String cityName;
    private Country country;
    
    public City(int cityId, String cityName, Country country){
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }
    
    public int getCityId(){
        return cityId;
    }
    
    public String getCityName(){
        return cityName;
    }
    
    public Country getCountry(){
        return country;
    }
    
    public void setCityId(int cityId){
        this.cityId = cityId;
    }
    
    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    
    public void setCountry(Country country){
        this.country = country;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof City){
            City comparedCity = (City)o;
            if(this.cityId == comparedCity.getCityId()){
                if(this.cityName.equals(comparedCity.getCityName())){
                    if(this.country.equals(comparedCity.getCountry())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "City ID: " + this.cityId + " City Name: " + this.cityName + " Country: " + this.country;
    }
}
