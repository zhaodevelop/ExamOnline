package com.ruanyuan.pojo;
/**
 * 题库试卷中间关系表实体类
 * @author 
 *
 */
public class ItemPaper {
	//关系id
	private int id;
	//题库
	private ItemBank itemBank;
	//试卷
	private TestPaper testPaper;
	/**
	 * 属性的geeter/setter方法
	 * @return
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ItemBank getItemBank() {
		return itemBank;
	}
	public void setItemBank(ItemBank itemBank) {
		this.itemBank = itemBank;
	}
	public TestPaper getTestPaper() {
		return testPaper;
	}
	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}
	@Override
	public String toString() {
		return "ItemPaper [id=" + id + ", itemBank=" + itemBank + ", testPaper=" + testPaper + "]";
	}
	
}
