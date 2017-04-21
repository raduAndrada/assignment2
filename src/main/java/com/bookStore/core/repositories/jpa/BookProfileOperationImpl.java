package com.bookStore.core.repositories.jpa;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.repositories.BookProfileOperation;
import com.bookStore.core.repositories.parsers.BookProfileParser;
import com.bookStore.core.services.util.BookList;
import com.bookStore.core.services.util.RegularUserList;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Repository;



import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by Andrada on 4/10/2017.
 */

public class BookProfileOperationImpl implements BookProfileOperation {
    public BookProfileOperationImpl() {
        try {
            availableBooks = parser.readBookProfile();
            generateCSV();
            generatePDF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BookProfileParser parser= new BookProfileParser();
    private BookList availableBooks ;

    @Override
    public BookProfile createBookProfile(BookProfile book) {
        if(availableBooks!= null) {
            Long id =generateUniqueId();
            book.setId(id);
            availableBooks.add(book);
            try {
                parser.setBooks(availableBooks);
                parser.writeBookProfile();
                generateCSV();
                generatePDF();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {availableBooks = new BookList();
            availableBooks.setBooks(new ArrayList<BookProfile>());
            availableBooks.add(book);
            availableBooks.setId(0L);}
        return book;
    }

    @Override
    public BookProfile deleteBookProfile(Long id) {
        BookProfile book = availableBooks.find(id);
        availableBooks.deleteBookProfile(id);
        try {
            parser.setBooks(availableBooks);
            parser.writeBookProfile();
            generateCSV();
            generatePDF();
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BookProfile updateBookProfile(Long id, BookProfile newBook) {
        availableBooks.udpate(id, newBook);
        try {
            parser.setBooks(availableBooks);
            parser.writeBookProfile();
            generateCSV();
            generatePDF();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBook;

    }



    @Override
    public BookProfile findBookById(Long id) {
        return availableBooks.find(id);
    }

    @Override
    public BookList getAllBooks() {
        return availableBooks;
    }

    @Override
    public BookList searchForBook(String search) {
        return availableBooks.search(search);
    }

    public void generateCSV()
    {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("report.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("Id");
            sb.append(',');
            sb.append("Title");
            sb.append(',');
            sb.append("Genre");
            sb.append(',');
            sb.append("Author");
            sb.append(',');
            sb.append("Price");
            sb.append('\n');
            for (BookProfile bk: availableBooks.getBooks())
            {
                if(bk.getQuantity() <= 0) {
                    sb.append(bk.getId());
                    sb.append(',');
                    sb.append(bk.getTitle());
                    sb.append(',');
                    sb.append(bk.getGenre());
                    sb.append(',');
                    sb.append(bk.getAuthor());
                    sb.append(',');
                    sb.append(bk.getPrice());
                    sb.append('\n');
                }
            }
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

  public void generatePDF()
  {
      Document document = new Document();

      try {

          PdfWriter.getInstance(document, new FileOutputStream(new File("report.pdf")));

          //open
          document.open();

          Paragraph p = new Paragraph();
          p.add("Books out of stock");
          p.setAlignment(Element.ALIGN_CENTER);
          Font f = new Font();
          f.setStyle(Font.BOLD);
          f.setSize(8);
          p.setFont(f);
          document.add(p);

          for (BookProfile bk: availableBooks.getBooks())
          {
              if(bk.getQuantity() <= 0) {
                  StringBuilder sb = new StringBuilder();
                  Paragraph p1 = new Paragraph();
                  p1.setAlignment(Element.ALIGN_LEFT);
                  sb.append("Id: ");
                  sb.append(bk.getId());
                  sb.append('\n');
                  sb.append("Title: ");
                  sb.append(bk.getTitle());
                  sb.append('\n');
                  sb.append("Genre: ");
                  sb.append(bk.getGenre());
                  sb.append('\n');
                  sb.append("Author: ");
                  sb.append(bk.getAuthor());
                  sb.append('\n');
                  sb.append("Price: ");
                  sb.append(bk.getPrice());
                  sb.append('\n');
                  p1.add(sb.toString());
                  document.add(p1);
              }
          }
          //close
          document.close();


      } catch (FileNotFoundException | DocumentException e) {
          e.printStackTrace();
          document.close();
      }

  }

    private Long generateUniqueId()
    {
        long val = -1;
        do
        {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        } while (val < 0);
        return val;
    }
}
