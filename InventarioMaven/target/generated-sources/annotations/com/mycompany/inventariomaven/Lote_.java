package com.mycompany.inventariomaven;

import com.mycompany.inventariomaven.FacturaCompra;
import com.mycompany.inventariomaven.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-03T17:02:56", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Lote.class)
public class Lote_ { 

    public static volatile SingularAttribute<Lote, Integer> noLote;
    public static volatile SingularAttribute<Lote, Float> precioUnitario;
    public static volatile SingularAttribute<Lote, Producto> productoid;
    public static volatile SingularAttribute<Lote, Float> costoTotal;
    public static volatile SingularAttribute<Lote, Integer> id;
    public static volatile SingularAttribute<Lote, Integer> cantidad;
    public static volatile SingularAttribute<Lote, FacturaCompra> facturaCompraid;
    public static volatile SingularAttribute<Lote, Float> precioTotal;
    public static volatile SingularAttribute<Lote, Float> costoUnitario;
    public static volatile SingularAttribute<Lote, Float> ganancia;
    public static volatile SingularAttribute<Lote, Boolean> disponible;

}