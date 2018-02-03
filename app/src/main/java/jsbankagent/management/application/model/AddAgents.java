package jsbankagent.management.application.model;

/**
 * Created by Administrator on 1/6/2018.
 */

public class AddAgents {

    private int autoagentId;
    private String agentId;
    private String agentName;
    private String agentAddress;
    private int cityId;
    private int provinceId;
    private String agentComments;
    private String imagePath;
    private Boolean formStatus;
    private String latitude;
    private String longitude;


    public AddAgents(){

    }
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getAgentComments() {
        return agentComments;
    }

    public void setAgentComments(String agentComments) {
        this.agentComments = agentComments;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getAutoagentId() {
        return autoagentId;
    }

    public void setAutoagentId(int autoagentId) {
        this.autoagentId = autoagentId;
    }

    public Boolean getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Boolean formStatus) {
        this.formStatus = formStatus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
