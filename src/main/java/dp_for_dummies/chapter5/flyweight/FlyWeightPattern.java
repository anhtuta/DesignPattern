package dp_for_dummies.chapter5.flyweight;

import java.util.Arrays;

class Student {
    private String name;
    private int id;
    private int score;
    private double averageScore;

    public Student(double a) {
        averageScore = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    // Số phần trăm chênh lệch giữa điểm của học sinh này với điểm trung bình của cả lớp.
    // Giả sử học sinh này được 45đ, còn trung bình cả lớp là 55đ, chênh lệch là -10đ,
    // do đó getStanding = -10/55 = -18%.
    // Note: getStanding = (score - averageScore)/averageScore = score/averageScore - 1
    public double getStanding() {
        return ((score) / averageScore - 1.0) * 100.0;
    }
}


public class FlyWeightPattern {

    public static void main(String[] args) {
        String names[] = {"Ralph", "Alice", "Sam"};
        int ids[] = {1001, 1002, 1003};
        int scores[] = {45, 55, 65};

        double total = Arrays.stream(scores).reduce(0, (a, b) -> a + b);
        double averageScore = total / scores.length;
        Student student = new Student(averageScore);

        for (int i = 0; i < scores.length; i++) {
            student.setName(names[i]);
            student.setId(ids[i]);
            student.setScore(scores[i]);
            System.out.printf("Name: %s, with standing: %.0f%%\n", student.getName(),
                    student.getStanding());
        }
    }
}
