package org.store.manager.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by hasan on 13/01/2018.
 */

@Getter
@Setter
public class Employee {

    private long cod_employee;
    private String name;
    private String surname;
    private String mobileNumber;
    private String phoneNumber;
    private String email;
    private String employeeType;
    private String password;
}
