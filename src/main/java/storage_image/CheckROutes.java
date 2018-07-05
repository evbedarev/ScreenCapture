package storage_image;

import org.apache.log4j.Logger;
import routes.RouteToYun11;

import java.awt.*;

public class CheckROutes {

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(CheckROutes.class);
        RouteToYun11 routeToYun11 = new RouteToYun11(logger);
        Thread.sleep(10000);
        routeToYun11.moveByMap();
    }
}
