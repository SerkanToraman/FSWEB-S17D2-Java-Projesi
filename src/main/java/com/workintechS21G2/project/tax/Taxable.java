package com.workintechS21G2.project.tax;


import org.springframework.stereotype.Component;

@Component
public interface Taxable {
    double getSimpleTaxRate();
    double getMiddleTaxRate( );
    double getUpperTaxRate();
}
