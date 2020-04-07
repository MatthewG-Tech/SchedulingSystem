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
public class Address {
    
    private int addressId;
    private String address;
    private String address2;
    private City city;
    private String postalCode;
    private String phone;
    
    public Address(int addressId, String address, String address2, City city, String postalCode, String phone){
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    public int getAddressId(){
        return addressId;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getAddress2(){
        return address2;
    }
    
    public City getCity(){
        return city;
    }
    
    public String getPostalCode(){
        return postalCode;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setAddressId(int addressId){
        this.addressId = addressId;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setAddress2(String address2){
        this.address2 = address2;
    }
    
    public void setCity(City city){
        this.city = city;
    }
    
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Address){
            Address comparedAddress = (Address)o;
            if(this.addressId == comparedAddress.getAddressId()){
                if(this.address.equals(comparedAddress.getAddress())){
                    if(this.address2.equals(comparedAddress.getAddress2())){
                        if(this.city.equals(comparedAddress.getCity())){
                            if(this.postalCode.equals(comparedAddress.getPostalCode())){
                                if(this.phone.equals(comparedAddress.getPhone())){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Address ID: " + this.addressId + " Address: " + this.address + " Address 2: " + this.address2 + " City: " + this.city + " Postal Code: " + this.postalCode + " Phone: " + this.phone;
    }
}
