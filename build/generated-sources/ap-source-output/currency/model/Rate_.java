package currency.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-03T23:39:07")
@StaticMetamodel(Rate.class)
public class Rate_ { 

    public static volatile SingularAttribute<Rate, String> toCur;
    public static volatile SingularAttribute<Rate, String> fromCur;
    public static volatile SingularAttribute<Rate, Double> rate;
    public static volatile SingularAttribute<Rate, Integer> conversionId;
    public static volatile SingularAttribute<Rate, Double> value;

}