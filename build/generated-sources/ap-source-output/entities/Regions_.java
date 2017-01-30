package entities;

import entities.Countries;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-30T00:44:44")
@StaticMetamodel(Regions.class)
public class Regions_ { 

    public static volatile SingularAttribute<Regions, BigDecimal> regionId;
    public static volatile SingularAttribute<Regions, String> regionName;
    public static volatile ListAttribute<Regions, Countries> countriesList;

}