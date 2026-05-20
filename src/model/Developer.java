package model;
import java.util.Objects;


public class Developer {

    private String developerName;

    public Developer(String developerName){
        this.developerName = developerName;
    }

    public String getDeveloperName(){
        return developerName;
    }
    public void setDeveloperName(String developerName){
        this.developerName = developerName;
    }

    @Override
    public String toString(){
        return developerName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null|| getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(developerName, developer.developerName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(developerName);
    }
}
