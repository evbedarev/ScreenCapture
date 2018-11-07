import java.util.*;
import java.util.stream.Collectors;

public class PointsFindNearestTest {
    private List<int[]> points = new ArrayList<>();

    public PointsFindNearestTest() {

    }

    /**
     * Находит ближайшую точку  текущему положению персонажа,
     * через гипотенузу прямоугольника
     * @param myPoint - текущее положение на карте
     * @return - возвращает индекс ближайшей точки к текущему положению.
     */
    public List<int[]> findNearestPoint(int[] myPoint) {
        Map<Integer,Integer> pointMap = new HashMap<>();
        points.forEach(e -> {
            int lenX =  Math.abs(myPoint[0] - e[0]);
            int lenY = Math.abs(myPoint[1] - e[1]);
            int hypotenuse = (int) Math.sqrt(Math.pow(lenX, 2) + Math.pow(lenY, 2));
            pointMap.put(points.indexOf(e), hypotenuse);
        });

        Comparator<Map.Entry<Integer, Integer>> valueComparator = Comparator.comparing(Map.Entry::getValue);
        Map<Integer, Integer> sortedMapPoints = pointMap.entrySet().stream().sorted(valueComparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int nearValueIndex = (int) sortedMapPoints.keySet().toArray()[0];
        return refactorListOfPoints(nearValueIndex);
    }

    /**
     * Перестраивает список точек. Ставит точку по переданным индексом первой в списке,
     * последующие за ней, предъидущие за ними.
     * @param pointIndex - индекс ближайшей точки.
     * @return - перестроенный список.
     */
    private List<int[]> refactorListOfPoints(int pointIndex) {
        List<int[]> newPoints = new ArrayList<>();
        newPoints.add(points.get(pointIndex));
        for (int i = pointIndex + 1; i < points.size(); i++) {
            newPoints.add(points.get(i));
        }

        for (int i = 0; i < pointIndex; i++) {
            newPoints.add(points.get(i));
        }
        return newPoints;
    }

    public void setPoints(List<int[]> points) {
        this.points = points;
    }


    public List<int[]> getPoints() {
        return points;
    }
}
