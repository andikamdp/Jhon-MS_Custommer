package com.ecommerce.customer.repo.changePassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String transactionId;
    private Long userId;
    private Date date;
    private String status;

    public ChangePassword(Long userId, Date date, String status) {
        this.userId = userId;
        this.date = date;
        this.status = status;
    }
}
