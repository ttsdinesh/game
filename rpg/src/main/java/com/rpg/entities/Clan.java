package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class Clan implements Serializable {

	public Clan(String name, String powers, int colorCode, ClanType clanType) {
		super();
		this.name = name;
		this.powers = powers;
		this.colorCode = colorCode;
		this.clanType = clanType;
	}

	private static final long serialVersionUID = 1L;
	private String name;
	private String powers;
	private int colorCode;
	private ClanType clanType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		this.powers = powers;
	}

	public int getColorCode() {
		return colorCode;
	}

	public void setColorCode(int colorCode) {
		this.colorCode = colorCode;
	}

	public ClanType getClanType() {
		return clanType;
	}

	public void setClanType(ClanType clanType) {
		this.clanType = clanType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clanType == null) ? 0 : clanType.hashCode());
		result = prime * result + colorCode;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((powers == null) ? 0 : powers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clan other = (Clan) obj;
		if (clanType != other.clanType)
			return false;
		if (colorCode != other.colorCode)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (powers == null) {
			if (other.powers != null)
				return false;
		} else if (!powers.equals(other.powers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Clan [name=");
		builder.append(name);
		builder.append(", powers=");
		builder.append(powers);
		builder.append(", colorCode=");
		builder.append(colorCode);
		builder.append(", clanType=");
		builder.append(clanType);
		builder.append("]");
		return builder.toString();
	}

}
