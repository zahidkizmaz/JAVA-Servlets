
package pl.polsl.report2;

import pl.polsl.report2.Airplane;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This is the Entity Class for the Customer
 * @author Kizmaz Zahid - Hernandez Raul
 * @version 1.0
 */

@Entity
@Table(name = "Customer")
public class Customer implements Serializable{
    /**
     * This is going to be the primary key for the Customer table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * This column is for the name of the Customer
     */ 
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * This column is for the lastname of the Customer
     */
    @Column(name = "lastname",nullable = false)
    private String lastname;
    
    /**
     * Here we define the relationship between the Airplane and Customer tables
     */
    @ManyToOne(optional = true)
    
    /**
     * Here is the column that will be in both Customer and Airplane tables
     */
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
