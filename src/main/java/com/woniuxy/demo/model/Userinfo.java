package com.woniuxy.demo.model;

import java.util.List;

public class Userinfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.uid
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    private Integer uid;
    private List roles;
    private List trees;
    public List getRoles() {
        return roles;
    }

    public List getTrees() {
        return trees;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    public void setTrees(List trees) {
        this.trees = trees;
    }


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.uname
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    private String uname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.upass
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    private String upass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.isdelete
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    private Boolean isdelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.uid
     *
     * @return the value of userinfo.uid
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.uid
     *
     * @param uid the value for userinfo.uid
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.uname
     *
     * @return the value of userinfo.uname
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public String getUname() {
        return uname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.uname
     *
     * @param uname the value for userinfo.uname
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.upass
     *
     * @return the value of userinfo.upass
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public String getUpass() {
        return upass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.upass
     *
     * @param upass the value for userinfo.upass
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public void setUpass(String upass) {
        this.upass = upass == null ? null : upass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.isdelete
     *
     * @return the value of userinfo.isdelete
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.isdelete
     *
     * @param isdelete the value for userinfo.isdelete
     *
     * @mbggenerated Tue May 26 21:56:53 CST 2020
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}