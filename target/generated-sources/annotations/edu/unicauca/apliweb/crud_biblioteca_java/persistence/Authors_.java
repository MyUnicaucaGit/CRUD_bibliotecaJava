package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-07-18T11:57:17")
@StaticMetamodel(Authors.class)
public class Authors_ { 

    public static volatile SingularAttribute<Authors, String> country;
    public static volatile SingularAttribute<Authors, Integer> idA;
    public static volatile SingularAttribute<Authors, String> name;
    public static volatile ListAttribute<Authors, Books> booksList;

}