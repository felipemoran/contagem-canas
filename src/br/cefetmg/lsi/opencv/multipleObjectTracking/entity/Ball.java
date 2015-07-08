package br.cefetmg.lsi.opencv.multipleObjectTracking.entity;

import org.opencv.core.Scalar;

import br.cefetmg.lsi.opencv.multipleObjectTracking.utils.PropertiesLoaderImpl;

public class Ball {
	private int xPos;
	private int yPos;
	private Colours type;
	private Scalar hsvMin;
	private Scalar hsvMax;
	private Scalar colour;
	static public enum Colours {
		NONE(""), RED("Red");
		
		private final String name;
		
		Colours(String colourName){
			name = colourName;
		}
		
		public String toString(){
			return name;
		}
	}; 
	
	public Ball(){
		xPos = 0;
		yPos = 0;
		type = Colours.NONE;
		int hMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.hMin"));
		int sMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.sMin"));
		int vMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.vMin"));
		int hMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.hMax"));
		int sMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.sMax"));
		int vMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.vMax"));
		setHsvMin(new Scalar(hMin, sMin, vMin));
		setHsvMax(new Scalar(hMax, sMax, vMax));
		setColour(new Scalar(0, 0, 255));
	}

	public Ball(Colours name){
		setType(name);
		int hMin = 0;
		int sMin = 0;
		int vMin = 0;
		int hMax = 0;
		int sMax = 0;
		int vMax = 0;


        hMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.hMin"));
        sMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.sMin"));
        vMin = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.vMin"));
        hMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.hMax"));
        sMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.sMax"));
        vMax = new Integer(PropertiesLoaderImpl.getValor("multipleObjectTracking.color.vMax"));
        setColour(new Scalar(0, 0, 255));

		setHsvMin(new Scalar(hMin, sMin, vMin));
		setHsvMax(new Scalar(hMax, sMax, vMax));
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public Colours getType() {
		return type;
	}

	public void setType(Colours type) {
		this.type = type;
	}

	public Scalar getHsvMin() {
		return hsvMin;
	}

	public void setHsvMin(Scalar hsvMin) {
		this.hsvMin = hsvMin;
	}

	public Scalar getHsvMax() {
		return hsvMax;
	}

	public void setHsvMax(Scalar hsvMax) {
		this.hsvMax = hsvMax;
	}

	public Scalar getColour() {
		return colour;
	}

	public void setColour(Scalar colour) {
		this.colour = colour;
	}
	
}
