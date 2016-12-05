package currency.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T14:36:25")
@StaticMetamodel(Rate.class)
public class Rate_ { 

    public static volatile SingularAttribute<Rate, Double> rate;
    public static volatile SingularAttribute<Rate, String> toCurrency;
    public static volatile SingularAttribute<Rate, String> fromCurrency;
    public static volatile SingularAttribute<Rate, Integer> conversionId;

}