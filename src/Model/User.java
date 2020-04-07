/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.*;

/**
 *
 * @author matthewguerra
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    
    public User(int userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof User){
            User comparedUser = (User)o;
            if(this.userId == comparedUser.getUserId()){
                if(this.userName.equals(comparedUser.getUserName())){
                    if(this.password.equals(comparedUser.getPassword())){
                        return true;
                    }  
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "User ID: " + this.userId + " User Name: " + this.userName + " Password: " + this.password;
    }
}
