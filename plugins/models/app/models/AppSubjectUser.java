package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zhangmeng on 16-8-31.
 */
@Entity
@Table(name = "app_subject_user")
public class AppSubjectUser extends Model {
    private Integer id ;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
