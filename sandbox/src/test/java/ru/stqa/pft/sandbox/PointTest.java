package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistanceNormal() {
        Point p1 = new Point (10.0, 20.0);
        Point p2 = new Point (15.0, 30.0);
        Assert.assertEquals(p1.distance(p2), Math.sqrt(25+100));
    }
    @Test
    public void testDistanceSame() {
        Point p1 = new Point (10.0, 20.0);
        Point p2 = new Point (10.0, 20.0);
        Assert.assertEquals(p1.distance(p2), 0);
    }
    @Test
    public void testDistanceNegative() {
        Point p1 = new Point (-10.0, -20.0);
        Point p2 = new Point (10.0, 20.0);
        Assert.assertEquals(p1.distance(p2), Math.sqrt(400+1600));
    }
}
