/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "userbooks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userbooks.findAll", query = "SELECT u FROM Userbooks u"),
    @NamedQuery(name = "Userbooks.findByUserId", query = "SELECT u FROM Userbooks u WHERE u.userbooksPK.userId = :userId"),
    @NamedQuery(name = "Userbooks.findByBookId", query = "SELECT u FROM Userbooks u WHERE u.userbooksPK.bookId = :bookId"),
    @NamedQuery(name = "Userbooks.findByLoanDate", query = "SELECT u FROM Userbooks u WHERE u.userbooksPK.loanDate = :loanDate"),
    @NamedQuery(name = "Userbooks.findByExDate", query = "SELECT u FROM Userbooks u WHERE u.exDate = :exDate")})
public class Userbooks implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserbooksPK userbooksPK;
    @Column(name = "ExDate")
    @Temporal(TemporalType.DATE)
    private Date exDate;
    @JoinColumn(name = "UserId", referencedColumnName = "Id_U", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "BookId", referencedColumnName = "Id_B", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Books books;

    public Userbooks() {
    }

    public Userbooks(UserbooksPK userbooksPK) {
        this.userbooksPK = userbooksPK;
    }

    public Userbooks(int userId, int bookId, Date loanDate) {
        this.userbooksPK = new UserbooksPK(userId, bookId, loanDate);
    }

    public UserbooksPK getUserbooksPK() {
        return userbooksPK;
    }

    public void setUserbooksPK(UserbooksPK userbooksPK) {
        this.userbooksPK = userbooksPK;
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userbooksPK != null ? userbooksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userbooks)) {
            return false;
        }
        Userbooks other = (Userbooks) object;
        if ((this.userbooksPK == null && other.userbooksPK != null) || (this.userbooksPK != null && !this.userbooksPK.equals(other.userbooksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks[ userbooksPK=" + userbooksPK + " ]";
    }
    
}
