//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.util.Random;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//public class Scatter {
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//    private static void createAndShowGUI() {
//
//        JFrame frame = new JFrame("Dynamic chart");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BoxLayout(
//                frame.getContentPane(), BoxLayout.PAGE_AXIS));
//
//        DynamicChartPanel panel = new DynamicChartPanel();
//        frame.getContentPane().add(panel);
//
//        JButton button = new JButton("Start");
//
//        button.addActionListener(new ActionListener() {
//            @Override public void actionPerformed(ActionEvent evt) {
//                if (panel.isStarted()) {
//                    panel.stop();
//                    button.setText("Start");
//                } else {
//                    panel.start();
//                    button.setText("Stop");
//                }
//            }
//        });
//
//        frame.getContentPane().add(button);
//
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//}
//
//class DynamicChartPanel extends JPanel{
//
//    private final ChartPanel content;
//    private final XYSeries agentLocations;
//    private final RandomModel model;
//    private final Timer timer;
//    private boolean started;
//
//    public DynamicChartPanel() {
//
//        agentLocations = new XYSeries("Agent locations");
//        this.model = new RandomModel();
//
//        updateAgentLocationSeries();
//
//        content = new ChartPanel(ChartFactory.createScatterPlot(
//                "Dynamic chart", "x", "y",
//                new XYSeriesCollection(agentLocations)));
//        this.add(content);
//        initializeChart();
//
//        timer = new Timer(100, new ActionListener() {
//            @Override public void actionPerformed(ActionEvent e) {
//                model.iterate();
//                updateAgentLocationSeries();
//            }
//        });
//        started = false;
//    }
//
//    private void initializeChart() {
//
//        int padding = 10;
//
//        XYPlot xyPlot = (XYPlot) content.getChart().getPlot();
//
//        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
//        domain.setRange(model.getxMin() - padding, model.getxMax() + padding);
//
//        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
//        range.setRange(model.getyMin() - padding, model.getyMax() + padding);
//    }
//
//    public void start() {
//        timer.start();
//        started = true;
//    }
//
//    public void stop() {
//        timer.stop();
//        started = false;
//    }
//
//    public boolean isStarted() {
//        return started;
//    }
//
//    private void updateAgentLocationSeries() {
//        agentLocations.clear();
//        for (Agent agent : model.getAgents()) {
//            agentLocations.add(agent.getX(), agent.getY());
//        }
//    }
//}
//
//class RandomModel {
//
//    private final Random random;
//
//    private final List<Agent> agents;
//    private final int agentCount;
//
//    /*
//       Space size
//     */
//    private final int xMin;
//    private final int yMin;
//    private final int xMax;
//    private final int yMax;
//
//    private final int moveRange;
//
//    public RandomModel() {
//
//        random = new Random();
//        agents = new ArrayList<>();
//
//        agentCount = 100;
//        xMin = 0;
//        xMax = 100;
//        yMin = 0;
//        yMax = 100;
//        moveRange = 4;
//
//        initializeAgents();
//    }
//
//    public List<Agent> getAgents() {
//        return agents;
//    }
//
//    public int getxMin() {
//        return xMin;
//    }
//
//    public int getyMin() {
//        return yMin;
//    }
//
//    public int getxMax() {
//        return xMax;
//    }
//
//    public int getyMax() {
//        return yMax;
//    }
//
//    public void iterate() {
//        moveAgents();
//    }
//
//    /*
//      Initially place each agent into a free random location
//    */
//
//    private void initializeAgents() {
//
//        for (int i = 0; i < agentCount; i++) {
//
//            int xPosition = random.nextInt(getxMax());
//            int yPosition = random.nextInt(getyMax());
//
//            while (locationTaken(xPosition, yPosition)) {
//                xPosition = random.nextInt((getxMax() - getxMin() + 1) + getxMin());
//                yPosition = random.nextInt((getyMax() - getyMin() + 1) + getyMin());
//            }
//
//            getAgents().add(new Agent(xPosition, yPosition));
//        }
//    }
//
//    /*
//      Every agent changes its coordinates within the move range
//    */
//
//    private void moveAgents() {
//        for (Agent agent : getAgents()) {
//            int xMinimum = agent.getX() - moveRange;
//            int xMaximum = agent.getX() + moveRange;
//            int yMinimum = agent.getY() - moveRange;
//            int yMaximum = agent.getY() + moveRange;
//            int x = wrapCoordinate(
//                    random.nextInt((xMaximum - xMinimum) + 1) + xMinimum,
//                    getxMin(), getxMax());
//            int y = wrapCoordinate(
//                    random.nextInt((yMaximum - yMinimum) + 1) + yMinimum,
//                    getyMin(), getyMax());
//            agent.setX(x);
//            agent.setY(y);
//        }
//    }
//
//    /*
//       Wrap number around min and max
//       If min is 0 and max is 100, 101 becomes 0 and -1 becomes 100
//     */
//
//    private int wrapCoordinate(int number, int min, int max) {
//        int rangeSize = max - min + 1;
//        if (number < min) {
//            number += rangeSize * ((min - number) / rangeSize + 1);
//        }
//        return min + (number - min) % rangeSize;
//    }
//
//    /*
//       Check if a location is taken by an agent
//     */
//
//    private boolean locationTaken(int xPosition, int yPosition) {
//        for (Agent agent : getAgents()) {
//            if (agent.getX() == xPosition && agent.getY() == yPosition) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
//
//class Agent {
//
//    private int x;
//    private int y;
//
//    public Agent(int xPosition, int yPosition)
//    {
//        x = xPosition;
//        y = yPosition;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//}