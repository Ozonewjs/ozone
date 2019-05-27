package com.ozone.mfls.beans;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import java.time.*;
/**
 * @ClassName User
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 21:41
 * @Version 1.0
 **/
@Entity
public class User {
    @Id
    @GenericGenerator(name="generator",strategy = "native")
    @GeneratedValue(generator = "generator")

    private Integer userId;
    @Column(nullable = false, unique = true)
    /** 登录用户名 **/
    private String userName;
    @Column(nullable = false)
    /** 名称（昵称或者真实姓名，根据实际情况定义） **/
    private String name;
    @Column(nullable = false)
    private String password;
    /** 加密密码的盐 **/
    private String salt;
    /** 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定. **/
    private byte state;
    /** 立即从数据库中进行加载数据; **/
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    /**  一个用户具有多个角色; **/
    private List<SysRole> roleList;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    /** 创建时间 **/
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    /** 过期日期 **/
    private LocalDate expiredDate;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     * @return
     */
    public String getCredentialsSalt(){
        return this.userName+this.salt;
    }
}
