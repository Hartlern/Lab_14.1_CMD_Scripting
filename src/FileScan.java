import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileScan
{
    public static void main( String[] args )
    {
        File fileToScan = null;

        if( args.length > 0 )
        {
            String fileName = args[ 0 ];
            fileToScan = new File( fileName );
            if( !fileToScan.exists() )
            {
                System.out.println( "The file does not exist: " + fileName );
                return;
            }
        }
        else
        {
            JFileChooser fileChooser = new JFileChooser();
            String projectPath = System.getProperty( "user.dir" ) + File.separator + "src";
            fileChooser.setCurrentDirectory( new File( projectPath ) );

            int returnValue = fileChooser.showOpenDialog( null );
            if( returnValue != JFileChooser.APPROVE_OPTION )
            {
                System.out.println( "No file selected, exiting program." );
                return;
            }
            fileToScan = fileChooser.getSelectedFile();
        }

        System.out.println( "Selected file: " + fileToScan.getAbsolutePath() );

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try( BufferedReader br = new BufferedReader( new FileReader( fileToScan ) ) )
        {
            String line;
            while( ( line = br.readLine() ) != null )
            {
                lineCount++;
                charCount += line.length();

                String[] words = line.split( "\\s+" );
                wordCount += words.length;

                System.out.println( line );
            }
        } catch( IOException e )
        {
            System.out.println( "Error reading the file: " + e.getMessage() );
        }

        System.out.println( "\nSummary Report:" );
        System.out.println( "File name: " + fileToScan.getName() );
        System.out.println( "Number of lines: " + lineCount );
        System.out.println( "Number of words: " + wordCount );
        System.out.println( "Number of characters: " + charCount );
    }
}
