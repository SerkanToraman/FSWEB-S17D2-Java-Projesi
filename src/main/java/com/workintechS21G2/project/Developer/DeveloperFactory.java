package com.workintechS21G2.project.Developer;

import com.workintechS21G2.project.tax.Taxable;

public class DeveloperFactory {
    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer createdDeveloper;
        String result = developer.getExperience().name().toLowerCase();
        switch (result) {
            case "junior":
                createdDeveloper = new JuniorDeveloper(developer.getId(),
                        developer.getName(), developer.getSalary() * (1 - taxable.getSimpleTaxRate()), developer.getExperience());
                break;
            case "mid":
                createdDeveloper = new MidDeveloper(developer.getId(),
                        developer.getName(), developer.getSalary() * (1 - taxable.getMiddleTaxRate()), developer.getExperience());
                break;
            case "senior":
                createdDeveloper = new SeniorDeveloper(developer.getId(),
                        developer.getName(), developer.getSalary() * (1 - taxable.getUpperTaxRate()), developer.getExperience());
            default:
                createdDeveloper=null;
                break;
        }
        return createdDeveloper;
    }
}
