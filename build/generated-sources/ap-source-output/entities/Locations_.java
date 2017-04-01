package entities;

import entities.Countries;
import entities.Departments;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-30T01:15:57")
@StaticMetamodel(Locations.class)
public class Locations_ { 

    public static volatile SingularAttribute<Locations, String> streetAddress;
    public static volatile SingularAttribute<Locations, String> city;
    public static volatile SingularAttribute<Locations, Short> locationId;
    public static volatile ListAttribute<Locations, Departments> departmentsList;
    public static volatile SingularAttribute<Locations, String> postalCode;
    public static volatile SingularAttribute<Locations, String> stateProvince;
    public static volatile SingularAttribute<Locations, Countries> countryId;

}