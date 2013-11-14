package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Discountoraddition generated by hbm2java
 */
@Entity
@Table(name = "discountoraddition", catalog = "rea")
public class Discountoraddition implements java.io.Serializable {

	private int id;
	private Resource resource;
	private double percentage;
	private boolean isDicsount;
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);

	public Discountoraddition() {
	}

	public Discountoraddition(Resource resource, double percentage,
			boolean isDicsount) {
		this.resource = resource;
		this.percentage = percentage;
		this.isDicsount = isDicsount;
	}

	public Discountoraddition(Resource resource, double percentage,
			boolean isDicsount, Set<Stockflow> stockflows) {
		this.resource = resource;
		this.percentage = percentage;
		this.isDicsount = isDicsount;
		this.stockflows = stockflows;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "resource"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Column(name = "Percentage", nullable = false, precision = 22, scale = 0)
	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Column(name = "IsDicsount", nullable = false)
	public boolean isIsDicsount() {
		return this.isDicsount;
	}

	public void setIsDicsount(boolean isDicsount) {
		this.isDicsount = isDicsount;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "position_has_discountoraddition", catalog = "rea", joinColumns = { @JoinColumn(name = "DiscountOrAddition_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Position_Id", nullable = false, updatable = false) })
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

}
