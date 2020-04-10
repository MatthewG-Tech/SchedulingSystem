/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author matthewguerra
 */
public class AppointmentReport {
    private String title;
    private String type;
    private String detail;
    private int length;
    
    public AppointmentReport(String title, String type, String detail){
        this.title = title;
        this.type = type;
        this.detail = detail;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public String getTitle(){
        return title;
    }
    public String getType(){
        return type;
    }
    public String getDetail(){
        return detail;
    }
}
