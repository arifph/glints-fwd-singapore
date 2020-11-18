
import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings({ "deprecation", "serial" })
public class TicTacToeDynamicBoard extends Applet implements ChangeListener, ActionListener {
	private int numberOfColumnAndRow = 3; // The board dimension should not be set like this, but how?
	private int maxHeight, maxWidth;
	private BoardShouldBeDynamicButIDoNotKnowHowToMakeItDynamicAndThisClassShouldBePlacedInADifferentFileButItKeptShowingBlankIfIDoThat board;
	private int turn = 1;
	private char position[] = new char[numberOfColumnAndRow * numberOfColumnAndRow];

	public void init() {
		maxHeight = getHeight();
		maxWidth = getWidth();
	}

	public void paint(Graphics g) {
		add(board = new BoardShouldBeDynamicButIDoNotKnowHowToMakeItDynamicAndThisClassShouldBePlacedInADifferentFileButItKeptShowingBlankIfIDoThat(),
				BorderLayout.CENTER);
		board.setPreferredSize(new Dimension(getMaxWidth(), getMaxHeight()));
		setVisible(true);
		board.paintComponent(g);
	}

	public int getMaxHeight() {
		return getHeight();
	}

	public int getMaxWidth() {
		return getWidth();
	}

	private class BoardShouldBeDynamicButIDoNotKnowHowToMakeItDynamicAndThisClassShouldBePlacedInADifferentFileButItKeptShowingBlankIfIDoThat
			extends JPanel implements MouseListener {
		private Point point;
		int[] horizontalLine = new int[numberOfColumnAndRow];
		int[] verticalLine = new int[numberOfColumnAndRow];

		public BoardShouldBeDynamicButIDoNotKnowHowToMakeItDynamicAndThisClassShouldBePlacedInADifferentFileButItKeptShowingBlankIfIDoThat() {
			addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent ev) {
			point = new Point(ev.getPoint());
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

		protected void paintComponent(Graphics graphic) {
			super.paintComponent(graphic);
			Graphics2D graphic2d = (Graphics2D) graphic;
			graphic2d.setPaint(Color.BLACK);
			graphic2d.setStroke(new BasicStroke(5));

			// Draw line
			for (int j = 0; j < numberOfColumnAndRow - 1; j++) {
				// Draw horizontal line and record the position
				int heightPos = maxHeight * (j + 1) / numberOfColumnAndRow;
				int widthPos = maxWidth * (j + 1) / numberOfColumnAndRow;
				graphic2d.draw(new Line2D.Double(0, heightPos, maxWidth, heightPos));
				horizontalLine[j] = heightPos;

				// Draw vertical line and record the position
				graphic2d.draw(new Line2D.Double(widthPos, 0, widthPos, maxHeight));
				verticalLine[j] = widthPos;
			}

			graphic.setFont(new Font("Calibri", Font.BOLD, 30));
			if (point != null) {
				// I was stuck in here
				for (int i = 0; i < position.length; i++) {
					if (Objects.equals(position[i], '\u0000')) {
						drawXorDrawO(graphic2d, point.x, point.y, turn);
					}
				}

				System.out.println("Turn : " + turn);

				int positionInArray = 0;
				int xPos = point.x * numberOfColumnAndRow / maxWidth;
				int yPos = point.y * numberOfColumnAndRow / maxHeight;
				System.out.println("X -> " + xPos);
				System.out.println("Y -> " + yPos);

				searchPos: for (int k = 0; k < numberOfColumnAndRow; k++) {
					for (int l = 0; l < numberOfColumnAndRow; l++) {
						if (xPos == l && yPos == k) {
							// Fill Position Array
							position[positionInArray] = drawXorDrawO(graphic2d, point.x, point.y, turn).charAt(0);
							break searchPos;
						}
						positionInArray++;
					}
				}
				System.out.println("Pos -> " + positionInArray);
				System.out.println("Array pos ==> " + Arrays.toString(position));

				turn++;
			}
		}

		public String drawXorDrawO(Graphics2D graphic2d, int xCoordinate, int yCoordinate, int turn) {
			String oPoint = new String("O");
			String xPoint = new String("X");
			if (turn % 2 == 1) {
				graphic2d.drawString(oPoint, point.x, point.y);
				return oPoint;
			} else {
				graphic2d.drawString(xPoint, point.x, point.y);
				return xPoint;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
	}

	@Override
	public void stateChanged(ChangeEvent ev) {
	}
	
	// Aaargh.. I got a headache, but the time is ticking.
	// Background music: A clock that sound "tick tock tick tock, tick tick tick".
}
