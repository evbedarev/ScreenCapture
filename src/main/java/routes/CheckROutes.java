package routes;

import org.apache.log4j.Logger;

public class CheckROutes {

    public static void main(String[] args) throws Exception {
        RouteToLocation routeYunField = new RouteAldebaran();
        RouteFromAldebaran routeFromAldebaran = new RouteFromAldebaran();
        Thread.sleep(10000);

//        routeYunField.tpUntil();
        routeFromAldebaran.moveToLocation();
        Thread.sleep(10000);

        routeYunField = new RouteYunField01();
        routeYunField.tpUntil();
        Thread.sleep(5000);

        routeYunField = new RouteYunField12();
        routeYunField.tpUntil();
        Thread.sleep(5000);

        routeYunField = new RouteYunField11();
        routeYunField.tpUntil();
        Thread.sleep(5000);

    }
}
