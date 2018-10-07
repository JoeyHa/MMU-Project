package com.hit.algorithm;

public class WrapperV<V> {
	public WrapperV(V v,int bits)
	{
		this.bits=bits;
	    this.v=v;
	}
	public V getV(){return this.v;}
	public int getBits(){return this.bits;}
	public void setV(V v){this.v=v;}
	public void setBits(int bits){this.bits=bits;}
	private int bits;
	private V v;
	
	

}