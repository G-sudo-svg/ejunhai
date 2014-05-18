package com.ejunhai.junhaimall.system.model;

/**
 * @author liudi
 * @date 2011-9-6 上午09:20:19
 *  手机短信
 */
public class Note implements java.io.Serializable{
 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3292188357189710420L;

	/**
	 * 手机短信帐户
	 */
	private String sname;
	
	/**
	 * 手机短信帐户密码
	 */
	private String spwd;
	
	/**
	 * 企业代码
	 */
	private String scorpid;
	
	/**
	 * 产品编号
	 */
	private String sprdid;
	
    /**
     * 手机号码
     */
	private String sdst[];
	
	/**
	 * 手机短信内容
	 */
	private String smsg;
	
	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the spwd
	 */
	public String getSpwd() {
		return spwd;
	}

	/**
	 * @param spwd the spwd to set
	 */
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	/**
	 * @return the scorpid
	 */
	public String getScorpid() {
		return scorpid;
	}

	/**
	 * @param scorpid the scorpid to set
	 */
	public void setScorpid(String scorpid) {
		this.scorpid = scorpid;
	}

	/**
	 * @return the sprdid
	 */
	public String getSprdid() {
		return sprdid;
	}

	/**
	 * @param sprdid the sprdid to set
	 */
	public void setSprdid(String sprdid) {
		this.sprdid = sprdid;
	}

	/**
	 * @return the sdst
	 */
	public String[] getSdst() {
		return sdst;
	}

	/**
	 * @param sdst the sdst to set
	 */
	public void setSdst(String[] sdst) {
		this.sdst = sdst;
	}

	/**
	 * @return the smsg
	 */
	public String getSmsg() {
		return smsg;
	}

	/**
	 * @param smsg the smsg to set
	 */
	public void setSmsg(String smsg) {
		this.smsg = smsg;
	}
	
	
}
