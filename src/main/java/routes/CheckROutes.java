package routes;

import checks.afterDeath.AfterDeath;
import checks.afterDeath.AfterDeathYun11;

public class CheckROutes {

    public static void main(String[] args) throws Exception {
        RouteToLocation routeYunField = new RouteAldebaran();
        RouteFromAldebaran routeFromAldebaran = new RouteFromAldebaran();
        Thread.sleep(10000);

//        routeYunField.tpUntil();
//        routeFromAldebaran.moveToLocation();
//        Thread.sleep(10000);
//
//        routeYunField = new RouteYunField01();
//        routeYunField.tpUntil();
//        Thread.sleep(5000);
//
//        routeYunField = new RouteYunField12();
//        routeYunField.tpUntil();
//        Thread.sleep(5000);
//
//        routeYunField = new RouteYunField11();
//        routeYunField.tpUntil();
//        Thread.sleep(5000);

//        RouteToLocation routeGef = new RouteGefField10();
//        routeGef.tpUntil();
//        RouteFromAldebaran routeFromAldebaran = new RouteFromAldebaran();
//        routeFromAldebaran.moveToLocation();

//        RouteToLocation route = new RouteYunField01();
//        route.tpUntil();
//        route = new RouteYunField12();
//        route.tpUntil();

        AfterDeath afterDeath = new AfterDeathYun11();
        ((AfterDeathYun11) afterDeath).putItemTest();

    }
}
