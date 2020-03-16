package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="speclevels")
public class SpecLevelModel {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lvlid")
	private Integer speclvlid;
	
	@ManyToOne
	@JoinColumn(name="specid")
	private SpecialSkillModel specid;
	
	@Column(name="lvlmark")
	private Integer levelmark;
	
	@Column(name="lvlcost")
	private Integer levelcost;

	public SpecLevelModel() {	}
	
	
	
	public SpecLevelModel(SpecialSkillModel specid, Integer levelmark, Integer levelcost) {
		super();
		this.specid = specid;
		this.levelmark = levelmark;
		this.levelcost = levelcost;
	}



	public Integer getSpeclvlid() {
		return speclvlid;
	}

	public void setSpeclvlid(Integer speclvlid) {
		this.speclvlid = speclvlid;
	}

	public SpecialSkillModel getSpecid() {
		return specid;
	}

	public void setSpecid(SpecialSkillModel specid) {
		this.specid = specid;
	}

	public Integer getLevelmark() {
		return levelmark;
	}

	public void setLevelmark(Integer levelmark) {
		this.levelmark = levelmark;
	}

	public Integer getLevelcost() {
		return levelcost;
	}

	public void setLevelcost(Integer levelcost) {
		this.levelcost = levelcost;
	}

	@Override
	public String toString() {
		return "SpecLevelModel [speclvlid=" + speclvlid + ", levelmark=" + levelmark
				+ ", levelcost=" + levelcost + "]";
	}
	
	
	
}
