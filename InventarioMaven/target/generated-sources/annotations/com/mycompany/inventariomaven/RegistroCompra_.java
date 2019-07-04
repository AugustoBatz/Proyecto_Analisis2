package com.mycompany.inventariomaven;

import com.mycompany.inventariomaven.FacturaCompra;
import com.mycompany.inventariomaven.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-03T20:27:11", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(RegistroCompra.class)
public class RegistroCompra_ { 

    public static volatile SingularAttribute<RegistroCompra, Float> precioUnitario;
    public static volatile SingularAttribute<RegistroCompra, Producto> productoid;
    public static volatile SingularAttribute<RegistroCompra, Float> costoTotal;
    public static volatile SingularAttribute<RegistroCompra, Integer> id;
    public static volatile SingularAttribute<RegistroCompra, Integer> cantidad;
    public static volatile SingularAttribute<RegistroCompra, FacturaCompra> facturaCompraid;
    public static volatile SingularAttribute<RegistroCompra, Float> precioTotal;
    public static volatile SingularAttribute<RegistroCompra, Float> costoUnitario;
    public static volatile SingularAttribute<RegistroCompra, Float> ganancia;

}