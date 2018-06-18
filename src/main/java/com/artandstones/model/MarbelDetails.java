/**
 * 
 */
package com.artandstones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gargvis
 *
 */

@Entity
@Table(name = "marbel_details")
public class MarbelDetails {
	
	@Id
	@Column(name = "MARBEL_NAME")
	private String marbleName;
	
	@Column(name = "MARBEL_TYPE")
	private String marbelType;
	
	@Column(name = "MARBEL_PRICE")
	private String marbelPrice;
	
	@Column(name = "MARBEL_DESC")
	private String marbelDesc;

	public String getMarbleName() {
		return marbleName;
	}

	public void setMarbleName(String marbleName) {
		this.marbleName = marbleName;
	}

	public String getMarbelType() {
		return marbelType;
	}

	public void setMarbelType(String marbelType) {
		this.marbelType = marbelType;
	}

	public String getMarbelPrice() {
		return marbelPrice;
	}

	public void setMarbelPrice(String marbelPrice) {
		this.marbelPrice = marbelPrice;
	}

	public String getMarbelDesc() {
		return marbelDesc;
	}

	public void setMarbelDesc(String marbelDesc) {
		this.marbelDesc = marbelDesc;
	}

	@Override
	public String toString() {
		return "MarbelDetails [marbleName=" + marbleName + ", marbelType=" + marbelType + ", marbelPrice=" + marbelPrice
				+ ", marbelDesc=" + marbelDesc + "]";
	}

}
