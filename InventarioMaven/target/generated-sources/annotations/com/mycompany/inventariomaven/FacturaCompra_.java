package com.mycompany.inventariomaven;

import com.mycompany.inventariomaven.Proveedor;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-03T17:02:56", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(FacturaCompra.class)
public class FacturaCompra_ { 

    public static volatile SingularAttribute<FacturaCompra, Date> fecha;
    public static volatile SingularAttribute<FacturaCompra, Float> total;
    public static volatile SingularAttribute<FacturaCompra, Integer> numero;
    public static volatile SingularAttribute<FacturaCompra, Float> totalFactura;
    public static volatile SingularAttribute<FacturaCompra, Proveedor> proveedorid;
    public static volatile SingularAttribute<FacturaCompra, String> serie;
    public static volatile SingularAttribute<FacturaCompra, Integer> cantidadProd;
    public static volatile SingularAttribute<FacturaCompra, Integer> id;
    public static volatile SingularAttribute<FacturaCompra, Boolean> anulado;

}