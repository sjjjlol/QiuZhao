package lottery;

class Prize {
    String name;
    int weight; // 权重（可视为概率权重）

    public Prize(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}