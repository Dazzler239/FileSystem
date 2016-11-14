

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.spi.FileSystemProvider;

import javax.swing.filechooser.*;

public class FDemo {
	
	public static void main(String[] args) throws IOException
    {
        System.out.println("\n******File system roots details******\n");
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        
        String line;
        
        
        
        for (int i = 0; i < roots.length; i++)
        {
            System.out.println("Root: " + roots[i]);
        }
        
        
        System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("\n******File system Metadata Details******\n");
        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++)
        {
            System.out.println("\nDrive: " + f[i]);
            System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
            Process p = Runtime.getRuntime().exec ("fsutil fsinfo volumeinfo "+f[i]);
            BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            System.out.println("File system type :");
            while ((line = input.readLine()) != null)
              System.out.println(line);
            input.close();
            
            System.out.println("Is drive: " + fsv.isDrive(f[i]));
            System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
            //System.out.println("File system type : " + fsv.getSystemTypeDescription(f[i]));
            System.out.println("Readable: " + f[i].canRead());
            System.out.println("Writable: " + f[i].canWrite());
            System.out.println("Total space: " + f[i].getTotalSpace());
            System.out.println("Usable space: " + f[i].getUsableSpace());
            System.out.println("Free Space: " + f[i].getFreeSpace());
            
            System.out.println("--------------------------------------------------------");
        }
    }

}
