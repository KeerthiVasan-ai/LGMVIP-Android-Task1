package com.keerthi77459.covid19tracker;

public class caseName {
    String activeCase,confirmedCase,decreasedCase,recoveredCase;

    public caseName(String activeCase, String confirmedCase, String decreasedCase, String recoveredCase) {
        this.activeCase = activeCase;
        this.confirmedCase = confirmedCase;
        this.decreasedCase = decreasedCase;
        this.recoveredCase = recoveredCase;
    }

    public String getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(String activeCase) {
        this.activeCase = activeCase;
    }

    public String getConfirmedCase() {
        return confirmedCase;
    }

    public void setConfirmedCase(String confirmedCase) {
        this.confirmedCase = confirmedCase;
    }

    public String getDecreasedCase() {
        return decreasedCase;
    }

    public void setDecreasedCase(String decreasedCase) {
        this.decreasedCase = decreasedCase;
    }

    public String getRecoveredCase() {
        return recoveredCase;
    }

    public void setRecoveredCase(String recoveredCase) {
        this.recoveredCase = recoveredCase;
    }
}
