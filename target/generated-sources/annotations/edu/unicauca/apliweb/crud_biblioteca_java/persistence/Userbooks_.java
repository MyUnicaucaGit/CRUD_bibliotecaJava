package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.UserbooksPK;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-07-18T12:19:41")
@StaticMetamodel(Userbooks.class)
public class Userbooks_ { 

    public static volatile SingularAttribute<Userbooks, Books> books;
    public static volatile SingularAttribute<Userbooks, Date> exDate;
    public static volatile SingularAttribute<Userbooks, UserbooksPK> userbooksPK;
    public static volatile SingularAttribute<Userbooks, Users> users;

}