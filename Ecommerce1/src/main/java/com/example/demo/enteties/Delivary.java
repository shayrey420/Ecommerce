package com.example.demo.enteties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Delivary {
    @Id
    private int delivar_id;
    @NotNull
    private Date delivary_time;

   /* @ManyToOne
    private Employee employee;*/

    @OneToOne(cascade=CascadeType.ALL)
    private Payment payment;

    public int getDelivar_id() {
        return delivar_id;
    }

    public void setDelivar_id(int delivar_id) {
        this.delivar_id = delivar_id;
    }

    public Date getDelivary_time() {
        return delivary_time;
    }

    public void setDelivary_time(Date delivary_time) {
        this.delivary_time = delivary_time;
    }
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /*public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/
}
