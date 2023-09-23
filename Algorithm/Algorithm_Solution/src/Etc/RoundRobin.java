package Etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RoundRobin {
    /* T 2022 Challenge 연습 문제
     * 여러 대의 서버로 부하를 분산하는 로드밸러서를 만들고자 한다. 해당 로드밸런서는 기본적으로는 라운드 로빈 방식으로 요청을 서버로 분배한다.
     * 다만 요청의 sticky 옵션이 true인 경우 이전에 분배된 서버로 요청이 분배되어야 한다.
     * 라운드 로빈 방식: 1번 서버 → 2번 서버 → 3번 서버 → ... N번 서버 → 1번 서버 → 2번 서버
     */
    /*
     * sticky == false
     * int servers = 2;
     * int[] requests = new int[]{1, 2, 3, 4}
     * int[][] result = solution(servers, sticky, request)
     * println(result) → [1, 3], [2, 4]
     */
    /*
     * sticky == true
     * int servers = 3;
     * int[] requests = new int[]{1, 2, 3, 2, 4, 1, 3, 2, 2, 2}
     * int[][] result = solution(servers, sticky, request)
     * println(result) → [1, 4, 1], [2, 2, 2, 2, 2], [3, 3]
     */
    public int[][] solution(int servers, boolean sticky, int[] requests) {
        if (sticky) {
            HashMap<Integer, List<Integer>> serverToRequests = solutionForStickyTrue(servers, requests);
            return createAnswer(serverToRequests);
        } else {
            HashMap<Integer, List<Integer>> serverToRequests = solutionForStickyFalse(servers, requests);
            return createAnswer(serverToRequests);
        }
    }


    private HashMap<Integer, List<Integer>> solutionForStickyTrue(int servers, int[] requests) {
        HashMap<Integer, List<Integer>> serverToRequests = new HashMap<>(); // 특정 서버에 할당된 요청들을 담아둔 결과
        HashMap<Integer, Integer> requestToServer = new HashMap<>(); // sticky가 true일 때 특정 요청이 할당된 서버를 알아야한다. 그래서 key는 request, value는 server인 맵을 만든다.

        int serverNum = 0; // 라운드로빈 방식을 위한 변수
        for (int request : requests) {
            int calculatedServerNum;
            if (requestToServer.containsKey(request)) { // 특정 요청이 서버에 할당된 적이 있으면
                calculatedServerNum = requestToServer.get(request); // 그 요청의 value인 서버 번호를 반환한다. 이전에 요청을 할당해주려고 했던 서버의 번호는 serverNum이 여전히 가리키고 있다.
            } else {
                requestToServer.put(request, serverNum); // 라운드로빈 방식을 기반으로 어떤 요청을 어떤 서버에 할당하는지를 map에 저장한다.
                calculatedServerNum = requestToServer.get(request); // 이때 calculateServerNum == serverNum 이다.
                serverNum += 1; // 원래 할당 받을 차례인 서버에 요청을 할당해 주었으므로 serverNum++
                serverNum %= servers;
            }

            if (serverToRequests.containsKey(calculatedServerNum)) { // 특정 서버에 어떤 요청이 한번이라도 할당되었던 경우
                serverToRequests.get(calculatedServerNum).add(request);
            } else { // 특정 서버에 요청이 한번도 할당된 적이 없는 경우
                serverToRequests.put(calculatedServerNum, new ArrayList<>());
                serverToRequests.get(calculatedServerNum).add(request);
            }
        }

        return serverToRequests;
    }

    private HashMap<Integer, List<Integer>> solutionForStickyFalse(int servers, int[] requests) {
        HashMap<Integer, List<Integer>> serverToRequests = new HashMap<>();
        int serverNum = 0;
        for (int request : requests) {
            if (serverToRequests.containsKey(serverNum)) {
                serverToRequests.get(serverNum).add(request);
            } else {
                serverToRequests.put(serverNum, new ArrayList<>());
                serverToRequests.get(serverNum).add(request);
            }
            serverNum += 1;
            serverNum %= servers;
        }
        return serverToRequests;
    }

    private int[][] createAnswer(HashMap<Integer, List<Integer>> map) {
        int[][] answer = new int[map.size()][];
        Set<Integer> servers = map.keySet();
        for (Integer server : servers) {
            List<Integer> requests = map.get(server);
            answer[server] = requests.stream().mapToInt(i -> (int) i).toArray();
        }
        return answer;
    }

}
