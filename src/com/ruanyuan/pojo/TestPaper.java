package com.ruanyuan.pojo;

import java.util.List;

/**
 * @Description: 试卷表实体类
 * 
 **/
public class TestPaper {
	//试卷ID
    private int tpId; 	
    //试卷名称
    private String tpName; 	
    //试卷类别 外键
    private Field field; 
    //课程id 外键
    private Course course; 	
    //知识点id 外键
    private Knowledge knowledge; 	
    //试题数量
    private Integer number; 
    //单个题的分数
    private Integer oneBranch;
    //答题开始时间
    private String startTime;
    //答题结束时间
    private String endTime; 
    //试卷生成时间
    private String tpTime; 		
    //总分
    private Integer totalScore; 
    //组卷人 外键
    private User user; 		
    //题库表 外键
    List<ItemBank> itemBankList;
    // 起始行
	private Integer start; 
	// 所取行数
	private Integer rows;           

    //getter and setter
    public int getTpId() {
        return tpId;
    }

    public void setTpId(int tpId) {
        this.tpId = tpId;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }
    public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOneBranch() {
        return oneBranch;
    }

    public void setOneBranch(Integer oneBranch) {
        this.oneBranch = oneBranch;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTpTime() {
        return tpTime;
    }

    public void setTpTime(String tpTime) {
        this.tpTime = tpTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemBank> getItemBankList() {
        return itemBankList;
    }

    public void setItemBankList(List<ItemBank> itemBankList) {
        this.itemBankList = itemBankList;
    }
    
    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}


	/**
     * 重写toString
     * @return
     */
	@Override
	public String toString() {
		return "TestPaper [tpId=" + tpId + ", tpName=" + tpName + ", field="
				+ field + ", course=" + course + ", knowledge=" + knowledge
				+ ", number=" + number + ", oneBranch=" + oneBranch
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", tpTime=" + tpTime + ", totalScore=" + totalScore
				+ ", user=" + user + ", itemBankList=" + itemBankList
				+ ", start=" + start + ", rows=" + rows + "]";
	}
	
}
