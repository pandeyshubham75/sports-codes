import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class JarTrimmer {

    private static final int BUFFER_SIZE = 4096;

    static List<String> filesListInDir = new ArrayList<String>();

    public static void main (String args[]) {
        System.out.println("Welcome to JARTrimmer, a command line utility to remove applcore modules from jar");
        System.out.println("WARNING: PLEASE MAKE SURE THAT JAR NAME IS IN THE SAME FORMAT CS_<SOME_NAME>_<CSID>.jar");
        System.out.println("WARNING: It should be left with the same name as downloaded, don't use renamed JAR files");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter Full Path of Original JAR file:");
        String jarPath = scan.nextLine();
//        String jarPath = "C:\\Users\\shubhpan\\Downloads\\New Jar\\CS_BL_CSM_05142020_5913752038307843.jar";
        HashMap<String, Boolean> inclusionMap = new HashMap();
        // TODO Add capability to remove other modules altogether
        // TODO Add a graphical Interface which recursively expands everything,
        //  and allows to select unselect specific files and directories, just like Siemens case [would be an excellent design]
        String[] arrayOfModules = {"flex", "valueset", "lookups", "attachments", "messages", "datasecurity"};
        System.out.println("For each modules asked one by one, please type y and press enter if you want to REMOVE them\n" +
                "or simple press enter leaving blank, if you DO NOT want to remove them");
        System.out.println("Entering any other string other than y or Y [Case Insensitive] will NOT remove that module from the jar");
        for (String module: arrayOfModules) {
            System.out.println("Do you want to REMOVE "+ module +", type y or Y to remove " + module + ", any other key or no key to keep them:");
            String input = scan.nextLine();
            if ("y".equals(input) || "Y".equals(input)) {
                inclusionMap.put(module, false);
                System.out.println("Okay, will remove " + module + " from the JAR");
            } else {
                inclusionMap.put(module, true);
                System.out.println("Okay, will not remove the " + module);
            }
        }
        String jarName = jarPath.substring(jarPath.lastIndexOf('\\') + 1);
        String csId = jarName.substring(jarName.lastIndexOf('_') + 1, jarName.length() - 4);
        // TODO Handle the RENAMED JAR Scenario
        System.out.println("JAR Name is: "+jarName);
        System.out.println("CS ID is: "+csId);
        String rootPath = jarPath.substring(0, jarPath.length() - 4);
        String destinationPath = rootPath+"\\";
        System.out.println("UNZipping the outermost JAR....");
        unzip(
                jarPath,
                destinationPath
            );
        System.out.println("Outermost JAR UNZipped successfully, now UNZipping the ADF JAR....");
        String adfPath = destinationPath+"ADF\\"+"CS_ADF_"+csId;
        unzip(
                adfPath+".jar",
                adfPath
        );
        System.out.println("UNZipped the ADF JAR, now UNZipping the APPLCORE JAR....");
        String applcorePath = destinationPath+"ADF\\"+"CS_ADF_"+csId+"\\CS_ADF_APPLCORE_"+csId;
        unzip(
                applcorePath+".jar",
                applcorePath
        );
        System.out.println("UNZipped the APPLCORE JAR, removing the selected APPLCORE modules");
        // TODO Pre deleted modules
        // TODO Add a text which recommends valuesets and flex removal together
        removeSelectedDirectories(destinationPath+"ADF\\"+"CS_ADF_"+csId+"\\CS_ADF_APPLCORE_"+csId+"\\", inclusionMap);
        System.out.println("Deleting Old APPLCORE JAR");
        (new File(applcorePath+".jar")).delete();
        System.out.println("Selected Directories Removed, zipping them back, zipping APPLCORE Archive");
        addContentsOfDirectoryToJAR(applcorePath, null);
        System.out.println("Zipped Back the APPLCORE JAR and deleted residual APPLCORE Contents, deleting old ADF JAR and zipping up the new ADF JAR");
        (new File(adfPath+".jar")).delete();
        addContentsOfDirectoryToJAR(adfPath, null);
        System.out.println("Generated ADF JAR and deleted the ADF extracted contents, generating the ADF checksum");
        String adfCheckSum = null;
        try {
            adfCheckSum = generateChecksum(adfPath+".jar");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Generated checksum, adding it to the checksum properties file");
        try {
            updateValueInPropertyFile(destinationPath+"CSCheckSum.properties", "ADF", adfCheckSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Updated checksum properties file with the new ADF checksum, zipping the outermost jar now");
        addContentsOfDirectoryToJAR(rootPath, "TRIMMED");
        System.out.println("New JAR generated, path is: " + rootPath + "\\" + jarName.replace(csId, csId+"_TRIMMED"));
    }

    private static void updateValueInPropertyFile(String filePath, String key, String value) throws IOException {
        File propsFile = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(propsFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String preContent = "";
        String lineToBeUpdated = key+"="+value+System.lineSeparator();
        String postContent = "";
        String line = reader.readLine();
        boolean lineToBeUpdatedFound = false;
        while (line != null)
        {
            if(lineToBeUpdatedFound) {
                postContent+=(line+System.lineSeparator());
            } else if ((key+"=").equals(line.substring(0, key.length()+1))) {
                lineToBeUpdatedFound = true;
            } else {
                preContent+=(line+System.lineSeparator());
            }
            line = reader.readLine();
        }
        FileWriter writer = new FileWriter(propsFile);
        writer.write(preContent+lineToBeUpdated+postContent);
        reader.close();
        writer.close();
    }

    private static void addContentsOfDirectoryToJAR(String path, String nameToAppend) {
        List<File> foldersToBeZipped = new ArrayList();
        File dir = new File(path);
        for (String module : dir.list()) {
            foldersToBeZipped.add(new File(path+"\\"+module));
        }
        try {
            zip(foldersToBeZipped, path + (nameToAppend != null ? "_" + nameToAppend : "") + ".jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            deleteDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compresses a list of files to a destination zip file
     * @param listFiles A collection of files and directories
     * @param destZipFile The path of the destination zip file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException,
            IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
        for (File file : listFiles) {
            if (file.isDirectory()) {
                zipDirectory(file, file.getName(), zos);
            } else {
                zipFile(file, zos);
            }
        }
        zos.flush();
        zos.close();
    }

    /**
     * Adds a directory to the current zip output stream
     * @param folder the directory to be  added
     * @param parentFolder the path of parent directory
     * @param zos the current zip output stream
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void zipDirectory(File folder, String parentFolder,
                              ZipOutputStream zos) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
                bytesRead += read;
            }
            zos.closeEntry();
            bis.close();
        }
    }
    /**
     * Adds a file to the current zip output stream
     * @param file the file to be added
     * @param zos the current zip output stream
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void zipFile(File file, ZipOutputStream zos)
            throws FileNotFoundException, IOException {
        zos.putNextEntry(new ZipEntry(file.getName()));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        long bytesRead = 0;
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = bis.read(bytesIn)) != -1) {
            zos.write(bytesIn, 0, read);
            bytesRead += read;
        }
        zos.closeEntry();
        bis.close();
    }



    private static void removeSelectedDirectories(String applcoreContentsPath, HashMap<String, Boolean> inclusionMap) {
        for (String key: inclusionMap.keySet()) {
            if (!inclusionMap.get(key)) {
                System.out.println("Removing the module: "+key);
                File applcoreModuleDirectory = new File(applcoreContentsPath + key);
                try {
                    deleteDirectory(applcoreModuleDirectory);
                } catch (IOException e) {
                    System.out.println("IOException in deleting Module Directory");
                    e.printStackTrace();
                }
                System.out.println("Module "+key+" Removed");
            }
        }
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
//                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectory(File file)
            throws IOException{

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    deleteDirectory(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                }
            }

        }else{
            //if file, then delete it
            if (!file.delete()){
                System.out.println("UNABLE TO DELETE FILE " + file.getPath() + "BECAUSE: "+ file.exists());
            }
        }
    }

    public static String generateChecksum(String adfJARPath) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        String checkSum = null;
        System.out.println("form the message digest with MD5");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        InputStream fin = new FileInputStream(adfJARPath);
        try {
            // create the digest input stream
            fin = new DigestInputStream(fin, md);
            byte[] dataBytes = new byte[1024];
            int nread = 0;
            int totalbytesRead = 0;
            // We are reading the data using DigestInputStream which updates
            // MessageDigest with the data
            // passing through the stream no need to update it again.
            while ((nread = fin.read(dataBytes)) != -1) {
                totalbytesRead += nread;
            }
        } finally {
            try {
                fin.close();
            } catch (IOException e) {
                System.out.println("getCheckSumForFile" + e.getLocalizedMessage());
            }
        }
        // get the byte checksum
        byte[] digest = md.digest();
        // convert the byte to hex format method
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        // get the check sum as string
        checkSum = hexString.toString();
        System.out.println("Checksum generated is: " + checkSum);
        return checkSum;
    }
}
