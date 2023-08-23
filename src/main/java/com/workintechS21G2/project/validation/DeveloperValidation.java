package com.workintechS21G2.project.validation;

import com.workintechS21G2.project.Developer.Developer;

public class DeveloperValidation {

    public static boolean isIdValid(int id){
        return id > 0;
    }

    public static boolean isDeveloperValid(Developer developer){
        return isIdValid(developer.getId()) &&
                developer.getName() != null && !developer.getName().isEmpty()
                && developer.getSalary() > 25000;
    }
}
