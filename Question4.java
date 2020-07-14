import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question4 {
    public static void main(String[] args) {
        // test case 1 - valid input
        List<List<int[]>> coordinates1 = new ArrayList<>();
        List<int[]> coordinates1SubList1 = new ArrayList<>();
        coordinates1SubList1.add(new int[]{0, 0});
        coordinates1SubList1.add(new int[]{4, 4});
        List<int[]> coordinates1SubList2 = new ArrayList<>();
        coordinates1SubList2.add(new int[]{1, 1});
        coordinates1SubList2.add(new int[]{2, 5});
        coordinates1.add(coordinates1SubList1);
        coordinates1.add(coordinates1SubList2);

        int n1 = 2, k1 = 1;
        int n2 = 2, k2 = 2;
        int n3 = 2, k3 = 3;
        System.out.println(findHousesAdvertisedMoreThanKTimes(n1, k1, coordinates1));
        System.out.println(findHousesAdvertisedMoreThanKTimes(n2, k2, coordinates1));
        System.out.println(findHousesAdvertisedMoreThanKTimes(n3, k3, coordinates1));

        // test case 2 - invalid input
        List<List<int[]>> coordinates2 = new ArrayList<>();
        List<int[]> coordinates1SubList3 = new ArrayList<>();
        coordinates1SubList3.add(new int[]{0, 0});
        coordinates1SubList3.add(new int[]{4, 4});
        coordinates2.add(coordinates1SubList3);
        int n4 = 4, k4 = 4;
        System.out.println(findHousesAdvertisedMoreThanKTimes(n4, k4, coordinates2));
    }

    private static int findHousesAdvertisedMoreThanKTimes(int n, int k, List<List<int[]>> coordinates) {
        // Sanity Check, return -1 if invalid
        if (n < 0 || coordinates == null || n != coordinates.size()) {
            return -1;
        }

        // Use a map to track the frequency of the coordinates that have been visited.
        Map<String, Integer> posFreqMap = new HashMap<>();
        int numOfHouseVisitedMoreThanKTimes = 0;

        for (List<int[]> coordinate : coordinates) {
            // Let's assume we can return -1 if there is an invalid coordinate
            if (coordinate == null || coordinate.size() != 2) {
                return -1;
            }

            int[] bottomLeftPos = coordinate.get(0);
            int[] topRightPos = coordinate.get(1);
            int x1 = bottomLeftPos[0];
            int y1 = bottomLeftPos[1];
            int x2 = topRightPos[0];
            int y2 = topRightPos[1];

            // From the prompt, we can assume 0 ≤ x1, y1, x2, y2 ≤ 109, x1 < x2, y1 < y2
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    String posStr = buildPosStr(x, y);
                    posFreqMap.put(posStr, posFreqMap.getOrDefault(posStr, 0) + 1);

                    if (posFreqMap.get(posStr) == k) {
                        numOfHouseVisitedMoreThanKTimes++;
                    }
                }
            }
        }

        return numOfHouseVisitedMoreThanKTimes;
    }

    private static String buildPosStr(int x, int y) {
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y);

        return sb.toString();
    }
}
