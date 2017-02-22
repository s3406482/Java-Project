package ams.model;

import ams.model.visitor.visitable;

public interface Course extends visitable
{

	public abstract String getCode();

	public abstract String getTitle();

	public abstract int getCreditPoints();

	public abstract String[] getPreReqs();

}