package lottery;

import java.util.*;

public class LotterySystem {

    private final List<Prize> prizeList = new ArrayList<>();
    private int totalWeight = 0;
    private final Random random = new Random();

    // 添加奖项
    public void addPrize(String name, int weight) {
        prizeList.add(new Prize(name, weight));
        totalWeight += weight;
    }

    // 抽奖
    public String draw() {
        int r = random.nextInt(totalWeight); // [0, totalWeight)
        int sum = 0;
        for (Prize p : prizeList) {
            sum += p.weight;
            if (r < sum) return p.name;
        }
        return "未中奖";
    }

    public static void main(String[] args) {
        LotterySystem lottery = new LotterySystem();
        lottery.addPrize("一等奖", 10);
        lottery.addPrize("二等奖", 20);
        lottery.addPrize("三等奖", 30);
        lottery.addPrize("未中奖", 40);

        Map<String, Integer> resultCount = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            String result = lottery.draw();
            resultCount.put(result, resultCount.getOrDefault(result, 0) + 1);
        }

        // 打印概率
        resultCount.forEach((k, v) -> System.out.println(k + " -> " + v / 1000.0 + "%"));
    }
}