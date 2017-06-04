package com.lemon.vo.invoice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lemon.vo.base.Company;

/**
 * Created by plin on 2017/2/12. 发票信息
 */
public class InvoiceInfo implements Serializable {

    private static final Long serialVersionUID = 1l;

    /** 唯一ID */
    private int id;

    /** 开票日期 */
    private Date invoiceDate;

    /** 行业分类 */
    private String industryClass;

    /** 发票代码 */
    private String invoiceNo;

    /** 发票号码 */
    private String invoiceCode;

    /** 购货单位 */
    private Company company;

    /** 所购商品信息 */
    private List<BuyGoods> bugGoodsList = new ArrayList<BuyGoods>();

    /** 金额小写合计 */
    private BigDecimal total;

    /** 金额大写合计 */
    private String amountInWords;

    /** 销货单位 */
    private Company saleCompany;

    /** 备注 */
    private String comments;

    /** 收款人 */
    private String payee;

    /** 开票人 */
    private String drawer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<BuyGoods> getBugGoodsList() {
        return bugGoodsList;
    }

    public void setBugGoodsList(List<BuyGoods> bugGoodsList) {
        this.bugGoodsList = bugGoodsList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public Company getSaleCompany() {
        return saleCompany;
    }

    public void setSaleCompany(Company saleCompany) {
        this.saleCompany = saleCompany;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getIndustryClass() {
        return industryClass;
    }

    public void setIndustryClass(String industryClass) {
        this.industryClass = industryClass;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        InvoiceInfo that = (InvoiceInfo) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
