package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventory_Entity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	public String product_name;
	public String units;
	public double pricing;
	public String location;
	public long order_number;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public double getPricing() {
		return pricing;
	}
	public void setPricing(double pricing) {
		this.pricing = pricing;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getOrder_number() {
		return order_number;
	}
	public void setOrder_number(long order_number) {
		this.order_number = order_number;
	}
	public Inventory_Entity(String product_name, String units, double pricing, String location, long order_number) {
		super();
		this.product_name = product_name;
		this.units = units;
		this.pricing = pricing;
		this.location = location;
		this.order_number = order_number;
	}
	public Inventory_Entity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product_name=" + product_name + ", units=" + units + ", pricing=" + pricing
				+ ", location=" + location + ", order_number=" + order_number + "]";
	}
	
	
	
	
	

}
