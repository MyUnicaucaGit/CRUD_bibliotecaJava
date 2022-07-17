package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Authors;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-07-17T15:27:57")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile SingularAttribute<Books, Integer> idB;
    public static volatile ListAttribute<Books, Userbooks> userbooksList;
    public static volatile SingularAttribute<Books, String> title;
    public static volatile ListAttribute<Books, Authors> authorsList;

}