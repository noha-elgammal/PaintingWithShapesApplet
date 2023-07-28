import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingApplet extends Applet implements ActionListener, MouseListener, MouseMotionListener {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final int MIN_THICKNESS = 1;
    private static final int MAX_THICKNESS = 10;
    private static final int SHAPE_SIZE = 100;

    private Button rectButton, squareButton, circleButton, ovalButton, triangleButton;
    private Button redButton, greenButton, blueButton;
    private Checkbox borderButton, filledButton;
    private Button eraseButton;
    private Choice borderThickness;
    private Canvas drawingBoard;
    private Graphics g;
    private boolean drawBorder = true;
    private int thickness = MIN_THICKNESS;
    private Color shapeColor = Color.BLACK;
    private List<Shape> shapes = new ArrayList<>();

    private enum ShapeType {
        RECTANGLE, SQUARE, CIRCLE, OVAL, TRIANGLE
    }

    private ShapeType currentShape = ShapeType.RECTANGLE;

    public void init() {
        setLayout(new BorderLayout());

        // Create buttons
        Panel buttonPanel = new Panel(new GridLayout(6, 2, 5, 5));
        rectButton = createButton("Rectangle");
        squareButton = createButton("Square");
        circleButton = createButton("Circle");
        ovalButton = createButton("Oval");
        triangleButton = createButton("Triangle");
        eraseButton = createButton("Eraser");
        redButton = createColorButton(Color.RED);
        greenButton = createColorButton(Color.GREEN);
        blueButton = createColorButton(Color.BLUE);
        borderButton = createCheckbox("Border", true);
        filledButton = createCheckbox("Filled", false);
        borderThickness = createChoice(1, MAX_THICKNESS, MIN_THICKNESS);

        // Add buttons to panel
        buttonPanel.add(rectButton);
        buttonPanel.add(squareButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(triangleButton);
        buttonPanel.add(eraseButton);
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(borderButton);
        buttonPanel.add(filledButton);
        buttonPanel.add(borderThickness);

        // Create drawing board
        drawingBoard = new Canvas();
        drawingBoard.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        drawingBoard.setBackground(Color.WHITE);
        drawingBoard.addMouseListener(this);
        drawingBoard.addMouseMotionListener(this);

        // Add components to applet
        add(buttonPanel, BorderLayout.NORTH);
        add(drawingBoard, BorderLayout.CENTER);

        // Set initial graphics object
        g = drawingBoard.getGraphics();
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.addActionListener(this);
        return button;
    }

    private Button createColorButton(Color color) {
        Button button = new Button();
        button.setBackground(color);
        button.addActionListener(this);
        return button;
    }

    private Checkbox createCheckbox(String label, boolean state) {
        Checkbox checkbox = new Checkbox(label, state);
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == borderButton) {
                    drawBorder = borderButton.getState();
                } else if (e.getSource() == filledButton) {
                    drawBorder = !filledButton.getState();
                }
            }
        });
        return checkbox;
    }

    private Choice createChoice(int start, int end, int initial) {
        Choice choice = new Choice();
        for (int i = start; i <= end; i++) {
            choice.add(Integer.toString(i));
        }
        choice.select(Integer.toString(initial));
        choice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                thickness = Integer.parseInt(choice.getSelectedItem());
            }
        });
        return choice;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rectButton) {
           currentShape = ShapeType.RECTANGLE;
        } else if (e.getSource() == squareButton) {
            currentShape = ShapeType.SQUARE;
        } else if (e.getSource() == circleButton) {
            currentShape = ShapeType.CIRCLE;
        } else if (e.getSource() == ovalButton) {
            currentShape = ShapeType.OVAL;
        } else if (e.getSource() == triangleButton) {
            currentShape = ShapeType.TRIANGLE;
        } else if (e.getSource() == redButton) {
            shapeColor = Color.RED;
        } else if (e.getSource() == greenButton) {
            shapeColor = Color.GREEN;
        } else if (e.getSource() == blueButton) {
            shapeColor = Color.BLUE;
        } else if (e.getSource() == eraseButton) {
            shapes.clear();
            g.setColor(drawingBoard.getBackground());
            g.fillRect(0, 0, drawingBoard.getWidth(), drawingBoard.getHeight());
            return;
        }
        borderThickness.requestFocus();
    }

    public void mousePressed(MouseEvent e) {
        shapes.add(createShape(e.getX(), e.getY()));
    }

    public void mouseDragged(MouseEvent e) {
        shapes.add(createShape(e.getX(), e.getY()));
        drawShapes();
    }

    public void mouseReleased(MouseEvent e) {
        shapes.add(createShape(e.getX(), e.getY()));
        drawShapes();
    }

    private Shape createShape(int x, int y) {
        Shape shape;
        switch (currentShape) {
            case RECTANGLE:
                shape = new Rectangle(x, y, SHAPE_SIZE, SHAPE_SIZE / 2);
                break;
            case SQUARE:
                shape = new Rectangle(x, y, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case CIRCLE:
                shape = new Ellipse2D.Double(x, y, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case OVAL:
                shape = new Ellipse2D.Double(x, y, SHAPE_SIZE, SHAPE_SIZE / 2);
                break;
            case TRIANGLE:
                shape = new Polygon(
                        new int[]{x, x + SHAPE_SIZE / 2, x + SHAPE_SIZE},
                        new int[]{y + SHAPE_SIZE, y, y + SHAPE_SIZE},
                        3);
                break;
            default:
                shape = new Rectangle(x, y, SHAPE_SIZE, SHAPE_SIZE);
        }
        return shape;
    }

    private void drawShapes() {
        g.setColor(shapeColor);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        for (Shape shape : shapes) {
            if (drawBorder) {
                g2.draw(shape);
            } else {
                g2.fill(shape);
            }
        }
    }

    // Unused methods of MouseListener and MouseMotionListener
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}