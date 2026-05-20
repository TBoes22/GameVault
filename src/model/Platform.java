package model;
import java.util.Objects;


public class Platform {

    private String platformName;

    public Platform(String platformName){
        this.platformName = platformName;
    }

    public String getPlatformName(){
        return platformName;
    }
    public void setPlatform(String platformName){
        this.platformName = platformName;
    }
    @Override
    public String toString() {
        return platformName;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return Objects.equals(platformName, platform.platformName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(platformName);
    }


}
