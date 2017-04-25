package com.mk.coffee.utils;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
public class DistanceUtils {
    //赤道半径是6378km,极半径为6357km,地球的平均半径6371km
    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     *
     * @param long1
     * @param lat1
     * @param long2
     * @param lat2
     * @return
     */
    public static double GetDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * EARTH_RADIUS
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    public static double[] getSquarePoint(double longitude, double latitude, float dis) {
        double[] doubles = new double[4];
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = latitude - dlat;
        double maxlat = latitude + dlat;
        double minlng = longitude - dlng;
        double maxlng = longitude + dlng;
        doubles[0] = minlat;
        doubles[1] = maxlat;
        doubles[2] = minlng;
        doubles[3] = maxlng;
        return doubles;
    }


}
