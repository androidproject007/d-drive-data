package com.schoolmanagement.Student.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Self_Study_Gks_Object {

    String id, qns,image, op1, op2, op3, op4, op5, ringtans, selectans;
    boolean isSelect;
    public static final int NONE = 1000;     // no answer selected
    public static final int ANSWER_ONE_SELECTED = 0;   //first answer selected
    public static final int ANSWER_TWO_SELECTED = 1;   //second answer selected
    public static final int ANSWER_THREE_SELECTED = 2; //third answer selected
    public static final int ANSWER_FOUR_SELECTED = 3;
    public static final int ANSWER_FIFTH_SELECTED = 4;  // FOUR ANSWER SELECTED
    public int current = NONE;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQns() {
        return qns;
    }
    public void setQns(String qns) {
        this.qns = qns;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getSelectans() {
        return selectans;
    }

    public void setSelectans(String selectans) {
        this.selectans = selectans;
    }

    public String getOp5() {
        return op5;
    }

    public void setOp5(String op5) {
        this.op5 = op5;
    }

    public String getRingtans() {
        return ringtans;
    }

    public void setRingtans(String ringtans) {
        this.ringtans = ringtans;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public Self_Study_Gks_Object(String id, String qns, String image, String op1, String op2, String op3, String op4, String op5, String ringtans, String selectans, boolean isSelect) {
        this.id = id;
        this.qns = qns;
        this.image = image;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.op5 = op5;
        this.ringtans = ringtans;
        this.selectans = selectans;
        this.isSelect = isSelect;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("q_id", id);
            obj.put("que", qns);
            obj.put("img",image);
            obj.put("o1", op1);
            obj.put("o2", op2);
            obj.put("o3", op1);
            obj.put("o4", op2);
            obj.put("o5", op5);
            obj.put("ans", ringtans);
            obj.put("radio", selectans);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
