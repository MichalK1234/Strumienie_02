package Strumienie_02_GIT;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Michal on 2017-02-17.
 */
public class Body {


    private Color color;
    private BodyType bodyType;
    private List<Accessories> accessoriesList;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public List<Accessories> getAccessoriesList() {
        return accessoriesList;
    }

    public void setAccessoriesList(List<Accessories> accessoriesList) {
        this.accessoriesList = accessoriesList;
    }

    public Body(Color color, BodyType bodyType, List<Accessories> accessoriesList) {
        this.color = color;
        this.bodyType = bodyType;
        this.accessoriesList = accessoriesList;
    }

    public Body() {

    }

    @Override
    public String toString() {
        return color + " " + bodyType + " " + accessoriesList;
    }
}


