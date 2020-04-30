import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Graph {

    public final Vertex[] vertexList;
    public final int[][] adjacencyMatrix;
    public int numberOfVertices;
    public final Stack<Integer> myStack;
    Edge edges = new Edge();
    JFrame myInnerFrame = new JFrame();

    public Graph() {
        int MAX_VERTICES = 6;
        vertexList = new Vertex[MAX_VERTICES];
        adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        numberOfVertices = 0;
        myStack = new Stack<Integer>();
    }

    public void addVertex(String lab, int rowIndex, int columnIndex) {
        vertexList[numberOfVertices++] = new Vertex(lab, rowIndex, columnIndex);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
        adjacencyMatrix[end][start] = 1;
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < numberOfVertices; j++) {
            if (adjacencyMatrix[v][j] == 1 && !vertexList[j].wasVisited) {
                return j;
            }
        }
        return -1;
    }

    public void dfs() throws InterruptedException {
        Thread.sleep(3000);
        vertexList[0].wasVisited = true;
        vertexList[0].setForeground(Color.RED);
        edges.changeColorNext(0);
        myStack.push(0);
        myInnerFrame.repaint();
        while (!myStack.isEmpty()) {
            Thread.sleep(3000);
            int v = getAdjUnvisitedVertex(myStack.peek());
            if (v == -1) {
                myStack.pop();
            } else {
                vertexList[v].wasVisited = true;
                vertexList[v].setForeground(Color.RED);
                edges.changeColorNext(v);
                myStack.push(v);
                myInnerFrame.repaint();
            }
        }
    }

    public void initializeUI() {
        welcomePageCreation().setVisible(true);
    }

    public void drawGraph(JPanel[][] panelHolder) {
        // adding vertices
        for (Vertex v : vertexList)
            panelHolder[v.rowIndex][v.columnIndex].add(v);
    }

    private JPanel[][] setGridLayout(int i, int j, JFrame myFrame) {
        JPanel[][] panelHolder = new JPanel[i][j];
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                myFrame.add(panelHolder[m][n]);
            }
        }
        return panelHolder;
    }

    private JFrame welcomePageCreation() {
        JFrame welcomePage = new JFrame("Hosgeldiniz");
        JPanel panel = new JPanel(new GridLayout(6, 1, 4, 4));

        JLabel header = new JLabel("Depth First Search Algorithm", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 40));
        header.setSize(new Dimension(100, 100));
        panel.add(header);

        JButton simulationButton = new JButton("Simulasyon");
        simulationButton.setFont(new Font("Arial", Font.PLAIN, 30));
        simulationButton.setBackground(Color.BLACK);
        simulationButton.setForeground(Color.RED);

        simulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset everything
                JFrame simulationFrame = new JFrame("Simulasyon") {
                    public void paint(Graphics g) {
                        super.paint(g);
                        Graphics2D g2 = (Graphics2D) g;
                        Stroke stroke3 = new BasicStroke(12f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                        g2.setStroke(stroke3);

                        edges.lines.forEach((line, color) -> {
                            g.setColor(color);
                            g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
                        });
                    }
                };

                simulationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                simulationFrame.setPreferredSize(new Dimension(800, 600));
                simulationFrame.setResizable(false);
                simulationFrame.setLayout(new GridLayout(3, 4));
                JPanel[][] panelHolder = setGridLayout(3, 4, simulationFrame);
                drawGraph(panelHolder);
                myInnerFrame = simulationFrame;
                myInnerFrame.pack();
                myInnerFrame.setVisible(true);

            }
        });
        panel.add(simulationButton);

        JButton algorithmButton = new JButton("Pseudo-code");
        algorithmButton.setFont(new Font("Arial", Font.PLAIN, 30));
        algorithmButton.setBackground(Color.BLACK);
        algorithmButton.setForeground(Color.RED);

        algorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame pseudoCodeFrame = new JFrame("Pseudo-code");
                ImageIcon img = new ImageIcon("images\\The-pseudo-code-for-DFS.png");
                JLabel imgLabel = new JLabel(img);
                imgLabel.setPreferredSize(new Dimension(300, 300));
                pseudoCodeFrame.add(imgLabel);
                pseudoCodeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                pseudoCodeFrame.setPreferredSize(new Dimension(800, 600));
                pseudoCodeFrame.pack();
                pseudoCodeFrame.setVisible(true);
            }
        });
        panel.add(algorithmButton);


        JButton explanationButton = new JButton("Kelimelerle DFS");
        explanationButton.setFont(new Font("Arial", Font.PLAIN, 30));
        explanationButton.setBackground(Color.BLACK);
        explanationButton.setForeground(Color.RED);
        explanationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame explanationFrame = new JFrame("Kelimelerle DFS");
                JTextArea textArea = new JTextArea(2, 20);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setOpaque(false);
                textArea.setEditable(false);
                textArea.setFocusable(false);
                textArea.setBackground(UIManager.getColor("Label.background"));
                textArea.setFont(UIManager.getFont("Label.font"));
                textArea.setBorder(UIManager.getBorder("Label.border"));

                String text = "Türkçesi “Derinlik öncelikli arama” şeklinde geçen DFS algoritması bizim " +
                        "belirlediğimiz bir root (kök) node’dan başlıyor ve herhangi bir çocuğunu seçiyor. " +
                        "Daha sonra bu çocuktan daha önce gezmediğimiz herhangi bir node’a gidiliyor. " +
                        "Bu seçimlerdeki iki kuralımız var birincisi seçeceğimiz node’a direkt yol olmalı " +
                        "diğeri ise o node daha önce gezilmemiş olmalı. Bu kuralları uygulayarak rekürsif " +
                        "bir şekilde geziliyor.\n" +
                        "\n" +
                        "Ancak öyle bir node’a geliyoruz ki gidilecek yer kalmıyor. Çünkü direkt " +
                        "bağlantımızın olduğu bütün node’lar daha önceden gezilmiş. Böyle bir durumda ise " +
                        "bu node’dan önce en son gezdiğimiz node’a geri dönüyoruz ve başka tercihler " +
                        "var mı diye bakıyoruz. Algoritma rekürsif olarak çalıştığı için her takıldığımız " +
                        "noktada bir üste çıkmamıza olanak sağlıyor. Algoritmamız başladığımız yere " +
                        "geldiğimizde ve hali hazırda bütün çocuklarımızı gezdiğimizde bitiyor.";
                textArea.setText(text);

                explanationFrame.add(textArea);
                explanationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                explanationFrame.setPreferredSize(new Dimension(800, 600));
                explanationFrame.pack();
                explanationFrame.setVisible(true);
            }
        });
        panel.add(explanationButton);

        JButton complexityButton = new JButton("Karmasiklik");
        complexityButton.setFont(new Font("Arial", Font.PLAIN, 30));
        complexityButton.setBackground(Color.BLACK);
        complexityButton.setForeground(Color.RED);

        complexityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame explanationFrame = new JFrame("Kelimelerle DFS");
                JTextArea textArea = new JTextArea(2, 20);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setOpaque(false);
                textArea.setEditable(false);
                textArea.setFocusable(false);
                textArea.setBackground(UIManager.getColor("Label.background"));
                textArea.setFont(UIManager.getFont("Label.font"));
                textArea.setBorder(UIManager.getBorder("Label.border"));

                String text = "DFS için zaman karmaşıklığı O (n + m) 'dir. Bu karmaşıklığı, her düğümü sadece bir " +
                        "kez ziyaret ettiğimiz ve bir ağaç durumunda (döngü yok) tüm kenarları bir kez geçtiğimiz " +
                        "varsayiminda alıyoruz.\n" +
                        "Örneğin, başlangıç düğümü u. bitiş düğümü v olsun, v'nin en son ziyaret edilen düğüm olacağı " +
                        "en kötü senaryoyu dusunelim. Ilk komşu u'nun bağlantılı tum dugumlerini, " +
                        "ardından ikinci komşunun bağli butun dugumlerini ziyaret etmeye başlıyoruz. Bu ziyaretler v " +
                        "bulacagimiz son komsunun baglantilarina kadar suruyor. Her bir düğümü sadece bir kez ziyaret ettik " +
                        "ve aynı baglantiyi bir kereden fazla geçmemis olduk.";
                textArea.setText(text);

                explanationFrame.add(textArea);
                explanationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                explanationFrame.setPreferredSize(new Dimension(800, 600));
                explanationFrame.pack();
                explanationFrame.setVisible(true);
            }
        });
        panel.add(complexityButton);
        JButton exitButton = new JButton("Cikis");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomePage.setPreferredSize(new Dimension(1200, 800));
        welcomePage.add(panel);
        welcomePage.pack();
        return welcomePage;
    }
}
