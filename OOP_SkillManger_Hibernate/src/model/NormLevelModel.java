package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="normlevels")
public class NormLevelModel implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lvlid")
	private Integer skilllvlid;
	
	@ManyToOne
	@JoinColumn(name="skillid")
	private SkillBodyModel skId;

	@Column(name="lvlmark")
	private Integer levelmark;
	
	@Column(name="lvlcost")
	private Integer levelcost;
	
	@Column(name="lvlbenef")
	private Boolean levelbenef;

	public NormLevelModel() { }
	
	public NormLevelModel( SkillBodyModel skId, Integer levelmark, Integer levelcost,
			Boolean levelbenef) {
		super();
		this.skId = skId;
		this.levelmark = levelmark;
		this.levelcost = levelcost;
		this.levelbenef = levelbenef;
	}



	public SkillBodyModel getSkId() {
		return skId;
	}
	
	public void setSkId(SkillBodyModel skId) {
		this.skId = skId;
	}
	public Integer getSkilllvlid() {
		return skilllvlid;
	}

	public void setSkilllvlid(Integer skilllvlid) {
		this.skilllvlid = skilllvlid;
	}

	public SkillBodyModel getSkillid() {
		return skId;
	}

	public void setSkillid(SkillBodyModel skillid) {
		this.skId = skillid;
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

	public Boolean getLevelbenef() {
		return levelbenef;
	}

	public void setLevelbenef(Boolean levelbenef) {
		this.levelbenef = levelbenef;
	}

	@Override
	public String toString() {
		return "LevelModel [skilllvlid=" + skilllvlid + ", levelmark=" + levelmark
				+ ", levelcost=" + levelcost + ", levelbenef=" + levelbenef + "]";
	}
	
}
