/*
 * Copyright 2012 MoonJava LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.moonjava.flight.view.passagem;

/**
 * @version 1.0 Sep 7, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Using JAVA to print simple <span class="IL_AD" id="IL_AD2">text file</span>
 * to a printer
 */

public class PrintFileToPrinter implements Printable {

  public static AttributedString myStyledText = null;

  /**
   * This method reads the content of a text file. The location of the file is
   * provided in the <span class="IL_AD" id="IL_AD9">parameter</span>
   */
  public static String readContentFromFileToPrint(String fileName) {
    String dataToPrint = "";

    try {
      BufferedReader input = new BufferedReader(new FileReader(fileName));
      String line = "";
      /** Read the file and populate the data **/
      while ((line = input.readLine()) != null) {
        dataToPrint += line + "\n";
      }
    } catch (Exception e) {
      return dataToPrint;
    }
    return dataToPrint;
  }

  /**
   * Printing the data to a printer. Initialization done in this method.
   */
  public static void printToPrinter() {
    /**
     * Get a Printer Job
     */
    PrinterJob printerJob = PrinterJob.getPrinterJob();

    /**
     * Create a book. A book contains a pair of page painters called printables.
     * Also you have different pageformats.
     */
    Book book = new Book();
    /**
     * Append the Printable Object (this one itself, as it implements a
     * printable interface) and the page format.
     */
    book.append(new PrintFileToPrinter(), new PageFormat());
    /**
     * Set the object to be printed (the Book) into the PrinterJob. Doing this
     * before bringing up the print dialog allows the print dialog to correctly
     * display the page range to be printed and to dissallow any print settings
     * not appropriate for the pages to be printed.
     */
    printerJob.setPageable(book);

    /**
     * Calling the printDialog will pop up the Printing Dialog. If you want to
     * print without user confirmation, you can directly call printerJob.print()
     * 
     * doPrint will be false, if the user cancels the print operation.
     */
    boolean doPrint = printerJob.printDialog();

    if (doPrint) {
      try {
        printerJob.print();
      } catch (PrinterException ex) {
        System.err.println("Error occurred while trying to Print: "
            + ex);
      }
    }
  }

  /**
   * This method comes from the Printable interface. The method implementation
   * in this class <span class="IL_AD" id="IL_AD11">prints</span> a page of
   * text.
   */
  @Override
  public int print(Graphics g, PageFormat format, int pageIndex) {

    Graphics2D graphics2d = (Graphics2D) g;
    /**
     * Move the origin from the corner of the Paper to the corner of the
     * imageable area.
     */
    graphics2d.translate(format.getImageableX(), format.getImageableY());

    /** Setting the text color **/
    graphics2d.setPaint(Color.black);
    /**
     * Use a LineBreakMeasurer instance to break our text into lines that fit
     * the imageable area of the page.
     */
    Point2D.Float pen = new Point2D.Float();
    AttributedCharacterIterator charIterator = myStyledText.getIterator();
    LineBreakMeasurer measurer = new LineBreakMeasurer(charIterator,
        graphics2d.getFontRenderContext());
    float wrappingWidth = (float) format.getImageableWidth();
    while (measurer.getPosition() == charIterator.getEndIndex()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      pen.y += layout.getAscent();
      float dx = layout.isLeftToRight() ? 0 : (wrappingWidth - layout
          .getAdvance());
      layout.draw(graphics2d, pen.x + dx, pen.y);
      pen.y += layout.getDescent() + layout.getLeading();
    }
    return Printable.PAGE_EXISTS;
  }
}