package ra.model.entity;

import lombok.Data;

import javax.persistence.Entity;



public class Filter {
    private String rulesName;
    private String rulesValue;

    public Filter() {
    }

    public Filter(String rulesName, String rulesValue) {
        this.rulesName = rulesName;
        this.rulesValue = rulesValue;
    }

    public String getRulesName() {
        return rulesName;
    }

    public void setRulesName(String rulesName) {
        this.rulesName = rulesName;
    }

    public String getRulesValue() {
        return rulesValue;
    }

    public void setRulesValue(String rulesValue) {
        this.rulesValue = rulesValue;
    }
}
