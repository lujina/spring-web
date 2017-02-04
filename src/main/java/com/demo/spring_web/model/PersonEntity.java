package com.demo.spring_web.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_person")
public class PersonEntity implements java.io.Serializable {  
  private static final long serialVersionUID = -4376187124011546736L; 
  private Integer id;
  private String name;
  
  @Id
  public Integer getId(){
	  return id;
  }
  
  public void setId(Integer id){
	  this.id = id;
  }
  
  @Column(length = 50,nullable = false, unique=true)
  public String getName(){
	  return name;
  }
  public void setName(String name){
	  this.name = name;
  }
  
  public String toString(){
	  return "PersonEntity [id=" + id +", name=" + name + "]";
  }
}  
