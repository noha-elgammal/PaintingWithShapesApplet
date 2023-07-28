# PaintingWithShapesApplet
This program allows one to draw different shapes using different colors. Also, it gives the user the choice of having borders-only shapes or filled. One can also choose the thickness of lines. (Below image there's a more detailed explanation)

![Capture](https://github.com/noha-elgammal/PaintingWithShapesApplet/assets/139645547/f5c38868-66c9-4f20-9da5-8961691f987b)

The applet provides a canvas for the user to draw various shapes (rectangle, square, circle, oval, and triangle) using the mouse. The user can choose the shape, color, border thickness, and whether to draw a border or fill the shape. There is also an erase button to clear the canvas.

To use this applet, you can embed it in an HTML page and open the page in a web browser. The applet will be displayed in the browser and you can interact with it.

1. Import statements and class declaration

The first few lines of the code contain import statements for various Java classes and interfaces that will be used in the applet. The DrawingApplet class is then declared and extends Applet, which means it is a Java applet that can be embedded in a web page.

2. Constants and instance variables

The next few lines of the code define some constants and instance variables that will be used throughout the applet. The constants define the default size of the canvas, the minimum and maximum border thickness values, and the size of the shapes that can be drawn. The instance variables include various buttons, checkboxes, and controls that will be displayed in the applet, as well as a List of Shape objects that will store the shapes drawn by the user.

3. The init() method

The init() method is called when the applet is first created. In this method, the layout of the applet is set to a BorderLayout, which means that components can be added to the north, south, east, west, or center of the applet.

Several buttons, checkboxes, and controls are created using helper methods (createButton(), createColorButton(), createCheckbox(), and createChoice()) and added to a Panel with a GridLayout.

A Canvas object is also created and added to the center of the applet. The canvas is given a default size and background color of white, and listeners are added for mouse events (clicks, drags, and releases) and mouse motion events (moves and drags).

Finally, the graphics object for the canvas is set to a member variable so it can be accessed later.

4. Helper methods

There are several helper methods that are used to create the buttons, checkboxes, and controls used in the applet. These methods take in parameters such as the label for the button, the default state of the checkbox, or the minimum and maximum values for the choice control.

5. The paint() method

The paint() method is called whenever the applet needs to be redrawn, such as when a new shape is added to the canvas. In this method, the graphics object is used to draw each shape in the shapes list. If the shape is supposed to have a border, the color and thickness are set accordingly.

6. Event listeners

The applet implements several event listener interfaces (ActionListener, MouseListener, and MouseMotionListener) to handle user input.

The ActionListener interface is used to handle button clicks. When a button is clicked, the appropriate action is taken, such as changing the current shape to draw or changing the color of the shapes.

The MouseListener interface is used to handle mouse clicks, releases, and drags. When the mouse is clicked, the starting coordinates of the shape are stored. When the mouse is released, the shape is created and added to the shapes list. When the mouse is dragged, a temporary shape is drawn on the canvas to provide visual feedback to the user.

The MouseMotionListener interface is used to handle mouse movements and drags. When the mouse is moved or dragged, the temporary shape is updated to reflect the current mouse position.

__________________


To sum up, here's what's happening in the code:


When a shape button is clicked, it sets the currentShape to be drawn.

When a color button is clicked, it sets the shapeColor used for drawing.

When the erase button is clicked, it clears all shapes and redraws the background.

On mouse press, a new shape is created based on the currentShape and mouse position, and added to the shapes list.

On mouse drag, a new shape is created and added to update the drawing.

On mouse release, a final shape is added and all shapes are drawn.

The shapes are drawn either with a border or filled, based on the checkboxes.

The Choice widget allows setting the border thickness.

The shapes that have been drawn are stored in the shapes list, and redrawn when needed.

____________

So this program allows users to:

- Select a shape type
  
- Select a color
  
- Select a border thickness
 
- Draw shapes either with a border or filled
  
- Erase all drawn shapes and clear the canvas
  
- Drag the mouse to dynamically create and update shapes
as they're drawn.

________________________________

The key parts are:

- Storing the drawn shapes in a list

- Creating new shapes based on the mouse position and current shape type
  
- Redrawing all stored shapes when needed
