package logic.move_by_card;

import java.util.*;
import java.util.stream.Collectors;

public class PointsFindNearest {
    private List<int[]> points = new ArrayList<>();

    public PointsFindNearest() {

    }

    /**
     * Создает новый
     * @param myPoint - текущее положение на карте
     * @return
     */
    public List<int[]> findNearestPoint(int[] myPoint) {
        int value;

        Map<Integer,Integer> pointMap = new HashMap<>();

        points.forEach(e -> pointMap.put(points.indexOf(e),e[0] + e[1]));
        for (Map.Entry<Integer, Integer> entry : pointMap.entrySet()) {
            value = Math.abs(entry.getValue() - (myPoint[0] + myPoint[1]));
            entry.setValue(value);
        }
        Comparator<Map.Entry<Integer, Integer>> valueComparator = Comparator.comparing(Map.Entry::getValue);
        Map<Integer, Integer> sortedMapPoints = pointMap.entrySet().stream().sorted(valueComparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int nearValueIndex = (int) sortedMapPoints.keySet().toArray()[0];

        return refactorListOfPoints(nearValueIndex);
    }

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
