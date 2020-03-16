package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.engine.FetchStyle;


@Entity
@Table(name="specbody")
public class SpecialSkillModel implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="specid")
	private Integer specid;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name="skillid")
	private SkillBodyModel skId;

	@Column(name="specidentif")
	private Integer specidentif;
	
	@Column(name="specrequir")
	private Integer specrequir;
	
	@Column(name="specname")
	private String specname;
	
	@Column(name="specnote")
	private String specnote;
	
	@OneToMany(mappedBy="specid", cascade= CascadeType.ALL )
	@Fetch(value = FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SpecLevelModel> speclvl = new ArrayList<SpecLevelModel>();
	
	public SpecialSkillModel() {	}
	
	
	
	
	public SpecialSkillModel( Integer specidentif, Integer specrequir,
			String specname, String specnote) {
		super();
		this.specidentif = specidentif;
		this.specrequir = specrequir;
		this.specname = specname;
		this.specnote = specnote;
	}




	public Integer getSpecidentif() {
		return specidentif;
	}




	public void setSpecidentif(Integer specidentif) {
		this.specidentif = specidentif;
	}




	public String getSpecname() {
		return specname;
	}
	
	
	public void setSpecname(String specname) {
		this.specname = specname;
	}


	public Integer getSpecid() {
		return specid;
	}


	public void setSpecid(Integer specid) {
		this.specid = specid;
	}


	public SkillBodyModel getSkId() {
		return skId;
	}


	public void setSkId(SkillBodyModel skId) {
		this.skId = skId;
	}


	public Integer getSpecrequir() {
		return specrequir;
	}


	public void setSpecrequir(Integer specrequir) {
		this.specrequir = specrequir;
	}


	public String getSpecnote() {
		return specnote;
	}


	public void setSpecnote(String specnote) {
		this.specnote = specnote;
	}


	public List<SpecLevelModel> getSpeclvl() {
		return speclvl;
	}


	public void setSpeclvl(List<SpecLevelModel> speclvl) {
		this.speclvl = speclvl;
	}


	@Override
	public String toString() {
		return "SpecialisModel [specid=" + specid + ", specidentif="+ specidentif + 
				", specrequir=" + specrequir + ", name=" + specname 
				+", specnote=" + specnote + "]";
	}
	
	
	
	
}
