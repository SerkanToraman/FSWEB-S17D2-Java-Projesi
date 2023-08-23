package com.workintechS21G2.project.tax;


import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{

    @Override
    public double getSimpleTaxRate() {
        return 0.1;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.15;
    }

    @Override
    public double getUpperTaxRate() {
        return 0.2;
    }
}
