import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static final int repeatedTimes = 5000;

    public static void experiment1() {
        List<Integer> xValues = new ArrayList<>();
        List<Double> normalBstDepths  = new ArrayList<>();
        List<Double> optimalBSTDepths  = new ArrayList<>();
        BST<Integer> testBSt = new BST<>();

        Random rand = new Random();
        BST<Integer> testBst = new BST<>();
        for(int x = 1; x <= 5000; x++){
            int newItem = rand.nextInt(Integer.MAX_VALUE);
            testBst.add(newItem);
            xValues.add(x);
            optimalBSTDepths.add(ExperimentHelper.optimalAverageDepth(x));
            normalBstDepths.add(testBst.getAverageDepth());

        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("optimal average depth", xValues, optimalBSTDepths);
        chart.addSeries("random added average depth", xValues, normalBstDepths);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        List<Integer> xValues = new ArrayList<>();
        List<Double> depthOfBST  = new ArrayList<>();
        Random rand = new Random();

        BST<Integer> testBSt =  Experiments.generateRandomTree(20000, rand);

        for(int i = 1; i <= repeatedTimes; i++){
            int newItem = rand.nextInt(Integer.MAX_VALUE);
            xValues.add(i);
            Experiments.deleteAndAddRandomBSTAsymmetric(testBSt, newItem);
            depthOfBST.add(testBSt.getAverageDepth());
        }


        XYChart chart = new XYChartBuilder().width(1000).height(1000).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random added average depth(Asymmetric Deletion)", xValues, depthOfBST);
        new SwingWrapper(chart).displayChart();
    }


    public static void experiment3() {
        List<Integer> xValues = new ArrayList<>();
        List<Double> depthOfBST  = new ArrayList<>();
        Random rand = new Random();

        BST<Integer> testBSt =  Experiments.generateRandomTree(20000, rand);

        for(int i = 1; i <= repeatedTimes; i++){
            int newItem = rand.nextInt(Integer.MAX_VALUE);
            xValues.add(i);
            Experiments.deleteAndAddRandomBSTSymmetric(testBSt, newItem);
            depthOfBST.add(testBSt.getAverageDepth());
        }


        XYChart chart = new XYChartBuilder().width(1000).height(1000).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random added average depth(Symmetric Deletion)", xValues, depthOfBST);
        new SwingWrapper(chart).displayChart();
    }

    public static BST<Integer> generateRandomTree(int max, Random rand){
        BST<Integer> newBSt = new BST<>();
        for(int i = 0; i < max; i++){
            newBSt.add(rand.nextInt(Integer.MAX_VALUE));
        }
        return newBSt;
    }

    private static void deleteAndAddRandomBSTAsymmetric (BST<Integer> bst, int newItem){
        int item = bst.getRandomKey();
        bst.deleteTakingSuccessor(item);
        bst.add(newItem);
    }

    private static void deleteAndAddRandomBSTSymmetric  (BST<Integer> bst, int newItem){
        int item = bst.getRandomKey();
        bst.deleteTakingRandom(item);
        bst.add(newItem);
    }

    public static void main(String[] args) {
        experiment2();
        experiment3();
    }
}
