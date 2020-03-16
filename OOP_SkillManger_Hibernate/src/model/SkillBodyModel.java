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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="skillbody")
public class SkillBodyModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skillid")
	private Integer id;
	
	@Column(name="skillidentif")
	private Integer ident;
	@Column(name="skillattrib")
	private Integer attrib;
	@Column(name="skillrequir")
	private Integer requir;
	@Column(name="skillcostbenef")
	private Boolean benef;
	@Column(name="skillname")
	private String name;
	@Column(name="skillnote")
	private String note;
	
	@OneToMany(mappedBy="skId", fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@Fetch(value = FetchMode.SUBSELECT)
	private List<NormLevelModel> levels = new ArrayList<NormLevelModel>();
	
	@OneToMany(mappedBy="skId", fetch=FetchType.EAGER, cascade= CascadeType.ALL )
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SpecialSkillModel> special = new ArrayList<SpecialSkillModel>();
	
	public SkillBodyModel () {	}
	
	public SkillBodyModel( Integer ident, Integer attrib, Integer requir, Boolean benef, String name,
			String note) {
		super();
		this.ident = ident;
		this.attrib = attrib;
		this.requir = requir;
		this.benef = benef;
		this.name = name;
		this.note = note;
	}



	public List<SpecialSkillModel> getSpecial() {
		return special;
	}
	public void setSpecial(List<SpecialSkillModel> special) {
		this.special = special;
	}
	public List<NormLevelModel> getLevels() {
		return levels;
	}
	public void setLevels(List<NormLevelModel> levels) {
		this.levels = levels;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdent() {
		return ident;
	}
	public void setIdent(Integer ident) {
		this.ident = ident;
	}
	public Integer getAttrib() {
		return attrib;
	}
	public void setAttrib(Integer attrib) {
		this.attrib = attrib;
	}
	public Boolean getBenef() {
		return benef;
	}
	public void setBenef(Boolean benef) {
		this.benef = benef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Model [id=" + id + ", ident=" + ident + ", attrib=" + attrib + ", resuir: " + requir +
				", benef=" + benef + ", name=" + name + ", note=" + note + " levels:" + levels.size() +"]";
	}
	public Integer getRequir() {
		return requir;
	}
	public void setRequir(Integer requir) {
		this.requir = requir;
	}
	
	
}
