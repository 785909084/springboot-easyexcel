package com.al.day01;

public class OrgInfo {
    private String org;
    private String orgName;

    public OrgInfo(String org, String orgName) {
        this.org = org;
        this.orgName = orgName;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
