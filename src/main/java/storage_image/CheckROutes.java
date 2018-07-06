package storage_image;

import org.apache.log4j.Logger;
import routes.RouteYunField11;


public class CheckROutes {

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(CheckROutes.class);
        RouteYunField11 routeToYun11 = new RouteYunField11(logger);
        Thread.sleep(10000);
        routeToYun11.tpUntil();
    }
}
