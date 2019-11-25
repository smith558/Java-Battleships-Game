import java.util.ArrayList;
import java.util.List;

public class Main extends Game {

    // Complete the dynamicArray function below.
    static List<Integer> dynamicArray(int num, List<List<Integer>> queries) {
        int lastAnswer = 0;
        // residual list of all 'lastAnswer'
        List<Integer> lastAnswersList = new ArrayList<>();
        // new list of sequences(lists)
        List<ArrayList<Integer>> seqList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            seqList.add(new ArrayList<>());
        }

        for (List query : queries) {
            Integer type = (Integer) query.get(0);
            Integer x = (Integer) query.get(1);
            Integer y = (Integer) query.get(2);
            List seq = seqList.get((x ^ lastAnswer) % num);
            // query type 1
            if (type == 1) {
                seq.add(y);
                // query type 2
            } else {
                lastAnswer = (Integer) seq.get(y % seq.size());
                lastAnswersList.add(lastAnswer);
                System.out.println(lastAnswer);
            }
        }
        return lastAnswersList;
    }

    public static void main(String args[]) {
        // game is ON!
	Game game = new Game();
    }
}