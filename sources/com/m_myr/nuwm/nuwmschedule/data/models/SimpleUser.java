package com.m_myr.nuwm.nuwmschedule.data.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class SimpleUser implements Serializable {

    @SerializedName("first_name")
    protected String firstName;

    @SerializedName("id")
    protected int id;

    @SerializedName("last_name")
    protected String lastName;

    @SerializedName("patronymic")
    protected String patronymic;

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public int getId() {
        return this.id;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return this.lastName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.firstName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.patronymic;
    }

    public String getSimpleName() {
        return this.lastName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.firstName;
    }

    public String getInitials() {
        if (this.firstName.isEmpty()) {
            if (this.patronymic.isEmpty()) {
                return "-";
            }
            return this.patronymic.charAt(0) + ".";
        }
        if (this.patronymic.isEmpty()) {
            return this.firstName.charAt(0) + ".";
        }
        return this.firstName.charAt(0) + "." + this.patronymic.charAt(0) + ".";
    }
}
