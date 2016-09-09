package aoptest;

/**
 * Created by zhangmeng on 16-7-13.
 */
public class Account {
    public String seqNo;
    public String accountName;
    public Account(String seqNo, String accountName) {
        this.seqNo = seqNo ;
        this.accountName = accountName;
    }
    public String getSeqNo(){
        return this.seqNo;
    }
    public String getAccountName(){
        return accountName;
    }
}
