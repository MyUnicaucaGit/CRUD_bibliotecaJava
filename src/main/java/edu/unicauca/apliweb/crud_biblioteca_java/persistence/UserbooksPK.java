/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class UserbooksPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BookId")
    private int bookId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LoanDate")
    @Temporal(TemporalType.DATE)
    private Date loanDate;

    public UserbooksPK() {
    }

    public UserbooksPK(int userId, int bookId, Date loanDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) bookId;
        hash += (loanDate != null ? loanDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserbooksPK)) {
            return false;
        }
        UserbooksPK other = (UserbooksPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        if ((this.loanDate == null && other.loanDate != null) || (this.loanDate != null && !this.loanDate.equals(other.loanDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_biblioteca_java.persistence.UserbooksPK[ userId=" + userId + ", bookId=" + bookId + ", loanDate=" + loanDate + " ]";
    }
    
}
