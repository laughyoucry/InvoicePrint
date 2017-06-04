package com.lemon.vo.base;

import java.io.Serializable;

/**
 * Created by plin on 2017/2/12. 公司信息
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 2910947392031918239L;

    /** 唯一id */
    private int id;

    /** (购方/销方)单位名称 */
    private String name;

    /** 纳税人识别号 */
    private String taxpayerID;

    /** 地址和电话 */
    private String addrAndPhone;

    /** 开户行和账号 */
    private String bankNameAndNo;

    public Company() {
    }

    public Company(int id) {
        super();
        this.id = id;
    }

    public Company(int id, String name, String taxpayerID, String addrAndPhone, String bankNameAndNo) {
        super();
        this.id = id;
        this.name = name;
        this.taxpayerID = taxpayerID;
        this.addrAndPhone = addrAndPhone;
        this.bankNameAndNo = bankNameAndNo;
        System.out.println("id is " + id + " and name is " + name + " and taxpayerID is " + taxpayerID + " and addrAndPhone is " + addrAndPhone
                + " and bankNameAndNo is " + bankNameAndNo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxpayerID() {
        return taxpayerID;
    }

    public void setTaxpayerID(String taxpayerID) {
        this.taxpayerID = taxpayerID;
    }

    public String getAddrAndPhone() {
        return addrAndPhone;
    }

    public void setAddrAndPhone(String addrAndPhone) {
        this.addrAndPhone = addrAndPhone;
    }

    public String getBankNameAndNo() {
        return bankNameAndNo;
    }

    public void setBankNameAndNo(String bankNameAndNo) {
        this.bankNameAndNo = bankNameAndNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Company company = (Company) o;

        return id == company.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
