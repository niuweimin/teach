package com.tt.teach.pojo;
/**
 * @作者：niuweimin
 * @时间：2018/12/19 14:32
 * @描述：实体类Grade——》对应数据库年级表
 */
public class Grade {
    private Integer gradeID;
     private String gradeName;

    public Grade() {
    }

    public Grade(Integer gradeID, String gradeName) {

        this.gradeID = gradeID;
        this.gradeName = gradeName;
    }

    public Integer getGradeID() {
        return gradeID;
    }

    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
