package ui_DayCare.model;

import java.io.Serializable;

public class UpcomingVaccineInfo implements Serializable {
    public String firstName;
    public String lastName;
    public Boolean mmr = false, tdap = false, polio = false, hib = false, hepatitis = false, varicella = false;

    public UpcomingVaccineInfo() {
    }

    public UpcomingVaccineInfo(String firstName, String lastName, Boolean mmr, Boolean tdap, Boolean polio, Boolean hib, Boolean hepatitis, Boolean varicella) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mmr = mmr;
        this.tdap = tdap;
        this.polio = polio;
        this.hib = hib;
        this.hepatitis = hepatitis;
        this.varicella = varicella;
    }

    public UpcomingVaccineInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getMmr() {
        return mmr;
    }

    public void setMmr(Boolean mmr) {
        this.mmr = mmr;
    }

    public Boolean getTdap() {
        return tdap;
    }

    public void setTdap(Boolean tdap) {
        this.tdap = tdap;
    }

    public Boolean getPolio() {
        return polio;
    }

    public void setPolio(Boolean polio) {
        this.polio = polio;
    }

    public Boolean getHib() {
        return hib;
    }

    public void setHib(Boolean hib) {
        this.hib = hib;
    }

    public Boolean getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(Boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public Boolean getVaricella() {
        return varicella;
    }

    public void setVaricella(Boolean varicella) {
        this.varicella = varicella;
    }

    public String[] getStringArray() {
        return new String[] {getFirstName(), getLastName(), getHib().toString(), getTdap().toString(),
                getPolio().toString(), getHepatitis().toString(), getMmr().toString(), getVaricella().toString()};
    }

    @Override
    public String toString() {
        return "DBPendingVaccineInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mmr=" + mmr +
                ", tdap=" + tdap +
                ", polio=" + polio +
                ", hib=" + hib +
                ", hepatitis=" + hepatitis +
                ", varicella=" + varicella +
                '}';
    }
}
