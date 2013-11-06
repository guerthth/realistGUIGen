package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Pricepolicy generated by hbm2java
 */
@Entity
@Table(name = "pricepolicy", catalog = "rea")
public class Pricepolicy implements java.io.Serializable {

	private int id;
	private Resource resource;
	private Pricelist pricelist;
	private Double price;

	public Pricepolicy() {
	}

	public Pricepolicy(int id, Resource resource, Pricelist pricelist) {
		this.id = id;
		this.resource = resource;
		this.pricelist = pricelist;
	}

	public Pricepolicy(int id, Resource resource, Pricelist pricelist,
			Double price) {
		this.id = id;
		this.resource = resource;
		this.pricelist = pricelist;
		this.price = price;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ResourceType_Id", nullable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PriceList_Id", nullable = false)
	public Pricelist getPricelist() {
		return this.pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	@Column(name = "Price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
