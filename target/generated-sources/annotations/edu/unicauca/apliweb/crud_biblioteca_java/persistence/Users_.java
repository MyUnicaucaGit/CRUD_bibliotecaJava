package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-07-18T12:19:41")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Integer> idU;
    public static volatile ListAttribute<Users, Userbooks> userbooksList;
    public static volatile SingularAttribute<Users, String> email;

}